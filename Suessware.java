package Abgabe;

public class Suessware extends CartItem {
	private double mCalorienanzahl;
	public Suessware(String name, int quantity, double price, double calorien) {
		super(name, quantity, price);
		mCalorienanzahl = calorien;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s %s(kca)", super.toString(), mCalorienanzahl);
	}
	public double getCalorienanzahl() {
		return mCalorienanzahl;
	}
	
	public void setCalorienanzahl(double calorien) {
		mCalorienanzahl = calorien;
	}
}
