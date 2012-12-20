package controllerEJB;

import java.util.ArrayList;
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

	int valeur;
	ArrayList<GameControllerJeu1Remote> listGame;
    /**
     * Default constructor. 
     */
    public PartyController() {
        // TODO Auto-generated constructor stub
    }
    
    public void setValeur(int valeur) {
    	this.valeur = valeur;
    }
    
    public int getValeur() {
    	return this.valeur;
    }
    
    public void addGame(String name_JNDI,int valeur){
    	
    	if(listGame == null){
    		listGame = new ArrayList<GameControllerJeu1Remote>();
    	}
    	
    	Context ctx;
		try {
			Hashtable<String, String> h = new Hashtable<String, String>();
			h.put("java.naming.factory.initial","org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
			h.put("java.naming.factory.url.pkgs", "org.objectweb.jonas.naming");
			
			ctx = new InitialContext(h);
			GameControllerJeu1Remote ref = (GameControllerJeu1Remote) ctx.lookup(name_JNDI);
			
			ref.setValeur(valeur);
			listGame.add(ref);
		

	    	
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
    
    public String affiche(){
    	String message = "    Party:"+valeur+" Games:\n";
    	
    	for(int i=0;i<listGame.size();i++){

    		
    		message += "valGame:" + i + " :x " + listGame.get(i).affiche() + "\n";
    		
		}
    	return message;
    }

}
