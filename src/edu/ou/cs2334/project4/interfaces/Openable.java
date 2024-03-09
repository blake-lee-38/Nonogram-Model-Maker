package edu.ou.cs2334.project4.interfaces;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 * This interface provides an "Open" method stub, meant for
 * implementing classes to allow user to open a file from the
 * File System
 * 
 * @author Blake Lee
 *
 */
public interface Openable {
	void open(File file) throws FileNotFoundException;
}
