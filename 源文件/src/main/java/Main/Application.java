package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @FXML
    public Button analyseBt = new Button();
    public Button checkBt = new Button();
    public Button exitApplicationBt = new Button();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Java 源代码注释及关键字分析");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @FXML
    public void analyze(ActionEvent actionEvent) {
        Stage thisStage = (Stage) analyseBt.getScene().getWindow();
        thisStage.close();
        Analyze temp = new Analyze();
        try {
            temp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Overlook(ActionEvent actionEvent) {
        Stage thisStage = (Stage) checkBt.getScene().getWindow();
        thisStage.close();
       // Overlook temp = new Overlook();
        FileMenu temp = new FileMenu();
        try {
            temp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void exitApplication(ActionEvent actionEvent) {
        Stage thisStage = (Stage) exitApplicationBt.getScene().getWindow();
        thisStage.close();
    }
}