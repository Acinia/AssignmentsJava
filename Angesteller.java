
package Abgabe;
//@author Melek
/*
 * Angestellten Klasse
 */
public class Angesteller {
	private String mName;
	private String mPIN;
	
	public Angesteller(String name, String pin) {
		mName = name;
		mPIN = pin;
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return mName + " " + mPIN;
	}
	
	public String getName() {
		return mName;
	}
	public String getPIN() {
		return  mPIN;
	}
}
