/**
 * Copyright (C), 2015-2019, 南昌大学软件学院1807班
 * FileName: MyController
 * Author:   肖海军
 * Date:     2019/10/31 21:04
 * Description: fersrfew
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package cn.edu.ncu.java.view.gui;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 〈一句话功能简述〉</br> 
 * 〈fersrfew 〉
 * @author 肖海军
 * @create 2019/10/31
 * @since 1.0.0
 **/
public class LoginController {// implements Initializable{//
    @FXML
    Button confirm;
    @FXML AnchorPane root;
    @FXML Button cancel;
    @FXML Button forget;
    @FXML
    PasswordField userPwd;
    @FXML
    TextField userAccount;
    @FXML
    Label Account;
    @FXML Label pwd;
    String name;
    String password;

   public void login(ActionEvent event) throws Exception {
       userAccount.setUserData("123456");//设置默认账号密码
       userPwd.setUserData("000000");
       confirm.setOnAction(event1 -> {

        name=userAccount.getText();
        password=userPwd.getText();
         // if(name.equals(userAccount.getText())&&password==Integer.valueOf(userPwd.getText())){
                if(name.equals(userAccount.getUserData())&&password.equals(userPwd.getUserData())){
              Stage primaryStage = (Stage) confirm.getScene().getWindow();//将登录按钮)与Main类中的primaryStage(新窗口)绑定 并执行close()
              primaryStage.close();//打开新的窗口 所以要关闭当前的窗口
              Index we = new Index();//新窗口类
              Stage stage = new Stage();
              // MainApplication.stage.close();
              //  (new MainApp()).start(new Stage());
              try {
                  we.start(stage);//打开新窗口
              } catch (Exception e) {
                  e.printStackTrace();
              }
          } else {
              FadeTransition fade=new FadeTransition();
              fade.setDuration(Duration.seconds(0.5));
              fade.setNode(root);
              fade.setFromValue(0);
              fade.setToValue(1);
              fade.play();
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("错误提示");
              alert.setHeaderText(null);
              alert.setContentText("用户名或密码错误,请重新输入");
              alert.showAndWait();
              userAccount.clear();
              userPwd.clear();
          }
          });
   }
   public void forget(ActionEvent event)throws Exception{
       forget.setOnAction(event1 -> {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("密码");
           alert.setHeaderText("提示");
           userAccount.setUserData("123456");//设置默认账号密码
           userPwd.setUserData("000000");
           alert.setContentText("账户为："+userAccount.getUserData()+"密码为："+userPwd.getUserData());
           alert.showAndWait();
   });
   }
    public void close(ActionEvent event) throws Exception {
        cancel.setOnAction(event1 -> {
            userAccount.setText("");
            userPwd.setText("");
        });
    }


}

 /*  MenuItem menuItem = new MenuItem("Open");
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            System.out.println("Opening Database Connection...");
        }
    });*/
//menuItem.setGraphic(new ImageView(new Image("flower.png")));

  /* private Button myButton;
    private TextField myTextField;
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void showDateTime(ActionEvent event) {
        System.out.println("Button Clicked!");

        Date now= new Date();

        DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        String dateTimeString = df.format(now);

        myTextField.setText(dateTimeString);

    }


    @FXML
    private Button confirm;
    private Button register;
    private Button cancel;
    private PasswordField userPwd;
    private TextField userAccount;
    private Label Account;
    private Label pwd;
    private String name="123456";
    private String password ="1234";
    private Button register1;
    private Button cancel1;
    private PasswordField userPwd1;
    private TextField userAccount1;

     confirm.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(userPwd.getText().equals(name)&&userAccount.getText().equals(password)){
                // if (MyLogin.findUser(userAccount.getText(), userPwd.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("成功提示");
                alert.setHeaderText(null);
                alert.setContentText("登录成功");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误提示");
                alert.setHeaderText(null);
                alert.setContentText("用户名或密码错误,请重新输入");
                alert.showAndWait();
                userAccount.clear();
                userPwd.clear();
            }
        }

    });
        cancel1.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            primaryStage.close();
        }
    });
    //注册按钮实现函数
       register1.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Stage dialog = new Stage();
            dialog.setTitle("登录界面");
            dialog.setScene(new Scene(root1, 600, 500));
            // dialog.show();
            //Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(primaryStage);
            // User user = MyLogin.newUser();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //  alert.setTitle("成功提示");
            // alert.setHeaderText(null);
            //  alert.setContentText("您的账户为: " + user.getAccount());
            //  alert.showAndWait();
            //   dialog.setTitle("设置密码");
            //    PasswordField newPwd = new PasswordField();
            //   Label label = new Label("密码");
            //   Button confirm = new Button("确认");
            confirm.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    name=userPwd1.getText();
                    password=userAccount.getText();
                    alert.setTitle("成功提示");
                    alert.setHeaderText(null);
                    alert.setContentText("注册成功,请返回登录");
                    // user.setPwd(newPwd.getText());
                    ////  MyLogin.addNewUser(user);
                    alert.showAndWait();
                    dialog.close();
                    //    userAccount.setText(user.getAccount());
                    //  userPwd.clear();
                }
            });
            cancel1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialog.close();
                }
            });
        }
    });*/
// name=(String)userAccount.getUserData();
//  password=(int)userPwd.getUserData();
        /*  name=userAccount.getText();//获取当前输入的账号密码
        password=userPwd.getText();
             if (name.equals(userAccount.getUserData()) && password.equals(userPwd.getUserData())) {//判断是否正确
                   System.out.println("登录成功!");
                   Stage primaryStage = (Stage) confirm.getScene().getWindow();//将submit(登录按钮)与Main类中的primaryStage(新窗口)绑定 并执行close()
                   primaryStage.close();//打开新的窗口 所以要关闭当前的窗口
                   MainApp we = new MainApp();//新窗口类
                   Stage stage = new Stage();
                   try {
                       we.start(stage);//打开新窗口
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               } else {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("错误");
                   alert.setContentText("账号密码错误");
                   alert.showAndWait();

               }*/