package edu.ou.cs2334.project4.handlers;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project4.interfaces.Saveable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;
/**
 * This class extends the AbstractBaseHandler class and implements its handle() method,
 * allowing users to save the current Nonogram they are working on to a txt file in the file
 * system
 * 
 * @author Blake Lee
 *
 */
public class SaveHandler extends AbstractBaseHandler implements EventHandler<ActionEvent>{
	private Saveable saver;
	
	/**
	 * 
	 * Constructor that initializes window, filechooser, and saveable objects passed to it
	 * @param window: Window to display filechooser
	 * @param fileChooser: Object allowing user to interact with File System
	 * @param saver: Allows access to "save" method that handles actions after saving
	 */
	public SaveHandler(Window window, FileChooser fileChooser, Saveable saver) {
		super(window, fileChooser);
		this.saver = saver;
	}
	
	/**
	 * Handles the event created when "Save" file option is selected by user
	 * Opens File System to allow user to save their file
	 */
	public void handle(ActionEvent event) {
		File file = fileChooser.showSaveDialog(window);
			try {
				saver.save(file.getName());
			} catch (IOException e) {
			}
	}
}
