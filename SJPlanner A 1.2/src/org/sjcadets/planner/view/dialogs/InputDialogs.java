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
import org.sjcadets.planner.model.PlannerObjectEnum;

/**
 * Call either {@code showAddDialog} or {@code showEditDialog}
 * to easily pop up one of the dialog boxes.
 * @author Tommy
 *
 */
public class InputDialogs<T> {
	
	private DialogMode mode;
	private PlannerObjectEnum objectType;
	private Window owner;
	
	public Object getPlannerObject() {
		if()
		return plannerObject;
	}

	public void setPlannerObject(Object plannerObject) {
		this.plannerObject = plannerObject;
	}

	private Object plannerObject;
	
	private static final URL COURSE_DIALOG_FXML = App.class.getResource("view/dialogs/EditCourseDialog.fxml");
	
	public InputDialogs(PlannerObjectEnum obj, DialogMode mode, Window owner) {
		this.object = obj;
		this.mode = mode;
		this.owner = owner;
	}
	
	/**
	 * Pops up a dialog based on the mode
	 * and object set.
	 */
	public InputDialogController popUp() {
		if(mode == DialogMode.ADD) {
			if(object == PlannerObjectEnum.COURSE)
				return showAddCourse();
			
		} else if(mode == DialogMode.EDIT) {
			if(object == PlannerObjectEnum.COURSE) {
				return showEditCourse();
			}
		}
		return null;
	}
	
	private InputDialogController showAddCourse() {
		return loadAndShow(COURSE_DIALOG_FXML);
		 
	}
	
	private InputDialogController showEditCourse(Course c) {
		EditCourseDialogController controller = (EditCourseDialogController) loadAndShow(COURSE_DIALOG_FXML);
	}
	
	/**
	 * In order to use the correct controller one
	 * must cast to the appropriate controller.
	 * @return InputDialogController of the FXML document
	 */
	private InputDialogController loadAndShow(URL resource) {
		try {
			//Load XML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(resource);
			AnchorPane page = (AnchorPane) loader.load();
			
			//Dialog box
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Course");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(owner);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			InputDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
}
