package org.sjcadets.planner.view.dialogs;

import org.sjcadets.planner.model.AbstractPlannerObject;
import org.sjcadets.planner.view.Controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Abstract class for input dialog controllers. Contains:
 * <ul>
 * <li> concrete onCancel method
 * <li> abstract onSave method
 * <li> concrete setDialogStage method
 * <li> abstract validFields method
 * </ul>
 * <p>
 * TODO test if injection works in inherited classes,
 * i.e. {@code @FXML}
 * 
 * @author Tommy
 *
 */
public abstract class InputDialogController extends Controller {
	

	private Stage dialogStage;
	private boolean edit = false;
	
	public boolean getEdit() {
		return this.edit;
	}
	
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public Stage getDialogStage() {
		return dialogStage;
	}

	
	@FXML
	public void onCancel() {
		dialogStage.close();
	}
	
	@FXML
	public abstract void onSave();
	public abstract boolean validFields();
	public abstract void setEdit(AbstractPlannerObject apo);
}
