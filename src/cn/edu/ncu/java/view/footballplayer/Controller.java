/**
 * Copyright (C), 2015-2019, 南昌大学软件学院1807班
 * FileName: Controller
 * Author:   肖海军
 * Date:     2019/11/10 20:59
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package cn.edu.ncu.java.view.footballplayer;

/**
 * 〈一句话功能简述〉</br> 
 * 〈〉
 * @author 肖海军
 * @create 2019/11/10
 * @since 1.0.0
 **/

import cn.edu.ncu.java.entity.Player;
import cn.edu.ncu.java.service.PlayerManService;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Controller {
    @FXML
    private Button mBtnDelete;
    @FXML
    private TableView mTable;
    @FXML
    private TextField mTxtName;
    @FXML
    private TextField mTxtName1;
    @FXML
    private TextField mTxtSex;
    @FXML
    private TextField mTxtAge;
    @FXML
    private TextField mTxtBirthday;
    @FXML
    private TextField mTxtTeam;
    @FXML
    private TextField mTxtPosition;
    @FXML
    private TextField mTxtNum;
    @FXML
    private TextField mTxtNum1;
    @FXML
    private TextField mTxtHeight;
    @FXML
    private TextField mTxtWeight;
    @FXML
    private TableColumn<Player, Boolean> mColumnSelect;
    @FXML
    private TableColumn<Player, String> mColumnName;
    @FXML
    private TableColumn<Player, String> mColumnSex;
    @FXML
    private TableColumn<Player, String> mColumnAge;
    @FXML
    private TableColumn<Player, String> mColumnBirthday;
    @FXML
    private TableColumn<Player, String> mColumnTeam;
    @FXML
    private TableColumn<Player, String> mColumnPosition;
    @FXML
    private TableColumn<Player, String> mColumnHeight;
    @FXML
    private TableColumn<Player, String> mColumnWeight;
    @FXML
    private TableColumn<Player, String> mColumnNum;

    private ObservableList<Player> data = FXCollections.observableArrayList();
    private PlayerManService playerManService;

  //  public Controller(TableColumn<Player, String> mColumnAge) {
    //    this.mColumnAge = mColumnAge;
  //  }

    public Controller() throws SQLException, IOException, BiffException {
        playerManService = new PlayerManService();
        ArrayList<Player>  list =  playerManService.showAllPlayer();
        if(list!=null) {
            Iterator<Player> iterator = list.iterator();
            while (iterator.hasNext())
                data.add(iterator.next());
        }
    }


    @FXML
    public void onDelete(ActionEvent event) {
        this.deleteStudents();
    }

    @FXML
    private void initialize() {
        this.mColumnSelect.setCellFactory(CellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
            public ObservableValue<Boolean> call(Integer index) {
                final Player g = (Player)Controller.this.mTable.getItems().get(index);
                ObservableValue<Boolean> ret = new SimpleBooleanProperty(g, "selected", g.getSelected());
                ret.addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        g.setSelected(newValue);
                    }
                });
                return ret;
            }
        }));
        Callback<TableColumn<Player, String>, TableCell<Player, String>> cellFactory = (p) -> {
            return new EditingCell();
        };
        this.mColumnSelect.setCellValueFactory(new PropertyValueFactory("selected"));
        this.mColumnName.setCellValueFactory(new PropertyValueFactory("name"));
        this.mColumnName.setCellFactory(cellFactory);
        this.mColumnAge.setCellValueFactory(new PropertyValueFactory("age"));
        this.mColumnAge.setCellFactory(cellFactory);
        this.mColumnTeam.setCellValueFactory(new PropertyValueFactory("team"));
        this.mColumnTeam.setCellFactory(cellFactory);
        this.mColumnNum.setCellValueFactory(new PropertyValueFactory("number"));
        this.mColumnNum.setCellFactory(cellFactory);

        this.mColumnSex.setCellValueFactory(new PropertyValueFactory("gender"));
        this.mColumnSex.setCellFactory(cellFactory);
        this.mColumnPosition.setCellValueFactory(new PropertyValueFactory("position"));
        this.mColumnPosition.setCellFactory(cellFactory);
        this.mColumnHeight.setCellValueFactory(new PropertyValueFactory("height"));
        this.mColumnHeight.setCellFactory(cellFactory);
        this.mColumnWeight.setCellValueFactory(new PropertyValueFactory("weight"));
        this.mColumnWeight.setCellFactory(cellFactory);
       // this.mColumnSex.setOnEditCommit((t) -> {
      //      ((Player)t.getTableView().getItems().get(t.getTablePosition().getRow())).setSex((String)t.getNewValue());
      //  });

        this.mColumnBirthday.setCellValueFactory(new PropertyValueFactory("birthdate"));
        this.mColumnBirthday.setCellFactory(cellFactory);

        this.mTable.setItems(this.data);
        this.mTable.setEditable(true);
    }

    @FXML
    public void onAdd(ActionEvent event) {
        if (this.mTxtName.getText() != null && this.mTxtSex.getText() != null&&
                this.mTxtAge.getText()!=null&&this.mTxtBirthday.getText()!=null&&
                this.mTxtTeam.getText()!=null&&this.mTxtPosition.getText()!=null&&
        this.mTxtNum.getText()!=null&&this.mTxtHeight.getText()!=null&&this.mTxtWeight.getText()!=null) {
            Player player = new Player(
                    this.mTxtName.getText(),
                    this.mTxtAge.getText(),
                    this.mTxtTeam.getText(),
                    this.mTxtSex.getText(),
                    this.mTxtPosition.getText(),
                    this.mTxtNum.getText(),
                    this.mTxtHeight.getText(),
                    this.mTxtWeight.getText() ,
                    this.mTxtBirthday.getText()
            );
            this.data.add(player);
            playerManService.insertPlayer(player);
            this.mTxtName.clear();
            this.mTxtAge.clear();
            this.mTxtTeam.clear();
            this.mTxtNum.clear();
            this.mTxtSex.clear();
            this.mTxtPosition.clear();
            this.mTxtHeight.clear();
            this.mTxtWeight.clear();
          this.mTxtBirthday.clear();
        }

    }
    public void onFind(ActionEvent event) {
        mTxtName1.setUserData("123456");//测试
        mTxtNum1.setUserData("000000");//测试
         String team;
         String num;
         team=mTxtName1.getText();//读入远动原姓名
        num=mTxtNum1.getText();//读入远动员编号
        Player player = playerManService.findPlayer(team,num);
        //这里需要把name和num传入数据集库
        //if(    ){
        if(player!=null){
        //if(mTxtName1.getUserData().equals(mTxtName1.getText())&&mTxtNum1.getUserData().equals(mTxtNum1.getText())){//测试
            //这里需要比对成功进行以下操作
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("远动员详细信息");
            alert.setHeaderText("");
            alert.setWidth(800);
            alert.setHeight(800);
            alert.setContentText(player.toString());
            alert.showAndWait();
            mTxtName1.clear();
            mTxtNum1.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("错误");
            alert.setHeaderText("");
            alert.setContentText("远动员队名与编号或查无此人！");
            alert.showAndWait();
            mTxtName1.clear();
            mTxtNum1.clear();
        }
    }
    public void onDel(ActionEvent event) {
        mTxtName1.setText("");
        mTxtNum1.setText("");
    }
    public boolean deleteStudents() {
        new ArrayList();
        int size = this.data.size();
        if (size <= 0) {
            return false;
        } else {
            for(int i = size - 1; i >= 0; --i) {
                Player s = (Player)this.data.get(i);
                if (s.getSelected()) {
                    this.data.remove(s);
                    playerManService.deletePlayer(s);
                }
            }
            return true;
        }
    }
}
