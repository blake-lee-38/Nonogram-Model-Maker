package edu.ou.cs2334.project4.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/**
 * This class handles all of the back-end logic and processes need to store,
 * update, open, and save the Nonogram
 * 
 * @author Blake Lee
 *
 */
public class NonogramMakerModel {
	private static char EMPTY_CELL_CHAR = '0';
	private static char FILLED_CELL_CHAR = '1';
	
	private int numRows;
	private int numCols;
	private boolean[] grid;
	
	
	/**
	 * Constructor that creates a NonogramMakerModel object with the specified amount of rows and columns
	 * Throws an IllegalArgumentException if either parameter is not greater than 0
	 * @param numRows Number of Desired Rows in Nonogram, Must be greater than 0
	 * @param numCols Number of Desired Columns in Nonogram, Must be greater than 0
	 */
	public NonogramMakerModel(int numRows, int numCols) {
		if(numRows <= 0 || numCols <= 0) throw new IllegalArgumentException();
		this.numRows = numRows;
		this.numCols = numCols;
		grid = new boolean[numRows * numCols];
	}
	
	/**
	 * Constructor that creates a NonogramMakerModel object with the given text file
	 * Reads data according to the format given in the project example
	 * @param file Text file that contains data needed for the initialization of the Nonogram
	 * @throws FileNotFoundException If the file passed to the constructor does not exist or is not accessible
	 */
	public NonogramMakerModel(File file) throws FileNotFoundException {
	//Uses Scanner Rather Than BufferedReader: Allows for easier access to data
	//and because file length is relatively small [2*numRows + numCols + 1] [31 for 10x10 grid]
		Scanner fileScanner = new Scanner(file);
	
	//Gathers Data from 1st Line of Text File, then reads in newline
		numRows = fileScanner.nextInt();
		numCols = fileScanner.nextInt();
		grid = new boolean[numRows * numCols];
		fileScanner.nextLine();
		
	//Cycles through intermediate lines in order to read in last lines later
		for(int i = 0; i < numRows + numCols; ++i) {
			fileScanner.nextLine();
		}
		
	//Uses last lines to populate boolean array using 0s and 1s
		for(int i = 0; i < numRows; ++i) {
			String line = fileScanner.nextLine();
			for(int j = 0; j < numCols; ++j) {
				int next = (i * numCols) + j;
				grid[next] = (line.charAt(j) == FILLED_CELL_CHAR);
			}
		}
		fileScanner.close();
	}
	
	/**
	 * Constructor that creates a NonogramMakerModel object with the given string,
	 * representing the name of a File containing initialization data.
	 * Reads data according to the format given in the project example
	 * @param filename String representing name of text file that contains data needed for the initialization of the Nonogram
	 * @throws FileNotFoundException If the file name passed to the constructor does not reference a valid file
	 */
	public NonogramMakerModel(String filename) throws FileNotFoundException {
		this(new File(filename));
	}
	
	/**
	 * Returns a copy of the boolean grid representing Nonogram values where true
	 * represents a filled in value and false is an empty value
	 * @return boolean grid representing Nonogram values in format described in description
	 */
	public boolean[] getGrid() {
		return Arrays.copyOf(grid, grid.length);
	}
	
	/**
	 * Returns the boolean value of the Nonogram location given (True = Filled In, False = Empty)
	 * @param rowIdx Index of Row for desired cell
	 * @param colIdx Index of Column for desired cell
	 * @return Value of Nonogram cell at location given
	 */
	public boolean getCell(int rowIdx, int colIdx) {
		return grid[(rowIdx * numCols) + colIdx];
	}
	
	/**
	 * Sets the specific cell given by index values to the value given by state
	 * @param rowIdx Index of Row for desired cell
	 * @param colIdx Index of Column for desired cell
	 * @param state New state of desired cell (True = Filled In, False = Empty)
	 */
	public void setCell(int rowIdx, int colIdx, boolean state) {
		grid[(rowIdx * numCols) + colIdx] = state;
	}
	
	/**
	 * Returns the Nonogram puzzle's number of rows
	 * @return Number of rows in Nonogram
	 */
	public int getNumRows() {
		return numRows;
	}
	
