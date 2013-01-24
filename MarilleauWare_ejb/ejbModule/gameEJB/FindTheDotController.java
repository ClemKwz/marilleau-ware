package gameEJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import controllerEJB.GameController2Local;

/**
 * Session Bean implementation class FindTheDotController
 */
@Stateless
public class FindTheDotController implements FindTheDotControllerLocal {

	@EJB
	GameController2Local gc;
    
    /**
     * Default constructor. 
     */
    public FindTheDotController() {
        
    }
	
    @Override
	public int calculScoreFinal(int score) {
		return (500-score)*1000;
	}
	
	/*
	 * Genere un tableau d'entiers aleatoires correspondant a :
	 * 	- indice 0 : idGame
	 * 	- indices 1 a 20 : positions des checkbox
	 *  - indices 21 a 40 : couleurs des contours des checkbox
	 */
	@Override
	public String generateDataGame(int idGame) {
		
		int[] tab = new int[5];
		
		int x1 = (int) (Math.random()*400);
		int y1 = (int) (Math.random()*300);

		int xInverted = (int) ((Math.random()>0.5)?0:1);
		int yInverted = (int) ((Math.random()>0.5)?0:1);
		tab[0] = idGame;
		tab[1] = x1;
		tab[2] = y1;
		tab[3] = xInverted;
		tab[4] = yInverted;
			
		String params = "";
		params = tab[1] + ";" + tab[2] + ";" + tab[3] + ";" + tab[4];
		
		// Insertion dans la base
		gc.addDataGame(idGame,params);
		// Retour des params
		
		return params;
	}
	
	@Override
	public String getDataGame(int idGame) {
		
		String params = gc.getDataFromGame(idGame);
		
		return params;
	}
}
