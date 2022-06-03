package ra5.eurovision.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(
			 this.getClass().getResource("/ra5/eurovision/vista/GuiFestival.fxml"));
			 BorderPane root = loader.load();

			Scene scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Festival de Eurovisión");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
