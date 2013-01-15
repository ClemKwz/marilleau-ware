package managerDB;

import java.util.List;

import javax.ejb.Local;

import model.GamesDesc;
import model.Party;

@Local
public interface GameManagerLocal {

	public void createGame(GamesDesc gamesDesc, Party party, int sequence);
	public int getIdByName(String name);
	public List<GamesDesc> getAllGamesDesc();
	public void addUserGame(int idGame, int idUser);
	public int getIdGameByPartyBySeq(int idParty, int sequence);
	public void addScore(int idGame, int idUser, double score);
	public boolean containsNegativeScore(int idGame);
}
