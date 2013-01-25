package gameEJB;

import javax.ejb.Local;

@Local
public interface BuildPathControllerLocal {

	public int calculScoreFinal(int sec, int msec);
	public String generateDataGame(int idGame);
	public String getDataGame(int idGame);
}
