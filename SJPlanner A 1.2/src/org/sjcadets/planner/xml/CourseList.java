package org.sjcadets.planner.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.sjcadets.planner.model.Course;

//used in saving the objects to XML

@XmlRootElement(name = "courses")
@XmlJavaTypeAdapter(CourseListAdapter.class)
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CourseList {
	
	private List<Course> courses = new ArrayList<>();

	@XmlElement(name = "course")
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}
