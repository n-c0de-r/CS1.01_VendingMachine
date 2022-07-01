
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * GUI for the Machine, based on the former labs.
 * 
 * @author n-c0de-r
 * @version 14.12.2021
 */
public class GUI implements ActionListener {

	private TicketMachine machine;
	private JFrame frame;
	private JTextField display;
	private JButton cancel, discount, empty, price;
	private JPanel panel;
	private JLabel message;

	/**
	 * Basic constructor for any Ticket Machine. Takes a Ticket Machine as a
	 * parameter.
	 */
	public GUI(TicketMachine ticketMachine) {
		machine = ticketMachine;
		makeFrame();
		frame.setVisible(true);
	}

	/**
	 * Simple constructor for a standard Ticket machine. Overload constructor,
	 * passes on a new standard Ticket Machine.
	 */
	public GUI() {
		this(new TicketMachine());
	}

	/**
	 * Set the visibility of the interface.
	 * 
	 * @param visible true if the interface is to be made visible, false otherwise.
	 */
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	/**
	 * Creates the GUI Frame
	 */
	private void makeFrame() {
		frame = new JFrame(machine.getProvider() + " Ticket Machine");

		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.setLayout(new BorderLayout(8, 8));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		display = new JTextField("" + (double) ((machine.getAmount()) / 100) + "0 €");
		contentPane.add(display, BorderLayout.NORTH);
		display.setEditable(false);

		JPanel buttonPanel = new JPanel(new GridLayout(5, 3));

		price = new JButton("Show price");
		price.addActionListener(this);
		buttonPanel.add(price);
		price.setEnabled(true);
		discount = new JButton("Set discount");
		discount.addActionListener(this);
		buttonPanel.add(discount);
		discount.setEnabled(false);
		empty = new JButton("Empty machine");
		empty.addActionListener(this);
		buttonPanel.add(empty);
		empty.setEnabled(false);

		addButton(buttonPanel, "50 €");
		addButton(buttonPanel, "20 €");
		addButton(buttonPanel, "10 €");

		addButton(buttonPanel, " 5 €");
		addButton(buttonPanel, " 2 €");
		addButton(buttonPanel, " 1 €");

		addButton(buttonPanel, "50ct");
		addButton(buttonPanel, "20ct");
		addButton(buttonPanel, "10ct");

		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		buttonPanel.add(cancel);
		cancel.setEnabled(true);
		addButton(buttonPanel, " ");
		addButton(buttonPanel, "Buy Ticket");

		contentPane.add(buttonPanel, BorderLayout.CENTER);

		panel = new JPanel();
		message = new JLabel("For machine sercive push empty button 3x");
		message.setPreferredSize(new Dimension(300, 100));
		panel.add(message);
		contentPane.add(panel, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
	}

	/**
	 * Performs an action according to the String labeling a button.
	 * 
	 * @param event The event causing the action, a button click.
	 */
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		String upStr = "";
		String lowStr = "";

		if (command.equals(" ")) {
			if (display.getText().startsWith("Reduce price by: ")) {
				int discount = Integer.valueOf(display.getText().substring(display.getText().length() - 2));
				if (machine.getPrice() > discount) {
					machine.discount(discount);
					lowStr = "New price is " + (double) ((machine.getPrice()) / 100.0) + "0 €";
				}
				display.setEditable(false);
			} else {
				machine.increaseService(1);
			}
			if (machine.checkService() == 3) {
				display.setEditable(true);
				upStr = "000000";
				lowStr = "Please enter service PIN, then chose option.";
				discount.setEnabled(true);
				empty.setEnabled(true);
			}
		}

		else {
			display.setEditable(false);
			discount.setEnabled(false);
			empty.setEnabled(false);
			machine.increaseService(-machine.checkService());
			if (command.equals("Show price")) {
				lowStr = "A ticket costs " + (double) ((machine.getPrice()) / 100.0) + "0 €";
			}

			else if (command.equals("Set discount")) {
				if (checkPin()) {
					upStr = "Reduce price by: ";
					lowStr = "Enter discount in cents. Then hit the empty button.";
					display.setEditable(true);
				} else {
					lowStr = "Wrong PIN entered!";
				}
			}

			else if (command.equals("Empty machine")) {
				if (checkPin()) {
					lowStr = "You got " + (double) ((machine.getTotal()) / 100) + "0 € out";
					machine.empty();
				} else {
					lowStr = "Wrong PIN entered!";
				}
				display.setEditable(false);
				empty.setEnabled(false);
				machine.increaseService(-machine.checkService());
				upStr = "" + (double) ((machine.getAmount()) / 100) + "0 €";
			}

			else if (command.equals("Cancel")) {
				machine.returnMoney(machine.getAmount());
				upStr = "" + (double) ((machine.getAmount()) / 100) + "0 €";
				lowStr = machine.prompt();
			}

			else if (command.equals("Buy Ticket")) {
				if (machine.getAmount() < machine.getPrice()) {
					machine.printTicket();
					lowStr = machine.prompt();
				} else {
					machine.printTicket();

					JFrame f = new JFrame("Ticket");
					f.setPreferredSize(new Dimension(250, 200));
					f.setLocationRelativeTo(null);

					// set panel
					JPanel p = (JPanel) f.getContentPane();

					// create a label
					JLabel l = new JLabel(machine.prompt());

					p.add(l);
					f.setVisible(true);
					f.pack();

					machine.returnMoney(machine.getAmount());
					lowStr = machine.prompt();
				}
				upStr = "" + (double) ((machine.getAmount()) / 100) + "0 €";
			}

			else {
				int num = 0;
				if (command.contains("€")) {
					num = (int) (Double.parseDouble(command.substring(0, command.length() - 2).trim()) * 100);
				} else {
					num = Integer.parseInt(command.substring(0, command.length() - 2).trim());
				}
				machine.insertMoney(num);
				upStr = "" + ((double) (machine.getAmount()) / 100) + "0 €";
			}
		}
		display.setText(upStr);
		message.setText(lowStr);
	}

	/**
	 * Add a button to the button panel.
	 * 
	 * @param panel      The panel to receive the button.
	 * @param buttonText The text for the button.
	 */
	protected void addButton(Container panel, String buttonText) {
		JButton button = new JButton(buttonText);
		button.addActionListener(this);
		panel.add(button);
	}

	private boolean checkPin() {
		return machine.checkPin() == Integer.parseInt(display.getText());
	}
}
