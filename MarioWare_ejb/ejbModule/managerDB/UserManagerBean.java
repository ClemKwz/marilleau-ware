package managerDB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.User;

/**
 * Session Bean implementation class UserManagerBean
 */
@Stateless
public class UserManagerBean implements UserManagerLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
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
}
