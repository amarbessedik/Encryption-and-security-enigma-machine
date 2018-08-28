import java.io.*;
/**
 *
 * @author Amar-cs
 */
public class DecryptUser {

   /**
    *
    * @param args
    */
   public static void main(String[] args) {
   
      Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
      Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
      Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
      Reflector rf = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");
      EnigmaMachine em = new EnigmaMachine(r1, r2, r3, rf);
      FindRotorSettings rs = new FindRotorSettings(); 
      Decrypt d = new Decrypt();
      
      String dataFileName = "ENCRYPTED.txt"; 
      String decryptionStorageFileName = "DECRYPTED.txt";
      String englishFileName = "ENGLISH.txt";
      
      searchForRealEnglish(em, d, dataFileName, englishFileName);
      
      findRotorSettingsForEnglish(rs);
      
      decryptFile(em, d, rs, dataFileName, decryptionStorageFileName);
      
   
   }//End main()
   
   
   //==========================================================================
   
   

   /**
    *
    * @param em
    * @param d
    * @param fileNameForEncryptedData
    * @param fileNameForEnglish
    */
   private static void searchForRealEnglish(EnigmaMachine em, Decrypt d, String dataFile, String storageFile) {
      System.out.println("\nSearching for English in the file named "+dataFile+" is in progress ...");
      deleteFileIfExists("ENGLISH.txt");
      d.searchForEnglish(em, dataFile, storageFile);
      System.out.println("\n---> The results for English are in the project's main folder in the file named : ENGLISH.txt");
   }//End searchForRealEnglish()

   /**
    *
    */
   private static void findRotorSettingsForEnglish(FindRotorSettings rs) {
      System.out.println("\nROTOR SETTINGS FOR ENGLISH: ----> "
             + "(LEFT ROTOR: " + rs.settingsForEnglish[0]
             + ", MIDDLE ROTOR: " + rs.settingsForEnglish[1]
             + ", RIGHT ROTOR : " + rs.settingsForEnglish[2]
             + ")\n");
   }//End rotorSettingsForEnglish()

   /**
    *
    * @param em
    * @param d
    * @param dataFile
    * @param storageFile
    */
   private static void decryptFile(EnigmaMachine em, Decrypt d, String dataFile, String storageFile) {
      System.out.println("\nDecryption of the file "+dataFile+" is in progress ...");
      deleteFileIfExists(storageFile);
      d.decryptFile(em, dataFile, storageFile);
      System.out.println("\n ---> Decryption results are in the project main folder in the file named: DECRYPTED.txt");
   }//End decryptFile()
   
   /**
    *
    * @param em
    * @param d
    * @param rs
    * @param dataFile
    * @param storageFile
    */
   
   private static void decryptFile(EnigmaMachine em, Decrypt d, FindRotorSettings rs, String dataFile, String storageFile){
      System.out.println("\nDecryption of the file "+dataFile+" is in progress ...");
      deleteFileIfExists(storageFile);
      d.decryptFile(em, dataFile, rs.getRotorSettingsForEnglish()[0], 
                                  rs.getRotorSettingsForEnglish()[1], 
                                  rs.getRotorSettingsForEnglish()[2], storageFile);
      System.out.println("\n ---> Decryption results are in the project main folder in the file named: "+storageFile);
   }
   
   /**
    * Delete file if it exists -- this helps just to avoid appending same data 
    * the existing file since several dozens executions were perforemed on the data
    * No need for this method if files were deleted manually or program running only once
    *
    * @param fileName
    * @return
    */
   private static boolean deleteFileIfExists(String fileName){
      File file = new File(fileName);
      if(file.exists()){
         boolean delete = file.delete();
         return delete;
      }
      return false;    
   }

}//End DecryptUser Class
