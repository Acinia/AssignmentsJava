package Abgabe;

public class Milchprodukte extends CartItem {
	private double mFettinhalt;
	public Milchprodukte(String name, int quantity, double price, double fett) {
		super(name, quantity, price);
		mFettinhalt = fett;
		}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s %s", super.toString(), mFettinhalt);
	}
	
	public double getFettanteil() {
		return mFettinhalt;
	}
	
	public void setFettanteil(double fett) {
		mFettinhalt = fett;
	}
	
	
}
