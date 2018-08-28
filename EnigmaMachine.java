
/**
 * EnigmaMachine Class: Contains a constructor and 4 methods
 * It designs the logic needed to encode a character, encode a line of
 * characters, set the retors to particular positons & increment the retors
 * 
 * @author Amar-cs
 */
public class EnigmaMachine {

   private Rotor r1;//1ST ROTOR
   private Rotor r2;//2ND ROTOR
   private Rotor r3;//3RD ROTOR
   private Reflector rf;//REFLECTOR  

  //CONSTRUCTOR
   /**
    *
    * @param r1
    * @param r2
    * @param r3
    * @param rf
    */
   public EnigmaMachine(Rotor r1, Rotor r2, Rotor r3, Reflector rf) {
      //INITIALIZATIONS
      this.r1 = r1;
      this.r2 = r2;
      this.r3 = r3;
      this.rf = rf;
   }//End Constructor

   /**
    * ENCODE A CHARACTER IF IT IS AN UPPERCASE LETTER. OTHERWISE, RETURN THE INPUT CHARACTER.
    * The algorithm operates in 3 different stages: L->R, REFLECTOR, THEN R->L
    * For readability and clarity purposes, 3 char variables are declared to illustrate 
    * how the encoding takes place. Otherwise its possible to do it in one line of code
    * as folows: 
    * r1.encodeRL(r2.encodeRL(r3.encodeRL(rf.encodeLR(r3.encodeLR(r2.encodeLR(r1.encodeLR(character)))))));
    * @param c
    * @return
    */
   public char encodeChar(char c) {//c is the input character
      if (Character.isLetter(c) && Character.isUpperCase(c)) {
         /*FIRST STAGE:
           Encode from L->R: The result would be input for the Reflector
           The character goes through Rotor1, Rotor2,and Rotor3 respectivelly*/
         char c1 = r3.encodeLR(r2.encodeLR(r1.encodeLR(c)));
         /*SECOND STAGE:
           Reflect the previous character using the Reflector
           The result would be an input for R->L encoding */
         char c2 = rf.encodeLR(c1);
         /*THIRD STAGE:
           The character goes through Rotor3, Rotor2,and Rotor1 respectivelly */
         char c3 = r1.encodeRL(r2.encodeRL(r3.encodeRL(c2)));
         //Increment the Rotors
         incrementRotors();
         return c3;//The encoded character.
      }
      return c;//The input character if no encoding occured.
   }//End encodeChar()
                                      
   /**
    * ENCODE ONE LINE OF CHARACTERS
    * REPEAT encodeChar ALGORITHM FOR EACH CHARACTER IN THE LINE
    * @param line
    * @return
    */
   public String encodeLine(String line) {
      StringBuilder encodedLine = new StringBuilder();
      for (int i = 0; i < line.length(); i++) {
         encodedLine.append(encodeChar(line.charAt(i)));
      }
      return encodedLine.toString();
   }//End encodeLine()

   /**
    * SET ROTORS TO POSITIONS a,b, and c RESPECTIVELY
    * @param a
    * @param b
    * @param c
    */
   public void setRotors(int a, int b, int c) {
      r1.set(a);
      r2.set(b);
      r3.set(c);
   }//End setRotors()

   /**
    *INCREMENT ROTORS POSITIONS
    */
   private void incrementRotors() {
      if (r1.inc())//LEFT ROTOR ADVANCES AFTER EACH ENCODED CHARACTER
      {
         if (r2.inc())//MIDDLE ROTOR ADVANCES AFTER LEFT ROTOR MADE A COMPLETE CIRCLE
         {
            r3.inc();//RIGHT ROTOR ADVANCES AFTER MIDDLE RETOR MADE A COMPLETE CIRCLE
         }
      }
   }//End incrementRotors()
}//END CLASS
