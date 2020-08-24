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
package cn.edu.ncu.java.view.matchH1;

/**
 * 〈一句话功能简述〉</br> 
 * 〈〉
 * @author 肖海军
 * @create 2019/11/10
 * @since 1.0.0
 **/

import cn.edu.ncu.java.entity.Player;
import cn.edu.ncu.java.entity.ScoreOfTeam;
import cn.edu.ncu.java.service.PlayerManService;
import cn.edu.ncu.java.service.ScoreOfTeamService;
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
    private TableColumn<ScoreOfTeam, String>idCol;

    @FXML
    private TableColumn<ScoreOfTeam, String>changesCol;
    @FXML
    private TableColumn<ScoreOfTeam, String> mColumnName;
    @FXML
    private TableColumn<ScoreOfTeam, String>matchsCol;
    @FXML
    private TableColumn<ScoreOfTeam, String>winCol;
    @FXML
    private TableColumn<ScoreOfTeam, String>evenCol;
    @FXML
    private TableColumn<ScoreOfTeam, String>beatenCol;
    @FXML
    private TableColumn<ScoreOfTeam, String>goalCol;
    @FXML
    private TableColumn<ScoreOfTeam, String>lostCol;
    @FXML
    private TableColumn<ScoreOfTeam, String>netCol;
    @FXML
    private TableColumn<ScoreOfTeam, String>avergoalCol;
    @FXML
    private TableColumn<ScoreOfTeam, String>averlostCol;
    @FXML
    private TableColumn<ScoreOfTeam, String>avernetCol;
    @FXML
    private TableColumn<ScoreOfTeam, String>mColumnScore;



    private final ObservableList<ScoreOfTeam> data = FXCollections.observableArrayList();
    private ScoreOfTeamService scoreOfTeamService;

    public Controller() throws SQLException, IOException, BiffException {
       scoreOfTeamService = new ScoreOfTeamService();
        ArrayList<ScoreOfTeam> list =  scoreOfTeamService.showAllScoreOfTeamForFemale();
        if(list!=null) {
            Iterator<ScoreOfTeam> iterator = list.iterator();
            while (iterator.hasNext())
                data.add(iterator.next());
        }
    }

    public ObservableList<ScoreOfTeam> getStuData() {
        return data;
    }
    public void showStuTable(ObservableList<ScoreOfTeam> stuLists) {
        changesCol.setCellValueFactory(new PropertyValueFactory<>("changes"));
        mColumnName.setCellValueFactory(new PropertyValueFactory<>("teamname"));
        matchsCol.setCellValueFactory(new PropertyValueFactory<>("matchs"));
        winCol.setCellValueFactory(new PropertyValueFactory<>("win"));
        evenCol.setCellValueFactory(new PropertyValueFactory<>("even"));
        beatenCol.setCellValueFactory(new PropertyValueFactory<>("beaten"));
        goalCol.setCellValueFactory(new PropertyValueFactory<>("goal"));
        lostCol.setCellValueFactory(new PropertyValueFactory<>("lost"));
        netCol.setCellValueFactory(new PropertyValueFactory<>("net"));
        avergoalCol.setCellValueFactory(new PropertyValueFactory<>("avergoal"));
        averlostCol.setCellValueFactory(new PropertyValueFactory<>("averlost"));
        avernetCol.setCellValueFactory(new PropertyValueFactory<>("avernet"));
        mColumnScore.setCellValueFactory(new PropertyValueFactory<>("averpoint"));
        //  mColumnscore.setCellFactory(TextFieldTableCell.forTableColumn());//给需要编辑的列设置属性
        //  mTable.setEditable(true);//表格设置为可编辑
        mTable.setItems(stuLists);
        idCol.setCellFactory((col) -> {
            TableCell<ScoreOfTeam, String> cell = new TableCell< ScoreOfTeam, String>() {
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
