package controllerEJB;

import java.util.List;

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
	
    public GameController2() {
    }

	@Override
	public void createGameParty(Party party) {

		System.out.println("11111111111111111111111111111111111okazedzehfihzecjo");
		
		// En fonction des games dans la table Game_desc
		// on cree autant de jeu
		List<GamesDesc> lgd = gm.getAllGamesDesc();
		System.out.println("okazedzehfihzecjo");
		//gm.createGame(lgd.get(0), party);
		
		for (GamesDesc resultElement : lgd) {
			System.out.println("jerico "+resultElement.getIdGame_desc());
			gm.createGame(resultElement, party);
		}
		
		// et on ajoute les user dans la table tj_user_game
	}

	@Override
	public void addUserGame(int idGame, int idUser) {

		gm.addUserGame(idGame, idUser);
	}
}
