package org.sjcadets.planner.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import org.controlsfx.dialog.Dialogs;
import org.sjcadets.planner.App;
import org.sjcadets.planner.model.PlannerObjectType;
import org.sjcadets.planner.view.dialogs.CourseDialogController;
import org.sjcadets.planner.view.dialogs.EventDialogController;
import org.sjcadets.planner.view.dialogs.InputDialogControllerFactory;
import org.sjcadets.planner.view.dialogs.ShowType;
import org.sjcadets.planner.view.dialogs.StudentInfoDialogController;
import org.sjcadets.planner.view.dialogs.TaskDialogController;

/**
 * Controller class for the BaseView
 * @author Tommy
 *
 */
public class BaseController {
	
	@FXML private TabPane baseTabPane;
	@FXML private Tab homeTab;
	@FXML private Tab calendarTab;
	@FXML private Tab scheduleTab;
	
	@FXML private AnchorPane homeAnchorPane;
	@FXML private AnchorPane calendarWrapper;
	@FXML private AnchorPane scheduleAnchorPane;
	
	@FXML private MenuItem addTask;
	@FXML private MenuItem addCourse;
	@FXML private MenuItem addEvent;
	
	
	public BaseController() {
		
	}
	
	/**
	 * Initializes the home anchor pane by
	 * linking it with the anchor pane from
	 * HomeView.fxml.
	 * @see HomeView.fxml
	 */
	private void initHomeAnchorPane() {
		try {
			//FXML loading the homeView
			FXMLLoader loader = new FXMLLoader(App.class.getResource("view/HomeView.fxml"));
			AnchorPane homeView = (AnchorPane) loader.load();
		
			//links the base to the homeView
			homeTab.setContent(homeView);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes the calendar wrapper view by
	 * linking it with the anchor pane from
	 * CalendarWrapper.fxml.
	 * @see CalendarWrapper.fxml
	 */
	private void initCalendarWrapper() {
		try {
			//FXML loading calendar wrapper view
			FXMLLoader loader = new FXMLLoader(App.class.getResource("view/CalendarWrapper.fxml"));
			AnchorPane calendarView = (AnchorPane) loader.load();
			
			//links the base to the calendarView
			calendarTab.setContent(calendarView);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes the schedule view by
	 * linking it with the anchor pane from
	 * ScheduleView.fxml.
	 * @see ScheduleView.fxml
	 */
	private void initScheduleAnchorPane() {
		try {
			//FXML loading calendar wrapper view
			FXMLLoader loader = new FXMLLoader(App.class.getResource("view/ScheduleView.fxml"));
			AnchorPane scheduleView = (AnchorPane) loader.load();
			
			//links the base to the calendarView
			scheduleTab.setContent(scheduleView);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
		
	@FXML
	private void initialize() {
		initHomeAnchorPane(); //"Home" tab
		initCalendarWrapper(); //"Calendar" tab
		initScheduleAnchorPane(); //"Schedule" tab
	}
	
	//Action Events for Add menu items
	
	@FXML
	private void onAddTask() {
		
		try {
			
			InputDialogControllerFactory factory = new InputDialogControllerFactory.Builder()
				.plannerObjectType(PlannerObjectType.TASK).window(baseTabPane.getScene().getWindow())
				.build();
			
			factory.getControllerAndPop();	
			
		} catch (Exception e) {
			e.printStackTrace();
			Dialogs.create().title("Error").masthead("Could Not Save").message(e.getMessage()).showError();
			return;
		}
	}
	
	@FXML
	private void onAddCourse() {
		try {
			InputDialogControllerFactory factory = new InputDialogControllerFactory.Builder()
				.plannerObjectType(PlannerObjectType.TASK).window(baseTabPane.getScene().getWindow()).build();
			
			factory.getControllerAndPop();
		
		} catch (Exception e) {
			e.printStackTrace();
			Dialogs.create().title("Error").masthead("Could Not Save").message(e.getMessage()).showError();
			return;
		}
	}
	@FXML
	public void onAddStudentInfo() {
		try {

			InputDialogControllerFactory factory = new InputDialogControllerFactory.Builder()
				.plannerObjectType(PlannerObjectType.STUDENT_INFO).window(baseTabPane.getScene().getWindow()).build();
			
			factory.getControllerAndPop();
			
		} catch (Exception e) {
			e.printStackTrace();
			Dialogs.create().title("Error").masthead("Could Not Save").message(e.getMessage()).showError();
			return;
		}
	}
	@FXML
	public void onAddEvent() {
		try {
			
			InputDialogControllerFactory factory = new InputDialogControllerFactory.Builder()
				.plannerObjectType(PlannerObjectType.EVENT).window(baseTabPane.getScene().getWindow()).build();
			
			factory.getControllerAndPop();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
