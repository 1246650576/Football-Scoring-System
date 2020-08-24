/**
 * Copyright (C), 2015-2019, 南昌大学软件学院1807班
 * FileName: MainApplication
 * Author:   肖海军
 * Date:     2019/11/1 16:21
 * Description: hdfrhg
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package cn.edu.ncu.java.view.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 〈一句话功能简述〉</br> 
 * 〈hdfrhg 〉
 * @author 肖海军
 * @create 2019/11/1
 * @since 1.0.0
 **/
public class Login extends Application {


    @FXML

    public static void main(String[] args)
    {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
     Parent root = FXMLLoader.load(getClass().getResource("images/MainWindow.fxml"));
     primaryStage.setTitle("登录界面");
     primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/timg.gif")));
     primaryStage.setResizable(false);//窗口不可改变高度 宽度 这样就不用调节自适应了
        //E:\CodeOfProgramming\Intellij_IDE_Java\footballs\src\cn\edu\ncu\java\view\images
     primaryStage.show();



    }



}
