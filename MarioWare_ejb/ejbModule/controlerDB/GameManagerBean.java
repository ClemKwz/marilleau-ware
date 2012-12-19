package controlerDB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.*;

/**
 * Session Bean implementation class UserManagerBean
 */
@Stateless
public class GameManagerBean implements GameManagerLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public GameManagerBean() {
        
    }

	@Override
	public void createGame(String name, String description) {
		Game game = new Game();
		game.setName(name);
		game.setDescription(description);
		this.em.persist(game);
		this.em.flush();
		
	}

	@Override
	public int getIdByName(String name) {
		// Creation de la requete
				Query query = em.createQuery("from Game where name=:p");
				query.setParameter("p", name);
				
				Game game;
				
				// Recuperation du resultat
				try {
					game = (Game) query.getSingleResult();
				} catch (NoResultException e)  {
					return -1;
				}
				
				return game.getIdGame();
			}
	}