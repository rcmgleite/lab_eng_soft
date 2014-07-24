package exemplo3.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
			
			List<Accident> result = q.getResultList();
			em.clear();
			return result;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public void salvar(Accident accident) throws Exception {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			if(accident.getId() == null)
				em.persist(accident);
			else
				em.merge(accident);
			em.flush();
			tx.commit();
			
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void remover(Long pk) throws Exception {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			TypedQuery<Accident> q = em.createQuery(
					"from Accident where id = " + pk,
					Accident.class);
			em.remove(q.getSingleResult());
			
			em.flush();
			tx.commit();
			
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
