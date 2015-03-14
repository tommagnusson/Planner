package org.sjcadets.planner.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Contains data associated with a student:
 * <ul>
 * <li>First and Last name
 * <li>Year, eg Freshman
 * <li>Guidance counselor
 * <li>Locker number
 * <li>Locker combo
 * <li>Homeroom
 * </ul>
 * Uses a builder pattern to set the parameters, i.e. chaining.
 * An example of this would be: <p>
 * <blockquote><code> StudentInfo studentInfo = new StudentInfo.Builder().name( <br>"Tom", "Magnusson")
 * 		.counselor("Mrs. Sullivan").lockerNumber(575).build(); </code></blockquote>
 * @author Tommy
 *
 */
@XmlRootElement
public class StudentInfo extends AbstractPlannerObject{
	
	private final SimpleStringProperty firstName;
	private final SimpleStringProperty lastName;
	private final SimpleStringProperty counselor;
	
	private final SimpleStringProperty homeRoom;
	private final SimpleStringProperty lockerNumber;
	
	private final SimpleStringProperty lockerFirst;
	private final SimpleStringProperty lockerSecond;
	private final SimpleStringProperty lockerThird;
	
	private final SimpleObjectProperty<Grade> year;
	
	/**
	 * Default: "" on all ivars.
	 * The program should check for these defaults and display "" on all
	 * Labels if there are defaults.
	 */
	public StudentInfo() {
		this.firstName = new SimpleStringProperty("");
		this.lastName = new SimpleStringProperty("");
		this.counselor = new SimpleStringProperty("");
		
		this.homeRoom = new SimpleStringProperty("");
		this.lockerNumber = new SimpleStringProperty("");
		
		this.lockerFirst = new SimpleStringProperty("");
		this.lockerSecond = new SimpleStringProperty("");
		this.lockerThird = new SimpleStringProperty("");
		
		this.year = new SimpleObjectProperty<Grade>(Grade.OTHER);
	
		
	}
	
	/**
	 * Enumeration for each Grade in school, such as Freshman.
	 * Each grade is associated with a value; Freshman is 9.
	 * @author Tommy
	 *
	 */
	@XmlEnum
	public enum Grade {
		OTHER(0), FRESHMAN(9), SOPHOMORE(10), JUNIOR(11), SENIOR(12);
		
		private int year;
		
		Grade(int year) {
			this.year = year;
		}
		
	}
	public boolean isDefault(){
		if (this.equals(new StudentInfo())){
			return true;
		}
		else{
			return false;
		}
	}
	//Getters and Setters

	public final SimpleStringProperty firstNameProperty() {
		return this.firstName;
	}
	@XmlElement
	public final java.lang.String getFirstName() {
		return this.firstNameProperty().get();
	}

	public final void setFirstName(final java.lang.String firstName) {
		this.firstNameProperty().set(firstName);
	}

	public final SimpleStringProperty lastNameProperty() {
		return this.lastName;
	}

	@XmlElement
	public final java.lang.String getLastName() {
		return this.lastNameProperty().get();
	}

	public final void setLastName(final java.lang.String lastName) {
		this.lastNameProperty().set(lastName);
	}

	public final SimpleStringProperty counselorProperty() {
		return this.counselor;
	}
	@XmlElement
	public final java.lang.String getCounselor() {
		return this.counselorProperty().get();
	}

	public final void setCounselor(final java.lang.String counselor) {
		this.counselorProperty().set(counselor);
	}

	public final ObjectProperty<Grade> yearProperty() {
		return this.year;
	}

	@XmlElement
	public final Grade getYear() {
		return this.yearProperty().get();
	}

	public final void setYear(final Grade year) {
		this.yearProperty().set(year);
	}

	public final SimpleStringProperty homeRoomProperty() {
		return this.homeRoom;
	}

	@XmlElement
	public final String getHomeRoom() {
		return this.homeRoomProperty().get();
	}

	public final void setHomeRoom(final String homeRoom) {
		this.homeRoomProperty().set(homeRoom);
	}

	public final SimpleStringProperty lockerNumberProperty() {
		return this.lockerNumber;
	}

	@XmlElement
	public final String getLockerNumber() {
		return this.lockerNumberProperty().get();
	}

	public final void setLockerNumber(final String lockerNumber) {
		this.lockerNumberProperty().set(lockerNumber);
	}

	public final SimpleStringProperty lockerFirstProperty() {
		return this.lockerFirst;
	}

	@XmlElement
	public final java.lang.String getLockerFirst() {
		return this.lockerFirstProperty().get();
	}

	public final void setLockerFirst(final java.lang.String lockerFirst) {
		this.lockerFirstProperty().set(lockerFirst);
	}

	public final SimpleStringProperty lockerSecondProperty() {
		return this.lockerSecond;
	}

	@XmlElement
	public final java.lang.String getLockerSecond() {
		return this.lockerSecondProperty().get();
	}

	public final void setLockerSecond(final java.lang.String lockerSecond) {
		this.lockerSecondProperty().set(lockerSecond);
	}

	public final SimpleStringProperty lockerThirdProperty() {
		return this.lockerThird;
	}

	@XmlElement
	public final java.lang.String getLockerThird() {
		return this.lockerThirdProperty().get();
	}

	public final void setLockerThird(final java.lang.String lockerThird) {
		this.lockerThirdProperty().set(lockerThird);
	}
}
