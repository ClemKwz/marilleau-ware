package controllerEJB;

import javax.ejb.Local;

import model.Party;

@Local
public interface GameController2Local {

	public void createGameParty(Party party);

	public void addUserGame(int idGame, int idUser);

}
