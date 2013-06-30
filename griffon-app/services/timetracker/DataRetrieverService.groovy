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
				Query q = em.createQuery("select p from JpaActiveWindowInfo as p where p.name=:name and p.title=:title");
				q.setParameter("name", awInfo.name);
				q.setParameter("title", awInfo.title);
				ActiveWindowInfo awInfoTemp
				try{
					awInfoTemp=q.singleResult//TODO: overwrite awInfo
					awInfoTemp.updatedCount++
					em.merge(awInfoTemp)
				}catch(NoResultException ex){
					em.persist(awInfo)
				}
				println awInfoTemp
				
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
