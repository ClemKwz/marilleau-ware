package controllerEJB;

import javax.ejb.Stateful;

/**
 * Session Bean implementation class Test
 */
@Stateful(mappedName="GameControllerJeu1")
public class GameControllerJeu1 implements GameControllerJeu1Remote {

	int valeur;
	
    /**
     * Default constructor. 
     */
    public GameControllerJeu1() {
        // TODO Auto-generated constructor stub
    }
    
    public void setValeur(int valeur) {
    	this.valeur = valeur;
    }
    
    public int getValeur() {
    	return this.valeur;
    }
    
    public String affiche(){
    	String message = "                 Game:"+valeur+"\n";
    	return message;
    }

}
