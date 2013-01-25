package gameEJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import controllerEJB.GameController2Local;

/**
 * Session Bean implementation class CheckBoxController
 */
@Stateless
public class CheckBoxController implements CheckBoxControllerLocal {

	@EJB
	GameController2Local gc;
	
    public CheckBoxController() {
    	
    }

	@Override
	public int calculScoreFinal(int score) {
		return score*10000;
	}
	
	/*
	 * Genere un tableau d'entiers aleatoires correspondant a :
	 * 	- indice 0 : idGame
	 * 	- indices 1 a 20 : positions des checkbox
	 *  - indices 21 a 40 : couleurs des contours des checkbox
	 */
	@Override
	public String generateDataGame(int idGame) {
		
		int possiblePositions = 20*15 + 1; // nombre de positions possibles pour les checkbox dans le canvasElem
		int nb_checkbox = 20;
		int sizeTab = 1 + 2 * nb_checkbox;
		int[] randTab = new int[sizeTab];
		String params = "";
		
		randTab[0] = idGame;
		
		for (int i=1; i<sizeTab; i++) {
			randTab[i] = (int) (Math.random()*possiblePositions);
		}
		
		for (int i=1; i < randTab.length-1; i++) {
			params += randTab[i] + ";";
		}
		params += randTab[randTab.length-1];
		
		// Insertion dans la base
		gc.addDataGame(idGame,params);
		
		// Retour des params
		return params;
	}
	
	@Override
	public String getDataGame(int idGame) {
		
		return gc.getDataFromGame(idGame);
	}
}
