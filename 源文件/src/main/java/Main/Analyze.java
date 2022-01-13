package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;

public class Analyze {
    public Button confirm = new Button();
    public TextField fileName = new TextField();
    public Label hint = new Label();
    public Button back = new Button();
    public Stage stage = new Stage();

    public void start() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Analyze.class.getResource("Analyze.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("文件分析");
        stage.setScene(scene);
        stage.show();
    }

    public void confirmCheck(ActionEvent actionEvent) {
        Data.reset();
        AnalyzeFile check = new AnalyzeFile(fileName.getText());
        Data.dir = check.getTheFile().getPath();   //获取分析目录
        if (!check.isExist()) {
            hint.setText("提示：输入的目录或文件名不存在");
        } else if (!check.isJava() && check.isFile()) {
            hint.setText("提示：不是 Java 源程序文件");
        } else if (check.isDirectory()) {
            String fileNameNow = check.getName().substring(check.getName().lastIndexOf("\\")+1);
            hint.setText("目录分析结束, 分析结果存放在文件[data\\D_" + fileNameNow + "_Result.txt]!");
        } else {
            String fileNameNow = check.getName().substring(check.getName().lastIndexOf("\\")+1);
            hint.setText("文件分析结束, 分析结果存放在文件[data\\F_" + fileNameNow + "_Result.txt]!");
        }
        if (check.isDirectory()) {
            check.checkALlFile();
        } else if (check.isJava()) {
            check.checkFile(check.getTheFile().getPath());
        }
        Data.check = check;
        Data.setWords();
        Collections.sort(Data.wordLinkedList);
        outputFile.output();
    }

    public void comeBack(ActionEvent actionEvent) {
        Stage thisStage = (Stage) back.getScene().getWindow();
        thisStage.close();
        Application newMenu = new Application();
        try {
            newMenu.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}