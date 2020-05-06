
import java.text.NumberFormat;

abstract class Ticket {
	protected Format format;  //(IMAX, 3D, NONE)
	protected Rating rating;  //(G, PG, PG13, R, or NR)
	protected double price;
	protected String type; //(Adult, Child, Employee, MoviePass )
	protected String movieName;
	protected int movieDay;
	protected int movieTime;
	private NumberFormat currencyFormat=NumberFormat.getCurrencyInstance();
	
	/**
	 * No arg constructor
	 */
	public Ticket() {}
	
	/**
	 * 
	 * @param movieName
	 * @param movieTime
	 * @param movieDay
	 * @param format
	 * @param rating
	 * @param type
	 */
	public Ticket(String movieName, int movieDay, int movieTime,  String format, String rating, String type) {
		this.movieName = movieName;
		this.movieTime = movieTime;
		this.movieDay = movieDay;
		switch (format) {
		case "IMAX":
			this.format = Format.IMAX;
			break;
		case "3D":
			this.format = Format.THREE_D;
			break;
		case "NONE":
			this.format = Format.NONE;
			break;
		}
		switch (rating) {
		case "G":
			this.rating = Rating.G;
			break;
		case "PG":
			this.rating = Rating.PG;
			break;
		case "PG13":
			this.rating = Rating.PG13;
			break;
		case "R":
			this.rating = Rating.R;
			break;
		case "NR":
			this.rating = Rating.NR;
			break;
		}
		this.type = type;
	}
	
	public abstract double calculateTicketPrice();
	public abstract int getId();
	
	
	
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @param movieName the movieName to set
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	/**
	 * @param movieDay the day to set
	 */
	public void setDay(int movieDay) {
		this.movieDay = movieDay;
	}
	/**
	 * @param movieTime the movieTime to set
	 */
	public void setMovieTime(int movieTime) {
		this.movieTime = movieTime;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(Format format) {
		this.format = format;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	/**
	 * @return the movieName
	 */
	public String getMovieName() {
		return movieName;
	}
	/**
	 * @return the movieDay
	 */
	public int getDay() {
		return movieDay;
	}
	/**
	 * @return the movieTime
	 */
	public int getMovieTime() {
		return movieTime;
	}
	/**
	 * @return the format
	 */
	public Format getFormat() {
		return format;
	}
	/**
	 * @return the rating
	 */
	public Rating getRating() {
		return rating;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  "Format: " + format +
				" Movie: " + movieName +
				" Rating: " + rating +
				" Day: " + movieDay +
				" Time: " + movieTime +
				" Price: " + currencyFormat.format(price);
	}
	
	
}
