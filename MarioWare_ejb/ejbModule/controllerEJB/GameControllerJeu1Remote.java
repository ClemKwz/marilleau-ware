package controllerEJB;

import javax.ejb.Remote;

@Remote
public interface GameControllerJeu1Remote {

	public void setValeur(int id);
	public int getValeur();
	public String affiche(); 
}
