//@autor: 1530369 Melek Melisa Cizer
package Pflichtaufgabe;

import java.util.ArrayList;


public class Person {
	
	private static ArrayList<Person> mKundenliste = new ArrayList<Person>();
	private ArrayList<Auto> mKundenverlauf = new ArrayList<Auto>();
	private String mName;
	private Auto mAuto;
	
	public Person (String name){
		mName = name;
		mAuto = null;
		mKundenliste.add(this);
	}
	
	//getter und setter methoden
	
	public String getName(){
		return mName;
	}
	
	public void setName(String name){
		mName = name;
	}
	
	public Auto getAuto(){
		return mAuto;
	}
	public void setAuto(Auto auto){
		mAuto = auto;
	}

	public static ArrayList<Person> getKundenliste() {
		return mKundenliste;
	}
		
	public static void addKunde(Person kunde){
		mKundenliste.add(kunde);
	}
		
	public ArrayList<Auto> getKundenverlauf() {
		return mKundenverlauf;
	}
	
	//Methode um ein bestimmtes Object aus der Liste zu erreichen
	public static Person kundeVKundenliste(int i) {
		return mKundenliste.get(i);
	}
	//Autos welche in die verlaufsliste hinzugefügt wurden, werden mit ihren namen aus der liste
	//genommen und formatiert.
	public String getPrintetKundenverlauf(int zahl){
		
		String start = "Kunde " + Person.kundeVKundenliste(zahl).getName() + ": [";
		for (int i = 0; i < mKundenverlauf.size()-1; i++) {
			start = start + mKundenverlauf.get(i).getAutoName() + ", ";
		}
		start = start + mKundenverlauf.get(mKundenverlauf.size()-1).getAutoName() + "]";
		
		return start;
	}
	
	//Eine Person leiht ein Auto aus und das Auto wird der Verlaufsliste hinzugefügt.
	public void leiheAuto(Auto autoObject) {
		if (mAuto == null) {
			mAuto = autoObject;
			autoObject.ausgeliehenVon(this);
			mKundenverlauf.add(autoObject);
		}
	}
	
	//Eine Person gibt das Auto wieder zurück.
	public void rueckgabeAuto(Auto autoObject) {
		if (mAuto != null) {
			mAuto = null;
			autoObject.zuruckgegebenVon(this);
		}
	}
	
	
	//Die Methode kontrolliert ob der Benutzer existiert, wenn ja dann gibt er das Personenobjekt aus der Liste
	//zurück, wenn nicht wird ein neuer Kunde erstellt und dieser zurückgegeben.
	
	public static Person benutzernamenCheck(String benutzername){
		for (int i = 0; i < mKundenliste.size(); i++) {
			if (benutzername.toUpperCase().equals(mKundenliste.get(i).getName().toUpperCase())){
				return mKundenliste.get(i);
			}
		}
		
		return new Person(benutzername);
	}
	
	
}
			
		
		
					
		
		
	
		
		
		
		
		
		

	
