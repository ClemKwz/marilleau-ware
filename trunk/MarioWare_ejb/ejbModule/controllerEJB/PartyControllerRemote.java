package controllerEJB;

import javax.ejb.Remote;

@Remote
public interface PartyControllerRemote {

	public void setId(int id);
	public int getId();
	
	public void addGame(String name_JNDI, int valeur);

	public void setValeurGame(int idGame, int valeur); 
	public int getValeurGame(int idGame);
	
	public String displayHTML();
}
