package exemplo3.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import exemplo3.model.IEntity;

/* *
 * 		TODO - implementar um DAO genérico para evitar as repetições das classes de 
 * 			acesso ao banco de dados
 * 
 * */
public class GenericDAO {
	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("examplePU");
	EntityManager em = factory.createEntityManager();
	
	public <T> List<T> getList(Class<T> clazz){
		try {
			TypedQuery<T> q = em.createQuery(
					"from " + clazz.getName(), clazz);
			
			List<T> result = q.getResultList();
			em.clear();
			return result;
			
		} catch (Exception e) {
			em.clear();
			return null;
		}
	}
	
	public <T> void salvar(IEntity obj, Class<T> clazz) throws Exception {
		try {
			EntityManager em_in = factory.createEntityManager();
			EntityTransaction tx = em_in.getTransaction();
			tx.begin();
			
			if(obj.getId() == null)
				em_in.persist(clazz.cast(obj));
			else
				em_in.merge(clazz.cast(obj));
			em_in.flush();
			tx.commit();
			
			em_in.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public <T> void remover(Long pk, Class<T> clazz) throws Exception {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			TypedQuery<T> q = em.createQuery(
					"from " + clazz.getName() + " where id = " + pk,
					clazz);
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
	
	public <T> T findByPrimaryKey(Long pk, Class<T> clazz) throws Exception {
		try {
			TypedQuery<T> q = em.createQuery(
					"from " + clazz.getName() + " where id = " + pk,
					clazz);
			T result = q.getSingleResult(); 
			em.clear();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			em.clear();
			throw e;
		}
	}
	
	public <T> List<T> getListEntityWhere(String where, Class<T> clazz) throws Exception {
		try {
			TypedQuery<T> q = em.createQuery(
					"from " + clazz.getName() + " where " + where,
					clazz);
			List <T> result = q.getResultList();
			em.clear();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			em.clear();
			throw e;
		}
	}
	
	public <T> T getSingleEntityWhere(String where, Class<T> clazz) throws Exception {
		try {
			TypedQuery<T> q = em.createQuery(
					"from " + clazz.getName() + " where " + where,
					clazz);
			T result = q.getSingleResult(); 
			em.clear();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			em.clear();
			throw e;
		}
	}
}
