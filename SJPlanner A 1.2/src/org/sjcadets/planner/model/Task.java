package org.sjcadets.planner.model;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.sjcadets.planner.xml.LocalDateAdapter;

/**
 * A Java bean representing all the information associated with a <code>Task</code>.
 * Users can add, edit and remove tasks. Tasks along with Events are displayed
 * in the many calendar formats (Day, Week, Month).
 * <p>
 * Needs to be a Java bean (SimpleStringProperty, etc) because within
 * JavaFX in order to change and update data (for ObservableLists, etc)
 * data needs to be stored in properties.
 */
@XmlRootElement
public class Task {
	//constructors
	
	//complete constructor
	public Task(String className, String assignment, String description, LocalDate dueDate) {
		this.className = new SimpleStringProperty(className);
		this.assignment = new SimpleStringProperty(assignment);
		this.description = new SimpleStringProperty(description);
		
		this.dueDate = new SimpleObjectProperty<LocalDate>(dueDate);
	}
	
	/**
	 * Sets a model data into the task, sets the 
	 * due date to be tomorrow.
	 */
	public Task() {
		this("", "", "", LocalDate.now().plusDays(1));
		
		setClassName("English");
		setAssignment("Read");
		setDescription("1984");
		
		//setDueDate(LocalDate.now());
	}
	//Instance variables
	
	private final SimpleStringProperty className;
	private final SimpleStringProperty assignment;
	private final SimpleStringProperty description;
		
	private final ObjectProperty<LocalDate> dueDate;
	
//	//Getters and setters
	
	public final SimpleStringProperty classNameProperty() {
		return this.className;
	}
	public final java.lang.String getClassName() {
		return this.classNameProperty().get();
	}
	public final void setClassName(final java.lang.String className) {
		this.classNameProperty().set(className);
	}
	public final SimpleStringProperty assignmentProperty() {
		return this.assignment;
	}
	public final java.lang.String getAssignment() {
		return this.assignmentProperty().get();
	}
	public final void setAssignment(final java.lang.String assignment) {
		this.assignmentProperty().set(assignment);
	}
	public final SimpleStringProperty descriptionProperty() {
		return this.description;
	}
	public final java.lang.String getDescription() {
		return this.descriptionProperty().get();
	}
	public final void setDescription(final java.lang.String description) {
		this.descriptionProperty().set(description);
	}
	public final ObjectProperty<LocalDate> dueDateProperty() {
		return this.dueDate;
	}
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public final java.time.LocalDate getDueDate() {
		return this.dueDateProperty().get();
	}
	public final void setDueDate(final java.time.LocalDate dueDate) {
		this.dueDateProperty().set(dueDate);
	}
}
