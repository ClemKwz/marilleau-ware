package controllerEJB;

import java.util.TreeMap;

import javax.ejb.Local;

import model.Party;

@Local
public interface GameController2Local {

	public void createGameParty(Party party);

	public void addUserGame(int idParty, int idUser);
	
	public void addScore(int idGame, int idUser, double score);
	
	public boolean containsNegativeScore(int idGame);

	public TreeMap<String, Integer> getAllScore(int idGame);
	
	public int getNextgame(int idParty);
	
	public String getNameGameDesc(int idGame);

}
