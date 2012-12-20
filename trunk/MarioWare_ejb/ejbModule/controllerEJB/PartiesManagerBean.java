package controllerEJB;

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
public class PartiesManagerBean implements PartiesManagerLocal {

	private Map<UUID,String> userList;
	private int inttest;
	private int cpt = 0;
	private int[] tabVal = new int[2];
    /**
     * Default constructor. 
     */
    public PartiesManagerBean() {
    	
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

	@Override
	public void setTest(int test) {
		tabVal[cpt] = test;
		cpt++;
		if(cpt==1){
			
			System.out.println("tabVal[0] => " + tabVal[0]);
			System.out.println("tabVal[1] => " + tabVal[1]);
			
		}
	}
}
