package org.sjcadets.planner.model.xml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.sjcadets.planner.model.Course;

public class CourseListAdapter extends XmlAdapter<CourseList, ObservableList<Course>>{

	@Override
	public CourseList marshal(ObservableList<Course> v) throws Exception {
		CourseList clist = new CourseList();
		v.stream().forEach((course) -> clist.getCourses().add(course));
		return clist;
	}

	@Override
	public ObservableList<Course> unmarshal(CourseList v) throws Exception {
		ObservableList<Course> oList = FXCollections.observableArrayList(v.getCourses());
		return oList;
	}

}
