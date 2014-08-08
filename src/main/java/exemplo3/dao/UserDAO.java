package exemplo3.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import exemplo3.model.User;

public class UserDAO {

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("examplePU");
	EntityManager em = factory.createEntityManager();
	
	public List<User> getUsers() throws Exception {
		try {
			TypedQuery<User> q = em.createQuery(
					"from User", User.class);
			q.setHint("org.hibernate.cacheable", Boolean.FALSE);
			
			List<User> result = q.getResultList();
			em.clear();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			em.clear();
			throw e;
		}
	}
	
	public User getUserByUnamePassword(String username, String password){
		try {
			TypedQuery<User> q = em.createQuery(
					"from User where username = '" + username + "' and password = '" + password + "'", User.class);
			
			User result = q.getSingleResult();
			em.clear();
			return result;
			
		} catch (Exception e) {
			em.clear();
			return null;
		}
	}
	
	public List<User> getUsersWhere(String where){
		try {
			TypedQuery<User> q = em.createQuery(
					"from User where " + where, User.class);
			
			List<User> result = q.getResultList();
			em.clear();
			return result;
			
		} catch (Exception e) {
			em.clear();
			return null;
		}
	}

	public User findByPrimaryKey(Long pk) throws Exception {
		try {
			TypedQuery<User> q = em.createQuery(
					"from User where id = " + pk,
					User.class);
			User result = q.getSingleResult(); 
			em.clear();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			em.clear();
			throw e;
		}
	}
	
	public User getSingleUserWhere(String where) throws Exception {
		try {
			TypedQuery<User> q = em.createQuery(
					"from User where " + where,
					User.class);
			User result = q.getSingleResult(); 
			em.clear();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			em.clear();
			throw e;
		}
	}

	public void remover(Long pk) throws Exception {
		try {
			EntityManager em_in = factory.createEntityManager();
			EntityTransaction tx = em_in.getTransaction();
			tx.begin();
			
			TypedQuery<User> q = em_in.createQuery(
					"from User where id = " + pk,
					User.class);
			em_in.remove(q.getSingleResult());
			
			em_in.flush();
			tx.commit();
			
			em_in.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void salvar(User usuario) throws Exception {
		try {
			EntityManager em_in = factory.createEntityManager();
			EntityTransaction tx = em_in.getTransaction();
			tx.begin();
			if(usuario.getId() == null)
				em_in.persist(usuario);
			else
				em_in.merge(usuario);
			em_in.flush();
			tx.commit();
			
			em_in.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
