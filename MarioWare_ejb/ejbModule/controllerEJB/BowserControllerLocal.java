package controllerEJB;

import javax.ejb.Local;

@Local
public interface BowserControllerLocal {

	public void setValeur(int valeur);
	public int getValeur();
	
	public void addParty(String JNDI_NAME, int idParty);

	public void setValeurGame(int idParty, int idGame, int valeur);
	public int getValeurGame(int idParty, int idGame);
	
	public String displayHTML();
}
