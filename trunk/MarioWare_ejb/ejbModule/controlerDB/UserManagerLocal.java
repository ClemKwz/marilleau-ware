package controlerDB;

import javax.ejb.Local;

@Local
public interface UserManagerLocal {

	public void createUser(String pseudo, String password, int isAdmin);
	public boolean findByPseudo(String pseudo);
	public int getIdByPseudo(String pseudo);
	
}
