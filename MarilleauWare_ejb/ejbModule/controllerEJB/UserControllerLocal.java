package controllerEJB;

import javax.ejb.Local;

@Local
public interface UserControllerLocal {

	public void deleteUser(int idUser);

}
