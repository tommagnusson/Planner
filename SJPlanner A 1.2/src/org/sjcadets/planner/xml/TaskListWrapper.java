package org.sjcadets.planner.xml;

import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.sjcadets.planner.model.Task;

//used in saving the objects to XML

@XmlRootElement(name = "tasks")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TaskListWrapper {

		private ObservableList<Task> task;

		@XmlJavaTypeAdapter(TaskListAdapter.class)
		public ObservableList<Task> getTasks() {
			return task;
		}

		public void setTasks(ObservableList<Task> tasks) {
			this.task = tasks;
		}
		
}
