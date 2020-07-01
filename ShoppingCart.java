package Abgabe;
import java.util.ArrayList;


public class ShoppingCart {
	private ArrayList<CartItem> mShoppingCart; 
	private static int mAnzahl = 10;
	private static int mKunde;
	public ShoppingCart() {
		mShoppingCart  = new ArrayList<CartItem>();
	}
	
	public void add(CartItem item) {
		if (item instanceof Frucht )  {
			if (mAnzahl - item.getQuantity() >= 0){
				mAnzahl -=item.getQuantity();
				mShoppingCart.add(item);
				
			}else throw new RuntimeException("sadasdads");
		}else if(item.getName() != null){
			mShoppingCart.add(item);
		}
		
	}
	
	public double getTotalCost() {
		double cost = 0;
		for (int i = 0; i < mShoppingCart.size(); i++) {
			cost= cost + mShoppingCart.get(i).getCost();
		}
		return cost;
	}
	
	public static int getKunde(){
		mKunde++;
		return mKunde;
	}
	@Override
	public String toString() {
		String bon = "";
		for (int i = 0; i < mShoppingCart.size(); i++) {
			
			bon= bon + mShoppingCart.get(i).toString() + "\n";
		}
		
		return String.format("%s\nGesamtpreis %s€", bon, getTotalCost());
	}
}
