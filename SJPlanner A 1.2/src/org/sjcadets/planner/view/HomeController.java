package org.sjcadets.planner.view;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import org.sjcadets.planner.AppData;
import org.sjcadets.planner.model.Task;

/**
 * Controller class for the home view.
 * @author Tommy
 *
 */
public class HomeController {
	@FXML
	AnchorPane homeAnchorPane; //root anchor pane
	@FXML
	Label todayDate; //Top left above today lists
	
	//Task Table top left
	@FXML
	TableView<Task> tomorrowTaskTableView;
	@FXML
	TableColumn<Task, String> tomorrowTaskAssignmentColumn;
	@FXML
	TableColumn<Task, String> tomorrowTaskClassColumn;
	
	//Task info section top right
	@FXML
	Label classNameLabel;
	@FXML
	Label assignmentLabel;
	@FXML
	Label descriptionLabel;
	@FXML
	Label dueDateLabel;
	
	ObservableList<Task> masterTaskList;
	
	//TODO Event info section bottom right
	
	public AnchorPane gethomeAnchorPane() {
		return homeAnchorPane;
	}
	
	/**
	 * Sets the correct date above the Lists for today
	 */
	private void initDateLabels() {
		todayDate.setText(LocalDate.now().toString());
	}
	
	/**
	 * Initializes the TableView for tomorrow. It creates a filtered list and binds
	 * the filter condition (due date must be day after tomorrow) to the list,
	 * to account for possible changes in the data.
	 * 
	 * @see initTodayTaskTableView();
	 */
	private void initTomorrowTaskTableView() {
		//setting the columns with appropriate data
		tomorrowTaskAssignmentColumn.setCellValueFactory(cellData -> cellData.getValue().assignmentProperty());
		tomorrowTaskClassColumn.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());
		
		//Creating a filtered list with the tasks from todayTaskTableView
		FilteredList<Task> filteredList = new FilteredList<>(tomorrowTaskTableView.getItems());
		
		//Predicate is a method that compares the tasks in the list and sets the list to be
		//only the ones whose dueDates are equal to tomorrow
		filteredList.setPredicate(task -> task.getDueDate().equals(LocalDate.now().plusDays(1)));
		
		//Sorted list is used to bind the comparing property to the tasktableView
		//so that when the list is updated it still filters
		SortedList<Task> sortedList = new SortedList<Task>(filteredList);
		sortedList.comparatorProperty().bind(tomorrowTaskTableView.comparatorProperty());
		
		tomorrowTaskTableView.setItems(sortedList);
		
		
		//right hand information correctly reflects the currently selected task
		tomorrowTaskTableView.getSelectionModel()
			.selectedItemProperty().addListener((observable, oldValue, newValue) -> refreshTaskLabels(newValue));
		
		//TODO figure out how to change this to lambda expression
		masterTaskList.addListener( new ListChangeListener<Task>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends Task> c) {
				 tomorrowTaskTableView.setItems(FXCollections.observableArrayList(c.getList()));
				 initTomorrowTaskTableView(); //filters tomorrowTaskTableView
			}
		});
		//Attempt at lambda
		//masterTaskList.addListener( (change<ObservableList<Task>>) -> tomorrowTaskTableView.setItems(FXCollections.observableArrayList(change.getList())));
	}

	/**
	 * Refreshes the task labels in the top right side
	 * of the screen. This method is called from an event
	 * listener connected to the tables.
	 * <p>
	 * If {@code newValue} is {@code null} the labels will
	 * be set to "".
	 * @param newValue - the task whose information is to be
	 * displayed.
	 */
	private void refreshTaskLabels(Task newValue) {
		if(newValue == null) {
			classNameLabel.setText("");
			assignmentLabel.setText("");
			descriptionLabel.setText("");
			dueDateLabel.setText("");
		} else {
			classNameLabel.setText(newValue.getClassName());
			assignmentLabel.setText(newValue.getAssignment());
			descriptionLabel.setText(newValue.getDescription());
			dueDateLabel.setText(newValue.getDueDate().toString());
		}
	}

	@FXML
	private void initialize() {
		//creates a copy of the masterTaskList
		//to avoid changing the masterTaskList itself
		//(This app filters the masterTaskList's data)
		masterTaskList = AppData.getMasterTaskList();
		
		tomorrowTaskTableView.setItems(FXCollections.observableArrayList(masterTaskList));
		
		refreshTaskLabels(null); //set task labels blank
		initDateLabels(); //set date for today
		initTomorrowTaskTableView();
	}
}
