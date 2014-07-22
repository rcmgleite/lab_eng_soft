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
			throw e;
		}
	}

	public void remover(Long pk) throws Exception {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			TypedQuery<User> q = em.createQuery(
					"from User where id = " + pk,
					User.class);
			em.remove(q.getSingleResult());
			
			em.flush();
			tx.commit();
			
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void salvar(User usuario) throws Exception {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			if(usuario.getId() == null)
				em.persist(usuario);
			else
				em.merge(usuario);
			em.flush();
			tx.commit();
			
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
