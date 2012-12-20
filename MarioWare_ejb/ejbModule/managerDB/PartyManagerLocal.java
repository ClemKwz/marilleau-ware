package managerDB;

import javax.ejb.Local;

@Local
public interface PartyManagerLocal {

	public void createPartie(String name, String description, int idCreator);
	public int getIdByName(String name);
	public void addUserToParty(int idParty,int idUser);
	
}
