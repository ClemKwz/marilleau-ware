package controllerEJB;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import managerDB.GameManagerLocal;
import managerDB.PartyManagerLocal;
import model.Game;
import model.GamesDesc;
import model.Party;

/**
 * Session Bean implementation class GameController2
 */
@Stateless
public class GameController2 implements GameController2Local {

	final int NBGAMEMAXPERPARTY = 3; 
	
	@EJB
	GameManagerLocal gm;
	@EJB
	PartyManagerLocal pm;
	
    public GameController2() {
    	
    }

	@Override
	public void createGameParty(Party party) {

		System.out.println("Creation des games de la partie ("+party.getIdParty()+")");
		
		// En fonction des games dans la table Game_desc
		// On cree autant de jeu
		List<GamesDesc> lgd = gm.getAllGamesDesc();
		
		for (int i=0; i<NBGAMEMAXPERPARTY; i++) {
			Random random = new Random();
			int index = random.nextInt(lgd.size());
			gm.createGame(lgd.get(index), party, i);
		}
	}
	
	@Override
	public void addUserGame(int idParty, int idUser) {

		Party party = pm.getPartyById(idParty);
		
		List<Game> listGame = party.getGames();
		for(int i=0;i<listGame.size();i++){
			gm.addUserGame(listGame.get(i).getIdGame(), idUser);
		}
	}

	@Override
	public void addScore(int idGame, int idUser, int score) {
		
		gm.addScore(idGame, idUser, score);
	}
	
	@Override
	public boolean containsNegativeScore(int idGame) {
		
		// on regarde s'il reste des scores a -1 dans TjUserGame
		return gm.containsNegativeScore(idGame);
	}
	
	@Override
	public TreeMap<String, Integer> getAllScore(int idGame) {
	
		HashMap<String,Integer> map = gm.getAllScore(idGame);
		TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(new ValueComparator(map));
        sorted_map.putAll(map);
        
		return sorted_map;
	}
		
	class ValueComparator implements Comparator<String> {

	    Map<String, Integer> map;
	    public ValueComparator(Map<String, Integer> map) {
	        this.map = map;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(String a, String b) {
	    	
	    	int resultat = 0;
	    	
	        if (map.get(a) < map.get(b)) {
	        	resultat = 1;
	        } 
	        if (map.get(a) > map.get(b)) {
	        	resultat = -1;
	        }
	        
	        return resultat;
	    }
	}

	@Override
	public int getNextgame(int idGame) {
		
		return gm.getNextgame(idGame);
	}

	@Override
	public String getNameGameDesc(int idParty) {
		
		return gm.getNameGameDesc(idParty);
	}

	@Override
	public void addDataGame(int idGame, String data) {
		
		gm.addDataGame(idGame,data);
	}

	@Override
	public String getDataFromGame(int idGame) {
		
		String data = gm.getDataFromGame(idGame);
		
		return data;
	}
}