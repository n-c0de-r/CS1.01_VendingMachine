import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Ticket represents a ticket printed by the TicketMachine.
 * Being it's own object, could use some own methods, too.
 *
 * @author n-c0de-r
 * @version 2021.07.21
 */

public class Ticket {
	
	/**
	 * Constructor of a Ticket.
	 * 
	 * @param name	Name of the provider.
	 * @param price	Price of a single ticket.
	 * @param ticketnumber	This ticket's number.
	 */
	public Ticket (String name, int price, int ticketnumber) {
		// Simulate a ticket, output to the console.
		System.out.println("##################");
		System.out.println("# The " + name + " Line");
		System.out.println("# Ticket");
		System.out.println("# " + price + " cents.");
		System.out.println("##################");
		System.out.println("# Ticket number: "+ name + "_" + ticketnumber);
		
		//Set the date format.
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
		// Get the current time and add 2 hours to it, ticket is valid that long.
		System.out.println("# Valid until: " + LocalDateTime.now().plusHours(2).format(format));
		System.out.println("##################");
	}
}
