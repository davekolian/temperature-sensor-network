package clientCode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("housefxSimple.fxml"));
        Parent root = loader.load();
        stage.setTitle("Smart Home Temperature Measure");
        stage.setScene(new Scene(root, 941, 617));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("clientLogo.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
