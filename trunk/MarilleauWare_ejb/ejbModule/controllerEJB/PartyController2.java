package controllerEJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import managerDB.GameManagerLocal;
import managerDB.PartyManagerLocal;
import model.Party;

/**
 * Session Bean implementation class PartyController2
 */
@Stateless
public class PartyController2 implements PartyController2Local {

	@EJB
	PartyManagerLocal pm;
	@EJB
	GameManagerLocal gm;
	
    /**
     * Default constructor. 
     */
    public PartyController2() {
       
    }

	@Override
	public List<Party> getListOfParty() {
		
		return pm.getAllParty();
	}

	@Override
	public Party getPartyById(int idParty) {
		
		return pm.getPartyById(idParty);
	}

	@Override
	public void addUser(int idParty, int idUser) {
		
		pm.addUserToParty(idParty, idUser);
	}

	@Override
	public void initCurrentGame(int idParty) {
		
		int idGame = gm.getIdGameByPartyBySeq(idParty,0);
		pm.setPartyCurrentGame(idParty,idGame);
	}

	@Override
	public int getIdPartyByIdUser(int idUser) {
		return pm.getIdPartyByIdUser(idUser);
	}

	@Override
	public int getIdGameByIdParty(int idParty) {
		return pm.getIdGameByIdParty(idParty);
	}

}
