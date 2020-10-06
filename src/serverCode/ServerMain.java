package serverCode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("serverfxml.fxml"));
        stage.setTitle("Server");
        stage.setScene(new Scene(root, 380, 630));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
