package controllerEJB;

import javax.ejb.Local;

@Local
public interface BowserControllerLocal {

	public void setValeur(int id);
	public int getValeur();
	public void addParty(int valeur,String JNDI_NAME);
	public String affiche();
	public int getValeurGame(int indexParty,int indexGame);
	public void setValeurGame(int indexParty,int indexGame, int valeur);
}
