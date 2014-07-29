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
			
			List<Mission> result = q.getResultList();
			em.clear();
			return result;
			
		} catch (Exception e) {
			em.clear();
			return null;
		}
	}
	
	public void remover(Long pk) throws Exception {
		try {
			EntityManager em_in = factory.createEntityManager();
			EntityTransaction tx = em_in.getTransaction();
			tx.begin();
			
			TypedQuery<Mission> q = em_in.createQuery(
					"from Mission where id = " + pk,
					Mission.class);
			em_in.remove(q.getSingleResult());
			
			em_in.flush();
			tx.commit();
			
			em_in.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void salvar(Mission mission) throws Exception {
		try {
			EntityManager em_in = factory.createEntityManager();
			EntityTransaction tx = em_in.getTransaction();
			tx.begin();
			if(mission.getId() == null)
				em_in.persist(mission);
			else
				em_in.merge(mission);
			em_in.flush();
			tx.commit();
			
			em_in.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Mission findMissionByPrimaryKey(Long pk) throws Exception {
		try {
			TypedQuery<Mission> q = em.createQuery(
					"from Mission where id = " + pk,
					Mission.class);
			Mission result = q.getSingleResult(); 
			em.clear();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			em.clear();
			throw e;
		}
	}
	
}
