

public class Child extends Ticket{
	/**
	 * no arg constructor
	 */
	public Child(){
	}
	
	/**
	 * constructor
	 * @param movieName
	 * @param movieTime
	 * @param movieDay
	 * @param format
	 * @param rating
	 */
	public Child(String movieName, String rating, int movieDay, int movieTime,  
			String format) {
		super(movieName, movieDay, movieTime, format, rating, "Child");
	}
	
	/**
	 * return ID
	 */
	public int getId() {
		return -1;
	}
	
	/**
	 * Calculate ticket price
	 */
	public double calculateTicketPrice() {
		price = 0;
		if(movieTime<18)
			price = 5.75;
		else if(getMovieTime()>=18)
			price = 10.75;
		switch(format) {
		case IMAX:
			price += 2;
			break;
		case THREE_D:
			price += 1.5;
			break;
		case NONE:
			break;
		}
		//System.out.println(price);
		price = price + price * 0.096;
		setPrice(price);
		return price;
	}
	public String toString() {
		String toString = "CHILD ";
		toString += super.toString();
		return toString;	
	}	
}
