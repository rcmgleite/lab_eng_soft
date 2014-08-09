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
			em.clear();
			return null;
		}
	}
	
	public void salvar(Accident accident) throws Exception {
		try {
			EntityManager em_in = factory.createEntityManager();
			EntityTransaction tx = em_in.getTransaction();
			tx.begin();
			if(accident.getId() == null)
				em_in.persist(accident);
			else
				em_in.merge(accident);
			em_in.flush();
			tx.commit();
			
			em_in.close();
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
			em.clear();
			throw e;
		}
	}
	
	public Accident findByPrimaryKey(Long pk) throws Exception {
		try {
			TypedQuery<Accident> q = em.createQuery(
					"from Accident where id = " + pk,
					Accident.class);
			Accident result = q.getSingleResult(); 
			em.clear();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			em.clear();
			throw e;
		}
	}

	public List<Accident> getAccidentsWhere(String where) throws Exception {
		try {
			TypedQuery<Accident> q = em.createQuery(
					"from Accident where " + where,
					Accident.class);
			List <Accident> result = q.getResultList();
			em.clear();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			em.clear();
			throw e;
		}
	}
}
