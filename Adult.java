

public class Adult extends Ticket{
	
	/**
	 * no arg constructor
	 */
	public Adult(){
	}
	
	/**
	 * constructor
	 * @param movieName
	 * @param movieTime
	 * @param movieDay
	 * @param format
	 * @param rating
	 */
	public Adult(String movieName, String rating, int movieDay, int movieTime, 
			String format ) {
		super(movieName, movieDay, movieTime, format, rating, "Adult");
	}
	
	/**
	 * return ID
	 */
	public int getId() {
		return -1;
	}
	
	/**
	 * calculate price
	 */
	public double calculateTicketPrice() {
		price = 0;
		if(movieTime<18)
			price = 10.5;
		else if(movieTime>=18)
			price = 13.5;
		switch(format) {
		case IMAX:
			price += 3;
			break;
		case THREE_D:
			price += 2.5;
			break;
		case NONE:
			price += 0;
			break;
		}
		//System.out.println(price);
		price = price + price * 0.096;
		setPrice(price);
		return price;
	}
	
	/** to string
	 * 
	 */
	public String toString() {
		String toString = "ADULT ";
		toString += super.toString();
		return toString;	
	}	
} 
