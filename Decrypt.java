
import java.io.*;

/**
 * Decrypt Class: Designs the logic needed to decrypt some lines of code in order 
 * to retrieve suitable rotor settings for real English: decodes lines, check if it 
 * matches real English, if yes then stores lines in a file as well as 
 * the combination of rotors used for it. Saves Rotor settings for English
 * Also provides the logic on how to decrypt an entire file after retrieving the 
 * saved combination. Stores results in separate files.
 * @author Amar-cs
 */
public class Decrypt {
   //Allowed errors accordiing to the English Freq & Deviations
   final static int errorsAllowed = 7; 
   // Adds 25 percent for lower and upper DEVIATIONS
   final static double multiplier = 2.5; 
   // no of lines to decode
   final static int numberOfLines = 12;
   static FindRotorSettings rs = new FindRotorSettings();
   static int counter = 0;

   /**
    * DECRYPT ALL TEXT em: EnigmaMachine Object dataFile: An encrypted file
    * resultsFile: Where results of decryption are stored
    *
    * @param em
    * @param dataFile
    * @param resultsFile
    */
    
   public void decryptFile(EnigmaMachine em, String dataFile, String resultsFile) {
      try {
         BufferedWriter bufferedWriter;
         try (BufferedReader bufferedReader = 
                    new BufferedReader(
                           new InputStreamReader(
                                new FileInputStream(dataFile), "UTF-8"))) {
            bufferedWriter = new BufferedWriter(
                                  new OutputStreamWriter(
                                       new FileOutputStream(resultsFile), "UTF-8"));
            String line;
            em.setRotors(rs.getRotorSettingsForEnglish()[0], 
                         rs.getRotorSettingsForEnglish()[1], 
                         rs.getRotorSettingsForEnglish()[2]);
            try {
               while ((line = bufferedReader.readLine()) != null) {
                  try {
                     bufferedWriter.write(em.encodeLine(line));
                     bufferedWriter.newLine();
                  } 
                  catch (IOException e) {
                     System.out.println(e);
                  }
               }
            } 
            catch (IOException e) {
               System.out.println(e);
            } 
            finally {
               bufferedWriter.close();
               bufferedReader.close();
            }
         }
      } 
      catch (FileNotFoundException ex) {
         System.out.println(ex);
      } 
      catch (IOException e) {
         System.out.println(e);
      }
   }//End decryptFile()

   /**
    * RETREIVE & DECODE SOME LINES FROM THE PROVIDED DATA FILE
    *
    * @param em
    * @param fileName
    * @return
    */
   private String decryptLines(EnigmaMachine em, String fileName) {
      String line;
      int lineControl = 0;// 0..numberofLines
      StringBuilder decodedLines = new StringBuilder();
      try {
         BufferedReader bufferedReader;
         bufferedReader = new BufferedReader(
                             new InputStreamReader(
                               new FileInputStream(fileName), "UTF-8"));
         try {
            while (((line = bufferedReader.readLine()) != null 
                            && !line.isEmpty()) && lineControl < numberOfLines) {
               decodedLines.append(em.encodeLine(line)).append(System.getProperty("line.separator"));
               lineControl++;//DECODE 12 LINES PER COMBINATION OF ROTOR POSITIONS
            }//END WHILE-LOOP
         } 
         catch (IOException e) {
            System.out.println(e);
         } 
         finally {
            bufferedReader.close();
         }
      } 
      catch (FileNotFoundException ex) {
         System.out.println(ex);
      } 
      catch (IOException e) {
         System.out.println(e);
      }
      return decodedLines.toString();
   }//End decryptLines()

