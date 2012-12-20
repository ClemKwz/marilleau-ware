package controllerEJB;

import javax.ejb.Remote;

@Remote
public interface BowserControllerRemote {

	public void setValeur(int id);
	public int getValeur();
	public void addParty(int valeur,String JNDI_NAME);
	public String toString(); 
}
