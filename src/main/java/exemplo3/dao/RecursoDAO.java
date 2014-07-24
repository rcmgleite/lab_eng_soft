package exemplo3.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import exemplo3.model.Resource;
import exemplo3.model.ResourceType;

public class RecursoDAO {

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("examplePU");
	EntityManager em = factory.createEntityManager();
	
	public List<Resource> getAllocatedResources(){
		try {
			TypedQuery<Resource> q = em.createQuery(
					"from Resource", Resource.class);
			
			List<Resource> result = q.getResultList();
			em.clear();
			return result;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public ResourceType findRTByPrimaryKey(Long pk) throws Exception {
		try {
			TypedQuery<ResourceType> q = em.createQuery(
					"from ResourceType where id = " + pk,
					ResourceType.class);
			ResourceType result = q.getSingleResult(); 
			em.clear();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<ResourceType> getTypeResources(){
		try {
			TypedQuery<ResourceType> q = em.createQuery(
					"from ResourceType", ResourceType.class);
			
			List<ResourceType> result = q.getResultList();
			em.clear();
			return result;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public void salvarTipoRecurso(ResourceType rt) throws Exception {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			if(rt.getId() == null)
				em.persist(rt);
			else
				em.merge(rt);
			em.flush();
			tx.commit();
			
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void removerTipoRecurso(Long pk) throws Exception {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			TypedQuery<ResourceType> q = em.createQuery(
					"from ResourceType where id = " + pk,
					ResourceType.class);
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
