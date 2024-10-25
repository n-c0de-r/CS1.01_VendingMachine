import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Snack represents a snack dispensed by the VendingMachine.
 * Being it's own object, could use some own methods, too.
 *
 * @author n-c0de-r
 * @version 2023.02.05
 */

public class Snack {
    private String brandName;
    private int snackPrice;
    private int serialNumber;

    /**
     * Constructor of a Sweet.
     * 
     * @param brand     Brand of the snack.
     * @param price     Price of a single sweet.
     * @param number    This snack's serial number.
     */
    public Snack(String brand, int price, int number) {
        brandName = brand;
        snackPrice = price;
        serialNumber = number;
    }

    /**
     * Simulate dispensing a Snack, output to the console.
     */
    public String printSnackInfo() {
        String str = "##################<br>"
        + "# You got a " + brandName + " Snack <br>"
        + "# for " + snackPrice + " cents.<br>"
        + "##################" + "<br><br>"
        + "# Serial number: " + brandName + "_" + serialNumber + "<br><br>";

        // Set the date format.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
        // Get the current time and add 2 hours to it, the snack is best before that.
        str += "Best before: " + LocalDateTime.now().plusHours(2).format(format) + "<br>" + "##################";

        return str;
    }

}
