/**
 * Copyright (C), 2015-2019, 南昌大学软件学院1807班
 * FileName: Main
 * Author:   肖海军
 * Date:     2019/11/10 20:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package cn.edu.ncu.java.view.footballgoal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 〈一句话功能简述〉</br> 
 * 〈〉
 * @author 肖海军
 * @create 2019/11/10
 * @since 1.0.0
 **/

public class Main4 extends Application {
    public Main4() {
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("images1/footballgoal.fxml"));
        primaryStage.setTitle("进球信息");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 770, 665.0));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images1/background.png")));

        BorderPane pane = new BorderPane();
        pane.getStylesheets().add(
                getClass().getResource("images1/MainStyle.css").toExternalForm());

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

