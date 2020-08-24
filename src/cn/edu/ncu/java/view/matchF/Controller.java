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
package cn.edu.ncu.java.view.matchF;

/**
 * 〈一句话功能简述〉</br> 
 * 〈〉
 * @author 肖海军
 * @create 2019/11/10
 * @since 1.0.0
 **/

import cn.edu.ncu.java.entity.Judage;
import cn.edu.ncu.java.service.JudageService;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class Controller {
    @FXML
    private Button mBtnDelete;
    @FXML
    private TableView<Judage> mTable;
    @FXML
    private TextField mTxtJudge;
    @FXML
    private TextField mTxtNum;
    @FXML
    private TextField mTxtRed;
    @FXML
    private TextField mTxtYellow;
    @FXML
    private TextField mTxtTurn;
    @FXML
    private TextField mTxtTime;

    @FXML
    private TableColumn<Judage, Boolean> mColumnSelect;
    @FXML
    private TableColumn<Judage, String> mColumnJudge;
    @FXML
    private TableColumn<Judage, String> mColumnNum;
    @FXML
    private TableColumn<Judage, String> mColumnRed;
    @FXML
    private TableColumn<Judage, String> mColumnYellow;
    @FXML
    private TableColumn<Judage, String> mColumnTurn;
    @FXML
    private TableColumn<Judage, String> mColumnTime;

    private final ObservableList<Judage> data = FXCollections.observableArrayList();
    private JudageService judageService;

    public Controller() throws SQLException, IOException, BiffException {
        judageService = new JudageService();
        ArrayList<Judage> list = judageService.showAllJudge();
        if(list != null) {
            Iterator<Judage> iterator = list.iterator();
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
                final Judage g = Controller.this.mTable.getItems().get(index);
                ObservableValue<Boolean> ret = new SimpleBooleanProperty(g, "selected", g.getSelected());
                ret.addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        g.setSelected(newValue);
                    }
                });
                return ret;
            }
        }));
        Callback<TableColumn<Judage, String>, TableCell<Judage, String>> cellFactory = (p) -> {
            return new EditingCell6();
        };
        this.mColumnSelect.setCellValueFactory(new PropertyValueFactory<>("selected"));
        this.mColumnJudge.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        this.mColumnJudge.setCellFactory(cellFactory);
        this.mColumnNum.setCellValueFactory(new PropertyValueFactory<>("numbers"));
        this.mColumnNum.setCellFactory(cellFactory);
        this.mColumnRed.setCellValueFactory(new PropertyValueFactory<>("redCard"));
        this.mColumnRed.setCellFactory(cellFactory);
        this.mColumnYellow.setCellValueFactory(new PropertyValueFactory<>("yellowCard"));
        this.mColumnYellow.setCellFactory(cellFactory);
        this.mColumnTurn.setCellValueFactory(new PropertyValueFactory<>("turn"));
        this.mColumnTurn.setCellFactory(cellFactory);
        this.mColumnTime.setCellValueFactory(new PropertyValueFactory<>("judgeTime"));
        this.mColumnTime.setCellFactory(cellFactory);
        this.mTable.setItems(this.data);
        this.mTable.setEditable(true);
    }
    @FXML
    public void onAdd(ActionEvent event) {
        if (this.mTxtJudge.getText() != null && this.mTxtNum.getText() != null&&this.mTxtRed.getText()!=null
                &&this.mTxtYellow.getText()!=null&&this.mTxtTurn.getText()!=null&&this.mTxtTime.getText()!=null) {
            Judage judage = new Judage(this.mTxtJudge.getText(), this.mTxtNum.getText(),
                    this.mTxtRed.getText(),
                    this.mTxtYellow.getText(),
                    this.mTxtTurn.getText(),
                    this.mTxtTime.getText());
            this.data.add(judage);
            judageService.insertJudge(judage);
            this.mTxtJudge.clear();
            this.mTxtNum.clear();
            this.mTxtRed.clear();
            this.mTxtYellow.clear();
            this.mTxtTurn.clear();
            this.mTxtTime.clear();
        }

    }

    public boolean deleteStudents() {
        new ArrayList();
        int size = this.data.size();
        if (size <= 0) {
            return false;
        } else {
            for(int i = size - 1; i >= 0; --i) {
                Judage s = (Judage)this.data.get(i);
                if (s.getSelected()) {
                    this.data.remove(s);
                }
            }

            return true;
        }
    }
}
