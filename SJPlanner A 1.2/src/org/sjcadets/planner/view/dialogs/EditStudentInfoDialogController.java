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
import org.sjcadets.planner.model.StudentInfo;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
//sf dvgbhfghdfvgbfdo
public class EditStudentInfoDialogController {
	//TextFields
	
	@FXML private TextField firstNameField;
	@FXML private TextField lastNameField;
	@FXML private TextField counselorField;
	@FXML private TextField lockerNumberField;
	@FXML private TextField homeRoomField;
	@FXML private TextField lockerComboFirstField;
	@FXML private TextField lockerComboSecondField;
	@FXML private TextField lockerComboThirdField;
	
	private List<TextField> textFieldContainer;
	
	// Year Radio Buttons
	ToggleGroup studentYear = new ToggleGroup();
	@FXML private RadioButton freshman;
	@FXML private RadioButton sophomore;
	@FXML private RadioButton junior;
	@FXML private RadioButton senior;
	
	private static BiMap<StudentInfo.Grade, RadioButton> studentYearMap = HashBiMap.create(4);
	
	//Buttons
	@FXML private Button saveButton;
	@FXML private Button cancelButton;

	//Instance Variables
	private Stage dialogStage;
	private boolean saveClicked = false;
	
	
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}

	public boolean isSaveClicked(){
		return saveClicked;
	}
	@FXML
	public void initialize(){
		freshman.setToggleGroup(studentYear);
		sophomore.setToggleGroup(studentYear);
		junior.setToggleGroup(studentYear);
		senior.setToggleGroup(studentYear);
		
		studentYearMap.put(StudentInfo.Grade.FRESHMAN, freshman);
		studentYearMap.put(StudentInfo.Grade.SOPHOMORE, sophomore);
		studentYearMap.put(StudentInfo.Grade.JUNIOR, junior);
		studentYearMap.put(StudentInfo.Grade.SENIOR, senior);
		
		textFieldContainer = new ArrayList<TextField>(8);
		textFieldContainer.add(firstNameField);
		textFieldContainer.add(lastNameField);
		textFieldContainer.add(counselorField);
		textFieldContainer.add(lockerNumberField);
		textFieldContainer.add(homeRoomField);
		textFieldContainer.add(lockerComboFirstField);
		textFieldContainer.add(lockerComboSecondField);
		textFieldContainer.add(lockerComboThirdField);
		
		//if the master already has information in it, then
		//the user is editing information rather than adding
		//information for the first time.
		if(!AppData.getMasterStudentInfo().equals(new StudentInfo())) {
			firstNameField.setText(AppData.getMasterStudentInfo().getFirstName());
			lastNameField.setText(AppData.getMasterStudentInfo().getLastName());
			counselorField.setText(AppData.getMasterStudentInfo().getCounselor());
			lockerNumberField.setText(AppData.getMasterStudentInfo().getLockerNumber());
			homeRoomField.setText(AppData.getMasterStudentInfo().getHomeRoom());
			
			lockerComboFirstField.setText(AppData.getMasterStudentInfo().getLockerFirst());
			lockerComboSecondField.setText(AppData.getMasterStudentInfo().getLockerSecond());
			lockerComboThirdField.setText(AppData.getMasterStudentInfo().getLockerThird());
			
			studentYear.selectToggle(studentYearMap.get(AppData.getMasterStudentInfo().getYear()));
		}
	}
	private boolean validData() {
		for(TextField tf: textFieldContainer) {
			if(tf.getText() == null || tf.getText().equals("")) {
				return false;
			}
		}
		return true;
	}
	@FXML
	private boolean onSave() {
		if(validData()) {	
			AppData.getMasterStudentInfo().setFirstName(firstNameField.getText());
			AppData.getMasterStudentInfo().setLastName(lastNameField.getText());
			AppData.getMasterStudentInfo().setCounselor(counselorField.getText());
			AppData.getMasterStudentInfo().setHomeRoom(homeRoomField.getText());
			AppData.getMasterStudentInfo().setLockerNumber(lockerNumberField.getText());
			
			AppData.getMasterStudentInfo().setLockerFirst(lockerComboFirstField.getText());
			AppData.getMasterStudentInfo().setLockerSecond(lockerComboSecondField.getText());
			AppData.getMasterStudentInfo().setLockerThird(lockerComboThirdField.getText());
			
			AppData.getMasterStudentInfo().setYear(studentYearMap.inverse().get(studentYear.getSelectedToggle()));
			
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
		} else {
			Dialogs.create()
			.title("Fields")
			.masthead("Incorrect Fields")
			.message("Please input a value for each field.")
			.showWarning();
			return false;
		}
		saveClicked = true;
		dialogStage.close();
		return saveClicked;
	}
	
	@FXML
	private void onCancel() {
		dialogStage.close();
	}
}
