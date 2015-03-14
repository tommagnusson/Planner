package org.sjcadets.planner.view.dialogs;

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
public abstract class InputDialogController {

	private Stage dialogStage;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public Stage getDialogStage() {
		return dialogStage;
	}
	
	//public void setPlannerObject(AbstractPlannerObject o) {
	//	
	//}
	
	@FXML
	public void onCancel() {
		dialogStage.close();
	}
	
	@FXML
	public abstract void onSave();
	public abstract boolean validFields();
}
