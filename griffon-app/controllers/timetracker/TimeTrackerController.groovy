package timetracker

import java.util.List;

import javax.persistence.EntityManager
import javax.persistence.Query


class TimeTrackerController {
	def model
	def view

	void mvcGroupInit(Map args) {
		// this method is called after model and view are injected
		loadData()
	}
	def loadData = { evt = null ->
		withJpa { String persistenceUnit, EntityManager em ->
			List<ActiveWindowInfo> tmpList = []
			tmpList.addAll em.createQuery('select p from JpaActiveWindowInfo p order by p.id').resultList
			println tmpList
			
			execInsideUIAsync {
				model.activeWindowInfoList.clear()
				model.activeWindowInfoList.addAll tmpList
			}
		}
	}
	// void mvcGroupDestroy() {
	//    // this method is called when the group is destroyed
	// }
}
