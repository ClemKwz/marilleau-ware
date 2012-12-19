package marioware_ejb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.ejb.Stateful;

/**
 * Session Bean implementation class PartiesManager
 */
@Stateful
public class PartiesManager implements PartiesManagerLocal {

	private Map<UUID,String> userList;
	
    /**
     * Default constructor. 
     */
    public PartiesManager() {
    	
    	userList = new HashMap<UUID, String>();
    }

    public UUID addUser(String pseudo) {
    	
    	if (userList.containsValue(pseudo)) {
    		return null;
    	}
    	
    	UUID id = UUID.randomUUID();
    	while (userList.containsKey(id)) {
    		id = UUID.randomUUID();
    	}
		userList.put(id, pseudo);
		
		return id;
	}
	
	public void removeUser(String pseudo) {
		
		
	}
	
	public String displayListOfUsers() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("List of users : {<br>");
		
		Set<UUID> keys = userList.keySet();
		Iterator<UUID> it = keys.iterator();
		
		while (it.hasNext()){
		   Object key = it.next();
		   sb.append("id : " + key + " - pseudo : " + userList.get(key) + "<br>");
		}
		sb.append("}<br>");
		
		return sb.toString();
	}
}
