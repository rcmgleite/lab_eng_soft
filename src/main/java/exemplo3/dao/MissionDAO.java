package exemplo3.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
	
	public void remover(Long pk) throws Exception {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			TypedQuery<Mission> q = em.createQuery(
					"from Mission where id = " + pk,
					Mission.class);
			em.remove(q.getSingleResult());
			
			em.flush();
			tx.commit();
			
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void salvar(Mission mission) throws Exception {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			if(mission.getId() == null)
				em.persist(mission);
			else
				em.merge(mission);
			em.flush();
			tx.commit();
			
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
