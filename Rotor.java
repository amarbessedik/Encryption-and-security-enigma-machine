
/**
 * Rotor Class: Designs the Rotors
 * Provides the logic needed to encode a character from L-->R,
 * from R-->L, how to increment a rotor, how to set & get its position
 * as well as a couple method to check how the actual wiring is done
 *
 * @author Amar-cs
 */
public class Rotor {

   final static int max = 26;//Package protected

   /**
    * Method initializes ROTOR'S RIGHT to LEFT WIRING
    *
    * @param a
    * @param s
    */
   protected static void initializeRL(char[] a, String s) {
      for (int i = 0; i < max; i++) {
         a[i] = s.charAt(i);
      }
   }

   private int position; //ROTOR INITIAL POSITION

   /**
    * Rotor wiring from Left --> Right: leftToRightWiring is Package a
    * Protected variable therefore accessible from its subclass "Reflector"
    * (Reflector Extends Rotor)
    */
   protected final char[] leftToRightWiring = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
                                               'H', 'I', 'J', 'K', 'L', 'M', 'N',
                                               'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                                               'V', 'W', 'X', 'Y', 'Z'};
      // R->L wiring - from constructor parameter 
   private char[] rightToLeftWiring = new char[max];

   /**
    * Constructor to Build the 3 Rotors
    *
    * @param s
    */
   public Rotor(String s) {//CONSTRUCTOR
      position = getPosition();
      initializeRL(rightToLeftWiring, s);
   }

   /**
    * Useful to initialize the reflector since it Reflector Class extends Rotor
    * Class
    */
   public Rotor() {
   }

   /**
    * Method Advances the POSITION by one "click", 0...25, and returns whether
    * the Rotor went from 25 to 0
    *
    * @return
    */
   public boolean inc() {
      ++position;
      if (position % 26 == 0) {
         position = 0;
         return true;
      }
      return false;
   }//End inc()

   /**
    * Method that sets the Rotor position to a particular position n Method
    * sets ROTOR POSITION
    *
    * @param n
    */
   public void set(int n) {
      position = n;
   }//End set()

   /**
    * A private Method: returns index of a character that's going Left-->Right
    *
    * @param c
    * @return
    */
   protected int indexOfLeftCharacter(char c) {
      for (int index = 0; index < max; index++) {
         if (c == leftToRightWiring[index]) {
            return index;
         }
      }
      return -1;
   }

   /**
    * A private Method: returns index of a character that's going Right-->Left
    *
    * @param c
    * @return
    */
   protected int indexOfRightCharacter(char c) {
      for (int index = 0; index < max; index++) {
         if (c == rightToLeftWiring[index]) {
            return index;
         }
      }
      return -1;
   }

   /**
    * Method inspects if a char is an uppercase letter Then encodes a character
    * from LEFT to RIGHT Otherwise returns the character as is
    *
    * @param c
    * @return
    */
   public char encodeLR(char c) {//Character c is wired to going L->R
      if (Character.isLetter(c) && Character.isUpperCase(c)) {
         return rightToLeftWiring[(indexOfLeftCharacter(c) + position) % 26];
      }
      return c;
   }//End encodeLR()

   /**
    * Method encode a character from RIGHT to LEFT Then encodes a character
    * from RIGHT to LEFT Otherwise returns the character as is
    *
    * @param c
    * @return
    */
   public char encodeRL(char c) {//character c is wired to going R->L
      if (Character.isLetter(c) && Character.isUpperCase(c)) {
         return leftToRightWiring[((indexOfRightCharacter(c) - position) + 26) % 26];
      }
      return c;
   }//End encodeRL()

   /**
    * Method gets Rotor POSITION
    *
    * @return
    */
   final int getPosition() { //package private and cannot be overridden
      return position;
   }//EndgetPosition()

   /*====================================================================
    ----METHODS AFTER THIS LINE ARE USEFUL FOR DEBUGGING AND VERIFICATION
    =====================================================================*/
   /**
    * Method gets LEFT to RIGHT wiring of ROTORS
    *
    * @return
    */
   public String getLeftToRightWiring() {
      return new String(leftToRightWiring);
   }

   /**
    * Method gets RIGHT to LEFT wiring of ROTORS
    *
    * @return
    */
   public String getRightToLeftWiring() {
      return new String(rightToLeftWiring);
   }

}//End Class Rotor
