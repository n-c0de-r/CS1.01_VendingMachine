/**
 * WendingMachine models a naive wending machine that dispenses some snacks.
 * Git user n-c0de-r updated this naive machine to an improved one, for
 * showcasing purposes. This improved machine only accepts certain coins and
 * will return inappropriate amounts and set prices right as n-c0de-r
 * implemented all guards needed to solve that. Compared to the original
 * machine, this has a few extra methods to do so correctly. The price of a
 * snack is specified via the constructor. This can still be improved!
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @author n-c0de-r
 * @version 2023.02.05
 * 
 * Updated to have a GUI. This can still be improved!
 */
public class WendingMachine {
    // Name of the snack producing company.
    private String brand;
    // The price of a snack from this machine.
    private int price;
    // The amount of money entered by a customer so far.
    private int balance;
    // The total amount of money collected by this machine.
    private int total;
    // The total amount of snacks sold.
    private int snackNumbers;

    // inserted coins
    private int euroCoin2 = 0;
    private int euroCoin1 = 0;
    private int centCoin50 = 0;
    private int centCoin20 = 0;
    private int centCoin10 = 0;

    private int messageNr = 0;

    private int servicePin = 170621;
    private int serviceCommand = 0;

    /**
     * Create a machine that dispenses snack of the given price
     * from a certain brand of a name giving company.
     * 
     * @param name  The brand of the producing company.
     * @param cost  A single snack cost in Euro cents.
     */
    public WendingMachine(String name, int cost) {
        brand = name.toUpperCase();
        price = checkAmount(cost);
        balance = 0;
        total = 0;
        snackNumbers = 0;
    }

    /**
     * Create a machine that dispenses snacks of the given price.
     * Overload constructor, just with the price.
     * 
     * @param cost  The price for snacks of this machine.
     */
    public WendingMachine(int cost) {
        this("BlueJ", cost);
    }

    /**
     * Create a machine without parameters.
     * Overload constructor, price is set to 300 cents.
     */
    public WendingMachine() {
        this(300);
    }

    /**
     * Return the name of the brand.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Return the price of a snack.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Return the amount of money already inserted for the next snack.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Method get return the value of the total amount inserted.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Method get return the value of the total snacks sold.
     */
    public int getSnackNumbers() {
        return snackNumbers;
    }

    /**
     * Reduce price by the given amount.
     * @param amount    The amount to reduce the price by.
     */
    public void discount(int amount) {
        price = price - checkAmount(amount);
    }

    /**
     * This method empties the wending machine.
     */
    public void empty() {
        total = 0;
    }

    /**
     * Receive an amount of money from a customer.
     * @param amount    The amount inserted from a user.
     */
    public void insertMoney(int amount)
    {
        balance = balance + checkAmount(amount);
    }

    /**
     * This method shows a prompt to the user, if the amount is not correct.
     */
    public String prompt()
    {
        String str = "";
        // Prompts a warning.
        if (messageNr == 10)
        {
            str = "<html>---------- > WARNING < ----------<br>"
            + "There is no money in the machine.<br>"
            + "---------- ---------- -----------<html>";
        }

        if (messageNr == 11)
        {
            str = "<html>---------- > INFORMATION < -----------<br>"
            + "You get the following coins back:<br>";
            if (euroCoin2 != 0)
            {
                str += "" + euroCoin2 + "x of 2-Euro-Coins.<br>";
            }
            if (euroCoin1 != 0)
            {
                str += "" + euroCoin1 + "x of 1-Euro-Coins.<br>";
            }
            if (centCoin50 != 0)
            {
                str += "" + centCoin50 + "x of 50-Cent-Coins.<br>";
            }
            if (centCoin20 != 0)
            {
                str += "" + centCoin20 + "x of 20-Cent-Coins.<br>";
            }
            if (centCoin10 != 0)
            {
                str += "" + centCoin10 + "x of 10-Cent-Coins.<br>";
            }
            str += "---------- ---------- -----------<html>";
            centCoin10 = 0;
            centCoin20 = 0;
            centCoin50 = 0;
            euroCoin1 = 0;
            euroCoin2 = 0;
            euroCoin2 = 0;
        }

        if (messageNr == 12)
        {
            str = "<html>---------- > WARNING < -----------<br>"
            + "The inserted amount is not enough.<br>"
            + "You have inserted only " + balance + " cents,<br>"
            + "but a snack costs " + price
            + " cents.<br>"
            + "---------- ---------- -----------<html>";
        }

        if (messageNr == 13)
        {
            // Simulate the dispensing of a snack.
            str += "<html>---------- > PRINTING < -----------<br>";
            Snack snack = new Snack(brand, price, snackNumbers);
            str += snack.printSnackInfo() + "<html>";
        }

        if (messageNr < 0)
        {
            str += "<html>---------- > WARNING < -----------<br>"
            + "You may not enter negative values!<br>"
            + "There are now negative currency coins.<br>"
            + "The amount will be converted to positive now.<br>"
            + "---------- ---------- -----------<html>";
        }

        if (messageNr > 0 && messageNr < 10)
        {
            str = "<html>---------- > INFORMATION < -----------<br>"
            + "This machine accepts 10ct as the minimum amount.<br>"
            + "All amounts will be cut down to the next 10ct value.<br>"
            + "---------- ---------- -----------<html>";
        }

        if (messageNr >= 10000)
        {
            str += "---------- > WARNING < -----------<br>";
            str += "You may not enter more than 100€ worth of cents!<br>";
            str += "All amounts will be cut down to that limit value.<html>";
        }
        messageNr = 0;
        return str;
    }

