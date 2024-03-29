package managerDB;

import java.util.List;

import javax.ejb.Local;

import model.User;

@Local
public interface UserManagerLocal {

	public void createUser(String pseudo, String password, int isAdmin);
	public boolean findByPseudo(String pseudo);
	public int getIdByPseudo(String pseudo);
	User getUserById(int idUser);
	List<User> getAllUserPerParty(int idParty);
	public void deleteUser(int idUser);
	public void deleteUserTableGame(int idUser);
}
