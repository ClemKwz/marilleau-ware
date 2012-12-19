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
public class PartyManagerBean implements PartyManagerLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public PartyManagerBean() {
        
    }

	@Override
	/*
	 * Permet de créer une nouvelle partie avec son créateur
	 * (non-Javadoc)
	 * @see controlerDB.PartiesManagerLocal#createPartie(java.lang.String, java.lang.String, int)
	 */
	public void createPartie(String name, String description, int idCreator) {
		Party party = new Party();
		party.setName(name);
		party.setDescription(description);
		addUserToParty(party.getIdPartie(),idCreator);	
		this.em.persist(party);
		this.em.flush();
		
	}

	@Override
	public int getIdByName(String name) {
	// Creation de la requete
			Query query = em.createQuery("FROM Party where name=:p");
			query.setParameter("p", name);
			
			Party party;
			
			// Recuperation du resultat
			try {
				party = (Party) query.getSingleResult();
			} catch (NoResultException e)  {
				return -1;
			}
			
			return party.getIdPartie();
	}

	@Override
	public void addUserToParty(int idParty,int idUser) {

		Query query;
		//On recherche la partie
		// Creation de la requete
		query = em.createQuery("FROM Party where idParty=:p");
		query.setParameter("p", idParty);

		Party party= null;

		// Recuperation du resultat
		try {
			party = (Party) query.getSingleResult();
		} catch (NoResultException e)  {
			System.out.println("erreur");
		}


		User user = null;
		// Creation de la requete
		query = em.createQuery("from User u where idUser=:id");
		query.setParameter("id", idUser);

		// Recuperation du resultat
		try {
			user = (User) query.getSingleResult();
		} catch (NoResultException e)  {
			System.out.println("Erreur aucune valeur");
		}
		party.setUser(user);		

	}
    
	
}
