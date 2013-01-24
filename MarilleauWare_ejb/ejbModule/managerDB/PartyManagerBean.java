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
		
		Query query = em.createQuery("select u from Party t, User u where u.party.idParty=t.idParty and t.idParty=:p");
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

	@Override
	public void incrementCurrentGame(int idParty) {
		Party party = null;
		Query query;
		// On recherche la partie
		// Creation de la requete
		query = em.createQuery("from Party where idParty=:p");
		query.setParameter("p", idParty);

		// Recuperation du resultat
		try {
			party = (Party) query.getSingleResult();
		} catch (NoResultException e)  {
			System.out.println("erreur");
		}
		int sequence=-1;
		int idGameSuiv = -1;
		int sequencesuiv=-1;
		//on recupere la sequence courante
		for(int i = 0 ; i < party.getGames().size();i++ ){
			
			if(party.getGames().get(i).getIdGame() == party.getIdCurrentGame()){
				sequence = party.getGames().get(i).getSequence();
				System.out.println("PartyManager : Sequence courante:"+sequence);
				System.out.println("PartyManager : id game courant:"+party.getGames().get(i).getIdGame());
			}
		}
		if(  sequence == party.getGames().size() -1 ){
			
			System.out.println("PartyManager :finfinfin");
			party.setIdCurrentGame(-1);
			party.setEndParty(1);
		}else{
			//on recupere l'id de la game suivante
			for(int i = 0 ; i < party.getGames().size();i++ ){
				
				if(party.getGames().get(i).getSequence() == sequence +1){
					
					sequencesuiv = party.getGames().get(i).getSequence();
					idGameSuiv = party.getGames().get(i).getIdGame();
					System.out.println("PartyManager : Sequence du game suivant:"+sequencesuiv);
					System.out.println("PartyManager : id game suivant:"+idGameSuiv);
					
				}
			}
			party.setIdCurrentGame(idGameSuiv);
		}
		this.em.persist(party);
		this.em.flush();
		
	}

	@Override
	public void addScore(int idParty, int idPlayer, int score) {
		
		// Creation de la requete
		Query query = em.createQuery("from User where idUser=:p");
		query.setParameter("p", idPlayer);

		System.out.println("idParty => " + idParty + "  idUser => " + idPlayer + "  score => " + score);

		User user = null;
		// Recuperation du resultat
		try {
			user = (User) query.getSingleResult();
			int scorePrec = user.getScore();
			user.setScore(scorePrec+(int)score);
			this.em.persist(user);
			this.em.flush();
		} catch (NoResultException e)  {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isStarted(int idParty) {
		// Creation de la requete
		Query query = em.createQuery("from Party where idParty=:p");
		query.setParameter("p", idParty);

		//System.out.println("idParty => " + idParty + "  idUser => " + idPlayer + "  score => " + score);

		Party party = null;
		// Recuperation du resultat
		try {
			party = (Party) query.getSingleResult();
			int started = party.getStartParty();
			if(started ==0){
				return false;
			}else{
				return true;
			}
		} catch (NoResultException e)  {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void setStarted(int idParty) {
		Query query = em.createQuery("from Party where idParty=:p");
		query.setParameter("p", idParty);

		//System.out.println("idParty => " + idParty + "  idUser => " + idPlayer + "  score => " + score);

		Party party = null;
		
		// Recuperation du resultat
		try {
			party = (Party) query.getSingleResult();
			party.setStartParty(1);
			this.em.persist(party);
			this.em.flush();
		}catch(NoResultException e)  {
			e.printStackTrace();
		}
	}

	@Override
	public void deletePartyAndCo(int idParty) {
		
		Query query = em.createQuery("from Party where idParty =:p");
		query.setParameter("p", idParty);

		//System.out.println("idParty => " + idParty + "  idUser => " + idPlayer + "  score => " + score);

		Party party = null;
		
		// Recuperation du resultat
		try {
			party = (Party) query.getSingleResult();
		}catch(NoResultException e)  {
			e.printStackTrace();
		}
		//suppression des scores (entre user et games)
		List <Game> Allgame = party.getGames();
			for(int i=0;i<Allgame.size();i++){
				query = em.createQuery("DELETE FROM TjGamesUser t WHERE t.id.idGame =:id");
				query.setParameter("id", Allgame.get(i).getIdGame());
				query.executeUpdate();
				this.em.flush();
			}
				//suppression des games 
				query = em.createQuery("DELETE FROM Game g WHERE g.party =:id");
				query.setParameter("id", party);
				query.executeUpdate();
				this.em.flush();
				
				
				//suppression du chat
				query = em.createQuery("DELETE FROM ChatParty p WHERE p.idParty =:id ");
				query.setParameter("id", idParty);
				query.executeUpdate();
				this.em.flush();
				
				//Update de l'utilisateur
				List <User> allUser = party.getUsers();
				for(int i=0;i<allUser.size();i++){
					allUser.get(i).setScore(0);
					allUser.get(i).setParty(null);
				}
				this.em.flush();
				//suppression de la partie
				query = em.createQuery("DELETE FROM Party p WHERE p.idParty =:id ");
				query.setParameter("id", idParty);
				query.executeUpdate();
				this.em.flush();
				
	}
}
