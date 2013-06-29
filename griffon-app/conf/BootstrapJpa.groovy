import javax.persistence.EntityManager
import timetracker.JpaActiveWindowInfo
class BootstrapJpa {
    def init = { String persistenceUnit, EntityManager em ->
		em.getTransaction().begin()
		[[name: 'hamster-time-tracker',  title: 'time-tracker']].each { data ->
			em.persist(new JpaActiveWindowInfo(data))
		}
		em.getTransaction().commit()
    }

    def destroy = { String persistenceUnit, EntityManager em ->
    }
} 
