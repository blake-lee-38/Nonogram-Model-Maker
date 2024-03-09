package edu.ou.cs2334.project4.handlers;

import javafx.stage.FileChooser;
import javafx.stage.Window;
/**
 * 
 * This abstract class provides a constructor to initialize the parent objects for
 * children that implement this class
 * 
 * @author Blake Lee
 */
public abstract class AbstractBaseHandler {
	protected Window window;
	protected FileChooser fileChooser;
	
	/**
	 * Abstract constructor to initialize file handler window and filechooser
	 * @param window: Base window used to show fileChooser
	 * @param fileChooser: Used by implementing classes to allow user to access file system in some way
	 */
	protected AbstractBaseHandler(Window window, FileChooser fileChooser) {
		this.window = window;
		this.fileChooser = fileChooser;
	}
}
