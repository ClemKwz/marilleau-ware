package controllerEJB;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import managerDB.GameManagerLocal;
import managerDB.PartyManagerLocal;
import model.GamesDesc;
import model.Party;

/**
 * Session Bean implementation class GameController2
 */
@Stateless
public class GameController2 implements GameController2Local {

	@EJB
	GameManagerLocal gm;
	@EJB
	PartyManagerLocal pm;
	
	final int nbGameMaxPerParty = 3; 
	
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
	public void addUserGame(int idParty, int idUser) {

		Party party = pm.getPartyById(idParty);
		gm.addUserGame(party.getIdCurrentGame(), idUser);
	}

	@Override
	public void addScore(int idGame, int idUser, double score) {
		gm.addScore(idGame, idUser, (500-score)*1000);
	}
	
	public boolean containsEmptyScore(int idGame, int idUser) {
		// on regarde s'il reste des scores a -1 dans TjUserGame
		return gm.containsEmptyScore(idGame);
	}
	
}
