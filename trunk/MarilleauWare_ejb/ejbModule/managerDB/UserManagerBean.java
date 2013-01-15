package managerDB;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Party;
import model.User;

/**
 * Session Bean implementation class UserManagerBean
 */
@Stateless
public class UserManagerBean implements UserManagerLocal {

	@PersistenceContext
	private EntityManager em;
	
	public UserManagerBean() {
        
    }
    
	public void createUser(String pseudo, String password, int isAdmin) {
		User user = new User();
		user.setPseudo(pseudo);
		user.setPassword(password);
		user.setIsAdmin(isAdmin);
		this.em.persist(user);
		this.em.flush();
	}

	@Override
	public boolean findByPseudo(String pseudo) {
		
		// Creation de la requete
		Query query = em.createQuery("from User u where pseudo=:p");
		query.setParameter("p", pseudo);
		
		// Recuperation du resultat
		try {
			query.getSingleResult();
		} catch (NoResultException e)  {
			return false;
		}
		
		return true;
	}

	@Override
	public int getIdByPseudo(String pseudo) {
		
		// Creation de la requete
		Query query = em.createQuery("from User u where pseudo=:p");
		query.setParameter("p", pseudo);
		
		User user;
		
		// Recuperation du resultat
		try {
			user = (User) query.getSingleResult();
		} catch (NoResultException e)  {
			return -1;
		}
		
		return user.getIdUser();
	}
	
	@Override
	public User getUserById(int idUser) {
		
		// Creation de la requete
		Query query = em.createQuery("from User u where idUser=:i");
		query.setParameter("i", idUser);
		
		User user;
		
		// Recuperation du resultat
		try {
			user = (User) query.getSingleResult();
		} catch (NoResultException e)  {
			return null;
		}
		
		return user;
	}
	
	//@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUserPerParty(int idParty) {
		
		List<User> lu = new ArrayList<User>();
		
		Query query;
		// On recherche la partie
		// Creation de la requete
		query = em.createQuery("SELECT u from User u where idParty =:id");
		query.setParameter("id", idParty);

		// Recuperation du resultat
		try {
			lu = query.getResultList();
			
		} catch (NoResultException e)  {
			System.out.println("erreur");
		}
		
		return lu;
	}
}
