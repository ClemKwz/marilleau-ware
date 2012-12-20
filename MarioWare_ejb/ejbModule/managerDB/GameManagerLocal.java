package managerDB;

import javax.ejb.Local;

@Local
public interface GameManagerLocal {

	public void createGame(String name, String description);
	public int getIdByName(String name);

	
}
