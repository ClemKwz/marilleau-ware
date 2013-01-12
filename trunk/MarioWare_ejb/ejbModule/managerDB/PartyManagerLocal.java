package managerDB;

import javax.ejb.Local;

import model.User;

@Local
public interface PartyManagerLocal {

	public void createPartie(String name, String description, User userCreator);
	public int getIdByName(String name);
	public void addUserToParty(int idParty,int idUser);
	
}
