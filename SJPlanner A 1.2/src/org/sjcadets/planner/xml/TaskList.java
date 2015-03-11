package org.sjcadets.planner.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.sjcadets.planner.model.Task;

public class TaskList {

	@XmlElement(name = "task")
	List<Task> items = new ArrayList<>();
	
	public List<Task> getItems() {
		return items;
	}
}