	/**
	 * Returns the Nonogram puzzle's number of columns
	 * @return Number of columns in Nonogram
	 */
	public int getNumCols() {
		return numCols;
	}
	
	/**
	 * Returns a list of Integers representing the configuration of the 1D boolean array
	 * passed to it in the format described in the project example where numbers represent groups of cells
	 * and separate numbers means there is a gap in between those groups
	 * @param 1D boolean array of "cells" to be read in and analyzed by method
	 * @return List of results from array of cells given that holds number/group value from left to right or top to bottom
	 */
	public static List<Integer> project(boolean[] cells){
		int count = 0;
		List<Integer> projectList = new LinkedList<Integer>();
		for(int i = 0; i < cells.length; ++i) {
			if(cells[i]) {
				++count;
			} else {
				if(count != 0) projectList.add(count);
				count = 0;
			}
		}
		
	//Extra Check Accounts for Case if last element is true
		if(count != 0) projectList.add(count);
	//In case all elements are false, return 0
		if(projectList.isEmpty()) projectList.add(0);
		
		return projectList;
	}
	
	/**
	 * Constructs an array of the desired row and uses this class's project method to return
	 * a list of Integers representing the groups and configuration of the row
	 * @see NonogramMakerModel "project" class for explanation of number meaning
	 * @param rowIdx indicates the row desired to be projected and returned
	 * @return List of results from row given that holds number/group value from left to right or top to bottom
	 */
	public List<Integer> projectRow(int rowIdx){
		boolean[] list = new boolean[numCols];
		for(int i = 0; i < numCols; ++i) {
			list[i] = grid[rowIdx * numCols + i];
		}
		return project(list);
	}
	
	/**
	 * Constructs an array of the desired column and uses this class's project method to return
	 * a list of Integers representing the groups and configuration of the column
	 * @see NonogramMakerModel "project" class for explanation of number meaning
	 * @param colIdx indicates the column desired to be projected and returned
	 * @return List of results from column given that holds number/group value from left to right or top to bottom
	 */
	public List<Integer> projectCol(int colIdx){
		boolean[] list = new boolean[numRows];
		for(int i = 0; i < numRows; ++i) {
			list[i] = grid[(i * numCols) + colIdx];
		}
		return project(list);
	}
	
	/**
	 * Uses this class's toString() method to save the Nonogram to a text file with the name passed to this method as "filename"
	 * @see This class's toString() method for explanation on how data is stored
	 * @param filename: Name of the text file desired to be created and save Nonogram data
	 * @throws IOException If the filename can not be opened or created for any reason
	 */
	public void saveToFile(String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write(this.toString());
		writer.close();
	}
	
	/**
	 * Returns a String representing the Nonogram's data, including the Nonogram's number of rows and columns,
	 * followed by the projection of each row and column(respectively), then shows each row of the Nonogram
	 * and its data where a 1 represents a filled cell and a 0 represents an empty cell
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
	
	//Step 1: Add numRows and numCols to Top
		result.append(numRows + " " + numCols + System.lineSeparator());
		
	//Step 2: Add Row Projections
		for(int i = 0; i < numRows; ++i) {
			result.append(listToString(projectRow(i)) + System.lineSeparator());
		}
		
	//Step 3: Add Column Projections
		for(int i = 0; i < numCols; ++i) {
			result.append(listToString(projectCol(i)) + System.lineSeparator());
		}
		
	//Step 4: Create 0s and 1s Projections
		for(int row = 0; row < numRows; ++row) {
			for(int col = 0; col < numCols; ++col) {
				if(getCell(row, col)) {
					result.append(FILLED_CELL_CHAR);
				} else {
					result.append(EMPTY_CELL_CHAR);
				}
			}
			result.append(System.lineSeparator());
		}
		return result.toString();
	}
	
	private static String listToString(List<Integer> list) {
		StringBuilder nums = new StringBuilder();
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()) {
			nums.append(" " + iterator.next());
		}
		return nums.deleteCharAt(0).toString();
	}
}
