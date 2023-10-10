package fr97.umlfx.classdiagram.node.packagenode;

import fr97.umlfx.classdiagram.node.classnode.ClassNode;
import fr97.umlfx.common.AccessModifier;
import fr97.umlfx.common.Field;
import fr97.umlfx.common.Function;
import fr97.umlfx.javafx.UtilsFX;
import fr97.umlfx.javafx.scene.control.cell.EditableComboCell;
import fr97.umlfx.javafx.scene.control.cell.EditableTextCell;
import fr97.umlfx.views.FXMLController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PackageEditorController implements FXMLController<PackageNode> {

    private PackageNode node;
    @FXML
    private TextField txtName;

    @FXML
    private Slider sliderWidth, sliderHeight;

    @Override
    public void setModel(PackageNode model) {
        node = model;
        onModelSet();
    }

    private void onModelSet() {
        txtName.textProperty().bindBidirectional(node.nameProperty());
        sliderWidth.valueProperty().bindBidirectional(node.widthProperty());
        sliderHeight.valueProperty().bindBidirectional(node.heightProperty());
    }

    @Override
    public PackageNode getModel() {
        return node;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