    /**
     * Return the inserted balance in the least possible coin numbers.
     * 
     * @param amount    The amount of money to return.
     */
    public int returnMoney(int amount)
    {
        // IF the balance is zero, print out an appropriate message.
        if (amount == 0)
        {
            messageNr = 10;
        }
        else
        {
            // While the balance is not empty, loop through
            // and get the individual number of coins.
            convertCoins(amount);
            // Print out the number of coins returned.
            messageNr = 11;
        }
        balance = 0;
        return amount;
    }

    /**
     * Show the current snack price as a string in the console.
     * 
     * @return  A string containing the price to print in the GUI.
     */
    public String showPrice()
    {
        String str = "";
        str += "The price of a snack is " + price + " cents.\r\n";
        return str;
    }

    /**
     * Dispense a snack.
     * Update the total collected and reduce the balance by the price.
     */
    public void dispenseSnack()
    {
        // Guard against not enough balance.
        if (balance >= price)
        {
            // Increase the number of snacks sold.
            snackNumbers = snackNumbers + 1;

            messageNr = 13;

            // Update the total collected with the balance.
            total = total + price;
            // Clear the balance.
            balance = balance - price;

        }
        else
        {
            messageNr = 12;
        }
    }

    /**
     * Checks if the inserted amount is valid.
     * 
     * @param amount    The amount to check.
     * @return          The corrected amount.
     */
    private int checkAmount(int amount)
    {
        // If users enter negative values, catch that and convert it.
        if (amount < 0)
        {
            // Convert the negative amount to positive.
            amount = Math.abs(amount);
        }

        // If the amount is not fully divisible by 10, correct the amount.
        if (amount % 10 != 0)
        {
            // Reduce the amount by the excess residue.
            messageNr = returnMoney(amount % 10);
            amount = amount - amount % 10;

            // If you insert too much money!
            if (amount > 10000)
            {
                returnMoney(amount - 10000);
                amount = 10000;
            }
        }

        // Return the corrected value to the user.
        return amount;
    }
    
    /**
     * Returns this machines service pin.
     * 
     * @return  The service pin of the machine.
     */
    public int checkPin()
    {
        return servicePin;
    }

    public void increaseService(int number)
    {
        serviceCommand += number;
    }

    public int checkService()
    {
        return serviceCommand;
    }

    /**
     * Converts any cent input in coins accordingly.
     * 
     * @param amount    Amount in cents inserted.
     */
    private void convertCoins(int amount)
    {
        // Better solution, no loop
        euroCoin2 = amount / 200;
        amount = amount % 200;
        euroCoin1 = amount / 100;
        amount = amount % 100;
        centCoin50 = amount / 50;
        amount = amount % 50;
        centCoin20 = amount / 20;
        amount = amount % 20;
        centCoin10 = amount / 10;
        amount = amount % 10;
    }
    
}
