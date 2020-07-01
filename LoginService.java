package Abgabe;

import java.util.ArrayList;

public final class LoginService {
	private static ArrayList<Angesteller> mAngestelltenKonten = new ArrayList<Angesteller>();
	private static ArrayList<Angesteller> mLogINStatus = new ArrayList<Angesteller>();
	
	public static void prepareLogins() {
		
		mAngestelltenKonten.add(new Angesteller("Hase", "1111"));
		mAngestelltenKonten.add(new Angesteller("Bär", "2222"));
		mAngestelltenKonten.add(new Angesteller("Affe", "3333"));
		mAngestelltenKonten.add(new Angesteller("Giraffe", "4444"));
		mAngestelltenKonten.add(new Angesteller("Löwe", "5555"));
		
		
	}
	
	private LoginService(){
		
	}
	
	public static boolean login(String name, String pin) {
		
		if (mLogINStatus.toString().contains(name)) {
			return false;
		
		}else if (mAngestelltenKonten.toString().contains(name)) {
			for (int i = 0; i < mAngestelltenKonten.size(); i++) {
				if (mAngestelltenKonten.get(i).getName().equals(name)) {
					if (mAngestelltenKonten.get(i).getPIN().equals(pin)) {
						mLogINStatus.add(mAngestelltenKonten.get(i));
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean isWorking(String name) {
		if (mLogINStatus.toString().contains(name)) {
			return true;
		
		}return false;
	}
	
	public static void logout(String name) {
		for (int i = 0; i < mAngestelltenKonten.size(); i++) {
			if (mAngestelltenKonten.get(i).getName().equals(name)) {
				mAngestelltenKonten.remove(i);
			}
		}
	}
	
	
}
