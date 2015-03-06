package org.sjcadets.planner.view.dialogs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;

import org.controlsfx.dialog.Dialogs;
import org.sjcadets.planner.AppData;
import org.sjcadets.planner.model.Course;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class EditCourseDialogController {
	
	//TextFields
	
	@FXML private TextField courseField;
	@FXML private TextField teacherField;
	@FXML private TextField roomNumberField;
	@FXML private TextField periodField;
	@FXML private TextField materialsField;
	
	private List<TextField> textFieldContainer;
	
	//LunchWave radio buttons
	
	ToggleGroup lunchWaveGroup = new ToggleGroup();
	
	@FXML private RadioButton firstLunch;
	@FXML private RadioButton secondLunch;
	@FXML private RadioButton thirdLunch;
	@FXML private RadioButton fourthLunch;
	
	/** A bi-directional Map. It has a useful {@code inverse()} function. */
	private static BiMap<Integer, RadioButton> lunchWaveMap = HashBiMap.create(4);
	
	//Buttons
	
	@FXML private Button saveButton;
	@FXML private Button cancelButton;
	
	//Generic controller Instance variables
	
	private Stage dialogStage;
	private boolean saveClicked = false;
	
	public void setDialogStage(Stage dialogeStage) {
		this.dialogStage = dialogeStage;
	}
	
	public boolean isSaveClicked() {
		return saveClicked;
	}
	
	@FXML
	private void initialize() {
		firstLunch.setToggleGroup(lunchWaveGroup);
		secondLunch.setToggleGroup(lunchWaveGroup);
		thirdLunch.setToggleGroup(lunchWaveGroup);
		fourthLunch.setToggleGroup(lunchWaveGroup);
		
		//mapping each button to the lunches
		//in a bi-directional hashmap
		lunchWaveMap.put(1, firstLunch);
		lunchWaveMap.put(2, secondLunch);
		lunchWaveMap.put(3, thirdLunch);
		lunchWaveMap.put(4, fourthLunch);
		
		textFieldContainer = new ArrayList<TextField>(5);
		textFieldContainer.add(courseField);
		textFieldContainer.add(teacherField);
		textFieldContainer.add(roomNumberField);
		textFieldContainer.add(periodField);
		textFieldContainer.add(materialsField);
		
	}
	
	private boolean validData() {
		for(TextField tf: textFieldContainer) {
			if(tf.getText() == null || tf.getText().equals("")) {
				return false;
			}
		}
		return true;
	}
	
	//Event Handling Methods
	
	@FXML
	private boolean onSave() {
		Course temp = new Course();
		if(validData()) {
			temp.setName(courseField.getText());
			temp.setTeacher(teacherField.getText());
			temp.setPeriod(periodField.getText());
			temp.setMaterials(materialsField.getText());
			temp.setRoomNumber(roomNumberField.getText());
			
			temp.setLunchWave(lunchWaveMap.inverse().get(lunchWaveGroup.getSelectedToggle()).toString());
		} else {
			Dialogs.create()
			.title("Fields")
			.masthead("Incorrect Fields")
			.message("Please input a value for each field.")
			.showWarning();
			return false;
		}
		AppData.getMasterCourseList().add(temp);
		saveClicked = true;
		dialogStage.close();
		try {
			AppData.save();
		} catch (FileNotFoundException e) {
			Dialogs.create()
			.title("Could Not Save")
			.masthead("File Not Found")
			.message(e.getMessage())
			.showError();
			
			e.printStackTrace();
		} catch (JAXBException e) {
			Dialogs.create()
			.title("Could Not Save")
			.masthead("XML Document Error")
			.message(e.getMessage())
			.showError();
			
			e.printStackTrace();
		}
		return saveClicked;
	}
	
	@FXML
	private void onCancel() {
		dialogStage.close();
	}
}
