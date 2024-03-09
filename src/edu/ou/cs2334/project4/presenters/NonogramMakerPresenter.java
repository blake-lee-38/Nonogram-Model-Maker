package edu.ou.cs2334.project4.presenters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ou.cs2334.project4.handlers.OpenHandler;
import edu.ou.cs2334.project4.handlers.SaveHandler;
import edu.ou.cs2334.project4.handlers.ToggleButtonEventHandler;
import edu.ou.cs2334.project4.interfaces.Openable;
import edu.ou.cs2334.project4.interfaces.Saveable;
import edu.ou.cs2334.project4.models.NonogramMakerModel;
import edu.ou.cs2334.project4.views.NonogramMakerView;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * This class is the main connector of the program, acting as the intersection
 * between the model and view classes in order to facilitate their interaction
 * 
 * @author Blake Lee
 *
 */
public class NonogramMakerPresenter implements Openable, Saveable{
	private NonogramMakerView view;
	private NonogramMakerModel model;
	private int cellLength;
	private final EventType<ActionEvent> event = new EventType<ActionEvent>("event");

	
	/**
	 * Constructs a NonogramMakerPresenter object, and subsequently, a NonogramMakerModel and 
	 * NonogramMakerView object with the parameters given. Initializes and carries out the
	 * logic and actions needed to correctly link the Model and View to each other
	 *
	 * 
	 * @param numRows Number of rows desired in Nonogram
	 * @param numCols Number of columns desired in Nonogram
	 * @param cellLength Size of each cell/square in Nonogram
	 */
	public NonogramMakerPresenter(int numRows, int numCols, int cellLength) {
		this.cellLength = cellLength;
		view = new NonogramMakerView(numRows, numCols, cellLength);
		model = new NonogramMakerModel(numRows, numCols);
		init();
	}
	
	private Window getWindow() {
		try {
			return view.getPane().getScene().getWindow();
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	private void init() {
		initToggleButtons();
		bindToggleButtons();
		configureMenuItems();
	}
	
	private void initToggleButtons() {
		view.initButtons(model.getNumRows(), model.getNumCols(), cellLength);
		if(getWindow() != null) getWindow().sizeToScene();
	}
	
	private void bindToggleButtons() {
		for(int row = 0; row < model.getNumRows(); ++row) {
			for(int col = 0; col < model.getNumCols(); ++col) {
				view.getToggleButton(row, col).setSelected(model.getCell(row, col));
				view.getToggleButton(row, col).addEventHandler(event, new ToggleButtonEventHandler(model, row, col));
			}
		}
	}
	
	private void configureMenuItems() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.setInitialDirectory(new File("."));
		view.getMenuItem(NonogramMakerView.MENU_ITEM_OPEN)
		    .setOnAction(new OpenHandler(getWindow(), fileChooser, this));
		
		fileChooser = new FileChooser();
		fileChooser.setTitle("Save");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.setInitialDirectory(new File("."));
		view.getMenuItem(NonogramMakerView.MENU_ITEM_SAVE)
		    .setOnAction(new SaveHandler(getWindow(), fileChooser, this));
	}
	
	/**
	 * 
	 * @return The Pane object associated with this Nonogram
	 */
	public Pane getPane() {
		return view.getPane();
	}
	
	/**
	 * Implements Openable's "open" method and initializes the Model and 
	 * View objects according to the file's contents
	 */
	public void open(File file) throws FileNotFoundException{
		model = new NonogramMakerModel(file);
		init();
	}
	
	/**
	 * Saves the current Nonogram to a file given by the parameter "filename"
	 */
	public void save(String filename) throws IOException {
		model.saveToFile(filename);
	}
}
