package org.sjcadets.planner.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import org.sjcadets.planner.App;
import org.sjcadets.planner.AppData;
import org.sjcadets.planner.model.Course;
import org.sjcadets.planner.view.dialogs.EditStudentInfoDialogController;

public class ScheduleController {
	
	//Student Info Labels
	@FXML Label counselorLabel;
	@FXML Label lockerNumberLabel;
	@FXML Label lockerFirstLabel;
	@FXML Label lockerSecondLabel;
	@FXML Label lockerThirdLabel;
	@FXML Label homeroomLabel;
	
	@FXML AnchorPane schedulePane;
	
	@FXML
	TableView<Course> courseTable;
	@FXML
	TableColumn<Course, String> classNameColumn;
	@FXML
	TableColumn<Course, String> teacherColumn;
	@FXML
	TableColumn<Course, String> roomColumn;
	@FXML
	TableColumn<Course, String> periodColumn;
	@FXML
	TableColumn<Course, String> materialsColumn;
	@FXML
	TableColumn<Course, String> lunchWaveColumn;
	
	@FXML Button editStudentInfo;
	
	@FXML
	private void initialize() {
		courseTable.setItems(AppData.getMasterCourseList());
		initCourseTableView();
		initStudentInfoLabels();
	}
	
	//Private methods
	
	private void initCourseTableView() {
		//init columns
		classNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		teacherColumn.setCellValueFactory(cellData -> cellData.getValue().teacherProperty());
		roomColumn.setCellValueFactory(cellData -> cellData.getValue().roomNumberProperty());
		periodColumn.setCellValueFactory(cellData -> cellData.getValue().periodProperty());
		materialsColumn.setCellValueFactory(cellData -> cellData.getValue().materialsProperty());
		lunchWaveColumn.setCellValueFactory(cellData -> cellData.getValue().lunchWaveProperty());
		
		//init context menus (mouse right clicked on a row)
		MenuItem deleteCourseItem = new MenuItem("Delete Course");
		deleteCourseItem.setOnAction((ActionEvent e) -> {
		    	
		    	//"Are you sure?" dialog
		    	Action delete = Dialogs.create().title("Delete Course")
		    			.masthead("Are you sure?")
		    			.message("Delete this course?").showConfirm();
		    	if(delete == Dialog.Actions.YES) {
		    		//delete course
		    		Course toBeDeleted = courseTable.getSelectionModel().getSelectedItem();
		    		AppData.getMasterCourseList().remove(toBeDeleted);
		    		try {AppData.save();} catch(Exception ex) {ex.printStackTrace();}
		    	}
		    	
		});
		final ContextMenu contextMenu = new ContextMenu();
		courseTable.setContextMenu(contextMenu);
		contextMenu.getItems().add(deleteCourseItem);
	}

	private void initStudentInfoLabels() {
		//check if the AppData has any information for the masterStudentInfo
		//if not..
		//	set the labels to ""
		//if so...
		//	update the labels with the relevant information
		//attach an observer to the masterStudentInfo to track changes...
		
		//if the masterStudentInfo does not have any information...
		bindLabels();
	}
	
	private void bindLabels() {
		counselorLabel.textProperty().bind(AppData.getMasterStudentInfo().counselorProperty());
		lockerNumberLabel.textProperty().bind(AppData.getMasterStudentInfo().lockerNumberProperty());
		lockerFirstLabel.textProperty().bind(AppData.getMasterStudentInfo().lockerFirstProperty());
			lockerSecondLabel.textProperty().bind(AppData.getMasterStudentInfo().lockerSecondProperty());
			lockerThirdLabel.textProperty().bind(AppData.getMasterStudentInfo().lockerThirdProperty());
		homeroomLabel.textProperty().bind(AppData.getMasterStudentInfo().homeRoomProperty());
	}
	
	@FXML
	private void onEditStudentInfo() {
		try {
			//Load XML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("view/dialogs/EditStudentInfoDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			//Create dialog stage (new pop-up window)
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Student Info");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(schedulePane.getScene().getWindow());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			EditStudentInfoDialogController controller = loader.getController();
			
			controller.setDialogStage(dialogStage);
			
			//show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			
		} catch (Exception e) {
			e.printStackTrace();
			Dialogs.create().title("Error").masthead("Could Not Save").message(e.getMessage()).showError();
			return;
		}
	}
}
