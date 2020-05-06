

public class MoviePass extends Ticket{
	private int id;
	private int times;
	private int numMoviesToday;
	private int numThisMovie;
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the times
	 */
	public int getTimes() {
		return times;
	}
	/**
	 * @param times the times to set
	 */
	public void setTimes(int times) {
		this.times = times;
	}
	
	/**
	 * @return the numberOfMovieToday
	 */
	public int getNumMoviesToday() {
		return numMoviesToday;
	}
	/**
	 * @param numMoviesToday the numMoviesToday to set
	 */
	public void setNumMoviesToday(int numMoviesToday) {
		this.numMoviesToday = numMoviesToday;
	}
	/**
	 * @return the numThisMovie
	 */
	public int getNumThisMovie() {
		return numThisMovie;
	}
	/**
	 * @param numThisMovie the numThisMovie to set
	 */
	public void setRepeat(int numThisMovie) {
		this.numThisMovie = numThisMovie;
	}
	public MoviePass(String movieName, String rating, int movieDay, int movieTime,  
			String format, int id, int times, int numMoviesToday, int numThisMovie) {
		super(movieName, movieDay, movieTime,  format, rating, "MoviePass");
		this.id = id;
		this.times = times;
		this.numMoviesToday = numMoviesToday;
		this.numThisMovie = numThisMovie;
	}
	public int getId() {
		return id;
	}
	public double calculateTicketPrice() {
		price = 0;
		if (numMoviesToday < 1 && numThisMovie < 1 && format == Format.NONE){
			if(times<1)
				price = 9.99;
			else
			price = 0;
		}
		else {
			if(movieTime<18)
				price = 10.5;
			else if(getMovieTime()>=18)
				price = 13.5;
			switch(format) {
			case IMAX:
				price += 3;
				break;
			case THREE_D:
				price += 2.5;
				break;
			case NONE:
				break;
			}
			price = price + price * 0.096;
		}
		//System.out.println(price);
		
			setPrice(price);
			return price;
		
	}
	public String toString() {
		String toString = "MOVIEPASS-" + id;
		toString += super.toString();
		return toString;	
	}	
}
