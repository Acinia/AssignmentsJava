package Abgabe;
/*
 * @author: Melek
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * KLasse zum Ausführen des Programmes.
 */
public class Ausfeueher {
	
	/*
	 * Input durch die Console
	 */
	public static String inputConsole() {
		String eingabe = "";
		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(r);
		try {
			eingabe = br.readLine();
			return eingabe.trim();
		} catch (IOException e) {
			
			e.printStackTrace();
		}return eingabe;
	}
	
	/*
	 * Das erstellen einer Kasse und die Vorbereitung dieser, der erste Abschnitt des Programms
	 */
	public static Kasse kassensetup() {
		int zahler = 3;
		double kassengeld=0;
		boolean ok;
		while (zahler != 0) {
			System.out.print("Name: ");
			String benutzername = inputConsole();
			System.out.print("PIN: ");
			String pin = inputConsole();
			Kasse kasse = Kasse.loginToCounter(benutzername, pin);
			if (kasse != null) {
				System.out.print("Wie viel Geld befindet sich in der Kasse? ");
				do {
					try {
						kassengeld = Double.parseDouble(inputConsole());
						ok = true;
					} catch (Exception e) {
						System.out.print("Falsche Eingabe, nochmal eingeben: ");
						ok=false;
					}
					
				} while (!ok);
				kasse.addMoney(kassengeld);
				System.out.println("-----------------------------------------------------------------");
				return kasse;
			}else {
				zahler--;
				System.out.println("Benutzername oder PIN falsch.");
				System.out.println("Sie haben nur noch " + zahler + " Versuche");
			}
		}
		System.out.println("Sie haben ein mehrmals eine falsche Eingabe getätigt. Die Kasse wird nun gesperrt");
		System.exit(1);
		return null;
		
	}
	
	/*
	 * Der Kunde/Shoppingcart kommt und der Einkauf wird abgerechnet
	 */
	public static void kundensimmulation(Kasse kasse, ShoppingCart kunde){
		System.out.println("Kunde" + ShoppingCart.getKunde());
		System.out.println(kunde);
		double kassengeld = 0;
		boolean ok;
		boolean good;
		double change = -1;
		double kundengeld = 0;
		while (change < 0) {
			System.out.print("Erhaltener Betrag vom Kunden: ");
			kundengeld = kundengeld + Double.parseDouble(inputConsole());
			change = kasse.getChange(kunde.getTotalCost(), kundengeld);
			if (change < 0) {
				System.out.println("Es wurde zu wenig Geld gegeben. Es fehlen noch " + change*-1);
			}
			
		}
		System.out.println("Der Kunde kriegt " + change + " wieder");
		do {
			
			try {
				kasse.takeMoney(change);
				good = true;
				
				
			} catch (RuntimeException e) {
				good = false;
				System.out.print("Wie viel Geld wollen Sie hinzufügen Kasse? ");
				do {
					try {
						kassengeld = Double.parseDouble(inputConsole());
						ok = true;
					} catch (Exception d) {
						System.out.print("Falsche Eingabe, nochmal eingeben: ");
						ok=false;
					}
					
				} while (!ok);
				kasse.addMoney(kassengeld);
				
			}
		} while (!good);
		
		kasse.addMoney(kundengeld);
		System.out.println("--------------------------------------------------------------------------");
	}
	
	
	public static void main(String[] args) {
		LoginService.prepareLogins();
		ShoppingCart kunde1 = new ShoppingCart();
		ShoppingCart kunde2 = new ShoppingCart();
		ShoppingCart kunde3 = new ShoppingCart();
		Suessware schokolade = new Suessware("Schkolade", 1, 0.5, 20);
		CartItem fisch = new CartItem("Fisch", 3, 5);
		Milchprodukte butter = new Milchprodukte("Butter", 2, 4, 7);
		CartItem brot = new CartItem("Brot", 1, 2);
		Frucht apfel = new Frucht("apfel", 2, 3);
		Milchprodukte milch = new Milchprodukte("Milch", 10, 3, 20);
		kunde1.add(brot);
		kunde1.add(apfel);
		kunde1.add(schokolade);
		kunde1.add(milch);
		kunde2.add(apfel);
		kunde2.add(milch);
		kunde3.add(butter);
		kunde3.add(fisch);
		
		Kasse kasse1 = kassensetup();
		
		kundensimmulation(kasse1, kunde1);
		kundensimmulation(kasse1, kunde2);
		kundensimmulation(kasse1, kunde3);
		kasse1.logoutFromCounter();
		
	}
}
