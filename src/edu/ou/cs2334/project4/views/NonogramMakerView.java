package edu.ou.cs2334.project4.views;

import java.util.HashMap;

import javafx.scene.control.Menu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
/**
 * This class provides all of the front-end implementation of this Nonogram builder,
 * including all of the initializations needed to create a BorderPane that holds the MenuBar,
 * the MenuBar's MenuItems, and all of the buttons in the main frame
 * @author Blake Lee
 *
 */
public class NonogramMakerView {
	private BorderPane borderPane;
	private MenuBar menubar;
	private CellGridView cellGridView;
	private HashMap<String, MenuItem> menuItemsMap;
	
	public static final String MENU_ITEM_OPEN = "MENU_ITEM_OPEN";
	public static final String MENU_ITEM_SAVE = "MENU_ITEM_SAVE";
	public static final String MENU_ITEM_EXIT = "MENU_ITEM_EXIT";

	/**
	 * Constructor that initializes all values for the presenter pane of the Nonogram shown to users
	 * Includes the main Nonogram pane and a MenuBar that gives options for file Opening, Saving, and Exiting
	 * @param numRows Number of Rows desired in Nonogram
	 * @param numCols Number of Columns desired in Nonogram
	 * @param cellLength Size of each square in Nonogram pane
	 */
	public NonogramMakerView(int numRows, int numCols, int cellLength) {
		borderPane = new BorderPane();
		cellGridView = new CellGridView(numRows, numCols, cellLength);
		initMenuBar();
		borderPane.setTop(menubar);
		borderPane.setCenter(cellGridView.getPane());
	}
	
	private void initMenuBar() {
		Menu menu = new Menu("_File");
		MenuItem open = new MenuItem("_Open");
		MenuItem save = new MenuItem("_Save");
		MenuItem exit = new MenuItem("_Exit");
		menu.getItems().add(open);
		menu.getItems().add(save);
		menu.getItems().add(exit);
		menuItemsMap = new HashMap<String, MenuItem>();
		menuItemsMap.put(MENU_ITEM_OPEN, open);
		menuItemsMap.put(MENU_ITEM_SAVE, save);
		menuItemsMap.put(MENU_ITEM_EXIT, exit);
		exit.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		
		menubar = new MenuBar();
		menubar.getMenus().add(menu);
	}
	
	/**
	 * 
	 * @param name String key that is tied to a MenuItem Object
	 * @return MenuItem object represented by/tied to the String key passed to the method
	 */
	public MenuItem getMenuItem(String name) {
		return menuItemsMap.get(name);
	}
	
	/**
	 * 
	 * @return The entirety of the Nonogram pane created by this class including main Nonogram selection area and MenuBar
	 */
	public Pane getPane() {
		return borderPane;
	}
	
	/**
	 * Initializes the ToggleButtons for the Nonogram plane
	 * @see CellGridView's "initButtons" method
	 */
	public void initButtons(int numRows, int numCols, int cellLength) {
		cellGridView.initButtons(numRows, numCols, cellLength);
	}
	
	/**
	 * Returns the specific ToggleButton located at this location in the grid
	 * @param row: Row index of the button desired
	 * @param col: Column index of the button desired
	 * @return ToggleButton at the index of the row and column passed
	 */
	public ToggleButton getToggleButton(int row, int col) {
		return cellGridView.getToggleButton(row, col);
	}
	
	/**
	 * 
	 * @return Number of rows in the Nonogram grid
	 */
	public int getNumRows() {
		return cellGridView.getNumRows();
	}
	
	/**
	 * 
	 * @return Number of columns in the Nonogram grid
	 */
	public int getNumCols() {
		return cellGridView.getNumCols();
	}
}
