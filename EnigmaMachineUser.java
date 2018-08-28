
/**
 * Main class: Initializes Rotors and the Reflector
 * Sets the EnigmaMachine Object to rotors and reflector objects
 * Sets the The Rotors to : 5, 9, 14 respectvely for left, middle and right
 * Encodes a line of plain text and decodes it using the same retor settings
 * For verification:
 * -->The decoded text should "exactly" look like as the original plain text.
 *
 * @author Amar-cs
 */
public class EnigmaMachineUser {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String dataFile = "ENCRYPTED.txt";
        String resultsFile = "DECRYPTED.txt";
        String englishFile = "ENGLISH.txt";

        Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
        Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
        Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
        Reflector r = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");

        EnigmaMachine em = new EnigmaMachine(r1, r2, r3, r);

        String s = "AAAAAAAAAAAAAAAAAAAAAAAAAAA";
        System.out.println(" 1- Origina text using Rotor Settings (5, 9, 14).\t\t"+s);

        em.setRotors(5, 9, 14);
        String encodedS = em.encodeLine(s);
        
        System.out.println("\n 2- Encode Text:\t\t\t\t\t\t"+encodedS);

        em.setRotors(5, 9, 14);
        String decodedEncodedS = em.encodeLine(encodedS);
        System.out.println("\n 3- Decode text using Rotor Settings (5,9,14)\t\t\t"+decodedEncodedS);

        // //-----------------------------------
//         Decrypt d = new Decrypt();
// 
//         d.searchForEnglish(em, dataFile, englishFile);
// 
//         System.out.println("\nROTOR SETTINGS FOR ENGLISH: "
//                 + "\n(LEFT ROTOR = " + FindRotorSettings.settingsForEnglish[0]
//                 + " ,MIDDLE ROTOR = " + FindRotorSettings.settingsForEnglish[1]
//                 + " ,RIGHT ROTOR = " + FindRotorSettings.settingsForEnglish[2] + ")\n");
// 
//         d.decryptFile(em, dataFile, resultsFile);

    }
}
