public class Timer {
	private long startTime;
	private long endTime;
	private boolean timerRunning;
	// -1 - not running, 0 - nanoseconds, 1 - milliseconds
	private int timerMode;

	Timer() {
		this.timerMode = -1;
		this.startTime = 0;
		this.endTime = 0;
		this.timerRunning = false;
	}

	// returns whether the timer is still running
	public boolean isTimerRunning() {
		return timerRunning;
	}

	// returns the start time
	public long getStartTime() {
		return startTime;
	}

	// returns the end time
	public long getEndTime() {
		return endTime;
	}

	// stores the current time in nanoseconds as the start time
	public void startTimerNano() throws RuntimeException {
		// throws an exception if the timer was already running when the method was called
		if (!timerRunning) {
			startTime = System.nanoTime();
		} else {
			throw new RuntimeException("Attempted to start the timer while it was running");
		}

		timerMode = 0;
		timerRunning = true;
	}

	// stores the current time in milliseconds as the start time
	public void startTimerMilli() throws RuntimeException {
		// throws an exception if the timer was already running when the method was called
		if (!timerRunning) {
			startTime = System.currentTimeMillis();
		} else {
			throw new RuntimeException("Attempted to start the timer while it was running");
		}

		timerRunning = true;
		timerMode = 1;
	}

	// stores the current time as the end time
	public void stopTimer() throws RuntimeException {
		// throws an exception if the timer wasn't running when the method was called
		if (timerRunning) {
			// stops the timer in the correct time unit
			if (timerMode == 0) {
				endTime = System.nanoTime();
			} else {
				endTime = System.currentTimeMillis();
			}
		} else {
			throw new RuntimeException("Attempted to stop the timer while it wasn't running");
		}

		timerRunning = false;
	}

	// resets the timer
	public void resetTimer() throws RuntimeException {
		// throws an exception if the timer was running when the method was called
		if (!timerRunning) {
			startTime = 0;
			endTime = 0;
			timerMode = -1;
		} else {
			throw new RuntimeException("Attempted to reset the timer while it was running");
		}
	}

	// returns the difference between the start and end times or minus one if the timer hasn't been stopped
	public long getElapsedTime() throws RuntimeException {
		// throws an exception if the timer was running when the method was called
		if (!timerRunning) {
			return endTime - startTime;
		} else {
			throw new RuntimeException("Attempted to get the elapsed time while the timer was running");
		}
	}

	// wrapper for stopTimer and getElapsedTime
	public long stopAndGetElapsedTime() {
		stopTimer();
		return getElapsedTime();
	}
}
