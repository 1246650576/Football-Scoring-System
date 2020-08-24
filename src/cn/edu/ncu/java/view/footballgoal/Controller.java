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
package cn.edu.ncu.java.view.footballgoal;

/**
 * 〈一句话功能简述〉</br> 
 * 〈〉
 * @author 肖海军
 * @create 2019/11/10
 * @since 1.0.0
 **/

import cn.edu.ncu.java.entity.GoalOfPlayer;
import cn.edu.ncu.java.service.GoalOfPlayerService;
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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    private TextField mTxtTeamName;
    @FXML
    private TextField mTxtnumbers;
    @FXML
    private TextField mTxtturn;
    @FXML
    private TextField mTxtGoalTime;
    @FXML
    private TableColumn<GoalOfPlayer, Boolean> mColumnSelect;
    @FXML
    private TableColumn<GoalOfPlayer, String> mColumnTeamName;
    @FXML
    private TableColumn<GoalOfPlayer, String> mColumnnumbers ;
    @FXML
    private TableColumn<GoalOfPlayer, String> mColumnturn;
    @FXML
    private TableColumn<GoalOfPlayer, String> mColumnGoalTime;
    private final ObservableList<GoalOfPlayer> data = FXCollections.observableArrayList();
    private GoalOfPlayerService goalOfPlayerService;

    public Controller() throws SQLException, IOException, BiffException {
        goalOfPlayerService = new GoalOfPlayerService();
        ArrayList<GoalOfPlayer> list = goalOfPlayerService.showAllGoalOfPlayer();
        if (list !=null ){
            Iterator<GoalOfPlayer> iterator = list.iterator();
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
                final GoalOfPlayer g = (GoalOfPlayer) Controller.this.mTable.getItems().get(index);
                ObservableValue<Boolean> ret = new SimpleBooleanProperty(g, "selected", g.getSelected());
                ret.addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        g.setSelected(newValue);
                    }
                });
                return ret;
            }
        }));
        Callback<TableColumn<GoalOfPlayer, String>, TableCell<GoalOfPlayer, String>> cellFactory = (p) -> {
            return new EditingCell2();
        };
        this.mColumnSelect.setCellValueFactory(new PropertyValueFactory("selected"));
        this.mColumnTeamName.setCellValueFactory(new PropertyValueFactory("TeamName"));
        this.mColumnTeamName.setCellFactory(cellFactory);
        this.mColumnnumbers.setCellValueFactory(new PropertyValueFactory("numbers"));
        this.mColumnnumbers.setCellFactory(cellFactory);
        this.mColumnturn.setCellValueFactory(new PropertyValueFactory("turn"));
        this.mColumnturn.setCellFactory(cellFactory);
        this.mColumnGoalTime.setCellValueFactory(new PropertyValueFactory("GoalTime"));
        this.mColumnGoalTime.setCellFactory(cellFactory);
      //  this.mColumnTime.setOnEditCommit((t) -> {
    //        ((GoalOfPlayer)t.getTableView().getItems().get(t.getTablePosition().getRow())).setTime((String)t.getNewValue());
     //   });

        this.mTable.setItems(this.data);
        this.mTable.setEditable(true);
    }



    @FXML
    public void onAdd(ActionEvent event) {
        if (this.mTxtTeamName.getText() != null &&this.mTxtnumbers.getText() != null &&this.mTxtturn.getText() != null && this.mTxtGoalTime.getText() != null) {
            GoalOfPlayer goalOfPlayer = new GoalOfPlayer(this.mTxtTeamName.getText(),this.mTxtnumbers.getText(),
                    this.mTxtturn.getText(),
                    this.mTxtGoalTime.getText());
            goalOfPlayerService.insertGoalOfPlayer(goalOfPlayer);
            this.data.add(goalOfPlayer);
            this.mTxtTeamName.clear();
            this.mTxtnumbers.clear();
            this.mTxtturn.clear();
            this.mTxtGoalTime.clear();

        }

    }

    public boolean deleteStudents() {
        new ArrayList();
        int size = this.data.size();
        if (size <= 0) {
            return false;
        } else {
            for(int i = size - 1; i >= 0; --i) {
                GoalOfPlayer s = (GoalOfPlayer)this.data.get(i);
                if (s.getSelected()) {
                    this.data.remove(s);
                }
            }

            return true;
        }
    }
}
