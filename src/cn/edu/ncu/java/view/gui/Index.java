/**
 * Copyright (C), 2015-2019, 南昌大学软件学院1807班
 * FileName: Test
 * Author:   肖海军
 * Date:     2019/11/9 22:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package cn.edu.ncu.java.view.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 〈一句话功能简述〉</br> 
 * 〈〉
 * @author 肖海军
 * @create 2019/11/9
 * @since 1.0.0
 **/
public class Index extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("主界面");
         Group root1 = new Group();
        BorderPane pane = new BorderPane();
        root1.getChildren().add(pane);

        //测试嵌入式调用
        Media player = Media.newInstance(getClass().getResource("images/TestMedia.MP4").toString());
        pane.setCenter(player);
        pane.setAlignment(player,Pos.CENTER_LEFT);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/timg.gif")));
      //  root1.setStyle("  -fx-background-image: url(images/background.png);");
      //  pane. setStyle("  -fx-background-image: url(images/background.png);");
      //  pane.getStylesheets().add(getClass().getResource("MainStyle.css").toExternalForm());
        primaryStage.setScene(new Scene(root1, 1200, 750));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
