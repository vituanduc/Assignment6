import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieTicketManager extends java.lang.Object
implements MovieTicketManagerInterface {
	
	//field
	private java.util.ArrayList<Ticket> ticketList;
	private java.text.NumberFormat currencyFormat=NumberFormat.getCurrencyInstance();
	
	/**
	 * constructor
	 */
	public MovieTicketManager() {
		ticketList=new ArrayList<Ticket>();
	}

	/**
	 * 
	 */
	public double addTicket(String movieN, String rating, int d, int t, String f,
			String type, int id) {	
		Ticket ticket;
		double price = 0;
		switch(type) {
		case "Adult":
			ticket = new Adult(movieN, rating, d, t, f);
			ticketList.add(ticket);
			price = ticket.calculateTicketPrice();
			break;
		case "Child":
			ticket = new Child(movieN, rating, d, t, f);
			ticketList.add(ticket);
			price = ticket.calculateTicketPrice();
			break;
		case "Employee":
			int numVisits=numVisits(id);
			ticket=new Employee(movieN, rating, d, t, f, id, numVisits);
			ticketList.add(ticket);
			price = ticket.calculateTicketPrice();
			break;
		case "MoviePass":
			int numVisits1=numVisits(id);
			int numThisMovie=numThisMovie(id,movieN);
			int numMoviesToday=numMoviesToday(id, d);
			ticket=new MoviePass(movieN, rating, d, t, f, id, numVisits1, 
					numMoviesToday, numThisMovie);
			ticketList.add(ticket);
			price = ticket.calculateTicketPrice();
			break;
		default:
			break;
		}
		return price;
	}

	/**
	 * 
	 */
	public ArrayList<String> get3DTickets() {
		ArrayList<String> three_DTicket=new ArrayList<String>();
		sortByDay();
		for(Ticket ticket: ticketList) {
			if (ticket.getFormat()==Format.THREE_D) {
				three_DTicket.add(ticket.toString());
			}
		}
		return three_DTicket;
	}

	/**
	 * 
	 */
	public ArrayList<String> getAllTickets() {
		ArrayList<String> allTicket=new ArrayList<String>();
		sortByDay();
		for(Ticket ticket: ticketList) {
				allTicket.add(ticket.toString());
		}
		return allTicket;
	}

	/**
	 * 
	 */
	public ArrayList<String> getMoviePassTickets() {
		ArrayList<String> moviePassTicket=new ArrayList<String>();
		sortById();
		for(Ticket ticket: ticketList) {
			if (ticket.getType().equals("MoviePass")) {
				moviePassTicket.add(ticket.toString());
			}
		}
		return moviePassTicket;
	}

	/**
	 * 
	 */
	public String monthlySalesReport() {
		int numAdultTicket = 0;
		int numChildTicket = 0;
		int numEmployeeTicket = 0;
		int numMoviePassTicket = 0;
		double salesAdultTicket = 0;
		double salesChildTicket = 0;
		double salesEmployeeTicket = 0;
		double salesMoviePassTicket = 0;
		double totalSales = 0;
		String report ="    Monthly Sales Report" + "\n\n";
		report += "       Sales           Number\n";
		for (Ticket ticket: ticketList) {
			totalSales += ticket.price;
			if(ticket.type.equals("Adult")) {
				salesAdultTicket += ticket.price;
				numAdultTicket ++;
			}
			else if(ticket.type.equals("Child")) {
				salesChildTicket += ticket.price;
				numChildTicket ++;
			}
			else if(ticket.type.equals("Employee")) {
				salesEmployeeTicket += ticket.price;
				numEmployeeTicket ++;
			}
			else if(ticket.type.equals("MoviePass")) {
				salesMoviePassTicket += ticket.price;
				numMoviePassTicket ++;
			}
		}
		report += "ADULT      " + currencyFormat.format(salesAdultTicket) + "     " + numAdultTicket;
		report += "\nCHILD      " + currencyFormat.format(salesChildTicket) + "     " + numChildTicket;
		report += "\nEMPLOYEE   " + currencyFormat.format(salesEmployeeTicket) + "     " + numEmployeeTicket;
		report += "\nMOVIEPASS  " + currencyFormat.format(salesMoviePassTicket) + "     " + numMoviePassTicket;
		report += "\n\n Total Monthly Sales: " + currencyFormat.format(totalSales);
		return report;
	}

	/**
	 * 
	 */
	public int numMoviesToday(int id, int date) {
		int numMoviesToday=0;
		for (Ticket ticket: ticketList) {
			if (ticket.getId()==id && ticket.getDay()==date)
				numMoviesToday++;
		}
		return numMoviesToday;
	}

	/**
	 * 
	 */
	public int numThisMovie(int id, String movie) {
		int numThisMovie=0;
		for (Ticket ticket: ticketList) {
			if (ticket.getId()==id && (ticket.getMovieName()).equals(movie))
				numThisMovie++;
		}
		return numThisMovie;
	}

	/**
	 * Returns the number of times this patron has visited the theater this month
	 */
	public int numVisits(int id) {
		int numVisits=0;
		for (Ticket ticket: ticketList) {
			if (ticket.getId()==id)
				numVisits++;
		}
		return numVisits;
	}

	/**
	 * 
	 */
	public void readFile(File file) throws FileNotFoundException {
		Scanner in=new Scanner(file);
		String nextLine, movieN, rating, f, type;
		String[] data;
		int d, t, id;
		id=-1;
		while (in.hasNext()) {
			nextLine=in.nextLine();
			data=nextLine.split(":");
			movieN=data[0];
			rating=data[1];
			d=Integer.parseInt(data[2]);
			t=Integer.parseInt(data[3]);
			f=data[4];
			type=data[5];
			if (type.equals("Employee")||type.equals("MoviePass"))
				id=Integer.parseInt(data[6]);
			addTicket(movieN, rating, d, t, f, type, id);
		}	
	}
	
	private void sortByDay() {
		Ticket temp;
		for(int i=1;i<ticketList.size();i++) {
			for(int j=i; j>0; j--) {
				if(ticketList.get(j).getDay()<ticketList.get(j-1).getDay()) {
					temp = ticketList.get(j-1);
					ticketList.set(j-1,ticketList.get(j));
					ticketList.set(j, temp);
				}
			}
		}
	}
	
	private void sortById() {
		Ticket temp;
		for(int i=1;i<ticketList.size();i++) {
			for(int j=i; j>0; j--) {
				if(ticketList.get(j).getId()<ticketList.get(j-1).getId()) {
					temp = ticketList.get(j-1);
					ticketList.set(j-1,ticketList.get(j));
					ticketList.set(j, temp);
				}
			}
		}
	}

	/**
	 * 
	 */
	public double totalSalesMonth() {
		double totalSalesMonth = 0;
		for(Ticket ticket: ticketList) {
			totalSalesMonth += ticket.getPrice();
		}
		return totalSalesMonth;
	}
}
