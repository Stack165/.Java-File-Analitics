package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Overlook {
    public Stage stage = new Stage();
    public TextArea list = new TextArea("");
    public Button back = new Button();
    public static File f;

    public void start(String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Analyze.class.getResource("Overlook.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        for(int i=0;i<Data.storage.size();i++){
            if(name.equals(Data.storage.get(i).getName())){
                f=Data.storage.get(i);
            }
        }
        stage.setTitle("查看分析");
        stage.setScene(scene);
        stage.show();

    }

    public void showList() {
        Data.fileRead.clear();
        readFile.read(f);
        String temp = new String();
        if(!Data.fileRead.isEmpty()){
            for (String str : Data.fileRead
            ) {
                temp += str + "\n";
            }
        }
        list.setText(temp);
        list.setEditable(false);
        list.setWrapText(true);
    }

    public void comeBack(ActionEvent actionEvent) {
        Stage thisStage = (Stage) back.getScene().getWindow();
        thisStage.close();
        FileMenu temp = new FileMenu();
        try {
            temp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
