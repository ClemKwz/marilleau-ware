package managerDB;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import model.Party;

@Local
public interface PartyManagerLocal {

	public void createPartie(String name, String description, int userCreator);
	public int getIdPartyByName(String name);
	public void addUserToParty(int idParty,int idUser);
	
	public List<Party> getAllParty();
	public Party getPartyById(int idParty);
	public void setPartyCurrentGame(int idParty, int idGame);
	
	public int getIdPartyByIdUser(int idUser);
	public int getIdGameByIdParty(int idParty);
	
	public HashMap<String, Integer> getAllScore(int idParty);
	public void incrementCurrentGame(int idParty);
	public void addScore(int idParty, int idPlayer, int score);
	public boolean isStarted(int idParty);
	public void setStarted(int idParty);
}
