package controllerEJB;

import java.util.UUID;

import javax.ejb.Local;

@Local
public interface PartiesManagerLocal {

	public UUID addUser(String pseudo);
	public void removeUser(String pseudo);
	public String displayListOfUsers();
	public void setTest(int test);
}
