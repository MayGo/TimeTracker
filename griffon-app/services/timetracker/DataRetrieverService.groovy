package timetracker
import org.springframework.scheduling.annotation.Scheduled
import javax.persistence.EntityManager
import javax.persistence.Query
import javax.persistence.NoResultException

class DataRetrieverService {

	private void saveActiveWindow(ActiveWindowInfo awInfo){
		withJpa { String persistenceUnit, EntityManager em ->
			try{
				em.getTransaction().begin()
				Query q = em.createQuery("select p from JpaActiveWindowInfo as p order by p.id desc");
				q.setMaxResults(1)

				List<ActiveWindowInfo> tmpList=q.resultList//TODO: overwrite awInfo
				ActiveWindowInfo awInfoTemp
				if(tmpList){
					awInfoTemp=tmpList.first()
					if(awInfoTemp.name == awInfo.name && awInfoTemp.title == awInfo.title)
						awInfoTemp.updatedCount++
					else
						awInfoTemp=null
				}

				if(!awInfoTemp)
					em.persist(awInfo)
				else
					em.merge(awInfoTemp)

				em.getTransaction().commit()
			}catch(Exception ex){
				log.error ex.message
			}
		}
	}

	@Scheduled(cron="*/5 * * * * *")
	public void collectData(){
		saveActiveWindow(LinuxActiveWindow.getActiveWindowName())
	}
}
