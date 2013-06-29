package timetracker
import org.springframework.scheduling.annotation.Scheduled
import javax.persistence.EntityManager
import javax.persistence.Query

class DataRetrieverService {
	
	private void saveActiveWindow(ActiveWindowInfo awInfo){
		withJpa { String persistenceUnit, EntityManager em ->
			try{
				em.getTransaction().begin()
				em.persist(awInfo)
				em.getTransaction().commit()
			}catch(Exception ex){
				//Probably "Unique index or primary key violation"
				log.error ex.message
			}
		}
	}

    @Scheduled(cron="*/5 * * * * *")
	public void collectData(){
		saveActiveWindow(LinuxActiveWindow.getActiveWindowName())
	}
}
