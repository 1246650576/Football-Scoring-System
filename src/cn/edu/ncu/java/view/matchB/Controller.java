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
package cn.edu.ncu.java.view.matchB;

/**
 * 〈一句话功能简述〉</br> 
 * 〈〉
 * @author 肖海军
 * @create 2019/11/10
 * @since 1.0.0
 **/

import cn.edu.ncu.java.entity.Schedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller  implements Initializable {
    @FXML
    private TableView mTable;

    @FXML
    private TableColumn<Schedule, String> mColumnTime;
    @FXML
    private TableColumn<Schedule, String> mColumnHomeTeam;
    @FXML
    private TableColumn<Schedule, String> mColumnvisitingTeam;
    @FXML
    private TableColumn<Schedule, String> mColumnHomeScore;
    @FXML
    private TableColumn<Schedule, String> mColumnVisitingScore;
    @FXML
    private TableColumn<Schedule, String> mColumnturnone;
    @FXML
    private TableColumn<Schedule, String> mColumnturntwo;
    private final ObservableList<Schedule> data = FXCollections.observableArrayList();
    public ObservableList<Schedule> getStuData() {

        //Schedule stu1 = new Schedule("","","","","",1,"");

       // ObservableList<Schedule> stuLists = FXCollections.observableArrayList(stu1);
        return data;
    }
    public void showStuTable(ObservableList<Schedule> stuLists) {
        mColumnTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
        mColumnHomeTeam.setCellValueFactory(new PropertyValueFactory<>("HomeTeam"));
        mColumnvisitingTeam.setCellValueFactory(new PropertyValueFactory<>("visitingTeam"));
        mColumnHomeScore.setCellValueFactory(new PropertyValueFactory<>("HomeScore"));
        mColumnVisitingScore.setCellValueFactory(new PropertyValueFactory<>("VisitingScore"));
        mColumnturnone.setCellValueFactory(new PropertyValueFactory<>("turnone"));
        mColumnturntwo.setCellValueFactory(new PropertyValueFactory<>("turntwo"));


//        mColumnscore.setCellFactory(TextFieldTableCell.forTableColumn());//给需要编辑的列设置属性
        //    mTable.setEditable(true);//表格设置为可编辑
        mTable.setItems(stuLists);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.showStuTable(this.getStuData());
    }

}
