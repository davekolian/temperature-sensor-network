package clientCode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("housefxSimple.fxml"));
        stage.setTitle("Sudoku Solver");
        stage.setScene(new Scene(root, 830, 520));
        //stage.getIcons().add(new Image(Main.class.getResourceAsStream("sudoku.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
