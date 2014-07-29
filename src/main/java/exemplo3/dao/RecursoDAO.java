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
	
	public List<Resource> getResources(){
		try {
			TypedQuery<Resource> q = em.createQuery(
					"from Resource", Resource.class);
			
			List<Resource> result = q.getResultList();
			em.clear();
			return result;
			
		} catch (Exception e) {
			em.clear();;
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
			em.clear();
			throw e;
		}
	}

	public Resource findResourceByPrimaryKey(Long pk) throws Exception {
		try {
			TypedQuery<Resource> q = em.createQuery(
					"from Resource where id = " + pk,
					Resource.class);
			Resource result = q.getSingleResult(); 
			em.clear();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			em.clear();
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
			em.clear();
			return null;
		}
	}

	public List<Resource> getResourcesWhere(String where){
		try {
			TypedQuery<Resource> q = em.createQuery(
					"from Resource where " + where, Resource.class);
			
			List<Resource> result = q.getResultList();
			em.clear();
			return result;
			
		} catch (Exception e) {
			em.clear();
			return null;
		}
	}
	
	public void salvarTipoRecurso(ResourceType rt) throws Exception {
		try {
			EntityManager em_in = factory.createEntityManager();
			EntityTransaction tx = em_in.getTransaction();
			tx.begin();
			if(rt.getId() == null)
				em_in.persist(rt);
			else
				em_in.merge(rt);
			em_in.flush();
			tx.commit();
			
			em_in.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void removerTipoRecurso(Long pk) throws Exception {
		try {
			/**
			 *	TODO - verificar pq é necessaário criar o entity_manager dentro da função. 
			 **/
			EntityManager em_in = factory.createEntityManager();
			TypedQuery<ResourceType> q = em_in.createQuery(
					"from ResourceType where id = " + pk,
					ResourceType.class);
			ResourceType to_delete = q.getSingleResult(); 
			EntityTransaction tx = em_in.getTransaction();
			if(!tx.isActive())
				tx.begin();
			em_in.remove(to_delete);
			
			em_in.flush();
			tx.commit();
			em_in.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void salvarRecurso(Resource resource) throws Exception {
		try {
			EntityManager em_in = factory.createEntityManager();
			EntityTransaction tx = em_in.getTransaction();
			tx.begin();
			if(resource.getId() == null)
				em_in.persist(resource);
			else
				em_in.merge(resource);
			em_in.flush();
			tx.commit();
			em_in.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void removerRecurso(Long pk) throws Exception {
		try {
			/**
			 *	TODO - verificar pq é necessaário criar o entity_manager dentro da função. 
			 **/
			EntityManager em_in = factory.createEntityManager();
			TypedQuery<Resource> q = em_in.createQuery(
					"from Resource where id = " + pk,
					Resource.class);
			Resource to_delete = q.getSingleResult(); 
			EntityTransaction tx = em_in.getTransaction();
			if(!tx.isActive())
				tx.begin();
			em_in.remove(to_delete);
			
			em_in.flush();
			tx.commit();
			em_in.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	public ResourceType getResourceTypeWhere(String where){
		try {
			TypedQuery<ResourceType> q = em.createQuery(
					"from ResourceType where " + where, ResourceType.class);
			
			ResourceType result = q.getSingleResult();
			em.clear();
			return result;
			
		} catch (Exception e) {
			em.clear();
			return null;
		}
	}
}
