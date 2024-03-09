package edu.ou.cs2334.project4.handlers;

import edu.ou.cs2334.project4.models.NonogramMakerModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;

/**
 * 
 * This class creates Event Handlers to handle the user's interaction with
 * each specific ToggleButton, helping connect the front-end user interaction
 * to the back-end data holding the Nonogram grid
 * 
 * @author Blake Lee
 *
 */
public class ToggleButtonEventHandler implements EventHandler<ActionEvent>
{
	private NonogramMakerModel model;
	private int rowIdx;
	private int colIdx;
	
	/**
	 * Constructor that creates an ToggleButtonEventHandler object used to monitor
	 * each ToggleButton and react when an event is triggered (Button is selected) 
	 * @param model NonogramMakerModel whose grid is updated when event is triggered
	 * @param rowIdx: Row index of specific ToggleButton tied to this instance
	 * @param colIdx: Column index of specific ToggleButton tied to this instance
	 */
	public ToggleButtonEventHandler(NonogramMakerModel model, int rowIdx, int colIdx) {
		this.model = model;
		this.rowIdx = rowIdx;
		this.colIdx = colIdx;
	}
	
	/**
	 * When an event is triggered by the ToggleButton, this method gets the source Button and sets
	 * the corresponding index in the NonogramMakerModel grid to the selection made
	 */
	public void handle(ActionEvent event) {
		boolean result = ((ToggleButton)event.getSource()).isSelected();
		model.setCell(rowIdx, colIdx, result);
	}
}
