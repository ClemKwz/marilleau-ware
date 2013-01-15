package managerDB;

import java.util.List;

import javax.ejb.Local;

import model.ChatParty;

@Local
public interface ChatPartyManagerLocal {

	public void addMessage(int idParty,int idUser,String message);
	public List<ChatParty> getAllMessages(int idParty);
	public List<ChatParty> getAllMessagesLimit(int idParty,int limit);
	public List<ChatParty> getMessagesAfter(int Party,int idMessage);
}
