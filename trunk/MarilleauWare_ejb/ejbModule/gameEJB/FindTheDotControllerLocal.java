package gameEJB;

import javax.ejb.Local;

@Local
public interface FindTheDotControllerLocal {

	int calculScoreFinal(int score);
	String generateDataGame(int idGame);
	String getDataGame(int idGame);
}
