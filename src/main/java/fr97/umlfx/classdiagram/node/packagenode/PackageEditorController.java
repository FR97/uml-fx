package fr97.umlfx.classdiagram.node.packagenode;

import fr97.umlfx.views.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
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
