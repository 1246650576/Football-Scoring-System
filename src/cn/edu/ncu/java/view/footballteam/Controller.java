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
package cn.edu.ncu.java.view.footballteam;

/**
 * 〈一句话功能简述〉</br> 
 * 〈〉
 * @author 肖海军
 * @create 2019/11/10
 * @since 1.0.0
 **/

import cn.edu.ncu.java.entity.Player;
import cn.edu.ncu.java.entity.Team;
import cn.edu.ncu.java.service.PlayerManService;
import cn.edu.ncu.java.service.TeamService;
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
    private TextField mTxtCollege;
    @FXML
    private TextField mTxtCoach;
    @FXML
    private TextField mTxtTime;
    @FXML
    private TableColumn<Team, Boolean> mColumnSelect;
    @FXML
    private TableColumn<Team, String> mColumnName;
    @FXML
    private TableColumn<Team, String> mColumnCollege;
    @FXML
    private TableColumn<Team, String> mColumnCoach;
    @FXML
    private TableColumn<Team, String> mColumnTime;
    private final ObservableList<Team> data = FXCollections.observableArrayList();
    private TeamService teamService;

    public Controller() throws SQLException, IOException, BiffException {
        this.teamService = new TeamService();
        ArrayList<Team>  list =  teamService.showAllTeam();
        if (list!=null) {
            Iterator<Team> iterator = list.iterator();
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
                final Team g = (Team) Controller.this.mTable.getItems().get(index);
                ObservableValue<Boolean> ret = new SimpleBooleanProperty(g, "selected", g.getSelected());
                ret.addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        g.setSelected(newValue);
                    }
                });
                return ret;
            }
        }));
        Callback<TableColumn<Team, String>, TableCell<Team, String>> cellFactory = (p) -> {
            return new EditingCelll();
        };
        this.mColumnSelect.setCellValueFactory(new PropertyValueFactory("selected"));
        this.mColumnName.setCellValueFactory(new PropertyValueFactory("name"));
        this.mColumnName.setCellFactory(cellFactory);
        this.mColumnCollege.setCellValueFactory(new PropertyValueFactory("college"));
        this.mColumnCollege.setCellFactory(cellFactory);
        this.mColumnCoach.setCellValueFactory(new PropertyValueFactory("coach"));
        this.mColumnCoach.setCellFactory(cellFactory);
     //   this.mColumnNum.setOnEditCommit((t) -> {
       //     ((Footballteam)t.getTableView().getItems().get(t.getTablePosition().getRow())).setNum((String)t.getNewValue());
     //   });
        this.mColumnTime.setCellValueFactory(new PropertyValueFactory("setTime"));
        this.mColumnTime.setCellFactory(cellFactory);
     

        this.mTable.setItems(this.data);
        this.mTable.setEditable(true);
    }

    @FXML
    public void onAdd(ActionEvent event) {
        if (this.mTxtName.getText() != null && this.mTxtCollege.getText() != null&&this.mTxtCoach.getText()!=null&&this.mTxtTime.getText()!=null) {
            Team team = new Team(this.mTxtName.getText(),this.mTxtCoach.getText(),this.mTxtCollege.getText(),this.mTxtTime.getText());
            this.data.add(team);
            teamService.insertTeam(team);
            this.mTxtName.clear();
            this.mTxtCollege.clear();
            this.mTxtCoach.clear();
            this.mTxtTime.clear();
        }

    }
    public void onFind(ActionEvent event) {

        //mTxtName1.setUserData("123456");//测试

        String teamname;
        teamname=mTxtName1.getText();//读入远动原姓名

        //这里需要把name和num传入数据集库
        //if(    ){
        Team team = teamService.findTeam(teamname);
        if(team!=null){
        //if(mTxtName1.getUserData().equals(mTxtName1.getText())){//测试
            //这里需要比对成功进行以下操作
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("队伍详细信息");
            alert.setHeaderText("");
            alert.setContentText(team.toString());
            alert.showAndWait();
            mTxtName1.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("错误");
            alert.setHeaderText("");
            alert.setContentText("查无此队！");
            alert.showAndWait();
            mTxtName1.clear();

        }
    }
    public void onDel(ActionEvent event) {
        mTxtName1.setText("");
    }
    public boolean deleteStudents() {
        new ArrayList();
        int size = this.data.size();
        if (size <= 0) {
            return false;
        } else {
            for(int i = size - 1; i >= 0; --i) {
                Team s = (Team)this.data.get(i);
                if (s.getSelected()) {
                    this.data.remove(s);
                    teamService.deleteTeam(s);
                }
            }

            return true;
        }
    }
}
