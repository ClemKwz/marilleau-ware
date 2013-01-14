package controllerEJB;

import java.util.List;

import javax.ejb.Local;

import model.Party;

@Local
public interface PartyController2Local {

	public List<Party> getListOfParty();

	public void addUser(int idParty, int idUser);

	Party getPartyById(int idParty);
}
