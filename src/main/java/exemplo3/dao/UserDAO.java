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
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public User getUserByUnamePassword(String username, String password){
		try {
			TypedQuery<User> q = em.createQuery(
					"from User where username = '" + username + "' and password = '" + password + "'", User.class);
			
			return q.getSingleResult();
			
		} catch (Exception e) {
			return null;
		}
	}

	public User consultarPorPK(Long pk) throws Exception {
		try {
			TypedQuery<User> q = em.createQuery(
					"select usu from Usuario usu where usu.id = " + pk,
					User.class);
			return q.getSingleResult();
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
					"select usu from Usuario usu where usu.id = " + pk,
					User.class);
			em.remove(q.getSingleResult());
			
			em.flush();
			tx.commit();
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
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
