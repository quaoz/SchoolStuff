import java.util.Calendar;

public class Main {
	// Creates a calendar object
	private static final Calendar calendar = Calendar.getInstance();

	public static void main(String[] args) {
		// Creates an array with 12 Month elements
		Month[] months = new Month[12];

		// Creates the Month instances
		months[0] = new Month("January", 31);
		months[1] = new Month("February", 28);
		months[2] = new Month("March", 31);
		months[3] = new Month("April", 30);
		months[4] = new Month("May", 31);
		months[5] = new Month("June", 30);
		months[6] = new Month("July", 31);
		months[7] = new Month("August", 31);
		months[8] = new Month("September", 30);
		months[9] = new Month("October", 31);
		months[10] = new Month("November", 30);
		months[11] = new Month("December", 31);

		int count = 1;

		// Gets the current month and day
		final int currentMonth = calendar.get(Calendar.MONTH);
		final int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

		// Iterates throughout the months
		for (int i = 0; i < 12; i++) {
			System.out.println(months[i].getName() + ":");

			// Iterates throughout the weeks in the month
			for (int j = 0; j < months[i].getDays().length - 1; j++) {
				for (int k = 0; k < months[i].getDays()[0].length; k++) {

					printDay(count, i, currentMonth, currentDay);
					count++;
				}
				System.out.println();
			}

			// Writes out the last row of days
			for (int k = 0; k < months[i].getDaysInTheMonth() % 7; k++) {
				printDay(count, i, currentMonth, currentDay);
				count++;
			}

			System.out.println();
			System.out.println();
			count = 1;
		}
	}

	private static void printDay(int day, int month, int currentMonth, int currentDay) {
		// Prints out the days
		if (month == currentMonth && ((day + 1) == currentDay)) {
			System.out.print(" " + day + "  ");
		} else if (month == currentMonth && (day == currentDay)) {
			if (day < 10) {
				System.out.print("[" + day + "]  ");
			} else {
				System.out.print("[" + day + "] ");
			}
		} else {
			if (day < 10) {
				System.out.print(" " + day + "   ");
			} else {
				System.out.print(" " + day + "  ");
			}
		}
	}
}
