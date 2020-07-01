

package Abgabe;

public class CartItem {
	private String mName;
	private int mQuantity;
	private double mPricePerUnit;
	
	public CartItem(String name, int quantity, double price) {
		//Invarianten
		if (quantity >= 1 && price > 0) {
			mName = name;
			mQuantity = quantity;
			mPricePerUnit = price;
			
		}else {
			System.out.println("Fehler");
			
		}
	}
	
	public void setQuantity(int quantity) {
		
		mQuantity = quantity;
	}
	public void setName(String name) {
		mName = name;
	}
	
	public void setPricePerUnity(double price) {
		mPricePerUnit = price;
	}
	public double getCost() {
		return mPricePerUnit * mQuantity;
	}
	
	public String getName() {
		return mName;
	}
	
	public int getQuantity(){
		return mQuantity;
	}
	
	
	@Override
	public String toString() {
		return String.format("%sx %s %s€ %s€", mQuantity, mName, mPricePerUnit, getCost());
	}
		
	
}
