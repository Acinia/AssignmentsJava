//@autor: 1530369 Melek Melisa Cizer
package Pflichtaufgabe;

import java.util.ArrayList;
import java.util.Collections;

public class Auto {
	//Liste aller existierenden Autos
	private static ArrayList<Auto> mAutoliste = new ArrayList<Auto>();
	//Liste aller momentan verliehenden Autos
	private static ArrayList<Auto> mVerliehen = new ArrayList<Auto>();
	//Kopie der mAutoliste
	private static ArrayList<Auto> mcopylist = new ArrayList<Auto>();
	
	private String mAutoName;
	private Person mPerson;
	private int mZaehler;
	
	public Auto (String name){
		mAutoName = name;
		mPerson = null;
		mAutoliste.add(this);
		mZaehler = 0;
		
	}
	//getter und setter methoden
	
	public String getAutoName(){
		return mAutoName;
	}
	
	public void setAutoName(String name){
		mAutoName = name;
	}
	
	public Person getPerson(){
		return mPerson;
	}
	
	public void setPerson(Person person){
		mPerson = person;
	}
	
	public int getZaehler() {
		return mZaehler;
	}
	
	public static ArrayList<Auto> getAutoliste() {
		return mAutoliste;
	}
	
	public static ArrayList<Auto> getVerlehliste() {
		return mVerliehen;
	}
	
	public static ArrayList<Auto> getCopyliste() {
		return mcopylist;
	}
	
	//Methode um ein bestimmtes Object aus der Liste zu erreichen
	public static Auto getAutoVAutoliste(int i) {
		return mAutoliste.get(i);
	}
	//Methode um ein bestimmtes Object aus der Liste zu erreichen
	public static Auto getAutoVcopyList(int i) {
		return mcopylist.get(i);
	}
	
	//Methode die einem Auto eine Person zuordnet unter der Bedingung das keine person dem Auto zugeordnet ist.
	public void ausgeliehenVon(Person personenObject){
		if (mPerson == null) {
			mPerson = personenObject;
			mVerliehen.add(this);
			personenObject.leiheAuto(this);
			mZaehler++;
		}
	}
	
	//Methode die die zugehörigkeit einer Person zum Auto behebt
	public void zuruckgegebenVon(Person personenObject) {
		if (mPerson != null) {
			mPerson = null;
			mVerliehen.remove(this);
			personenObject.rueckgabeAuto(this);
		}
	}
	
	//Methode die die Liste aller Autos sortiert und formatiert ausgibt. 
	public static void getPrintetAutoliste(){
		//temporäre Stringliste wird erstellt und die Namen der Autoobjekte darin gespeichert und sortiert. 
		ArrayList<String> placeholder = new ArrayList<String>();
		for (int i = 0; i < mAutoliste.size(); i++) {
			placeholder.add(mAutoliste.get(i).getAutoName());
		}
		Collections.sort(placeholder);
		//Die Strings werden fromatiert ausgegeben.
		String start = "[";
		for (int i = 0; i < mAutoliste.size()-1; i++) {
			start = start + placeholder.get(i) + ", ";
		}
		start = start + placeholder.get(Auto.mAutoliste.size()-1) + "]";
		System.out.println(start);
	}
	
	//Methode zum erkennen ob das auto existiert und dann um es potentiell auszuleihen.
	public static boolean vergleich(String versuch, Person kunde){
		//Die Autoliste wird durchlaufem
		for (int i = 0; i < mAutoliste.size(); i++) {
			//Die von Kunden eingegeben eingabe wird mit dem namen der autos verglichen, groß und kleinschreibung spielt keine rolle.
			//Wenn die eingabe mit dem autonamen übereinstimmt, wird kontrolliert ob das Auto schon verliehen ist
			//Wenn es nicht verliehen ist wird es ausgeliehen.
			if (versuch.toUpperCase().equals(getAutoVAutoliste(i).getAutoName().toUpperCase())){
				
				if (getAutoVAutoliste(i).getPerson() == null) {
					Auto.mAutoliste.get(i).ausgeliehenVon(kunde);
					System.out.println("Das Auto wurde ausgeliehen.\n##########################################\n");
					
				}else {
					System.out.println("Das Auto ist zurzeit leider schon verliehen. Sie werden zurück ins Menü geleitet.\n##########################################\n");
			} 	
		
			return false;
			}
		}//Wenn die eingabe mit keinem der Autos übereinstimmt wird ein boolean wert zurückgegeben
		//welcher für die eurneute befragung benutzt wird.
		System.out.println("Das Auto ist nicht verfügbar. Versuchen Sie es erneut:");
		return true;
	}
	
	//Methode die die copylist zunächst leert und dann die werte der autoliste darin kopiert.
	public static void copyList() {
		mcopylist.clear();
		for (int i = 0; i < mAutoliste.size(); i++) {
			mcopylist.add(getAutoVAutoliste(i));
		}
	}
}
	
	
			

