
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.Console;
public class Galgen_1530369_Melek_Melisa_Cizer {
    
    public static String tipp(String secret, String versuch, String losung) {
        //Der Versuch in Großbuchstaben um später ohne auf Groß- und  Kleinschreibung zu achten.
        versuch = versuch.toUpperCase();
        // Umwandlung des verschlüsselten Wortes in einen Array um "-" austauschen zu können.
        char[] array = losung.toCharArray();

        
        for (int i = 0; i < secret.length(); i++) {
            // String nur mit String vergleichbar:
            String newSecret = Character.toString(secret.charAt(i)).toUpperCase(); 
            
            // vergleicht den Versuch mit dem Geheimwort um im Array das "-" auszutauschen
            if (versuch.equals(newSecret)){
            
                array[i] = secret.charAt(i); 
            
            }   

            }
            losung = new String(array);
            return losung;
    }

    public static boolean sonderzeichenCheck(String wort){
       return wort.matches("^[a-zA-Zä-üÄ-Ü]+$");
       
    }
        
        
    public static void main(String[] args) {
         
        // Geheimer Consoleninput.
        Scanner console = new Scanner(System.in);
        Console secretWordInput = System.console();
        String secretWord = new String(secretWordInput.readPassword("Geheimwort: "));
        boolean laufvariable = Galgen.sonderzeichenCheck(secretWord);
        
        //Erneute Wiedereingabe bei einem Sonderzeichen
        while (laufvariable == false) {
            System.out.println("Du hast Sonderzeichen verwendet, bitte nochmal eingeben:");
            secretWordInput = System.console();
            secretWord = new String(secretWordInput.readPassword("Geheimwort: "));
            laufvariable = Galgen.sonderzeichenCheck(secretWord);
        }
        

        // Das Geheimwort in Großbuchstaben um später ohne auf Groß- und  Kleinschreibung zu achten.
        String greatSecretWord = secretWord.toUpperCase();
            
        // Der Counter für die Versuche
        int anzahl = 0;
            String losung = "";
            //Das noch verschlüsselte Lösungswort wird gebildet.
            for (int i = 0; i < secretWord.length(); i++) {
                losung = losung + "-";     
            }
    
            while (!secretWord.equals(losung)){
                System.out.print("Der Buchstabe? ");
                String versuch = console.next();
            //Der Versuch in Großbuchstaben um später ohne auf Groß- und  Kleinschreibung zu achten.    
                String greatVersuch = versuch.toUpperCase();
               
                //Vergleich der großgeschriebenen Versionen
                if (versuch.length() > 1){
                    System.out.println("Dies ist eine fehlerhafte Angabe");
                    
                } 
                
                else if (greatSecretWord.contains(greatVersuch)) {
                    //Wenn der Buchstabe enthalten ist, wird dieser ausgetauscht.
                    
                    System.out.println("Der Buchstabe ist enthalten");
                    losung = Galgen.tipp(secretWord, versuch, losung);
                    System.out.println(losung);
                }
                else { 
                    //Wenn der Buchstabe falsch ist geht der Counter hoch.
                    anzahl++;
                    System.out.println("Der Buchstabe ist nicht enthalten");
                    System.out.println(losung);
                    System.out.println("Anzahl der Versuche: " + anzahl);
                }
            }
           System.out.println("Du hast gewonnen!"); 
           System.out.println("Deine Anzahl an Versuchen: " + anzahl);
        
        
    }
}