package org.sjcadets.planner.view.dialogs;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;

import org.controlsfx.dialog.Dialogs;
import org.sjcadets.planner.AppData;
import org.sjcadets.planner.model.Task;
//6estestest
public class EditTaskDialogController {
	@FXML
	TextField assignmentField;
	@FXML
	TextField courseField;
	@FXML
	TextField descriptionField;
	@FXML
	DatePicker dueDatePicker;
	
	private Stage dialogStage;
	private boolean saveClicked = false;
	
	public void setDialogStage(Stage dialogeStage) {
		this.dialogStage = dialogeStage;
	}
	
	public boolean isSaveClicked() {
		return saveClicked;
	}
	
	/**
	 * Tests if the fields are valid in order to save.
	 * @return true if fields contain values.
	 */
	private boolean validFields() {
		if(assignmentField.getText() == null || assignmentField.getText().equals("")) {
			return false;
		}
		if(courseField.getText() == null || courseField.getText().equals("")) {
			return false;
		}
		if(descriptionField.getText() == null || descriptionField.getText().equals("")) {
			return false;
		}
		if(dueDatePicker.getValue() == null) {
			return false;
		}
		return true;
	}
	
	//Event handle methods
	
	@FXML
	public boolean onSave() throws FileNotFoundException, JAXBException {
		Task tempTask = new Task();
		if(validFields()) {
			tempTask.setAssignment(assignmentField.getText());
			tempTask.setClassName(courseField.getText());
			tempTask.setDescription(descriptionField.getText());
			tempTask.setDueDate(dueDatePicker.getValue());
		} else {
			Dialogs.create()
			.title("Fields")
			.masthead("Incorrect Fields")
			.message("Please input a value for each field.")
			.showWarning();
			return false;
		}
		AppData.getMasterTaskList().add(tempTask);
		AppData.save();
		saveClicked = true;
		dialogStage.close();
		return saveClicked;
	}
	
	@FXML
	public void onCancel() {
		dialogStage.close();
	}
	
}
