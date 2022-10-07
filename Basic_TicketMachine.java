/**
 * TicketMachine models a naive ticket machine that issues
 * flat-fare tickets.
 * The price of a ticket is specified via the constructor.
 * It is a naive machine in the sense that it trusts its users
 * to insert enough money before trying to print a ticket.
 * It also assumes that users enter sensible amounts.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @author n-c0de-r
 * @version 2021.07.20
 */
public class Basic_TicketMachine
{
    // The price of a ticket from this machine.
    private int price;
    // The amount of money entered by a customer so far.
    private int balance;
    // The total amount of money collected by this machine.
    private int total;
    // Assigment 8, The total amount of sold tickets.
    private int soldTickets;

    /**
     * Create a machine that issues tickets of the given price.
     * Note that the price must be greater than zero, and there
     * are no checks to ensure this.
     */
    public Basic_TicketMachine(int cost)
    {
        // Assignment 1
        // int prince = cost;
        price = cost;
        balance = 0;
        total = 0;
    }

    /**
     * Assignment 2, duplicate constructor
     */
    // public Basic_TicketMachine(int cost)
    // {
    // int prince = cost;
    // balance = 0;
    // total = 0;
    // }

    /**
     * Return the price of a ticket.
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * Return the amount of money already inserted for the
     * next ticket.
     */
    public int getBalance()
    {
        return balance;
    }

    /**
     * Assignment 4
     * Changed name
     */
    public int getAmount()
    {
        /* Does the same as above.
         * The return variable name and the function name
         * are independent from each other. They don't
         * need to be that same to work.
         * It's VERY advised to use a similar or even the
         * same name if possible, it conveys the information
         * better of what is returned - for consistency and 
         * readability of code.*/
        return balance;
    }

    /**
     * Assigment 5
     * Method get return the value of the total amount inserted.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Assigment 7
     * Reduce price by the given amount.
     */
    public void discount(int amount) {
        /* Right side: get the old price and reduce it by amount 'passed in
         * as a paramter in () round parentheses. ft side: store the result
         * of the right side back into the variable named 'price'.*/

        //price -= amount;
        // This line does the same as above.

        /* This is shorter but not necessarily better, be very careful
         * when using these. Use it when you are skilled enough to
         * understand its difference. */  
        price = price - amount;
    }

    /**
     * Assigment 8
     * This method shows a prompt to the user, if the amount is not correct.
     */
    public String prompt() {
        return "";
    }

    /**
     * Assignment 9
     * Method get return the value of the total tickets sold.
     */
    public int getSoldTickets() {
        return soldTickets;
    }

    /**
     * Assignment 10
     * Prints a message with the given price.
     */
    public void showPrice() {
        System.out.println("The price of a ticket is " + price + " cents");
        /* The 'price' variable needs to be outside the quote signs so its
         * value can be accessed and added to the string to print. */
    }

    /**
     * Assigment 11
     * Empties the machine.
     */
    public void empty() {
        System.out.println("You get " + total + " cents from the machine");
        total = 0; 
        /* Alternatively one could use the method form the 'for the bored'
         * task to convert the 'total' cent amount to correct coins and
         * print a message.*/
    }

    /**
     * Receive an amount of money from a customer.
     */
    public void insertMoney(int amount)
    {
        balance = balance + amount;
    }

    /**
     * Print a ticket.
     * Update the total collected and
     * reduce the balance by the given price (for the bored).
     */
    public void printTicket() {
        // Simulate the printing of a ticket.
        System.out.println("##################");
        System.out.println("# The HTW Line");
        System.out.println("# Ticket");
        System.out.println("# " + price + " cents.");

        // Assigment 1 change of the fourth statement
        System.out.println("# " + "price" + " cents.");
        /* Instead of printing the given price as a number taken
         * from the variable named 'price', this would print the WORD
         * 'price' as it is.
         */
        System.out.println("# price cents.");
        // The former line results in the same 'wrong' output.

        System.out.println("##################");
        System.out.println();

        // Update the total collected with the balance.
        total = total + balance; 

        // Assignment 8, Increase the number of sold tickets by 1
        soldTickets = soldTickets + 1;

        /* soldTickets += 1;
         * soldTickets++;
         * These two lines do the same. Use them with caution
         * and when you are sure and experienced enough!
         */

        // Clear the balance.
        // For the bored: needs to update the balance correctly!
        balance = balance - price;
        // For the bored: return the correct number of coins...
        returnMoney(balance);
        // ... then set the balance to zero, as the machine is empty.
        balance = 0;
    }

    /**
     * For the bored - Assignment 12
     * Converts the balance to each of the coins.
     * Prints the appropriate message.
     * Can be solved in many ways. Just an example!
     * 
     * @param amount Cents to convert.
     */
    private void returnMoney(int amount) {
        if (amount == 0) {
            // IF there is NO money to convert, print a message
            System.out.println("There's no money to return to you.");
            // Then stop the execution of this conversion immediately.
            return;
        }

        // Otherwise, there is money and we need to initialize the counter
        int euro02 = 0;
        int euro01 = 0;
        int cent50 = 0;
        int cent20 = 0;
        int cent10 = 0;

        euro02 = amount / 200; // Converts the amount to number of coins.
        amount = amount % 200; // Get the next smaller rest amount.
        euro01 = amount / 100; // Repeat the steps...
        amount = amount % 100;
        cent50 = amount / 50;
        amount = amount % 50;
        cent20 = amount / 20;
        amount = amount % 20;
        cent10 = amount / 10;
        amount = amount % 10; // ...until only amounts less than 10cts left.

        // When done print a message showing the coins
        // The first line will always get set, ...
        String str = "You get the following coins back:\n"; 
        if (euro02 != 0) {
            str += euro02 + "x of 2-Euro-Coins.\n"; //... all others
        }
        if (euro01 != 0) {
            str += euro01 + "x of 1-Euro-Coins.\n"; //... get added
        }
        if (cent50 != 0) {
            str += cent50 + "x of 50-Cent-Coins.\n"; //... if they
        }
        if (cent20 != 0) {
            str += cent20 + "x of 20-Cent-Coins.\n"; //... are needed.
        }
        if (cent10 != 0) {
            str += cent10 + "x of 10-Cent-Coins.\n"; // '\n' means
        }
        if (amount < 10) {
            str += "and " + amount + " single cents."; // a line break.
        }
        System.out.println(str); // Print the whole block.
    }
}
