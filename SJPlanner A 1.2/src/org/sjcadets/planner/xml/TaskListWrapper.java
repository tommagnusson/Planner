package org.sjcadets.planner.xml;

import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.sjcadets.planner.model.Task;

//used in saving the objects to XML

@XmlRootElement(name="tasks")
public class TaskListWrapper {

		private ObservableList<Task> task;

		@XmlElement(name="task")
		@XmlJavaTypeAdapter(TaskListAdapter.class)
		public ObservableList<Task> getTasks() {
			return task;
		}

		public void setTasks(ObservableList<Task> tasks) {
			this.task = tasks;
		}
		
}
