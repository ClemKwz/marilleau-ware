package marioware_ejb;

import javax.ejb.Remote;

@Remote
public interface TestRemote {

	public void setValeur(int id);
	public int getValeur();
	
}
