
/**
 * THE ENGLISH CLASS HOLDS SOME DATA RELIETED TO THE ENGLISH LANGUAGE. IT SERVES
 * FOR DETECTING REAL ENGLISH IN A DECODED SAMPLE OF TEXT
 *
 * @author Amar-cs
 */
final class English {

   /**
    * totalNumberOfLetters: USED TO DETECT ENGLISH - THIS VARIABLE IS PACKAGE
    * PRIVATE (TO AVOID MALICIOUS CODE TO CHANGE ITS VALUE) engLetterFreq:
    * ENGLISH LETTER FREQUENCY max: THE # OF ENGLISH LETTERS = 26 letterCount:
    * HOLDS LETTER COUNT- HOW MANY A(s), B(s), C(s), Z(s) IN A SAMPLE TEXT
    */
   static int totalNumberOfLetters;
   private final static int max = Rotor.max;
   private static final double[] engLetterFreq = {8.1, 1.6, 3.2, 3.6, 12.3, 2.3, 1.6, 
                                                  5.1, 7.2, 0.1, 0.5, 4.0, 2.2, 7.2, 
                                                  7.9, 2.3, 0.2, 6.0, 6.6, 9.6, 3.1, 
                                                  0.9, 2.0, 0.2, 1.9, 0.1};
                                                  
   private static final int[] engLetterDeviation = {10, 50, 30, 30, 10, 30, 50, 
                                                    20, 15, 100, 80, 30, 30, 20, 
                                                    20, 30, 100, 30, 20, 15, 30, 
                                                    60, 40, 100, 40, 100};
   private static int[] letterCount = new int[max];

   /**
    * COUNTS # OF LETTERS IN A STRING (TEXT)& COUNTS LETTERS INDIVIDUALLY
    *
    * @param s
    */
   protected static void countAllLetters(String s) {
   //Gets the length of the text sample to decode for checking puposes
      totalNumberOfLetters = s.length();
      int asciiOfChar = 65;//INITIALIZED TO UPPERCASE LETTER 'A' - FIRST LETTER OF THE ALPHABET
      for (int i = 0; i < max; i++) {
         for (int j = 0; j < totalNumberOfLetters; j++) {
            //STOPS AT UPPERCASE LETTER 'Z' - LAST LETTER OF THE ALPHABET
            if (s.charAt(j) == asciiOfChar && asciiOfChar <= 90) {
               letterCount[i]++;
            }
         }
         asciiOfChar++;// GO TO NEXT CHARACTER IN AN ORDERED MANNER
      }
   }

   /**
    * METHOD FINDS OUT HOW MANY ERRORS ARE THERE IN ORDER TO DECIDE IF THE
    * DECODED TEXT IS SOUHAITABLE TO CHECK WHETHER OR NOT IT MATCHES ENGLISH
    *
    * @param mult
    * @return
    */
   protected static int getErrorCount(double mult) {
      int errorCount = 0;//Errors initially set to 0 (no errors)
      for (int i = 0; i < max; i++) {
         double frequency = (letterCount[i] * 100.0) / totalNumberOfLetters;
         double lowerFreq = engLetterFreq[i] - ((mult * engLetterDeviation[i] / 100.0) * engLetterFreq[i]);
         double upperFreq = engLetterFreq[i] + ((mult * engLetterDeviation[i] / 100.0) * engLetterFreq[i]);
         
         if (frequency < (lowerFreq)|| frequency > upperFreq) {
            errorCount++;
         }
      }
      return errorCount;
   }//End getErrorCount()

   /**
    * METHOD RESETS LETTER COUNTER TO 0 - LETTER COUNT DIFFER FOR INDIVIDUAL
    * LETTERS AFTER EACH ROTOR SETTING
    */
   protected static void resetLetterCounters() {
      for (int i = 0; i < max; i++) {
         letterCount[i] = 0;
      }
   }//End resetLetterCounters()
}//END CLASS
