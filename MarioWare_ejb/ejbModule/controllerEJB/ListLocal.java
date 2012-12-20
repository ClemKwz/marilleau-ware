package controllerEJB;

import javax.ejb.Local;

@Local
public interface ListLocal {
	public void addOneTest(int valeur);
	 public int showTest(int valeur);
	public String print();
}
