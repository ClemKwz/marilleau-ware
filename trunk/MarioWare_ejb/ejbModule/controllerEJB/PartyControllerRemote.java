package controllerEJB;

import javax.ejb.Remote;

@Remote
public interface PartyControllerRemote {

	public void setValeur(int id);
	public int getValeur();
	public void addGame(String name_JNDI,int valeur);
	public String affiche(); 
}
