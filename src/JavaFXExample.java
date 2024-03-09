import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// All JavaFX applications must extend the Application class, which is abstract.
public class JavaFXExample extends Application {

	public static void main(String[] args) {
		// Launch the application. This method creates a JavaFXExample object
		// and calls its start method.
		launch(args);
	}

	// The start method is the entry point for any JavaFX application. It is
	// the only abstract method of the Application class and must be overridden.
	//
	// The launch method passes the start method a reference to the primary
	// stage of the application. This stage is the window that displays the
	// application content.
	@Override
	public void start(Stage primaryStage) throws Exception {

		// The graphical components of a JavaFX application are stored in
		// scenes. One such component is a pane. The next two lines create a
		// Scene object that contains an empty Pane object.
		Pane pane = new Pane();
		Scene scene = new Scene(pane);

		// The next three lines add the scene to the application stage, set the
		// text in the title bar, and display the window. Note that only one
		// scene can be displayed in a stage at a time.
		primaryStage.setScene(scene);
		primaryStage.setTitle("JavaFX Example");
		primaryStage.show();
	}
}

