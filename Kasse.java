package Abgabe;

public class Kasse {
	private double mMoney;
	private String mAngestelltenName;
	
	
	public static Kasse loginToCounter(String name, String pin){
		if(LoginService.login(name, pin)){
			System.out.println("Willkomen " + name);
			return new Kasse(name);
		}else return null;
	}
	
	private Kasse(String name){
		mAngestelltenName = name;
		
	}
	
	public void addMoney(double money) {
		mMoney = mMoney + money;
	}
	
	public double getChange(double toPay, double given) {
		 return (given - toPay);
		
	}
	public void takeMoney(double amount) {
		if (mMoney-amount < 0) {
			double dada = (mMoney-amount)*-1;
			System.out.println("Es fehlen noch " + dada + "in der Kasse" );
			throw new IllegalStateException("Die Kasse enthaelt nicht genug Geld, um den Restbetrag zu begleichen");
			
		}else {
			mMoney -= amount;
			
		}
	}
	
	private double getMoney(){
		return mMoney;
	}
	
	public void logoutFromCounter() {
		System.out.println("Schönen Feierabend " + mAngestelltenName);
		mAngestelltenName = "";
		LoginService.logout(mAngestelltenName);
		System.out.println("Der Restbetrag ist " + mMoney);
	}
	public String getLoggedInEmployee() {
		return mAngestelltenName;
	}

	

}
