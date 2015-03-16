package org.sjcadets.planner.view.dialogs;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import org.sjcadets.planner.App;
import org.sjcadets.planner.model.AbstractPlannerObject;
import org.sjcadets.planner.model.Course;
import org.sjcadets.planner.model.Task;

/**
 * Call either {@code showAddDialog} or {@code showEditDialog}
 * to easily pop up one of the dialog boxes.
 * @author Tommy
 *
 */
public class InputDialogs<T extends AbstractPlannerObject, S extends InputDialogController> {
	
	private T plannerObject;
	private DialogMode mode;
	private Window owner;
	
	private static final URL COURSE_DIALOG_FXML = App.class.getResource("view/dialogs/EditCourseDialog.fxml");
	private static final URL TASK_DIALOG_FXML = App.class.getResource("view/dialogs/EditTaskDialog.fxml");
	
	public InputDialogs(T obj, DialogMode mode, Window owner) {
		this.plannerObject = obj;
		this.mode = mode;
		this.owner = owner;
	}
	
	/**
	 * Pops up a dialog based on the mode
	 * and object set.
	 */
	public S popUp() {
		switch(mode) {
		case ADD:
			if(plannerObject instanceof Course) {
				return showAddCourse();
			}
			if(plannerObject instanceof Task) {
				return showAddTask();
			}
			break;
		case EDIT:
			if(plannerObject instanceof Course) {
				return showEditCourse((Course) plannerObject);
			}
			if(plannerObject instanceof Task) {
				return showEditTask((Task) plannerObject);
			}
			break;
		default:
			break;
		
		}

		return null;
	}
	
	private S showAddCourse() {
		return loadAndShow(COURSE_DIALOG_FXML, "Add Course");
		 
	}
	
	private S showEditCourse(Course c) {
		S returnController = loadAndShow(COURSE_DIALOG_FXML, "Edit Course");
		CourseDialogController controller = (CourseDialogController) returnController;
		controller.setCourse(c);
		return returnController;
	}
	
	private S showAddTask() {
		return loadAndShow(TASK_DIALOG_FXML, "Add Task");
	}
	
	private S showEditTask(Task t) {
		S returnController = loadAndShow(TASK_DIALOG_FXML, "Edit Task");
		TaskDialogController controller = (TaskDialogController) returnController;
		controller.setTask(t);
		return returnController;
	}
	
	/**
	 * In order to use the correct controller one
	 * must cast to the appropriate controller.
	 * @return InputDialogController of the FXML document
	 */
	private S loadAndShow(URL resource, String title) {
		try {
			//Load XML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(resource);
			AnchorPane page = (AnchorPane) loader.load();
			
			//Dialog box
			Stage dialogStage = new Stage();
			dialogStage.setTitle(title);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(owner);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			S controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.show();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
}
