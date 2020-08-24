/**
 * Copyright (C), 2015-2019, 南昌大学软件学院1807班
 * FileName: IndexController
 * Author:   肖海军
 * Date:     2019/11/10 18:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package cn.edu.ncu.java.view.gui;

import cn.edu.ncu.java.view.footballplayer.Main2;
import cn.edu.ncu.java.view.footballgoal.Main4;
import cn.edu.ncu.java.view.footballteam.Main3;
import cn.edu.ncu.java.view.matchA.Main5;
import cn.edu.ncu.java.view.matchB.Main6;
import cn.edu.ncu.java.view.matchC.Main7;
import cn.edu.ncu.java.view.matchD.Main8;
import cn.edu.ncu.java.view.matchE.Main9;
import cn.edu.ncu.java.view.matchF.Main10;
import cn.edu.ncu.java.view.matchH.Main12;
import cn.edu.ncu.java.view.matchH1.Main14;
import cn.edu.ncu.java.view.matchH2.Main122;
import cn.edu.ncu.java.view.matchJ.Main13;
import cn.edu.ncu.java.view.matchJ2.Main16;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈一句话功能简述〉</br> 
 * 〈〉
 * @author 肖海军
 * @create 2019/11/10
 * @since 1.0.0
 **/
public class IndexController {
    @FXML TextField myTextField;
    @FXML Button playBT;
    @FXML Button stopBT;
    @FXML Button maxBT;
    @FXML Button volumeBT;
    @FXML Label timeLB;
    @FXML Slider processSD;
    @FXML Slider volumeSD;
    @FXML MediaView mediaView;
    @FXML VBox controlBar;
    @FXML BorderPane mediaPane;
    @FXML AnchorPane anchorPane;
    @FXML Button footballplayer;
    @FXML Button footballscene;
//    @FXML Button footballjudge;
    @FXML Button footballteam;
    @FXML MenuItem menuitem1;
    @FXML MenuItem menuitem2;
    @FXML MenuItem menuitem3;
    @FXML MenuItem menuitem4;
    @FXML MenuItem menuitem5;
    @FXML Button menuitem67;
    @FXML MenuItem menuitem8;
    @FXML MenuItem menuitem81;
    @FXML MenuItem menuitem82;
    @FXML MenuItem menuitem9;
    @FXML MenuItem menuitem91;
    @FXML MenuItem menuitem92;
    //控件素材图片
    private String playIcon  = getClass().getResource("images/play.png").toString();
    private String pauseIcon  = getClass().getResource("images/pause.png").toString();
    private String stopIcon  = getClass().getResource("images/stop.png").toString();
    private String volOffIcon  = getClass().getResource("images/volume_off.png").toString();
    private String volOnIcon  = getClass().getResource("images/volume_On.png").toString();
    private String maxIcon  = getClass().getResource("images/max.png").toString();
    private MediaPlayer mediaPlayer;
    private Media media;
    private String url;     //资源的url地址
    private Scene scene ;  //父类窗口
    private boolean atEndOfMedia = false;    //记录视频是否处播放到结束
    private final boolean repeat = false;   //记录视频是否重复播放
    private double volumeValue;      //储存静音操作前的音量数据
    private Duration duration ;        //记录视频持续时间
    private int mediaHeight;        //视频资源的尺寸
    private int mediaWidth;
    private int currentHeight;    //当前整个播放器的尺寸
    private int currentWidth;

