package edu.ou.cs2334.project4.handlers;

import java.io.File;
import java.io.FileNotFoundException;

import edu.ou.cs2334.project4.interfaces.Openable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;
/**
 * This class extends the AbstractBaseHandler class and implements its handle()
 * method using an Openable object to display the file system to the user and allow
 * them to choose a file (if it exists)
 * 
 * @author Blake Lee
 *
 */
public class OpenHandler extends AbstractBaseHandler implements EventHandler<ActionEvent>{
	private Openable opener;
	
	/**
	 * Constructor that initializes window, filechooser, and openable objects passed to it
	 * @param window: Window to display filechooser
	 * @param fileChooser: Object allowing user to interact with File System
	 * @param opener: Allows access to "open" method that processes file once opened
	 */
	public OpenHandler(Window window, FileChooser fileChooser, Openable opener) {
		super(window, fileChooser);
		this.opener = opener;
	}
	
	/**
	 * Handles an event created when user selects the "Open" file choice to allow
	 * the user access to the file system and open their selected file
	 */
	public void handle(ActionEvent event){
		File file = fileChooser.showOpenDialog(window);
		try {
			opener.open(file);
		} catch (FileNotFoundException e) {				
		}
	}
}
