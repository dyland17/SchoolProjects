package program.javafx;
	import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
	import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class JavaFXForm extends Application{
	
	public static void main(String []args) {
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {
		window.setTitle("First Form");
		setupStage(window);
		window.show();
		
	}

	private void setupStage(Stage window) {	
		GridPane grid = setupGridPane();
		//Row 0
		Text title = new Text("Login:");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		title.setId("title-text");
		grid.add(title, 0, 0,2,1);
		//Row 1
		grid.add(new Label("Username: "), 0, 1);
		TextField userField = new TextField();
		grid.add(userField, 1, 1);
		//Row 2
		grid.add(new Label("Password: "), 0, 2);
		PasswordField passField = new PasswordField();
		grid.add(passField, 1, 2);
		//Row 3
		Button submitButton = new Button("Submit");
		submitButton.setOnAction(event ->{ 
			JOptionPane.showMessageDialog(null, "User: " + userField.getText() + "\n"
																					+ "Password: " + passField.getText());
		});
		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().add(submitButton);
		grid.add(hBox, 1, 3);
		Scene gridScene = new Scene(grid,260,280);
		gridScene.getStylesheets().add(JavaFXForm.class.getResource("formStyle.css").toExternalForm());
		window.setScene(gridScene);
	}

	private GridPane setupGridPane() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		return grid;
	}

}
