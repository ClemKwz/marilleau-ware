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
@Stateful
public class BowserController implements BowserControllerLocal {

	private int valeur = 0;
	ArrayList<PartyControllerRemote> listParty;
	
    /**
     * Default constructor. 
     */
    public BowserController() {
        // TODO Auto-generated constructor stub
    }
    
    public void setValeur(int valeur) {
    	this.valeur = valeur;
    }
    
    public int getValeur() {
    	return this.valeur;
    }
    
    public void addParty(int valeur,String JNDI_NAME){
    	
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
	    	ref.setValeur(valeur);
	    	
	    	//fonction de génération
	    	ref.addGame("GameControllerJeu1", 2);
	    	ref.addGame("GameControllerJeu1", 3);
	    	ref.addGame("GameControllerJeu1", 4);
	    	listParty.add(ref);
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
    public String affiche(){
    	String message = "Bowser : 0. Parties :<br>";
    	for(int i=0;i<listParty.size();i++){
    		message += "&nbsp;&nbsp;&nbsp;idParty : " + i + ", " + listParty.get(i).affiche() + "<br>";
		}
    	return message;
    }

	@Override
	public int getValeurGame(int indexParty, int indexGame) {
		return listParty.get(indexParty).getValeurGame(indexGame);
	}

	@Override
	public void setValeurGame(int indexParty, int indexGame, int valeur) {
		listParty.get(indexParty).setValeurGame(indexGame,valeur);
	}
}
