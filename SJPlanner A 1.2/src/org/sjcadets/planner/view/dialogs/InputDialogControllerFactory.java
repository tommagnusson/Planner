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
import org.sjcadets.planner.model.PlannerObjectType;

/**
 * Factory class for InputDialogControllers.
 * Defaults are as follows:
 * 
 * <ul>
 * <li> {@code Window owner = null}
 * <li> {@code ShowType show = ShowType.HIDE}
 * <li> {@code boolean edit = false}
 * <li> {@code AbstractPlannerObject po = null}
 * </ul>
 * 
 * This factory does not check if the Planner Object and the PlannerObjectType match.
 * @author Tommy
 *
 */
public class InputDialogControllerFactory {
	
	private static final URL COURSE_DIALOG_FXML = App.class.getResource("view/dialogs/CourseDialog.fxml");
	private static final URL TASK_DIALOG_FXML = App.class.getResource("view/dialogs/TaskDialog.fxml");
	private static final URL EVENT_DIALOG_FXML = App.class.getResource("view/dialogs/EventDialog.fxml");
	private static final URL STUDENT_INFO_DIALOG_FXML = App.class.getResource("view/dialogs/StudentInfoDialog.fxml");

	private  PlannerObjectType type;
	
	private Window owner = null;
	private ShowType show = ShowType.SHOW;
	private boolean edit = false;
	private AbstractPlannerObject po = null;
	
	private InputDialogControllerFactory(Builder builder) {
		this.type = builder.getPlannerObjectType();
		this.owner = builder.getOwner();
		this.show = builder.getShowType();
		this.edit = builder.isEdit();
		this.po = builder.getPlannerObject();
	}
	
	public static class Builder {
		
		private  PlannerObjectType type;
		
		private Window owner = null;
		private ShowType show = ShowType.SHOW;
		private boolean edit = false;
		private AbstractPlannerObject po = null;
		
		public Builder() {
		}
		
		private PlannerObjectType getPlannerObjectType() {
			return type;
		}
		
		private Window getOwner() {
			return owner;
		}
		
		private ShowType getShowType() {
			return show;
		}
		
		private boolean isEdit() {
			return edit;
		}
		
		private AbstractPlannerObject getPlannerObject() {
			return po;
		}
		
		public Builder plannerObjectType(PlannerObjectType type) {
			this.type = type;
			return this;
		}
		
		public Builder window(Window owner) {
			this.owner = owner;
			return this;
		}
		
		public Builder showType(ShowType show) {
			this.show = show;
			return this;
		}
		
		public Builder edit(boolean edit) {
			this.edit = edit;
			return this;
		}
		
		public Builder plannerObject(AbstractPlannerObject po) {
			this.po = po;
			return this;
		}
		
		public InputDialogControllerFactory build() {
			return new InputDialogControllerFactory(this);
		}
	}

	

	public InputDialogController getControllerAndPop() {
		switch(type) {
		case COURSE:
			return loadAndShow(COURSE_DIALOG_FXML);
		case EVENT:
			return loadAndShow(EVENT_DIALOG_FXML);
		case STUDENT_INFO:
			return loadAndShow(STUDENT_INFO_DIALOG_FXML);
		case TASK:
			return loadAndShow(TASK_DIALOG_FXML);
		default:
			throw new IllegalArgumentException();
		}
	}
	
	private  InputDialogController loadAndShow(URL resource) {
		try {
			//Load XML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(resource);
			AnchorPane page = (AnchorPane) loader.load();
			
			//Dialog box
			Stage dialogStage = new Stage();
			
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(owner);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			InputDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			if(edit && po != null) controller.setEdit(po);
			if(show == ShowType.SHOW) dialogStage.show();
			
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
