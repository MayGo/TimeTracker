package timetracker

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager
import javax.persistence.Query

import org.springframework.scheduling.annotation.Scheduled

class TimeTrackerController {
	def model
	def view
	Date lastRefresh = new Date()

	void mvcGroupInit(Map args) {
		// this method is called after model and view are injected
		def dateAtMidnight = new Date()
		dateAtMidnight.clearTime()
		loadData(dateAtMidnight)
	}

	def loadData = { evt = null ->
		refreshData()
	}

	//@Scheduled(fixedDelay=1000L)
	void refreshData(loadFromDate){
		withJpa { String persistenceUnit, EntityManager em ->
			List<ActiveWindowInfo> tmpList = []

			Date tempDate=(loadFromDate)?:lastRefresh
			lastRefresh = new Date()
			Query q = em.createQuery("select p from JpaActiveWindowInfo as p where p.dateUpdated>:dateUpdated order by p.dateUpdated");
			q.setParameter("dateUpdated",tempDate);
			tmpList=q.resultList
			
			execInsideUIAsync {
				tmpList.each{

					def itemIndex=model.activeWindowInfoList.findIndexOf{old->old.id==it.id}
					if(itemIndex!=-1)
						model.activeWindowInfoList[itemIndex]=it
					else
						model.activeWindowInfoList.add it

				}
			}
		}
	}
	// void mvcGroupDestroy() {
	//    // this method is called when the group is destroyed
	// }
}
