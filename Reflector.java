/**
 * REFLECTOR CLASS: Reflector Class extends Rotor Class. 
 * It contains a constructor, provides the logic on how to encode a character
 * Its particularity, it does not move and only encodes from left to right
 *
 * @author Amar-cs
 */
public class Reflector extends Rotor {

   private char[] reflector = new char[max];

   /**
    * Constructor to build the REFLECTOR
    * @param s
    */
   public Reflector(String s) {//CONSTRUCTOR - INITIALIZATION
      super();
      initializeRL(reflector, s);//RIGHT TO LEFT WIRING
   }

   /**
    * Method encodes a character from RIGHT to LEFT
    * @param c
    * @return
    */
   @Override    
   public char encodeLR(char c){
      int index = indexOfLeftCharacter(c);
      return reflector[index];
   }//End encodeLR()
   
   //===================================================================
   //The following method useful for checking how the reflector is wired
   //===================================================================

   /**
    * returns right to left wiring of the reflector
    * @return
    */
   @Override
   public String getRightToLeftWiring() {
      return new String(reflector);
   }
}
