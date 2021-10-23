package com.github.quaoz.common.timer;

import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implements a simple way to time code execution
 */
public class Timer {
	private final AtomicLong startTime = new AtomicLong();
	private final AtomicLong endTime = new AtomicLong();
	private final AtomicBoolean timerRunning = new AtomicBoolean(false);
	private final AtomicInteger timerMode = new AtomicInteger();                            // -1 - not running, 0 - nanoseconds, 1 - milliseconds
	private final Object2LongOpenHashMap<String> events = new Object2LongOpenHashMap<>();

	/**
	 * Constructor
	 */
	public Timer() {
		this.timerMode.set(-1);
		this.startTime.set(0);
		this.endTime.set(0);
		this.timerRunning.set(false);
	}

	/**
	 * Returns whether the timer is still running
	 *
	 * @return boolean Is the timer running
	 */
	public boolean isTimerRunning() {
		return timerRunning.get();
	}

	/**
	 * Returns the start time
	 *
	 * @return long The start time
	 */
	public long getStartTime() {
		return startTime.get();
	}

	/**
	 * Returns the end time
	 *
	 * @return long The end time
	 */
	public long getEndTime() {
		return endTime.get();
	}

	/**
	 * Returns the events map
	 *
	 * @return The events object
	 */
	public Object2LongOpenHashMap<String> getEvents() {
		return events;
	}

	/**
	 * Records a new event and the time it occurred at
	 *
	 * @param name The events name
	 *
	 * @throws RuntimeException Attempted to log an event when the timer wasn't running
	 */
	public void logEvent(String name) {
		if (timerRunning.get()) {
			events.put(name, getCurrentTime());
		} else {
			throw new RuntimeException("Attempted to log an event when the timer wasn't running");
		}
	}

	/**
	 * Returns the requested events time
	 *
	 * @param name The events name
	 *
	 * @return The time a specified event happened at
	 */
	public long getEvent(String name) {
		return events.getLong(name);
	}

	/**
	 * Stores the current time in nanoseconds as the start time
	 *
	 * @throws RuntimeException Attempted to start the timer while it was running
	 */
	public void startTimerNano() throws RuntimeException {
		// Throws an exception if the timer was already running when the method was called
		if (!timerRunning.get()) {
			startTime.set(System.nanoTime());
			timerRunning.set(true);
			timerMode.set(0);
			logEvent("start");
		} else {
			throw new RuntimeException("Attempted to start the timer while it was running");
		}
	}

	/**
	 * Stores the current time in milliseconds as the start time
	 *
	 * @throws RuntimeException Attempted to start the timer while it was running
	 */
	public void startTimerMilli() throws RuntimeException {
		// Throws an exception if the timer was already running when the method was called
		if (!timerRunning.get()) {
			startTime.set(System.currentTimeMillis());
			timerRunning.set(true);
			timerMode.set(1);
			logEvent("start");
		} else {
			throw new RuntimeException("Attempted to start the timer while it was running");
		}
	}

	/**
	 * Stores the current time as the end time
	 *
	 * @throws RuntimeException Attempted to stop the timer while it wasn't running
	 */
	public void stopTimer() throws RuntimeException {
		// Throws an exception if the timer wasn't running when the method was called
		endTime.set(getCurrentTime());
		logEvent("stop");
		timerRunning.set(false);
	}

	/**
	 * Gets the difference between the start and end times
	 *
	 * @return long The end time minus the start time
	 *
	 * @throws RuntimeException Attempted to get the elapsed time while the timer was running
	 */
	public long getElapsedTime() throws RuntimeException {
		long result;
		// Throws an exception if the timer was running when the method was called
		if (!timerRunning.get()) {
			result = endTime.get() - startTime.get();
		} else {
			throw new RuntimeException("Attempted to get the elapsed time while the timer was running");
		}
		return result;
	}

	/**
	 * Calls stopTimer then returns the elapsed time
	 *
	 * @return long The end time minus the start time
	 */
	public long stopAndGetElapsedTime() {
		stopTimer();
		return getElapsedTime();
	}

	/**
	 * Gets the current time
	 *
	 * @return long The current time in the correct mode
	 */
	public long getCurrentTime() {
		// Throws an exception if the timer wasn't running when the method was called
		if (timerRunning.get()) {
			// Stops the timer in the correct time unit
			return timerMode.get() == 0
					? System.nanoTime()
					: System.currentTimeMillis();
		} else {
			throw new RuntimeException("Attempted to stop the timer while it wasn't running");
		}
	}

	/**
	 * Resets the timer
	 *
	 * @throws RuntimeException Attempted to reset the timer while it was running
	 */
	public void resetTimer() throws RuntimeException {
		// Throws an exception if the timer was running when the method was called
		if (!timerRunning.get()) {
			startTime.set(0);
			endTime.set(0);
			timerMode.set(-1);
			events.clear();
		} else {
			throw new RuntimeException("Attempted to reset the timer while it was running");
		}
	}
}
