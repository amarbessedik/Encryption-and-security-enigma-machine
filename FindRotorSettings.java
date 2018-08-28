/**
 * FindRotorSettings Class: Needed to save & return the Rotor positions that are
 * suitable to decrypt an entire file
 * 
 * @author Amar-cs
 */
final class FindRotorSettings {
   //There are 3 Rotor Positions at a given time 
   //- only 3 values needed to decrypt the file
   private static final int size = 3;
   protected static int[] settingsForEnglish = new int[size];

   public FindRotorSettings() {
   }

   /**
    *
    * @param a
    * @param b
    * @param c
    */
   public void saveRotorSettingsForEnglish(int a, int b, int c) {
      settingsForEnglish[0] = a;
      settingsForEnglish[1] = b;
      settingsForEnglish[2] = c;
   }

   /**
    *
    * @return
    */
   public int[] getRotorSettingsForEnglish() {
      return new int[]{settingsForEnglish[0], settingsForEnglish[1], settingsForEnglish[2]};
   }

   /*====================================================================
    ----METHODS AFTER THIS LINE ARE USEFUL FOR DEBUGGING AND VERIFICATION
    =====================================================================*/
   /**
    *
    * @param r1
    * @param r2
    * @param r3
    * @return
    */
   public String getRotorPositions(Rotor r1, Rotor r2, Rotor r3) {
      return "\n\nROTOR POSITIONS:"
             + "\nROTOR1(LEFT): " + r1.getPosition()
             + "\nROTOR2(MIDDLE): " + r2.getPosition()
             + "\nROTOR3(RIGHT): " + r3.getPosition()
             + "\n\n";
   }

   /**
    *
    * @param r
    * @return
    */
   private String getRotorWirings(Rotor r) {
      return "LEFT --> RIGHT: " + r.getLeftToRightWiring()
             + "\nRIGHT --> LEFT: " + r.getRightToLeftWiring();
   }

   /**
    *
    * @param r1
    * @param r2
    * @param r3
    * @param rf
    * @return
    */
   public String getAllRotorSettings(Rotor r1, Rotor r2, Rotor r3, Reflector rf) {
      return "ROTOR1(LEFT): \nWIRINGS: \n" + getRotorWirings(r1)
             + "\nPOSITION: " + r1.getPosition() + "\n"
             + "\nROTOR2(MIDDLE): \nWIRINGS: \n" + getRotorWirings(r2)
             + "\nPOSITION: " + r2.getPosition() + "\n"
             + "\nROTOR3(RIGHT): \nWIRINGS: \n" + getRotorWirings(r3)
             + "\nPOSITION: " + r3.getPosition() + "\n"
             + "\nREFLECTOR: \nWIRINGS: \n" + getRotorWirings(rf)
             + "\n";
   }

}
