package exemplo3.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import exemplo3.model.Mission;

public class MissionDAO {

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("examplePU");
	EntityManager em = factory.createEntityManager();
	
	public List<Mission> getMissions(){
		try {
			TypedQuery<Mission> q = em.createQuery(
					"from Mission", Mission.class);
			
			return q.getResultList();
			
		} catch (Exception e) {
			return null;
		}
	}
	
}
