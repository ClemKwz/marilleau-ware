package controllerEJB;

import javax.ejb.Remote;

@Remote
public interface GameControllerJeu2Remote {

	public void setValeur(int id);
	public int getValeur();
	public String toString(); 
}
