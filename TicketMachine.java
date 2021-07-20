/**
 * TicketMachine models a naive ticket machine that issues
 * flat-fare tickets. Git user n-c0de-r uprated this naive
 * machine to an improved one, for showcase purposes.
 * This improved machine only accepts certain coins and
 * will return inapropriate amounts and set prices right
 * as n-c0de-r implemented all guards needed to solve that.
 * Compared to the original machine, this has a few extra
 * methods to do so correctly.
 * The price of a ticket is specified via the constructor.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2021.07.20
 */
public class TicketMachine
{
	// The price of a ticket from this machine.
	private int price;
	// The amount of money entered by a customer so far.
	private int balance;
	// The total amount of money collected by this machine.
	private int total;
	// The total amount of tickets sold.
	private int ticketnumbers;

	/**
	 * Create a machine that issues tickets of the given price.
	 * Note that the price must be greater than zero, and there
	 * are no checks to ensure this.
	 */
	public TicketMachine(int cost)
	{
		price = checkAmount(cost);
		balance = 0;
		total = 0;
		ticketnumbers = 0;
	}
	
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
	public int getAmount()
	{
		return balance;
	}
	
	/** 
	 * Method get return the value of the total amount inserted.
	 */
	public int getTotal()
	{
		return total;
	}
	
	/** 
	 * Method get return the value of the total tickets sold.
	 */
	public int getTicketNumbers()
	{
		return ticketnumbers;
	}
	
	/**
	 * Reduce price by the given amount.
	 */
	public void discount (int amount)
	{
		price = price - checkAmount(amount);
	}
    
	/**
	 * This method empties the ticket machine.
	 */
	public void empty()
	{
		total = 0;
	}
	
	/**
	 * Receive an amount of money from a customer.
	 */
	public void insertMoney(int amount)
	{
		balance = balance + checkAmount(amount);
	}
	
	/**
	 * This method shows a prompt to the user, if the amount is not correct.
	 */
	public void prompt()
	{
		// Prompts a warning.
		System.out.println("Please insert the correct amount of money.");
	}
	
	/**
	 * Return the inserted balance in the least possible coin numbers.
	 */
	public void returnMoney()
	{
		// Initialize coin counts.
		int euroCoin2 = 0;
		int euroCoin1 = 0;
		int centCoin50 = 0;
		int centCoin20 = 0;
		int centCoin10 = 0;
		
		// IF the balance is zero, print out an appropriate message.
		if (balance == 0) {
			System.out.println("There is no money in the machine.");
		} else {
			// While the balance is not empty, loop through
			// and get the individual number of coins.
			while (balance > 0) {
				if (balance >= 200) {
					balance = balance - 200;
					euroCoin2 = euroCoin2 + 1;
				} else if (balance >= 100) {
					balance = balance - 100;
					euroCoin1 = euroCoin1 + 1;
				} else if (balance >= 50) {
					balance = balance - 50;
					centCoin50 = centCoin50 + 1;
				} else if (balance >= 20) {
					balance = balance - 20;
					centCoin20 = centCoin20 + 1;
				} else if (balance >= 10) {
					balance = balance - 10;
					centCoin10 = centCoin10 + 1;
				} else if (balance < 10) {
					balance = 0;
				}
			}
			// Print out the number of coins returned.
			System.out.println("You get the following coins back:");
			if (euroCoin2 != 0) {
				System.out.println("" + euroCoin2 + "x of 2-Euro-Coins.");
			}
			if (euroCoin1 != 0) {
				System.out.println("" + euroCoin1 + "x of 1-Euro-Coins.");
			}
			if (centCoin50 != 0) {
				System.out.println("" + centCoin50 + "x of 50-Cent-Coins.");
			}
			if (centCoin20 != 0) {
				System.out.println("" + centCoin20 + "x of 20-Cent-Coins.");
			}
			if (centCoin10 != 0) {
				System.out.println("" + centCoin10 + "x of 10-Cent-Coins.");
			}
		}
	}
	
	/**
	 * Show the current ticket price as a string in the console.
	 */
	public void showPrice()
	{
		System.out.println("The price of a ticket is " + price + " cents.");
	}
	
	/**
	 * Print a ticket.
	 * Update the total collected and
	 * reduce the balance by the price.
	 */
	public void printTicket()
	{
		 //Guard against not enough balance.
		if (balance >= price) {
			// Simulate the printing of a ticket.
			System.out.println("##################");
			System.out.println("# The BlueJ Line");
			System.out.println("# Ticket");
			System.out.println("# " + price + " cents.");
			System.out.println("##################");
			System.out.println();
			
			// Increase the number of tickets sold.
			ticketnumbers = ticketnumbers + 1;
			// Print the current ticket number, too.
			System.out.println("This ticket's number is " + ticketnumbers);
			
			// Update the total collected with the balance.
			total = total + balance;
			// Clear the balance.
			balance = balance - price;
			
		} else {
			System.out.println("The inserted amount is not enough.");
			System.out.println("You have inserted only " + balance + " cents,");
			System.out.println("but a ticket costs " + price + " cents.");
		}
	}
	
	/**
	 * 
	 */
	private int checkAmount(int amount)
	{
		// If the amount is not fully divisible by 10, correct the amount.
		if (amount % 10 != 0) {
			System.out.println("This machine accepts 10ct as the minimum amount.");
			System.out.println("All amounts will be cut down to the next 10ct value.");
			System.out.println("You get " + amount % 10 + " cents back.");
			// Reduce the amount by the excess residue.
			amount = amount - amount % 10;
		}
		return amount;
	}
}
