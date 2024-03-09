package edu.ou.cs2334.project4;

import java.util.List;

import edu.ou.cs2334.project4.presenters.NonogramMakerPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is the main driver of the program. It takes in arguments as the user
 * starts the program and triggers the necessary components in other classes to 
 * display the Nonogram Maker and all of its capabilities
 * 
 * @author Blake Lee
 *
 */
public class Main extends Application{

	private static int IDX_NUM_ROWS = 0;
	private static int IDX_NUM_COLS = 1;
	private static int IDX_CELL_SIZE = 2;
	
	/**
	 * Launches the program and creates the overarching Nonogram application
	 * @param args NumRows, NumCols, and CellLength desired, separated by a space
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Uses all of the program's methods to construct the application scene/stage and show the interface to the user
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		List<String> data = getParameters().getRaw();
				
		NonogramMakerPresenter presenter = new NonogramMakerPresenter(Integer.parseInt(data.get(IDX_NUM_ROWS)), Integer.parseInt(data.get(IDX_NUM_COLS)),
																		Integer.parseInt(data.get(IDX_CELL_SIZE)));
		
		Scene scene = new Scene(presenter.getPane());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Nonogram Builder");
		scene.getStylesheets().add("/style.css");
		primaryStage.sizeToScene();
		primaryStage.show();
		
	}

}
