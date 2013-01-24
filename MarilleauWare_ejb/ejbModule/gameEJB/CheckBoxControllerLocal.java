package gameEJB;

import javax.ejb.Local;

@Local
public interface CheckBoxControllerLocal {

public int calculScoreFinal(int score);

public String getDataGame(int idGame);

public String generateDataGame(int idGame);
}
