package org.sjcadets.planner.view.dialogs;

import org.sjcadets.planner.model.PlannerObjectType;

public class InputDialogControllerFactory {

	InputDialogController controller;
	
	public InputDialogController getController(PlannerObjectType type) {
		switch(type) {
		case COURSE:
			break;
		case EVENT:
			break;
		case STUDENT_INFO:
			break;
		case TASK:
			break;
		default:
			break;
		}
	}
	
}
