package org.sjcadets.planner.model;
import javafx.beans.property.SimpleStringProperty;

/**
 * A Java bean representing all the information associated with a Course.
 * Users can enroll in, edit, and drop Courses within the GUI.
 * <p>
 * Needs to be a Java bean (SimpleStringProperty, etc) because within
 * JavaFX in order to change and update data (for ObservableLists, etc)
 * data needs to be stored in properties
 */
public class Course extends AbstractPlannerObject {
	
	//Constructors
	public Course(String name, String teacher, String roomNumber, String period, String materials, String lunchWave) {
		this.name = new SimpleStringProperty(name);
		this.teacher = new SimpleStringProperty(teacher);
		this.roomNumber = new SimpleStringProperty(roomNumber);
		this.period = new SimpleStringProperty(period);
		this.materials = new SimpleStringProperty(materials);
		this.lunchWave = new SimpleStringProperty(lunchWave);
	}
	public Course(){
		this("", "", "", "", "", "");
		
		setName("English");
		setTeacher("Lowell");
		setRoomNumber("311");
		setPeriod("E");
		setMaterials("Seven books");
		setLunchWave("4");
	}
	
	//Instance Variables
	private final SimpleStringProperty name;
	private final SimpleStringProperty teacher;
	private final SimpleStringProperty roomNumber;
	private final SimpleStringProperty period;
	private final SimpleStringProperty materials;
	private final SimpleStringProperty lunchWave;
	
	
	//public methods
	
	@Override
	public String toString(){
		String all = "Course Name: " + name + " Teacher: " + teacher + " Room Number: " + roomNumber + " Period: " + period;
		return all;
	}
	
	//getters and setters JavaFX
	
	public final SimpleStringProperty nameProperty() {
		return this.name;
	}
	public final java.lang.String getName() {
		return this.nameProperty().get();
	}
	public final void setName(final java.lang.String name) {
		this.nameProperty().set(name);
	}
	public final SimpleStringProperty teacherProperty() {
		return this.teacher;
	}
	public final java.lang.String getTeacher() {
		return this.teacherProperty().get();
	}
	public final void setTeacher(final java.lang.String teacher) {
		this.teacherProperty().set(teacher);
	}
	public final SimpleStringProperty roomNumberProperty() {
		return this.roomNumber;
	}
	public final java.lang.String getRoomNumber() {
		return this.roomNumberProperty().get();
	}
	public final void setRoomNumber(final java.lang.String roomNumber) {
		this.roomNumberProperty().set(roomNumber);
	}
	public final SimpleStringProperty periodProperty() {
		return this.period;
	}
	public final java.lang.String getPeriod() {
		return this.periodProperty().get();
	}
	public final void setPeriod(final java.lang.String period) {
		this.periodProperty().set(period);
	}
	public final SimpleStringProperty materialsProperty() {
		return this.materials;
	}
	public final java.lang.String getMaterials() {
		return this.materialsProperty().get();
	}
	public final void setMaterials(final java.lang.String materials) {
		this.materialsProperty().set(materials);
	}
	public final SimpleStringProperty lunchWaveProperty() {
		return this.lunchWave;
	}
	public final String getLunchWave() {
		return this.lunchWaveProperty().get();
	}
	public final void setLunchWave(final java.lang.String lunchWave) {
		this.lunchWaveProperty().set(lunchWave);
	}
}

