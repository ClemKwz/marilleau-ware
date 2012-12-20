package marioware_ejb;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Session Bean implementation class List
 */
@Stateful
public class List implements ListLocal {

	ArrayList<TestRemote> list = new ArrayList<TestRemote>();

    /**
     * Default constructor. 
     */
    public List() {
    	//list = new ArrayList<TestRemote>();
    }

    public void addOneTest(int valeur){
    	
    	Context ctx;
		try {
			Hashtable<String, String> h = new Hashtable<String, String>();
			h.put("java.naming.factory.initial","org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
			h.put("java.naming.factory.url.pkgs", "org.objectweb.jonas.naming");
			
			ctx = new InitialContext(h);
	    	String JNDI_NAME = "Test";
			TestRemote ref = (TestRemote) ctx.lookup(JNDI_NAME);
	    	ref.setValeur(valeur);
	    	list.add(ref);
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
    
 public int showTest(int valeur){
    	
    	Context ctx;
		try {
			Hashtable<String, String> h = new Hashtable<String, String>();
			h.put("java.naming.factory.initial","org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
			h.put("java.naming.factory.url.pkgs", "org.objectweb.jonas.naming");
			
			ctx = new InitialContext(h);
	    	String JNDI_NAME = "marioware_ejb.TestRemote";
			TestRemote ref = (TestRemote) ctx.lookup(JNDI_NAME);
	    	ref.setValeur(valeur);
	    	return ref.getValeur();
		} catch (NamingException e) {
			e.printStackTrace();
			return -1;
		}
    }

	@Override
	public String print() {
		
		String s = "";
		
		for(int i=0;i<list.size();i++){
			s += "val de ejb " + i + " : " + list.get(i).getValeur() + "\n";
		}
		
		return s;
	}
}
