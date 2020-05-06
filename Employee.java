

public class Employee extends Ticket{
	private int id;
	private int numVisits;
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the numVisits
	 */
	public int getNumVisits() {
		return numVisits;
	}
	/**
	 * @param numVisits the numVisits to set
	 */
	public void setTimes(int numVisits) {
		this.numVisits = numVisits;
	}
	
	public Employee(String movieName, String rating, int movieDay, int movieTime, 
			String format,  int id, int numVisits) {
		super(movieName,  movieDay, movieTime, format, rating, "Employee");
		this.id = id;
		this.numVisits = numVisits;
	}
	public int getId() {
		return id;
	}
	public double calculateTicketPrice() {
		price = 0;
		if(numVisits<2)
			price += 0;
		else {
			if(movieTime<18)
				price = 10.5/2;
			else if(getMovieTime()>=18)
				price = 13.5/2;
			switch(format) {
			case IMAX:
				price += 3/2;
				break;
			case THREE_D:
				price += 2.5/2;
				break;
			case NONE:
				price += 0;
				break;
			}
		}
		//System.out.println(price);
		price = price + price * 0.096;
		setPrice(price);
		return price;
		
	}
	public String toString() {
		String toString = "EMPLOYEE-" + id;
		toString += super.toString();
		return toString;	
	}	
}
