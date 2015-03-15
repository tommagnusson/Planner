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

public class EditTaskDialogController extends InputDialogController {
	
	@FXML TextField assignmentField;
	@FXML TextField courseField;
	@FXML TextField descriptionField;
	
	@FXML DatePicker dueDatePicker;
	
	//For edit
	private Task task;
	private boolean edit = false;
	
	private Stage dialogStage;
	private boolean saveClicked = false;
	
	public void setDialogStage(Stage dialogeStage) {
		this.dialogStage = dialogeStage;
	}
	
	public boolean isSaveClicked() {
		return saveClicked;
	}
	
	public void setTask(Task t) {
		this.task = t;
		edit = true;
		
		assignmentField.setText(task.getAssignment());
		courseField.setText(task.getClassName());
		descriptionField.setText(task.getDescription());
		dueDatePicker.setValue(task.getDueDate());
	}
	
	public Task getTask() {
		return this.task;
	}
	
	/**
	 * Tests if the fields are valid in order to save.
	 * @return true if fields contain values.
	 */
	public boolean validFields() {
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
	public void onSave() {
		Task tempTask = edit ? task : new Task();
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
		}
		if(!edit) AppData.getMasterTaskList().add(tempTask);
		try {
			AppData.save();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		saveClicked = true;
		dialogStage.close();
	}
	
	@FXML
	public void onCancel() {
		dialogStage.close();
	}
	
}
