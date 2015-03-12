package org.sjcadets.planner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.sjcadets.planner.model.Course;
import org.sjcadets.planner.model.Event;
import org.sjcadets.planner.model.StudentInfo;
import org.sjcadets.planner.model.Task;
import org.sjcadets.planner.model.xml.CourseList;
import org.sjcadets.planner.model.xml.TaskList;

/**
 * Class to hold all the app's data, such as
 * the list of courses, tasks and events.
 * @author Tommy
 *
 */
public final class AppData {
	
	//Instance variables
	
	private static ObservableList<Task> masterTaskList = FXCollections.observableArrayList();
	
	private static ObservableList<Course> masterCourseList = FXCollections.observableArrayList();
	
	private static ObservableList<Event> masterEventList = FXCollections.observableArrayList();
	
	private static StudentInfo masterStudentInfo = new StudentInfo();
	
	//Not meant to be instantiated
	private AppData() {
		throw new AssertionError("Cannot instantiate");
	}
	
	//getters and setters
	public static ObservableList<Task> getMasterTaskList() {
		return masterTaskList;
	}

	public static StudentInfo getMasterStudentInfo() {
		return masterStudentInfo;
	}

	public static void setMasterStudentInfo(StudentInfo masterStudentInfo) {
		AppData.masterStudentInfo = masterStudentInfo;
	}

	public static void setMasterTaskList(ObservableList<Task> masterTaskList) {
		AppData.masterTaskList = masterTaskList;
	}

	public static ObservableList<Course> getMasterCourseList() {
		return masterCourseList;
	}

	public static void setMasterCourseList(ObservableList<Course> masterCourseList) {
		AppData.masterCourseList = masterCourseList;
	}

	public static ObservableList<Event> getMasterEventList() {
		return masterEventList;
	}

	public static void setMasterEventList(ObservableList<Event> masterEventList) {
		AppData.masterEventList = masterEventList;
	}
	
	/**
	 * Used for populating the the various lists with test data.
	 */
	public static void populateLists() {
		//AppData.getMasterTaskList().add(new Task("Math", "Book", "problems", LocalDate.of(2015, 1, 17)));
		//AppData.getMasterTaskList().add(new Task("Religion", "Textbook", "problems", LocalDate.of(2015, 1, 17)));
		//AppData.getMasterTaskList().add(new Task());
		
		//AppData.getMasterCourseList().add(new Course());
		//AppData.getMasterCourseList().add(new Course("Math", "Mrs. Dennin", "107", "B", "notebook", "2"));
	}
	
	/**
	 * Save to XML using JAXB
	 * @throws JAXBException 
	 * @throws FileNotFoundException 
	 */
	public static void save() throws JAXBException, FileNotFoundException {
		
		//class used for XML specific tasks
		CourseList cl = new CourseList();
		cl.setCourses(getMasterCourseList());
		
		saveObject(cl, new File(System.getProperty("user.dir") + "/resources/xml/courses.xml"));
		
		TaskList tl = new TaskList();
		tl.setTasks(AppData.getMasterTaskList());
		
		saveObject(tl, new File(System.getProperty("user.dir") + "/resources/xml/tasks.xml"));
		
		saveObject(masterStudentInfo, new File(System.getProperty("user.dir") + "/resources/xml/student_info.xml"));
	}
	
	/**
	 * Saves a specific Object {@code obj} to an xml file {@code xml} using JAXB.
	 * @param obj
	 * @param xml
	 * @throws FileNotFoundException
	 * @throws JAXBException
	 */
	private static void saveObject(Object obj, File xml) throws FileNotFoundException, JAXBException {
		//context is used to determine what kind of class is going to be marshalled or unmarshalled
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		
		//loads to the XML file
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		//loads the current list of courses to the courses.xml file
		m.marshal(obj, new FileOutputStream(xml));
	}
	
	/**
	 * Initial setup for all the files for the program. Contains all the
	 * persistent data for the planner, such as courses, tasks, and events.
	 * <p>
	 * All data is saved in {@code [place of installment]/resources/xml/...}.
	 * @throws IOException
	 */
	public static void initFiles() throws IOException{
		File courses = new File(System.getProperty("user.dir") + "/resources/xml/courses.xml");
		File tasks = new File(System.getProperty("user.dir") + "/resources/xml/tasks.xml");
		File events = new File(System.getProperty("user.dir")+ "/resources/xml/events.xml");
		File studentInfo = new File(System.getProperty("user.dir")+ "/resources/xml/student_info.xml");
		
		//check if each file exists, if so unmarshall 
		if(courses.exists()) {
			try {
				JAXBContext context = JAXBContext.newInstance(CourseList.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				CourseList cl = (CourseList) unmarshaller.unmarshal(courses);
				
				AppData.getMasterCourseList().addAll(cl.getCourses());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			courses.createNewFile();
		}
		
		if(tasks.exists()){
			try {
				
				JAXBContext context = JAXBContext.newInstance(TaskList.class);
				Unmarshaller um = context.createUnmarshaller();
				TaskList taskList = (TaskList) um.unmarshal(tasks);
				
				//load courses into local memory
				AppData.getMasterTaskList().addAll(taskList.getTasks());
				
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			
		} else {
			tasks.createNewFile();
		}
		
		if(events.exists()) {
			//unmarshall events from file into AppData
		} else {
			events.createNewFile();
		}
		
		//Works perfectly. No wrapper classes involved
		if(studentInfo.exists()) {
			try {
				JAXBContext context = JAXBContext.newInstance(StudentInfo.class);
				
				Unmarshaller unmarshaller = context.createUnmarshaller();
				
				StudentInfo si = (StudentInfo) unmarshaller.unmarshal(studentInfo);
				AppData.getMasterStudentInfo().setCounselor(si.getCounselor());
				AppData.getMasterStudentInfo().setFirstName(si.getFirstName());
				AppData.getMasterStudentInfo().setHomeRoom(si.getHomeRoom());
				AppData.getMasterStudentInfo().setLastName(si.getLastName());
				AppData.getMasterStudentInfo().setLockerFirst(si.getLockerFirst());
				AppData.getMasterStudentInfo().setLockerNumber(si.getLockerNumber());
				AppData.getMasterStudentInfo().setLockerSecond(si.getLockerSecond());
				AppData.getMasterStudentInfo().setLockerThird(si.getLockerThird());
				AppData.getMasterStudentInfo().setYear(si.getYear());
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			studentInfo.createNewFile();
		}
	}
}
