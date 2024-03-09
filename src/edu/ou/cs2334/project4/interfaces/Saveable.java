package edu.ou.cs2334.project4.interfaces;

import java.io.IOException;

/**
 * 
 * This interface provides a "save" method stub, meant to allow
 * implementing classes to allow users to save a file to the file
 * system
 * 
 * @author Blake Lee
 *
 */
public interface Saveable {
	void save(String filename) throws IOException;
}
