package controllerEJB;

import javax.ejb.Remote;

@Remote
public interface PartyControllerRemote {

	public void setValeur(int id);
	public int getValeur();
	public void addGame(String name_JNDI,int valeur);
	public String affiche();
	public int getValeurGame(int indexGame);
	public void setValeurGame(int indexGame, int valeur); 
}
