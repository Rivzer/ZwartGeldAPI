package be.rivzer.zwartgeld.Utils;

public class CheckInt {

    public static boolean CheckInt(String check){
        try {
            Long.parseLong(check);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
