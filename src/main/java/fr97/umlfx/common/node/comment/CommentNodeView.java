package fr97.umlfx.common.node.comment;

import fr97.umlfx.app.Theme;
import fr97.umlfx.common.node.AbstractNodeView;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

public class CommentNodeView extends AbstractNodeView {

    private final StackPane container = new StackPane();
    private final TextArea textArea = new TextArea();

    public CommentNodeView(CommentNode node) {
        super(node);

        container.setPadding(new Insets(10, 10, 10, 10));
        container.prefWidthProperty().bind(node.widthProperty());
        container.prefHeightProperty().bind(node.heightProperty());
        container.maxWidthProperty().bind(node.widthProperty());
        container.maxHeightProperty().bind(node.heightProperty());
        container.borderProperty().bind(Theme.getDefaultTheme().borderProperty());
        container.getStyleClass().add("comment-stack");

        textArea.textProperty().bindBidirectional(node.textProperty());
        textArea.prefWidthProperty().bind(node.widthProperty().subtract(10));
        textArea.prefHeightProperty().bind(node.widthProperty().subtract(10));
        textArea.setPadding(new Insets(5, 5, 5, 5));
        textArea.fontProperty().bind(Theme.getDefaultTheme().fontProperty());
        textArea.setWrapText(true);

        container.getChildren().add(textArea);
        getChildren().add(container);
        createResizeLines(container);
    }

    @Override
    public void setSelected(boolean selected) {
        container.borderProperty().unbind();
        if (selected){
            container.borderProperty().bind(Theme.getDefaultTheme().selectedBorderProperty());
        } else{
            container.borderProperty().bind(Theme.getDefaultTheme().borderProperty());
        }
    }

    @Override
    public Bounds getBounds() {
        return textArea.getBoundsInParent();
    }
}
