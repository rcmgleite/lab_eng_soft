package exemplo3.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import exemplo3.model.Accident;

public class AccidentDAO {

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("examplePU");
	EntityManager em = factory.createEntityManager();
	
	public List<Accident> getAccidents(){
		try {
			TypedQuery<Accident> q = em.createQuery(
					"from Accident", Accident.class);
			
			return q.getResultList();
			
		} catch (Exception e) {
			return null;
		}
	}
}
