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
public class GameManagerBean implements GameManagerLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public GameManagerBean() {
        
    }
    
    @Override
    public void addUserGame(int idGame, int idUser){
    	TjGamesUserPK key = new TjGamesUserPK();
    	key.setIdGame(idGame);
    	key.setIdUser(idUser);
    	TjGamesUser table = new TjGamesUser();
    	table.setId(key);
    	table.setScore(0);
		this.em.persist(table);
		this.em.flush();
    }

	@Override
	public void createGame(GamesDesc gamesDesc, Party party, int sequence) {
		
		Game game = new Game();
		game.setParty(party);
		game.setGamesDesc(gamesDesc);
		game.setStartGame(0);
		game.setEndGame(0);
		game.setSequence(sequence);
		
		this.em.persist(game);
		this.em.flush();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GamesDesc> getAllGamesDesc(){
		
		List<GamesDesc> lg = new ArrayList<GamesDesc>();
		Query query;
		// On recherche la partie
		// Creation de la requete
		query = em.createQuery("SELECT g FROM GamesDesc g");

		// Recuperation du resultat
		try {
			lg = query.getResultList();/*
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
		
		return lg;
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

	@Override
	public int getIdGameByPartyBySeq(int idParty, int sequence) {
		
		// Creation de la requete
		Query query = em.createQuery("from Game where idParty=:p and sequence=:s");
		query.setParameter("p", idParty);
		query.setParameter("s", sequence);
		
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