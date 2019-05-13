package view.handlers;

public class MagneticCardHandler {

    public static String[] parseCreditCardData(String card_data, Boolean verbose) {
        String[] card_array=card_data.split("\\%B|\\&");

        if (verbose) {
            for(int i=0; i < card_array.length; i++) {
                System.out.println(card_array[i]);
            }
        }
        card_array=new String[0];
        return card_array;
    }
}
