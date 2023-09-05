package fr97.umlfx.common.tools;

import fr97.umlfx.api.UmlDiagram;
import fr97.umlfx.api.node.UmlNode;
import fr97.umlfx.api.node.UmlParentNode;
import fr97.umlfx.api.tool.UmlTool;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.function.Supplier;

public class NodeCreationTool implements UmlTool {

    private final Supplier<UmlNode> nodeSupplier;
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty tooltip = new SimpleStringProperty();

    public NodeCreationTool(Supplier<UmlNode> nodeSupplier, StringBinding name, StringBinding tooltip) {
        this.nodeSupplier = nodeSupplier;
        this.name.bind(name);
        this.tooltip.bind(tooltip);
    }

    @Override
    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public StringProperty tooltipProperty() {
        return tooltip;
    }


    @Override
    public void onMouseEvent(MouseEvent event, UmlDiagram diagram) {

        if (event.getEventType() == MouseEvent.MOUSE_CLICKED && event.getButton() == MouseButton.PRIMARY) {
            UmlNode n = nodeSupplier.get();
            System.out.println("Node created: " + n);
            n.setTranslateX(event.getX());
            n.setTranslateY(event.getY());
            if (diagram.addNode(n)) {
                assignToParentIfInsideParent(diagram, n);
            }
        }
        event.consume();
    }

    @Override
    public void onKeyEvent(KeyEvent event, UmlDiagram diagram) {

    }

    private void assignToParentIfInsideParent(UmlDiagram diagram, UmlNode n) {
        for (UmlNode node : diagram.getNodes()) {
            if (node != n
                    && node instanceof UmlParentNode parentNode
                    && parentNode.getBounds().contains(n.getBounds())) {
                System.out.println("Adding node " + n + "to parent " + parentNode);
                parentNode.addChild(n);
            }
        }
    }
}