    public IndexController() {
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    //程序初始化：设置按钮图标
    public void initialize(){
        //设置各控件图标
        setIcon(playBT,playIcon,25);
        setIcon(stopBT,stopIcon,25);
        setIcon(volumeBT,volOnIcon,25);
        setIcon(maxBT,maxIcon,25);
    }
    //程序启动项，传入必要参数
    public void start(String url,int width,int height) throws Exception {
        this.url = url;
        //MediaView设置
        media = new Media(url);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        //设置播放器，在媒体资源加载完毕后，获取相应的数据，设置组件自适应布局
        setMediaPlayer(width,height);
        //设置各组件动作事件
        setMediaViewOnClick();
        setPlayButton();
        setStopButton();
        setVolumeButton();
        setVolumeSD();
        setProcessSlider();
        Footballteam();
//        Footballjudge();
        Footballplayer();
        Footballscene();
        myTextField();
        Menuitem1();
        Menuitem2();
        Menuitem3();
        Menuitem4();
        Menuitem5();
        Menuitem67();
        Menuitem8();
        Menuitem81();
        Menuitem82();
        Menuitem9();
        Menuitem91();
        Menuitem92();
    }
    public void Menuitem1() {
        menuitem1.setOnAction(event1 -> {
            try {
                (new Main5()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void Menuitem2() {
        menuitem2.setOnAction(event1 -> {
            try {
                (new Main6()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void Menuitem3() {
        menuitem3.setOnAction(event1 -> {
            try {
                (new Main7()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void Menuitem4() {
        menuitem4.setOnAction(event1 -> {
            try {
                (new Main8()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void Menuitem5() {
        menuitem5.setOnAction(event1 -> {
            try {
                (new Main9()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void Menuitem67() {
        menuitem67.setOnAction(event1 -> {
            try {
                (new Main10()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void Menuitem8() {
        menuitem8.setOnAction(event1 -> {
            try {
                (new Main12()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void Menuitem81() {
        menuitem81.setOnAction(event1 -> {
            try {
                (new Main14()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void Menuitem82() {
        menuitem82.setOnAction(event1 -> {
            try {
                (new Main122()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void Menuitem9() {
        menuitem9.setOnAction(event1 -> {
            try {
                (new Main13()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void Menuitem91() {
        menuitem91.setOnAction(event1 -> {
            try {
                (new Main16()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void Menuitem92() {
        menuitem92.setOnAction(event1 -> {
            try {
                (new Main16()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public void myTextField() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        myTextField.setEditable(false);
        myTextField.setBorder(null);
        EventHandler<ActionEvent> eventHandler = e -> {
            myTextField.setText(df.format(new Date()));
                };
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }
    public void Footballplayer()  {
      footballplayer.setOnAction(event1 -> {
          try {
                (new  Main2()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void Footballteam() {
         footballteam.setOnAction(event1 -> {
            try {
                (new  Main3()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }});
    }
 /*   public void Footballjudge() {
        footballjudge.setOnAction(event1 -> {
            try {
                (new  Main1()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }});
    }*/
    public void Footballscene()  {
     footballscene.setOnAction(event1 -> {
            try {
                (new Main4()).start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }});
    }
    //设置mediaPlayer(参数：整个播放器的尺寸)
    void setMediaPlayer(int width,int height){
        mediaPlayer.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        //视频就绪时更新 进度条 、时间标签、音量条数据,设置布局尺寸
        mediaPlayer.setOnReady(new Runnable(){
            @Override
            public void run() {
                duration = mediaPlayer.getMedia().getDuration();
                volumeValue = mediaPlayer.getVolume();

                mediaHeight = media.getHeight();
                mediaWidth = media.getWidth();

                //设置布局尺寸
                setSize(width,height);

                //设置尺寸随窗口改变自适应变化（只使用于弹窗）

                if (scene!= null) {
                    scene.widthProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                            setSize(newValue.intValue(),currentHeight);}
                    });
                    scene.heightProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                            setSize(currentWidth,newValue.intValue());
                        }
                    });
                }
                //设置全屏时的UI变化:工具栏只有在鼠标进入MediaView时才出现
                EventHandler onScreen = new EventHandler<InputEvent>(){
                    @Override
                    public void handle(InputEvent event) {
                        controlBar.setVisible(true);
                    }
                };
                EventHandler offScreen = new EventHandler<InputEvent>(){
                    @Override
                    public void handle(InputEvent event) {
                        controlBar.setVisible(false);
                    }
                };
                if(scene != null ){
                    ((Stage)scene.getWindow()).fullScreenProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            if (newValue.booleanValue()) {
                                controlBar.setVisible(false);
                                mediaPane.addEventHandler(MouseEvent.MOUSE_CLICKED, onScreen);
                                controlBar.addEventHandler(MouseEvent.MOUSE_EXITED, offScreen);
                            }else{
                                controlBar.setVisible(true);
                                mediaPane.removeEventHandler(MouseEvent.MOUSE_CLICKED,onScreen);
                                controlBar.removeEventHandler(MouseEvent.MOUSE_EXITED,offScreen);
                            }
                        }
                    });
                }
                updateValues();
            }
        });
        //mediaPlayer当前进度发生改变时候，进度条 、时间标签、音量条数据
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>(){
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                updateValues();
            }
        });
    }
    //设置点击MediaView时暂停或开始
    private void setMediaViewOnClick(){
        mediaView.setOnMouseClicked(event -> {
            if(media == null)
                return;
            MediaPlayer.Status status = mediaPlayer.getStatus();
            if(status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED ){
                return;
            }
            //当资源处于暂停或停止状态时
            if(status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY || status == MediaPlayer.Status.STOPPED){
                //当资源播放结束时，重绕资源
                if(atEndOfMedia){
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    atEndOfMedia = false;
                }
                mediaPlayer.play();
                setIcon(playBT,pauseIcon,25);
            }else{   //当资源处于播放状态时
                mediaPlayer.pause();
                setIcon(playBT,playIcon,25);
            }
        });
    }

    //设置播放按钮动作
    private void setPlayButton(){
        playBT.setOnAction((ActionEvent e)->{
            if(media == null)
                return;
            MediaPlayer.Status status = mediaPlayer.getStatus();
            if(status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED ){
                return;
            }
            //当资源处于暂停或停止状态时
            if(status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY || status == MediaPlayer.Status.STOPPED){
                //当资源播放结束时，重绕资源
                if(atEndOfMedia){
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    atEndOfMedia = false;
                }
                mediaPlayer.play();
                setIcon(playBT,pauseIcon,25);
            }else{   //当资源处于播放状态时
                mediaPlayer.pause();
                setIcon(playBT,playIcon,25);
            }
        });
    }

    //设置停止按钮动作
    private void setStopButton(){
        stopBT.setOnAction((ActionEvent e )->{
            if(media == null)
                return;
            mediaPlayer.stop();
            setIcon(playBT,playIcon,25);
        } );
    }

    //设置视频进度条动作
    private void setProcessSlider(){
        processSD.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(processSD.isValueChanging()){     //加入Slider正在改变的判定，否则由于update线程的存在，mediaPlayer会不停地回绕
                    mediaPlayer.seek(duration.multiply(processSD.getValue()/100.0));
                }
            }
        });
    }

    //设置音量按钮动作
    private void setVolumeButton(){
        volumeBT.setOnAction((ActionEvent e)->{
            if(media == null)
                return;

            if(mediaPlayer.getVolume()>0){
                volumeValue = mediaPlayer.getVolume();
                volumeSD.setValue(0);
                setIcon(volumeBT,volOffIcon,25);
            }else{
                mediaPlayer.setVolume(volumeValue);
                volumeSD.setValue(volumeValue * 100);
                setIcon(volumeBT,volOnIcon,25);
            }
        });
    }

    //设置音量滑条动作
    private void setVolumeSD(){
        volumeSD.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(newValue.doubleValue()/100);
            }
        });
    }

    //更新视频数据（进度条 、时间标签、音量条数据）
    protected void updateValues(){
        if(processSD != null && timeLB!=null && volumeSD != null && volumeBT != null){
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    Duration currentTime = mediaPlayer.getCurrentTime();
                    timeLB.setText(formatTime(currentTime,duration));    //设置时间标签
                    processSD.setDisable(duration.isUnknown());   //无法读取时间是隐藏进度条
                    if(!processSD.isDisabled() && duration.greaterThan(Duration.ZERO) && !processSD.isValueChanging()){
                        processSD.setValue(currentTime.toMillis()/duration.toMillis() * 100);   //设置进度条
                    }
                    if(!volumeSD.isValueChanging()){
                        volumeSD.setValue((int)Math.round(mediaPlayer.getVolume() *100));   //设置音量条
                        if(mediaPlayer.getVolume() == 0){        //设置音量按钮
                            setIcon(volumeBT,volOffIcon,25);
                        }else{
                            setIcon(volumeBT,volOnIcon,20);
                        }
                    }
                }
            });
        }
    }

    //将Duration数据格式化，用于播放时间标签
    protected String formatTime(Duration elapsed,Duration duration){
        //将两个Duartion参数转化为 hh：mm：ss的形式后输出
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        int elapsedMinutes = (intElapsed - elapsedHours *60 *60)/ 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;
        if(duration.greaterThan(Duration.ZERO)){
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            int durationMinutes = (intDuration - durationHours *60 * 60) / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

            if(durationHours > 0){
                return String.format("%02d:%02d:%02d / %02d:%02d:%02d",elapsedHours,elapsedMinutes,elapsedSeconds,durationHours,durationMinutes,durationSeconds);
            }else{
                return String.format("%02d:%02d / %02d:%02d",elapsedMinutes,elapsedSeconds,durationMinutes,durationSeconds);
            }
        }else{
            if(elapsedHours > 0){
                return String.format("%02d:%02d:%02d / %02d:%02d:%02d",elapsedHours,elapsedMinutes,elapsedSeconds);
            }else{
                return String.format("%02d:%02d / %02d:%02d",elapsedMinutes,elapsedSeconds);
            }
        }
    }

    //为按钮获取图标
    private void setIcon(Button button,String path,int size){
        Image icon = new Image(path);
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(size);
        imageView.setFitHeight((int)(size * icon.getHeight() / icon.getWidth()));
        button.setGraphic(imageView);
        //设置图标点击时发亮
        ColorAdjust colorAdjust = new ColorAdjust();
        button.setOnMousePressed(event ->  {
            colorAdjust.setBrightness(0.5);
            button.setEffect(colorAdjust);
        });
        button.setOnMouseReleased(event -> {
            colorAdjust.setBrightness(0);
            button.setEffect(colorAdjust);
        });
    }
    public MediaPlayer getMediaPlayer(){
        return this.mediaPlayer;
    }

    //设置关闭窗口时的动作，手动释放资源，回收内存
    public void destroy(){
        if(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
            mediaPlayer.stop();
        }
        mediaPlayer.dispose();   //释放meidaPlayer的Media资源
        media = null;
        mediaPlayer = null;
        System.gc();    //通知JVM垃圾回收器

    }


    //设置播放器尺寸
    public void setSize(int width,int height){
        currentWidth = width;
        currentHeight  = height;
        setUISuitable();

    }
    //UI控件自适应大小
    private void setUISuitable(){
        anchorPane.setPrefSize(currentWidth,currentHeight);
        anchorPane.setBottomAnchor(controlBar, -0.-50);    //设置控制条位置
        anchorPane.setTopAnchor(mediaPane,((double)currentHeight  - (double)currentWidth *(double)mediaHeight / (double)mediaWidth - 50)/2);  //设置视频面板位置
        mediaView.setFitWidth(currentWidth);       //设置MediaView尺寸
        // mediaView.setFitWidth(940);
        // mediaView.setFitHeight(470);
        mediaView.setFitHeight((double)currentWidth*(double)mediaHeight / (double)mediaHeight);
        //   mediaView.setFitWidth(1000);
        controlBar.setPrefWidth(currentWidth);  //设置工具条宽度


    }

}
