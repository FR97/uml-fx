package fr97.umlfx.classdiagram.node.packagenode;

import fr97.umlfx.common.node.AbstractNodeView;
import fr97.umlfx.javafx.scene.layout.SectionPane;
import fr97.umlfx.javafx.scene.layout.section.CustomPaneSection;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

public class PackageNodeView extends AbstractNodeView {

    private final SectionPane sectionPane = new SectionPane();

    public PackageNodeView(PackageNode node) {
        super(node);
        bindWithNode(node);
        getChildren().add(sectionPane);
        createResizeLines(sectionPane);
    }

    private void bindWithNode(final PackageNode node) {
        sectionPane.getHeaderSection().textProperty().bind(node.nameProperty());
        sectionPane.getHeaderSection().stereotypeProperty().bind(node.stereotypeProperty());
        sectionPane.backgroundColorProperty().bind(new SimpleObjectProperty<>(Color.web("#0e5b9d")));
        CustomPaneSection section = new CustomPaneSection("children");

        sectionPane.getSubsections().addAll(section);
        sectionPane.prefWidthProperty().bind(node.widthProperty());
        sectionPane.maxWidthProperty().bind(node.widthProperty());
        sectionPane.prefHeightProperty().bind(node.heightProperty());
        sectionPane.maxHeightProperty().bind(node.heightProperty());
    }

    @Override
    public void setSelected(boolean selected) {
        sectionPane.setSelected(selected);
    }

    @Override
    public Bounds getBounds() {
        return getBoundsInParent();
    }
}
