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
package cn.edu.ncu.java.view.matchJ;

/**
 * 〈一句话功能简述〉</br> 
 * 〈〉
 * @author 肖海军
 * @create 2019/11/10
 * @since 1.0.0
 **/


import cn.edu.ncu.java.entity.ScoreOfTeam;
import cn.edu.ncu.java.entity.ShooterList;
import cn.edu.ncu.java.service.ScoreOfTeamService;
import cn.edu.ncu.java.service.ShooterListService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TableView mTable;
    @FXML
    private TableColumn<ShooterList, String>idCol;
    @FXML
    private TableColumn<ShooterList, String> mColumnName;
    @FXML
    private TableColumn<ShooterList, String> mColumnTeamName;
    @FXML
    private TableColumn<ShooterList, String> mColumnnumbers;
    @FXML
    private TableColumn<ShooterList, String>mColumngoal;
    @FXML
    private TableColumn<ShooterList, String> mColumnRedCard;
    @FXML
    private TableColumn<ShooterList, String> mColumnYelllowCard;

    private final ObservableList<ShooterList> data = FXCollections.observableArrayList();
    private ShooterListService shooterListService;

    public Controller() throws SQLException, IOException, BiffException {
        shooterListService = new ShooterListService();
        ArrayList<ShooterList> list =  shooterListService.showAllShooterListForMale();
        if(list!=null) {
            Iterator<ShooterList> iterator = list.iterator();
            while (iterator.hasNext())
                data.add(iterator.next());
        }
    }
    public ObservableList<ShooterList> getStuData() {
        return data;
    }
    public void showStuTable(ObservableList<ShooterList> stuLists) {
        mColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        mColumnTeamName.setCellValueFactory(new PropertyValueFactory<>("TeamName"));
        mColumnnumbers.setCellValueFactory(new PropertyValueFactory<>("numbers"));
        mColumngoal.setCellValueFactory(new PropertyValueFactory<>("goals"));
        mColumnRedCard.setCellValueFactory(new PropertyValueFactory<>("RedCard"));
        mColumnYelllowCard.setCellValueFactory(new PropertyValueFactory<>("YelllowCard"));

        //  mColumnscore.setCellFactory(TextFieldTableCell.forTableColumn());//给需要编辑的列设置属性
        //  mTable.setEditable(true);//表格设置为可编辑
        mTable.setItems(stuLists);
        idCol.setCellFactory((col) -> {
            TableCell<ShooterList, String> cell = new TableCell<ShooterList, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        int rowIndex = this.getIndex() + 1;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            return cell;
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.showStuTable(this.getStuData());
    }


}
