package fr97.umlfx.command;

import fr97.umlfx.api.node.UmlNode;
import fr97.umlfx.math.geometry.Point;

public class MoveNodeCommand implements Command {

    private final UmlNode node;
    private final Point startPoint;

    private final Point endPoint;

    public MoveNodeCommand(UmlNode node, Point startPoint, Point endPoint) {
        this.node = node;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }


    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