   /**
    * INSPECT ALL POSSIBLE ROTOR COMBINATIONS TO GET A HANDFUL OF CASES TO
    * INSPECT BY EYE READ SOME DATA FROM FILE, DECODE IT, CHECK IF IT MATCHES
    * REAL ENGLISH ACCORDING TO A SPECIFIC SET OF PARAMETERS -LETTER FREQUENCY
    * FOR ENGLISH (AS A PERCENTAGE) -ALLOWABLE DEVIATIONS FROM ENGLISH
    * FREQUENCIES (AS A PERCENTAGE) -NUMBER OF DECODED LINES -ALLOWED NUMBER OF
    * ERRORS -MULTIPLIER STORE RESULTS IF A PARTICULAR COMBINATION OF ROTOR
    * SETTINGS MATCHES
    *
    * @param em
    * @param fileName
    * @param resultsFile
    */
   public void searchForEnglish(EnigmaMachine em, String fileName, String englishFile) {
   
      String decodedLines;
      for (int k = 0; k < Rotor.max; k++) { // RIGHT ROTOR
         for (int j = 0; j < Rotor.max; j++) { // MIDDLE RETOR
            for (int i = 0; i < Rotor.max; i++) { // LEFT MOST RETOR
               computationCounter();
               //SET RETORS << i = LEFT, j = MIDDLE, k = RIGHT >>
               em.setRotors(i, j, k);
               //DECODE SOME LINES OF TEXT
               decodedLines = decryptLines(em, fileName);
               // CHECK IF IT MATCHES REAL ENGLISH
               if (matchesEnglish(decodedLines)) {
                  //STORE DECODED TEXT IF GOOD FOR REVIEW BY EYE
                  storeLines(i, j, k, decodedLines, englishFile);
                  //SAVE RETOR SETTINGS
                  rs.saveRotorSettingsForEnglish(i, j, k);
               }
               //RESET LETTER COUNTER AND ERASE UNWANTED TEXT
               English.resetLetterCounters();
            }//END INNER-LOOP
         }//END MIDDLE-LOOP
      }//END OUTER-LOOP
      System.out.print("\n\n# of OPERATIONS ---------> " + counter + " \n\n");
   }//END FETCH FOR ENGLISH

   /**
    * RETURNS TRUE IF DECODED TEXT USING A PARTICULAR ROTOR COMBINATION LOOKS
    * LIKE REAL ENGLISH
    *
    * @param text
    * @return
    */
   private boolean matchesEnglish(String text) {
      English.countAllLetters(text);
      return (English.getErrorCount(multiplier) <= errorsAllowed);
   }//End matchesEnglish

   /**
    * Stores decoded lines as well as the corresponding combination of Rotor
    * settings that was used to decode them.
    *
    * @param left // Left Rotor
    * @param middle // Middle Rotor
    * @param right // Right Rotor
    * @param decodedText // Holds text that's being decoded
    * @param resultsFile // File to put decoded text into
    */
   private void storeLines(int left, int middle, int right, String decodedText, String englishFile) {
      try (BufferedWriter bufferedWriter = 
                       new BufferedWriter(
                             new OutputStreamWriter(
                                  new FileOutputStream(englishFile), "UTF-8"))) {
         bufferedWriter.write(decodedText);
         bufferedWriter.write("\t\tComputation #: " + "(" + left + "," + middle + "," + right + ")");
         bufferedWriter.newLine();
         bufferedWriter.close();
      } 
      catch (IOException e) {
         System.out.println(e);
      }
   }//End storeLines()

   /**
    * Method verifies that the # of computations is indeed 26x26x26 = 17576
    */
   private static void computationCounter() {
      ++counter;
   }//End computationCounter()
   
   
   //==========================================
   
      public void decryptFile(EnigmaMachine em, String dataFile, int p1, int p2, int p3, String resultsFile) {
      try {
         BufferedWriter bufferedWriter;
         try (BufferedReader bufferedReader = 
                    new BufferedReader(
                           new InputStreamReader(
                                new FileInputStream(dataFile), "UTF-8"))) {
            bufferedWriter = new BufferedWriter(
                                  new OutputStreamWriter(
                                       new FileOutputStream(resultsFile), "UTF-8"));
            String line;
            em.setRotors(p1, p2, p3);
            try {
               while ((line = bufferedReader.readLine()) != null) {
                  try {
                     bufferedWriter.write(em.encodeLine(line));
                     bufferedWriter.newLine();
                  } 
                  catch (IOException e) {
                     System.out.println(e);
                  }
               }
            } 
            catch (IOException e) {
               System.out.println(e);
            } 
            finally {
               bufferedWriter.close();
               bufferedReader.close();
            }
         }
      } 
      catch (FileNotFoundException ex) {
         System.out.println(ex);
      } 
      catch (IOException e) {
         System.out.println(e);
      }
   }//End decryptFile()  
   
   

}//END CLASS
