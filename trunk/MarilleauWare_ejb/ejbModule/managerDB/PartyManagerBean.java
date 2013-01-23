package managerDB;

import java.util.ArrayList;
import java.util.HashMap;
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
	public int getIdPartyByName(String name) {
		
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Party> getAllParty() {
		
		List<Party> lp = new ArrayList<Party>();
		
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

	@Override
	public void setPartyCurrentGame(int idParty, int idGame) {
		
		Party party;
		
		Query query;
		// On recherche la partie
		// Creation de la requete
		query = em.createQuery("from Party where idParty=:p");
		query.setParameter("p", idParty);

		// Recuperation du resultat
		try {
			party = (Party) query.getSingleResult();
			party.setIdCurrentGame(idGame);
		} catch (NoResultException e)  {
			System.out.println("erreur");
		}
	}

	@Override
	public int getIdPartyByIdUser(int idUser) {
		User user;
		
		Query query;
		// On recherche la partie
		// Creation de la requete
		query = em.createQuery("from User where idUser=:p");
		query.setParameter("p", idUser);

		// Recuperation du resultat
		try {
			user = (User) query.getSingleResult();
			return user.getParty().getIdParty();
		} catch (NoResultException e)  {
			System.out.println("erreur");
			return -1;
		}
	}

	@Override
	public int getIdGameByIdParty(int idParty) {
		Party party;
		
		Query query;
		// On recherche la partie
		// Creation de la requete
		query = em.createQuery("from Party where idParty=:p");
		query.setParameter("p", idParty);

		// Recuperation du resultat
		try {
			party = (Party) query.getSingleResult();
			return party.getIdCurrentGame();
		} catch (NoResultException e)  {
			System.out.println("erreur");
			return -1;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, Integer> getAllScore(int idParty) {
		
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		
		Query query = em.createQuery("select u from Party p, User u where u.idUser=p.idUser and t.idParty=:p");
		query.setParameter("p", idParty);
		
		List<User> lu = null;
		try {
			lu = (List<User>)query.getResultList();
		} catch (NoResultException e)  {
			e.printStackTrace();
		}
		if(lu != null) {
			for(int i=0; i<lu.size(); i++){
				String pseudo = lu.get(i).getPseudo();
				int score = lu.get(i).getScore();
				map.put(pseudo, score);
			}
		}
		return map;
	}
}