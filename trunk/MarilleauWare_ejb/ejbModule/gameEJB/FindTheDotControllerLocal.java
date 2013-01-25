package gameEJB;

import javax.ejb.Local;

@Local
public interface FindTheDotControllerLocal {

	public int calculScoreFinal(int score);
	public String generateDataGame(int idGame);
	public String getDataGame(int idGame);
}
