//@autor: 1530369 Melek Melisa Cizer
package Pflichtaufgabe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Autovermietung {
	//das menü mit den unterscheidlichen fällen
	public static void menu(int menuzahl) {
		boolean ok;
		do {
			
				switch (menuzahl) {
				case 0:
					System.out.println("Das Programm wird beendet");
					System.exit(1);
					break;
					
				case 1:
					autoVermieten();
					break;
					
				case 2:
					autoZurueckgeben();
					break;
					
				case 3:
					ubersichtAuto();
					break;
					
				case 4:
					kundenVerlauf();
					break;
					
				case 5:
					popularitaet();
					break;
					
				default:
					System.out.print("Sie haben eine nicht gültige Eingabe getätigt. Versuchen Sie es erneut: ");
					break;
				}
				
			
				ok = false;
				boolean good;
				menudesign();
				do {
					try {
						menuzahl = Integer.parseInt(inputConsole());
						good = true;
					} catch (Exception e) {
						System.out.println("Sie haben eine nicht gültige Eingabe getätigt. Versuchen Sie es erneut: ");
						good = false;
					}
				} while (!good);
				
			
			
		} while (!ok); 
	}
	
	//methode zum printen des menüs
	public static void menudesign() {
		System.out.println("Willkommen zur Autovermietung\n\n0 : "
				+ "Beenden\n1 : Auto mieten\n2 : Auto zurückgeben\n3 : "
				+ "Übersicht zu vermieteten Autos\n4 : "
				+ "Übersicht über Vermietungsverlauf aller Kunden\n5 : "
				+ "Übersicht zur Popularität der Autos\n");
		
		System.out.print("Bitte geben Sie die Zahl zur Option Ihrer Wahl ein: ");
	}
	
	//Methode zum Ausleihen der Autos
	public static void autoVermieten() {
		System.out.println("+++++++Auto mieten+++++++");
		//Die Autoliste alles verfügbaren Autos wird verglichen mit der Liste der gerade ausgeliehenen Autos
		if (Auto.getAutoliste().size() == Auto.getVerlehliste().size()){
			System.out.println("Alle Autos sind zurzeit verliehen.");
			return;
		} else { 
			System.out.print("Geben Sie Ihrenen Benutzernamen ein: ");
			boolean laufvariable = true;
			String benutzername = inputConsole();
			Person kunde = Person.benutzernamenCheck(benutzername);
			//Es wird kontrolliert ob der Kunde schon ein Auto ausgeliehen hat.
			if (kunde.getAuto() != null) {
				System.out.println("Sie haben schon ein Auto ausgeliehen.\n#####################################\n");
				return;
			}
			//Hier wird das Auto ausgeliehen, bei falscher eingabe wird erneut nach einer angabe gefragt, 
			//wenn das Auto ausgeliehen ist wird man zurück in das Menü geleitet.
			while (laufvariable == true) {
				Auto.getPrintetAutoliste();
				System.out.print("Geben Sie ihr bevorzugtes Auto ein: ");
				String marke = inputConsole();
				laufvariable = Auto.vergleich(marke, kunde);
			} return;
		}
	}
	//Methode zum Zurückgeben des Autos
	public static void autoZurueckgeben() {
		System.out.print("Geben Sie Ihren Benutzernamen ein: ");
		String benutzername = inputConsole();
		Person kunde = Person.benutzernamenCheck(benutzername);
		//Konrollle ob der Kunde überhaut ein Auto besitzt.
		if (kunde.getAuto() == null) {
			System.out.println("Sie haben kein Auto ausgeliehen und werden zurück ins Menü geleitet.");
			return;
		}else {
			System.out.println("Der Kunde " + kunde.getName() + " hat derzeit das Auto " + kunde.getAuto().getAutoName() + " gemietet.");	
			boolean laufvariable = true;
			//Es wird efragt ob das Auto zurückgegeben werden soll.
			while (laufvariable) {
				System.out.print("Soll es wieder abgegeben werden? (j/n): ");
				String antwort = inputConsole();
				if (antwort.equals("j")) {
					System.out.println("Der " + kunde.getAuto().getAutoName() + " wurde erfolgreich zurückgegeben.");
					kunde.rueckgabeAuto(kunde.getAuto());
					laufvariable = false;
				} else if (antwort.equals("n")){
					System.out.println("Sie werden zurück zum Menu geleitet.");
					return;
				}else {
					System.out.println("Die Eingabe war inkorrekt.");
				}
			}
		}
	}
	
	//Methode zur Ausgabe der momentan vergebenen Autos.
	public static void ubersichtAuto() {
		ArrayList<String> placeholder = new ArrayList<String>();
		//Es wird die Liste aller Kunden durchlaufen und bei jedem Kunden geschaut ob dieser ein Auto besitzt. 
		//Falls ja wird dieser als String in einer Liste gespeichert, welcher dann sortiert und ausgegeben wird.
		for (int i = 0; i < Person.getKundenliste().size(); i++) {
			if (Person.kundeVKundenliste(i).getAuto() != null) {
				placeholder.add("Kunde " + Person.kundeVKundenliste(i).getName()+ " hat das Auto " +Person.kundeVKundenliste(i).getAuto().getAutoName() + " gemietet.");
			}
		}
		Collections.sort(placeholder);
		for (int j = 0; j < placeholder.size(); j++) {
			System.out.println(placeholder.get(j));
		}
	}
	
	public static void kundenVerlauf() {
		//Die Kundenliste wird durchlaufen und bei jedem Kunden wird überprüft ob es Autoobjekte in der Kundenverlaufliste gibt.
		//Diese werden dann sortiert und formartiert ausgegeben.
		ArrayList<String> placeholder = new ArrayList<String>();
		for (int i = 0; i < Person.getKundenliste().size(); i++) {
			if (Person.kundeVKundenliste(i).getKundenverlauf() != null) {
				placeholder.add(Person.kundeVKundenliste(i).getPrintetKundenverlauf(i));
			}
		}
		Collections.sort(placeholder);
		for (int i = 0; i < placeholder.size(); i++) {
			System.out.println(placeholder.get(i));
			
		}
	}
	
	//Manulle sortierung nach der Anzahl der Verleihungen 
	public static void popularitaet() {
		Auto.copyList();
		int max =0; 
		int index = 0;
		//während in der kopierten listen noch werte vorhanden sind 
		//wird der größte zähler rausgesucht, geprinten und direkt aus der liste gelöscht.
		while (Auto.getCopyliste().size() > 1) {
			max =0;
			index = 0;
			for (int i = 0; i < Auto.getCopyliste().size(); i++) {
				if (Auto.getAutoVcopyList(i).getZaehler() > max) {
					max = Auto.getAutoVcopyList(i).getZaehler();
					index = i;
				}
			}
			System.out.println(Auto.getAutoVcopyList(index).getAutoName()+": " + Auto.getAutoVcopyList(index).getZaehler());
			Auto.getCopyliste().remove(index);
		}System.out.println(Auto.getAutoVcopyList(0).getAutoName()+": " + Auto.getAutoVcopyList(0).getZaehler());
	}
	
	//Die Methode kontrolliert ob der Benutzer existiert, wenn ja dann gibt er das Personenobjekt aus der Liste
	//zurück, wenn nicht wird ein neuer Kunde erstellt und dieser zurückgegeben.
	
	
	//Methode zum Einlesen des Userinputs
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
	
	
	//Methode zum Einlesen der Datei und der Erstellung der Autoobjekte 
	public static void getCarObject() throws IOException, FileNotFoundException {
		InputStream a;
		a = new FileInputStream(new File("./Pflichtaufgabe/Autoliste.txt"));
		BufferedReader c = new BufferedReader(new InputStreamReader(a));
		String zeile = c.readLine();
		while ((zeile = c.readLine()) != null && !zeile.isEmpty()) {
			//System.out.println(zeile);
			new Auto(zeile.trim());
		}c.close();
	}
	
	public static void main(String[] args) {
		try {
			getCarObject();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("File not Found");
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*Auto toyota = new Auto("toyota");
		Auto mazda = new Auto("mazda");
		Auto mercedes = new Auto("mercedes");
		mercedes.getAutoName();
		Person b = new Person("b");
		Person a = new Person("a");
		a.leiheAuto(toyota);
		b.leiheAuto(mazda);*/
		menudesign();
		boolean ok;
		do { 
			try {
				int zahl =  Integer.parseInt(inputConsole());
				menu(zahl);
				ok = true;
			} catch (Exception e) {
				ok = false;
				System.out.print("Sie haben eine nicht gültige Eingabe getätigt. Versuchen Sie es erneut: ");
			}
			
		} while (!ok);
	}
}
		
		
		
		
	
	
			
			
		
			
			
			
			
				
		

			
			
				
		
		
					



