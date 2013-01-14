package controllerEJB;

import javax.ejb.Remote;

@Remote
public interface GameControllerRemote {

	public void setIdGame(int idGame);
	public int getIdGame();
	
	public void setValeur(int valeur);
	public int getValeur();
	
	public String displayHTML(); 
}
