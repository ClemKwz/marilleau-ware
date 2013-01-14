package controllerEJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import managerDB.PartyManagerLocal;
import model.Party;

/**
 * Session Bean implementation class PartyController2
 */
@Stateless
public class PartyController2 implements PartyController2Local {

	@EJB
	PartyManagerLocal pm;
	
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

}
