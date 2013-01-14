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
@Stateful(mappedName="PartyController")
public class PartyController implements PartyControllerRemote {

	int idParty;
	//ArrayList<GameControllerRemote> listGame;
	HashMap<Integer,GameControllerRemote> listGame;
	
    public PartyController() {
    	
    }
    
    public void setId(int idParty) {
    	this.idParty = idParty;
    }
    
    public int getId() {
    	return this.idParty;
    }
    
    public void addGame(String name_JNDI, int idGame){
    	
    	if(listGame == null){
    		listGame = new HashMap<Integer,GameControllerRemote>();
    	}
    	
    	Context ctx;
		try {
			Hashtable<String, String> h = new Hashtable<String, String>();
			h.put("java.naming.factory.initial","org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
			h.put("java.naming.factory.url.pkgs", "org.objectweb.jonas.naming");
			
			ctx = new InitialContext(h);
			GameControllerRemote game = (GameControllerRemote) ctx.lookup(name_JNDI);
			game.setIdGame(idGame);
			game.setValeur(-1);
			
			listGame.put(idGame,game);
		} catch (NamingException e) {
			e.printStackTrace();
		}
    	
    	/*
    	if(listGame == null){
    		listGame = new ArrayList<GameControllerRemote>();
    	}
    	
    	Context ctx;
		try {
			Hashtable<String, String> h = new Hashtable<String, String>();
			h.put("java.naming.factory.initial","org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
			h.put("java.naming.factory.url.pkgs", "org.objectweb.jonas.naming");
			
			ctx = new InitialContext(h);
			GameControllerRemote game = (GameControllerRemote) ctx.lookup(name_JNDI);
			
			game.setValeur(valeur);
			listGame.add(game);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		*/
    }

	@Override
	public void setValeurGame(int idGame, int valeur) {
		
		listGame.get(idGame).setValeur(valeur);
	}

	@Override
	public int getValeurGame(int idGame) {
		
		return listGame.get(idGame).getValeur();
	}
    
    public String displayHTML(){
    	
    	String message = "&nbsp;&nbsp;&nbspidParty : "+idParty+". Games: <br>";
    	
    	/*for(int i=0;i<listGame.size();i++){
    		message += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + listGame.get(i).displayHTML() + "<br>";
		}*/
    	
    	for (Integer key : listGame.keySet()) {
    		message += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + listGame.get(key).displayHTML() + "<br>";
    	}
    	
    	return message;
    }
}
