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
package cn.edu.ncu.java.view.footballteam;

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

public class Main3 extends Application {
    public Main3() {
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("image/footballteam.fxml"));
        primaryStage.setTitle("战队信息");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 846.0, 744.0));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("image/background.png")));

        BorderPane pane = new BorderPane();
        pane.getStylesheets().add(
              getClass().getResource("image/MainStyle.css").toExternalForm());

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

