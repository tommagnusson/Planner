package org.sjcadets.planner.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.sjcadets.planner.model.Task;

@XmlRootElement(name = "tasks")
@XmlJavaTypeAdapter(TaskListAdapter.class)
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TaskList {

	
	private List<Task> tasks = new ArrayList<>();
	
	@XmlElement(name = "task")
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> list) {
		this.tasks = list;
	}
}
