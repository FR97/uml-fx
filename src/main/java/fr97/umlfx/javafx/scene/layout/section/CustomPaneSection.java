package fr97.umlfx.javafx.scene.layout.section;

import javafx.scene.layout.Pane;

public class CustomPaneSection extends Section{

    private final Pane pane = new Pane();

    public CustomPaneSection(String name) {
        super(name);

        pane.prefWidthProperty().bind(this.widthProperty());
        pane.maxWidthProperty().bind(this.widthProperty());
        pane.prefHeightProperty().bind(this.heightProperty());
        pane.maxHeightProperty().bind(this.heightProperty());

        getChildren().add(pane);
    }

    public Pane getPane() {
        return pane;
    }
}
