/**
 * Copyright (C), 2015-2019, 南昌大学软件学院1807班
 * FileName: EditingCell
 * Author:   肖海军
 * Date:     2019/11/10 20:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package cn.edu.ncu.java.view.footballmatch;

import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

/**
 * 〈一句话功能简述〉</br> 
 * 〈〉
 * @author 肖海军
 * @create 2019/11/10
 * @since 1.0.0
 **/
public class EditingCell3 extends TableCell<Footballjudgement, String> {
    private TextField textField;

    public EditingCell3() {
    }

    public void startEdit() {
        if (!this.isEmpty()) {
            super.startEdit();
            this.createTextField();
            this.setText((String)null);
            this.setGraphic(this.textField);
            this.textField.selectAll();
        }

    }

    public void cancelEdit() {
        super.cancelEdit();
        this.setText((String)this.getItem());
        this.setGraphic((Node)null);
    }

    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            this.setText((String)null);
            this.setGraphic((Node)null);
        } else if (this.isEditing()) {
            if (this.textField != null) {
                this.textField.setText(this.getString());
            }

            this.setText((String)null);
            this.setGraphic(this.textField);
        } else {
            this.setText(this.getString());
            this.setGraphic((Node)null);
        }

    }

    private void createTextField() {
        this.textField = new TextField(this.getString());
        this.textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2.0D);
        this.textField.focusedProperty().addListener((arg0, arg1, arg2) -> {
            if (!arg2) {
                this.commitEdit(this.textField.getText());
            }

        });
    }

    private String getString() {
        return this.getItem() == null ? "" : ((String)this.getItem()).toString();
    }
}
