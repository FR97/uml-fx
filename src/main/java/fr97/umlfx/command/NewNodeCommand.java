package fr97.umlfx.command;


import fr97.umlfx.api.UmlDiagram;
import fr97.umlfx.api.node.UmlNode;
import fr97.umlfx.api.node.UmlParentNode;

public class NewNodeCommand implements Command{

    private final UmlNode node;
    private final UmlDiagram diagram;
    public NewNodeCommand(final UmlNode node, final UmlDiagram diagram){
        this.node = node;
        this.diagram = diagram;
    }


    @Override
    public void execute() {
        diagram.addNode(node);
        if (diagram.addNode(node)) {
            assignToParentIfInsideParent(diagram, node);
        }

    }

    @Override
    public void undo() {
        if(diagram.removeNode(node)) {
            removeFromParent(diagram, node);
        }
    }

    private void assignToParentIfInsideParent(UmlDiagram diagram, UmlNode n) {
        for (UmlNode node : diagram.getNodes()) {
            if (node != n
                    && node instanceof UmlParentNode parentNode
                    && parentNode.getBounds().contains(n.getBounds())) {
                parentNode.addChild(n);
            }
        }
    }

    private void removeFromParent(UmlDiagram diagram, UmlNode n) {
        for (UmlNode node : diagram.getNodes()) {
            if (node != n
                    && node instanceof UmlParentNode parentNode
                    && parentNode.getChildren().contains(n)) {
                parentNode.removeChild(n);
            }
        }
    }
}
