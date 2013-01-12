package controllerEJB;

import javax.ejb.Stateful;

/**
 * Session Bean implementation class Test
 */
@Stateful(mappedName="GameController")
public class GameController implements GameControllerRemote {

	private int idGame;
	private int valeur;

	public GameController() {
        
    }
    
	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}
	
	public int getIdGame() {
		return idGame;
	}
    
    public void setValeur(int valeur) {
    	this.valeur = valeur;
    }
    
    public int getValeur() {
    	return this.valeur;
    }
    
    public String displayHTML(){
    	
    	return "idGame : " + idGame + ", valeur = " + valeur + "<br>";
    }
}
