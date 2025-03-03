/** Main method that acts as UI for Playfair Encryption program
 * @author Santhosh Senthil, 115871889, R01
 */

import java.util.Scanner;

public class PlayfairEncryptionEngine {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String in="";

        System.out.println("Enter key phrase: ");
        KeyTable defKey = KeyTable.buildFromString(input.nextLine());
        System.out.println("Generation success!");
        while (true) {
            System.out.print("Menu:\n" +
                    "(CK) - Change key\n" +
                    "(PK) - Print key\n" +
                    "(EN) - Encrypt\n" +
                    "(DE) - Decrypt\n" +
                    "(Q) - Quit\n" +
                    "\n" +
                    "Please select an option:");
            in = input.nextLine();
            if (in.equalsIgnoreCase("CK")) {
                defKey=KeyTable.buildFromString(input.nextLine());
            } else if (in.equalsIgnoreCase("PK")) {
                System.out.println(defKey);
            } else if (in.equalsIgnoreCase("EN")) {
                System.out.println("Please enter a phrase to encrypt: ");
                System.out.println("Encrypted Text is: "
                        +Phrase.buildPhraseFromString(input.nextLine())
                        .encrypt(defKey).toString().replace(" ","").toLowerCase());
            } else if (in.equalsIgnoreCase("DE")) {
                System.out.println("Please enter a phrase to decrypt: ");
                System.out.println("Decrypted Text is: "
                        +Phrase.buildPhraseFromString(input.nextLine())
                        .decrypt(defKey).toString().replace(" ","").toLowerCase());
            } else if (in.equalsIgnoreCase("Q")) {
                System.out.println("Program Terminating...");
                break;
            }
            else
                throw new IllegalArgumentException("Wrong Input, Try Again");
        }
    }
}
