package Main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FileMenu {
    public Stage stage = new Stage();

    public void start() throws IOException {
        AnchorPane an = new AnchorPane();
        VBox vbox = new VBox();
        ObservableList<String> list = FXCollections.observableArrayList();
        String text;
        for (int i = 0; !Data.storage.isEmpty() && i < Data.storage.size(); i++) {
            text = Data.storage.get(i).getName();
            list.add(text);
        }
        ListView<String> listview = new ListView<String>();
        //添加一个可观测的列表显示
        listview.setItems(list);
        Button b1 = new Button("返回");
        Button b2 = new Button("选择");
        b2.setPrefWidth(300);
        b2.setPrefHeight(23);
        b1.setPrefWidth(300);
        b1.setPrefHeight(23);
        vbox.getChildren().addAll(b1, b2);
        //设置默认选择项
        listview.getSelectionModel().select(0);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage thisStage = stage;
                thisStage.close();
                Application temp = new Application();
                try {
                    temp.start(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //获得ListView中选中数据的可视化集合
                String theName = listview.getSelectionModel().getSelectedItem();
                Stage thisStage = stage;
                thisStage.close();
                Overlook temp = new Overlook();
                try {
                    temp.start(theName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //单选事件	item的监听
        listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // TODO Auto-generated method stub
                //System.out.println(newValue);
            }
        });
        //单选事件	索引的监听
        listview.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // TODO Auto-generated method stub
                //System.out.println(newValue.intValue());
            }
        });
        //设置单元格尺寸
        listview.setFixedCellSize(50);

        an.getChildren().addAll(listview, vbox);
        AnchorPane.setTopAnchor(listview, 50.0);
        AnchorPane.setLeftAnchor(listview, 20.0);
        Scene scene = new Scene(an);

        stage.setScene(scene);
        stage.setTitle("查看已分析文件");
        stage.setHeight(500);
        stage.setWidth(300);
        stage.show();

    }

}
