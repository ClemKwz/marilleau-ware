package controllerEJB;

import java.util.List;
import java.util.TreeMap;

import javax.ejb.Local;

import model.ChatParty;
import model.Party;
import model.User;

@Local
public interface PartyController2Local {

	public List<Party> getListOfParty();
	public void addUser(int idParty, int idUser);
	public Party getPartyById(int idParty);
	public void initCurrentGame(int idParty);
	public int getIdPartyByIdUser(int idUser);
	public int getIdGameByIdParty(int idParty);
	public List<User> getAllUserPerParty(int idParty);
	public void addMessage(int idParty,int idUser,String message);
	public List<ChatParty> getAllMessages(int idParty);
	public List<ChatParty> getAllMessagesLimit(int idParty,int limit);
	public List<ChatParty> getMessagesAfter(int Party,int idMessage);
	public TreeMap<String, Integer> getAllScore(int idParty);
	public void incrementCurrentGame(int idParty);
	public void addScore(int idParty, int idPlayer, int score);
	public boolean isStarted(int idParty);
	
	public void deletePartyAndCo(int idParty);
}
