package marioware_ejb;

import javax.ejb.Local;

@Local
public interface ListLocal {
	public void addOneTest(int valeur);

	public String print();
}
