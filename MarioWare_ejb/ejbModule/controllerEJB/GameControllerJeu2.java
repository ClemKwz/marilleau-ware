package controllerEJB;

import javax.ejb.Stateful;

/**
 * Session Bean implementation class Test
 */
@Stateful(mappedName="GameControllerJeu2")
public class GameControllerJeu2 implements GameControllerJeu2Remote {

	int valeur;
	
    /**
     * Default constructor. 
     */
    public GameControllerJeu2() {
        // TODO Auto-generated constructor stub
    }
    
    public void setValeur(int valeur) {
    	this.valeur = valeur;
    }
    
    public int getValeur() {
    	return this.valeur;
    }

}
