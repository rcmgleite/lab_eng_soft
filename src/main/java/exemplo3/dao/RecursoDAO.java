package exemplo3.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import exemplo3.model.Resource;

public class RecursoDAO {

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("examplePU");
	EntityManager em = factory.createEntityManager();
	
	public List<Resource> getResources(){
		try {
			TypedQuery<Resource> q = em.createQuery(
					"from Resource", Resource.class);
			
			return q.getResultList();
			
		} catch (Exception e) {
			return null;
		}
	}

}
