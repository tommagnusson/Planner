package org.sjcadets.planner.model;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class Event extends AbstractPlannerObject{
	//Instance variables
	private String name, location;
	private LocalDateTime start, end;
	
	//variables calculated from the above
	private Period period;
	private Duration duration;
	
	//Constructors
	
	/**
	 * Sets a default Event
	 */
	public Event() {
		setName("");
		setLocation("");
		setTime(LocalDateTime.now(), LocalDateTime.now());
		//setTimeDifference();
	}
	/**
	 * Sets an Event to the specified arguments
	 * @param name
	 * @param location
	 * @param start
	 * @param end
	 */
	public Event(String name, String location, LocalDateTime start,
			LocalDateTime end) {
		this.name = name;
		this.location = location;
		this.start = start;
		this.end = end;
		
		setTimeDifference();
	}


	//Getters and Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
		setTimeDifference();
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
		setTimeDifference();
	}
	public void setTime(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
		
		setTimeDifference();
	}
	
	private void setPeriod() {
		period = Period.between(start.toLocalDate(), end.toLocalDate());
	}
	private void setDuration() {
		duration = Duration.between(start.toLocalTime(), end.toLocalTime());
	}
	private void setTimeDifference() {
		setPeriod();
		setDuration();
	}
	
	@Override
	public String toString() { 
		return "Name: " + getName()
				+ " Location: " + getLocation()
				+ " Start: " + getStart()
				+ " End: " + getEnd()
				+ " Period: " + period.toString()
				+ " Duration: " + duration.toString();
	}
	
}
