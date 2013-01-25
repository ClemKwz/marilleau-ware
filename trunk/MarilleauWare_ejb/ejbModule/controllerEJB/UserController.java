package controllerEJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import managerDB.UserManagerLocal;

/**
 * Session Bean implementation class UserController
 */
@Stateless
public class UserController implements UserControllerLocal {

	@EJB
	UserManagerLocal um;
	
    public UserController() {
        
    }

	@Override
	public void deleteUser(int idUser) {
		
		um.deleteUserTableGame(idUser);
		um.deleteUser(idUser);
	}
}