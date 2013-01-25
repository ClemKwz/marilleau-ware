package gameEJB;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class CheckBoxController
 */
@Stateless
public class CheckBoxController implements CheckBoxControllerLocal {

    /**
     * Default constructor. 
     */
    public CheckBoxController() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public int calculScoreFinal(int score) {
		return score*10000;
		
	}


}
