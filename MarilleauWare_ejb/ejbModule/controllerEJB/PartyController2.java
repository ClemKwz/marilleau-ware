package controllerEJB;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import managerDB.ChatPartyManagerLocal;
import managerDB.GameManagerLocal;
import managerDB.PartyManagerLocal;
import managerDB.UserManagerLocal;
import model.ChatParty;
import model.Party;
import model.User;

/**
 * Session Bean implementation class PartyController2
 */
@Stateless
public class PartyController2 implements PartyController2Local {

	@EJB
	PartyManagerLocal pm;
	@EJB
	GameManagerLocal gm;
	@EJB
	UserManagerLocal um;
	@EJB
	ChatPartyManagerLocal cpm;
    /**
     * Default constructor. 
     */
    public PartyController2() {
       
    }

	@Override
	public List<Party> getListOfParty() {
		
		return pm.getAllParty();
	}

	@Override
	public Party getPartyById(int idParty) {
		
		return pm.getPartyById(idParty);
	}

	@Override
	public void addUser(int idParty, int idUser) {
		
		pm.addUserToParty(idParty, idUser);
		//on vérifie qu'il y a le nombre adéquat de joueur
		if(pm.getAllScore(idParty).size()>1){
			pm.setStarted(idParty);
		}
		
	}

	@Override
	public void initCurrentGame(int idParty) {
		
		int idGame = gm.getIdGameByPartyBySeq(idParty,0);
		pm.setPartyCurrentGame(idParty,idGame);
	}

	@Override
	public int getIdPartyByIdUser(int idUser) {
		return pm.getIdPartyByIdUser(idUser);
	}

	@Override
	public int getIdGameByIdParty(int idParty) {
		return pm.getIdGameByIdParty(idParty);
	}
	
	public List<User> getAllUserPerParty(int idParty) {
		return um.getAllUserPerParty(idParty);
	}

	@Override
	public void addMessage(int idParty, int idUser, String message) {
		cpm.addMessage(idParty, idUser, message);
	}

	@Override
	public List<ChatParty> getAllMessages(int idParty) {
		return cpm.getAllMessages(idParty);
	}

	@Override
	public List<ChatParty> getAllMessagesLimit(int idParty, int limit) {
		return cpm.getAllMessagesLimit(idParty, limit);
	}

	@Override
	public List<ChatParty> getMessagesAfter(int Party, int idMessage) {
		return cpm.getMessagesAfter(Party, idMessage);
	}
	
	@Override
	public TreeMap<String, Integer> getAllScore(int idParty){
	
		HashMap<String,Integer> map = pm.getAllScore(idParty);
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
	        	resultat = -1;
	        } 
	        if (map.get(a) > map.get(b)) {
	        	resultat = 1;
	        }
	        
	        return resultat;
	    }
	}

	@Override
	public void incrementCurrentGame(int idParty) {
		pm.incrementCurrentGame(idParty);
		
	}

	@Override
	public void addScore(int idParty, int idPlayer, int score) {
		pm.addScore(idParty, idPlayer, score);
	}

	@Override
	public boolean isStarted(int idParty) {
		
		return pm.isStarted(idParty);
	}
}
