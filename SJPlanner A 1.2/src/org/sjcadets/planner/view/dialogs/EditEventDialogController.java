package org.sjcadets.planner.view.dialogs;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.xml.bind.JAXBException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import org.controlsfx.dialog.Dialogs;
import org.sjcadets.planner.AppData;
import org.sjcadets.planner.model.Event;

/**
 * A dialog box for entering information for
 * Events.
 * @author Tommy
 * @see Event
 *
 */
public class EditEventDialogController extends InputDialogController {

	@FXML private TextField eventNameField;
	@FXML private TextField locationField;
	@FXML private TextField startTimeField;
	@FXML private TextField endTimeField;
	
	@FXML private DatePicker startDatePicker;
	@FXML private DatePicker endDatePicker;
	
	@FXML private Button saveButton;
	@FXML private Button cancelButton;

	/**
	 * Inner class which deals with parsing the dates and times
	 * from the respective text fields. Uses regular expressions
	 * to determine the validity of the data entered by the user.
	 * <p>
	 * The way the prompt is set up suggests to the user that
	 * he should use "00:00 a" or "00:00 am" formatting. This is
	 * the format that the LocalDateTimeHandler will check for.
	 * <p>
	 * It then conjoins the data from the respective date pickers to
	 * make a LocalDateTime class for start and end of the event.
	 * @author Tommy
	 *
	 */
	private class LocalDateTimeHandler {
		
		//conjoins LocalDate and LocalTime to LocalDateTime
		public LocalDateTime getStart() {
			LocalTime startTime = parseTime(startTimeField.getText());
			LocalDateTime start = LocalDateTime.of(startDatePicker.getValue(), startTime);
			return start;
		}
		
		//conjoins LocalDate and LocalTime to LocalDateTime
		public LocalDateTime getEnd() {
			LocalTime endTime = parseTime(endTimeField.getText());
			LocalDateTime end = LocalDateTime.of(endDatePicker.getValue(), endTime);
			return end;
		}
		
		public LocalTime parseTime(String input) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
			LocalTime t = LocalTime.from(formatter.parse(input));
			return t;
		}
		//TODO
		public boolean validTime(String input) {
			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
			try {
				formatter.parse(input);
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		
	}
	
	@Override
	public void onSave() {
		Event event = new Event();
		if(validFields()) {
			event.setName(eventNameField.getText());
			event.setLocation(locationField.getText());
			
			LocalDateTimeHandler handler = new LocalDateTimeHandler();
			event.setTime(handler.getStart(), handler.getEnd());
		} else {
			Dialogs.create()
			.title("Fields")
			.masthead("Incorrect Fields")
			.message("Please input a value for each field.")
			.showWarning();
			return;
		}
		AppData.getMasterEventList().add(event);
		try {
			AppData.save();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		getDialogStage().close();
	}

	/**
	 * Tests if the user entered all values into the appropriate fields.
	 */
	@Override
	public boolean validFields() {
		LocalDateTimeHandler handler = new LocalDateTimeHandler();
		
		if(eventNameField.getText() == null || eventNameField.getText().equals("")) {
			return false;
		}
		if(locationField.getText() == null || locationField.getText().equals("")) {
			return false;
		}
		if(startTimeField.getText() == null || startTimeField.getText().equals("")) {
			return false;
		}
		if(!handler.validTime(startTimeField.getText())) {
			return false;
		}
		if(endTimeField.getText() == null || endTimeField.getText().equals("")) {
			return false;
		}
		if(!handler.validTime(endTimeField.getText())) {
			return false;
		}
		if(startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
			return false;
		}
		//all fields have values
		return true;
	}
}
