package marioware_ejb;

import javax.ejb.Local;

@Local
public interface TestLocal {

	public void setValeur(int valeur);
	
}
