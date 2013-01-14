package controllerEJB;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import managerDB.GameManagerLocal;
import model.GamesDesc;
import model.Party;

/**
 * Session Bean implementation class GameController2
 */
@Stateless
public class GameController2 implements GameController2Local {

	@EJB
	GameManagerLocal gm;
	
	final int nbGameMaxPerParty = 10; 
	
    public GameController2() {
    }

	@Override
	public void createGameParty(Party party) {

		System.out.println("Creation des games de la partie ("+party.getIdParty()+")");
		
		// En fonction des games dans la table Game_desc
		// On cree autant de jeu
		List<GamesDesc> lgd = gm.getAllGamesDesc();
		
		for(int i=0; i<nbGameMaxPerParty; i++){
			Random random = new Random();
			int index = random.nextInt(lgd.size());
			gm.createGame(lgd.get(index), party, i);
		}
		
		/*
		for (GamesDesc resultElement : lgd) {
			System.out.println("creation du game de type "+resultElement.getName()+" ("+resultElement.getIdGame_desc()+")");
			gm.createGame(resultElement, party, sequence);
		}*/
	}
	
	@Override
	public void addUserGame(int idGame, int idUser) {

		gm.addUserGame(idGame, idUser);
	}
}
