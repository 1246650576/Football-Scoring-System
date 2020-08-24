/**
 * Copyright (C), 2015-2019, 南昌大学软件学院1807班
 * FileName: SimpleMediaPlayer
 * Author:   肖海军
 * Date:     2019/11/9 22:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package cn.edu.ncu.java.view.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * 〈一句话功能简述〉</br> 
 * 〈〉
 * @author 肖海军
 * @create 2019/11/9
 * @since 1.0.0
 **/
public class Media extends AnchorPane {
    private static Media media;   //创建实例保存到私有域中
   private IndexController controller;
  protected IndexController getController(){   //提供控制器对象的调用接口
        return this.controller;
       }
    //构造函数私有，实例保存在静态域，只向外部提供静态调用
    private Media(String mediaUrl){
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("images/Scene.fxml"));
            Parent root = fxmlloader.load();   //将fxml节点添加到根节点中
            controller = fxmlloader.getController();

            this.getChildren().add(root);   //主类节点加入根节点


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //实例化调用:默认大小1000*600
    public static Media newInstance(String mediaUrl) throws Exception {
        return newInstance(mediaUrl,1000,600);
    }
    public static Media newInstance(String mediaUrl, int width, int height) throws Exception {
        media = new Media(mediaUrl);
        media.getController().start(mediaUrl,width,height);   //非窗口化启动播放器控件
        return media;
    }

}
