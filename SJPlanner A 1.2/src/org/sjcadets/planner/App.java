package org.sjcadets.planner;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.sjcadets.planner.model.StudentInfo;
import org.sjcadets.planner.view.BaseController;
import org.sjcadets.planner.xml.CourseList;
import org.sjcadets.planner.xml.TaskList;

/**
 * The main application. This application serves as a "replacement"
 * for a Saint Joseph planner.<br>
 * A student has the ability to see their: <br> <br>
 * <ol>
 * <li> Assignments, such as homework or projects
 * <li> Events, such as a school play or sports practice
 * <li> Courses as they take them in school 
 * </ol>
 * The philosophy of the planner design is to replicate the
 * physical planner as much as possible, while enhancing it with
 * computer-only features, such as searching assignments or events,
 * and providing different ways of displaying the same information. <br>
 * <br>
 *  
 * @author Tommy Magnusson
 * @author Matt Cenci
 *
 */
public class App extends Application {
	/**
	 * The primary stage in which all other scenes live.
	 */
	private Stage primaryStage;
	
	/**
	 * The base pane, wraps all others. Contains a top bar (File, save as, etc)
	 * and a TabPane with tabs for the rest of the views (home, schedule, calendar).
	 * @see BaseView.fxml
	 */
	private BorderPane base;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("SJ Planner");
			
			//set icon
			this.primaryStage.getIcons().addAll(new Image("file:rescources/images/SJIcon_16.png"), new Image("file:resources/images/SJIcon.png"));
			
			//Populates the master lists with test data
			AppData.populateLists();
			
			//sets up the base view + controller, shows the primaryStage
			initBase();
			
			//sets up files
			initFiles();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(App.class, args);
	}
	
	/**
	 * Initializes the base view along with its controller.
	 * <p>
	 * Also shows the primaryStage. Must be called within the
	 * <code>start()</code> method in order for the primaryStage
	 * to show.
	 * @see BaseView.fxml
	 * @see BaseController
	 */
	public void initBase() {
		try {
			//FXML load the root layout from fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/BaseView.fxml"));
			this.base = (BorderPane) loader.load();
			
			//Show the scene containing the base
			Scene scene = new Scene(base);
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initial setup for all the files for the program. Contains all the
	 * persistent data for the planner, such as courses, tasks, and events.
	 * <p>
	 * All data is saved in {@code [place of installment]/resources/xml/...}.
	 * @throws IOException
	 */
	public void initFiles() throws IOException{
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
