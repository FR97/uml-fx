package fr97.umlfx.classdiagram.edge.association;

import fr97.umlfx.common.edge.AbstractEdgeView;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class AssociationEdgeView extends AbstractEdgeView {

    private static final double ARROW_ANGLE = Math.toRadians(30);

    public AssociationEdgeView(AssociationEdge edge) {
        super(edge);

        calculatePositions(null, null, null);
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected Group createShape(double startX, double startY, double endX, double endY) {
        Group group = new Group();

        int length = 15;
        double dy = startY - endY;
        double dx = startX - endX;
        double theta = Math.atan2(dy, dx);
        double x, y, rho = theta + ARROW_ANGLE;

        for (int j = 0; j < 2; j++) {
            x = startX - length * Math.cos(rho);
            y = startY - length * Math.sin(rho);
            Line arrowLine = new Line(startX, startY, x, y);
            arrowLine.setStrokeWidth(1);
            shapeLines.add(arrowLine);
            if (super.isSelected())
                arrowLine.setStroke(Color.RED);
            group.getChildren().add(arrowLine);
            rho = theta - ARROW_ANGLE;
        }

        group.setUserData("shape");
        return group;

    }

}
