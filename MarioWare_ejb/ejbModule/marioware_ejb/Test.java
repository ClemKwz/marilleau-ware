package marioware_ejb;

import javax.ejb.Stateful;

/**
 * Session Bean implementation class Test
 */
@Stateful(mappedName="Test1")
public class Test implements TestRemote {

	int valeur;
	
    /**
     * Default constructor. 
     */
    public Test() {
        // TODO Auto-generated constructor stub
    }
    
    public void setValeur(int valeur) {
    	this.valeur = valeur;
    }
    
    public int getValeur() {
    	return this.valeur;
    }

}
