package controllerEJB;

import java.util.HashMap;
import java.util.Hashtable;

import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Session Bean implementation class Test
 */
@Stateful
public class BowserController implements BowserControllerLocal {

	private int valeur = 0;
	HashMap<Integer, PartyControllerRemote> listParty;
	//ArrayList<PartyControllerRemote> listParty;
	
    public BowserController() {
        
    }
    
    public void setValeur(int valeur) {
    	this.valeur = valeur;
    }
    
    public int getValeur() {
    	return this.valeur;
    }
    
    public void addParty(String JNDI_NAME, int idParty){
    	
    	if(listParty == null){
    		listParty = new HashMap<Integer, PartyControllerRemote>();
    	}
    	
    	Context ctx;
		try {
			Hashtable<String, String> h = new Hashtable<String, String>();
			h.put("java.naming.factory.initial","org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
			h.put("java.naming.factory.url.pkgs", "org.objectweb.jonas.naming");
			
			ctx = new InitialContext(h);
			PartyControllerRemote party = (PartyControllerRemote) ctx.lookup(JNDI_NAME);
			party.setId(idParty);
	    	
			party.addGame("GameController", 2);
			party.addGame("GameController", 3);
			party.addGame("GameController", 4);
	    	listParty.put(idParty,party);
		} catch (NamingException e) {
			e.printStackTrace();
		}
    	
    	/*
    	if(listParty == null){
    		listParty = new ArrayList<PartyControllerRemote>();
    	}
    	
    	Context ctx;
		try {
			Hashtable<String, String> h = new Hashtable<String, String>();
			h.put("java.naming.factory.initial","org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
			h.put("java.naming.factory.url.pkgs", "org.objectweb.jonas.naming");
			
			ctx = new InitialContext(h);
			PartyControllerRemote ref = (PartyControllerRemote) ctx.lookup(JNDI_NAME);
	    	ref.setId(idParty);
	    	
	    	//fonction de génération
	    	ref.addGame("GameController", 2);
	    	ref.addGame("GameController", 3);
	    	ref.addGame("GameController", 4);
	    	listParty.add(ref);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		*/
    }

	@Override
	public void setValeurGame(int idParty, int idGame, int valeur) {
		
		listParty.get(idParty).setValeurGame(idGame,valeur);
	}

	@Override
	public int getValeurGame(int idParty, int idGame) {
		
		return listParty.get(idParty).getValeurGame(idGame);
	}
    
    public String displayHTML(){
    	
    	String message = "Bowser : " + valeur + ". Parties :<br>";

    	/*for(int i=0;i<listParty.size();i++){
    		message += "&nbsp;&nbsp;&nbsp;idParty : " + i + ", " + listParty.get(i).displayHTML() + "<br>";
		}*/
    	
    	for (Integer key : listParty.keySet()) {
    		message += ""+ listParty.get(key).displayHTML() + "<br>";
    	}
    	
    	return message;
    }
}
