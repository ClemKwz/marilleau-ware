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
	ArrayList<Object> listGame;
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
    	
    	GameControllerJeu1Remote jeu1=null;
    	GameControllerJeu2Remote jeu2=null;
    	
    	if(listGame == null){
    		listGame = new ArrayList<Object>();
    	}
    	
    	Context ctx;
		try {
			Hashtable<String, String> h = new Hashtable<String, String>();
			h.put("java.naming.factory.initial","org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
			h.put("java.naming.factory.url.pkgs", "org.objectweb.jonas.naming");
			
			ctx = new InitialContext(h);
			Object ref = (Object) ctx.lookup(name_JNDI);
			
			if(ref.getClass().getName() == "GameControllerJeu1Remote"){
				jeu1 = (GameControllerJeu1Remote) ref;
				jeu1.setValeur(valeur);
				listGame.add(jeu1);
			}
			if(ref.getClass().getName() == "GameControllerJeu2Remote"){
				jeu2 = (GameControllerJeu2Remote) ref;
				jeu2.setValeur(valeur);
				listGame.add(jeu2);
			}

	    	
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
    
    public String toString(){
    	String message = "    Party:"+valeur+" Games:\n";
    	for(int i=0;i<listGame.size();i++){
    		message += "        valGame:" + i + " : " + listGame.get(i).toString() + "\n";
		}
    	return message;
    }

}
