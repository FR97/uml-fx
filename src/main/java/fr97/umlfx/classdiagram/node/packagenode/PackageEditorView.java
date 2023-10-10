package fr97.umlfx.classdiagram.node.packagenode;

import fr97.umlfx.views.FXMLView;
import javafx.scene.layout.StackPane;

public class PackageEditorView extends FXMLView<StackPane, PackageNode> {

    public PackageEditorView(PackageNode model) throws IllegalStateException, IllegalArgumentException {
        super(model);
    }

}
