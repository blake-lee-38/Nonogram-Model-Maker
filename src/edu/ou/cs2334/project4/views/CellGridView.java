package edu.ou.cs2334.project4.views;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
/**
 * 
 * This class implements and handles the main grid GUI of the Nonogram
 * puzzle maker, including arranging and initializing buttons as well as
 * offering methods for other classes to access this pane and these buttons
 * 
 * @author Blake Lee
 *
 */
public class CellGridView {
	private ArrayList<ToggleButton> gridButtons;
	private GridPane gridpane;
	private int numRows;
	private int numCols;
	
	/**
	 * Constructor that initializes GridPane with given number of rows and columns
	 * and fills the pane with that amount of ToggleButtons to set up Nonogram
	 * 
	 * @param numRows Number of Rows in Pane for Nonogram
	 * @param numCols Number of Columns in Pane for Nonogram
	 * @param cellLength Side lengths of each square ToggleButton creates
	 */
	public CellGridView(int numRows, int numCols, int cellLength) {
		gridButtons = new ArrayList<ToggleButton>();
		gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		initButtons(numRows, numCols, cellLength);
	}
	
	/**
	 * Method that clears GridPane and fills it with ToggleButtons in the
	 * form of the given number of rows and columns. ToggleButton size is 
	 * determined by cellLength parameter
	 * @param numRows Number of Rows in Pane for Nonogram
	 * @param numCols Number of Columns in Pane for Nonogram
	 * @param cellLength Side lengths of each square ToggleButton creates
	 */
	public void initButtons(int numRows, int numCols, int cellLength) {
		this.numRows = numRows;
		this.numCols = numCols;
		gridButtons.clear();
		gridpane.getChildren().clear();
		for(int i = 0; i < numRows; ++i) {
			for(int j = 0; j < numCols; ++j) {
				ToggleButton next = new ToggleButton();
				next.setPrefHeight(cellLength);
				next.setMaxHeight(cellLength);
				next.setMinHeight(cellLength);
				next.setPrefWidth(cellLength);
				next.setMaxWidth(cellLength);
				next.setMinWidth(cellLength);
				gridpane.add(next, j, i);
				gridButtons.add(next);
			}
		}
	}
	
	/**
	 * 
	 * @return Number of rows in Nonogram's pane
	 */
	public int getNumRows() {
		return numRows;
	}
	
	/**
	 * 
	 * @return Number of columns in Nonogram's pane
	 */
	public int getNumCols() {
		return numCols;
	}
	
	/**
	 * Returns reference to the specific ToggleButton located at this
	 * row and column index in the Nonogram
	 * @param row: Row index of desired ToggleButton
	 * @param col: Column index of desired ToggleButton
	 * @return Reference to ToggleButton located at this index
	 */
	public ToggleButton getToggleButton(int row, int col) {
		return gridButtons.get((row * numCols) + col);
	}
	
	/**
	 * 
	 * @return GridPane that represents Nonogram body filled by ToggleButtons
	 */
	public Pane getPane() {
		return gridpane;
	}
	
}
