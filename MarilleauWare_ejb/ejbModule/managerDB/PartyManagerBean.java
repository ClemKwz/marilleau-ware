package managerDB;

import java.util.ArrayList;
import java.util.List;

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
	
    public PartyManagerBean() {
        
    }

	@Override
	public void createPartie(String name, String description, int idUserCreator) {
		
		Party party = new Party();
		party.setName(name);
		party.setDescription(description);
		party.setIdUserCreate(idUserCreator);
		
		//addUserToParty(party.getIdPartie(),idCreator);
		
		this.em.persist(party);
		this.em.flush();
	}
	
	@Override
	public Party getPartyById(int idParty) {
		
		// Creation de la requete
		Query query = em.createQuery("from Party where idParty=:p");
		query.setParameter("p", idParty);
		
		Party party;
		
		// Recuperation du resultat
		try {
			party = (Party) query.getSingleResult();
		} catch (NoResultException e)  {
			return null;
		}
		
		return party;
	}


	@Override
	public int getIdByName(String name) {
		
		// Creation de la requete
		Query query = em.createQuery("from Party where name=:p");
		query.setParameter("p", name);
		
		Party party;
		
		// Recuperation du resultat
		try {
			party = (Party) query.getSingleResult();
		} catch (NoResultException e)  {
			return -1;
		}
		
		return party.getIdParty();
	}

	@Override
	public void addUserToParty(int idParty, int idUser) {

		Query query;
		// On recherche la partie
		// Creation de la requete
		query = em.createQuery("from Party where idParty=:p");
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
		user.setParty(party);
		//arty.setUser(user);
	}

	@Override
	public List<Party> getAllParty() {
		
		List<Party> lp = new ArrayList<Party>();
		List<Object[]> lo = null;
		Query query;
		// On recherche la partie
		// Creation de la requete
		query = em.createQuery("SELECT p FROM Party p");

		// Recuperation du resultat
		try {
			lp = query.getResultList();/*
			for (Object[] resultElement : lo) {
		        String name = (String)resultElement[0];
		        String description = (String)resultElement[0];
		        Party p = new Party();
		        p.setName(name);
		        p.setDescription(description);
		        lp.add(new Party());
		    }*/
		} catch (NoResultException e)  {
			System.out.println("erreur");
		}
		
		return lp;
	}
}
