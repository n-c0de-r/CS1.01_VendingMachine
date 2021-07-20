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
		price = cost;
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
	 * method get return the value of the total amount inserted.
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
		price = price - amount;
	}
    
	/**
	 * Receive an amount of money from a customer.
	 */
	public void insertMoney(int amount)
	{
		balance = balance + amount;
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
	 * Print a ticket.
	 * Update the total collected and
	 * reduce the balance to zero.
	 */
	public void printTicket()
	{
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
		balance = 0;
	}
}
