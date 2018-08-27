package program.javafx;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 * <h2>JavaFX Hello World Application</h2>
 * 	<p>This application creates a window with a button.</br>
 * 			Once the button is clicked it will make a JOptionPane</br>
 *  			with the message Hello World!</p>
 * @author Dylan Dewald
 *
 */
public class JavaFXExample extends Application{ 
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * <h2>The starting point for the Application</h2>
	 * 	<p>This will create essential components to the Application.</br>
	 * 			These components include the Stage, Scene, and a layout type manager(StackPane).</br>
	 * 			The Button is for a user to click and get a message in return.
	 * 	</p>
	 */
	@Override
	public void start(Stage window) throws Exception {
		window.setTitle("Hello JavaFX!");
		Button helloButton = new Button("Hello World!");
		helloButton.setOnAction( event -> JOptionPane.showMessageDialog(null,"Hello World"));
		
		StackPane root = new StackPane();
		root.getChildren().add(helloButton);
		window.setScene(new Scene(root,240,300));
		window.show();
	}
}
