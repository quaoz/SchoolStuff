package tasks.calendar;

public class Month {
	private final String name;
	private final int daysInTheMonth;
	private final int[][] days;

	Month(String name, int daysInTheMonth) {
		this.name = name;
		this.daysInTheMonth = daysInTheMonth;
		int weeks = this.daysInTheMonth / 7;

		if (this.daysInTheMonth % 7 > 0) {
			weeks++;
		}

		this.days = new int[weeks][7];
	}

	public String getName() {
		return name;
	}

	public int getDaysInTheMonth() {
		return daysInTheMonth;
	}

	public int[][] getDays() {
		return days;
	}
}

