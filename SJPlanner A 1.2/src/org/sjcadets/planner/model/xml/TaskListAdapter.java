package org.sjcadets.planner.model.xml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.sjcadets.planner.model.Task;

public class TaskListAdapter extends XmlAdapter<TaskList, ObservableList<Task>>{

	@Override
	public TaskList marshal(ObservableList<Task> oList) throws Exception {
		TaskList tList = new TaskList();
		oList.stream().forEach((item) -> tList.getTasks().add(item));
		return tList;
	}

	@Override
	public ObservableList<Task> unmarshal(TaskList tList) throws Exception {
		ObservableList<Task> oList = FXCollections.observableArrayList(tList.getTasks());
		return oList;
	}

}
