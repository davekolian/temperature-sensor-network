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
        stage.setScene(new Scene(root, 330, 590));
        //stage.getIcons().add(new Image(Main.class.getResourceAsStream("sudoku.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
