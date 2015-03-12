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
import org.sjcadets.planner.model.xml.CourseList;
import org.sjcadets.planner.model.xml.TaskList;
import org.sjcadets.planner.view.BaseController;

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
			AppData.initFiles();
			
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
	

}
