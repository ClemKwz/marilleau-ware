package gameEJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import controllerEJB.GameController2Local;

/**
 * Session Bean implementation class BuildPathController
 */
@Stateless
public class BuildPathController implements BuildPathControllerLocal {

	@EJB
	GameController2Local gc;
	
    public BuildPathController() {
        
    }
    
    @Override
   	public int calculScoreFinal(int sec, int msec) {
    	
    	if (sec==-1 || sec==-1) {
    		return 0;
    	}
   		return (200000-(10000*sec+10*msec));
   	}
   	
   	@Override
   	public String generateDataGame(int idGame) {
   			
   		int nbPath = (int) (Math.random()*6);
		nbPath += 6;
		int neg;
		int posX=0;
		int posY=0;
		int ran;
		String s = "";
		for (int i=0; i<nbPath; i++) {
			do {
				ran = ((int)(Math.random()*50))+20;
				neg = ((Math.random()>0.65 && i>3)?1:0);
				if(neg==1)
					ran = -ran;
				System.out.println("ran : " + ran + "   posX : " + posX + "   posY : " + posY);
			} while((posX+ran)>380||(posX+ran)<20||(posY+ran)>280||(posY+ran)<20);
			posX += ran;
			posY += ran;
			s += ran + ";";
		}
   		
   		// Insertion dans la base
   		gc.addDataGame(idGame,s);
   		
   		// Retour des params
   		return s;
   	}
   	
   	@Override
   	public String getDataGame(int idGame) {
   		
   		return gc.getDataFromGame(idGame);
   	}
}