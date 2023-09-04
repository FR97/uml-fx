package fr97.umlfx.common.node;

import fr97.umlfx.api.node.UmlNode;
import fr97.umlfx.app.Localization;
import fr97.umlfx.classdiagram.node.classnode.ClassEditorView;
import fr97.umlfx.classdiagram.node.classnode.ClassNode;
import fr97.umlfx.classdiagram.node.interfacenode.InterfaceEditorView;
import fr97.umlfx.classdiagram.node.interfacenode.InterfaceNode;
import fr97.umlfx.classdiagram.node.packagenode.PackageEditorView;
import fr97.umlfx.classdiagram.node.packagenode.PackageNode;
import fr97.umlfx.common.node.comment.CommentEditorView;
import fr97.umlfx.common.node.comment.CommentNode;
import fr97.umlfx.javafx.UtilsFX;
import fr97.umlfx.views.FXMLView;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.util.function.Consumer;

/**
 * Common view for all node editors, other editors are embedded inside this one
 */
public final class NodeEditorView extends FXMLView<BorderPane, UmlNode> {


    private NodeEditorView(UmlNode model) throws IllegalStateException, IllegalArgumentException {
        super(model);
    }

    /**
     * Instantiates {@link NodeEditorViewBuilder}
     *
     * @return NodeEditorViewBuilder
     */
    public static NodeEditorViewBuilder builder() {
        return new NodeEditorViewBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeEditorController getController() {
        return (NodeEditorController) super.getController();
    }

    public final static class NodeEditorViewBuilder {

        private Consumer<Event> onApply = event -> {
        };
        private Consumer<Event> onCancel = event -> {
        };
        private UmlNode editedNode;
        private Node editorRoot;
        private Stage owner;

        private NodeEditorViewBuilder() {

        }

        public NodeEditorViewBuilder setOwner(Stage owner) {
            this.owner = owner;
            return this;
        }

        public NodeEditorViewBuilder setOnApply(Consumer<Event> onApply) {
            this.onApply = onApply;
            return this;
        }

        public NodeEditorViewBuilder setOnCancel(Consumer<Event> onCancel) {
            this.onCancel = onCancel;
            return this;
        }

        public NodeEditorViewBuilder setEditedNode(UmlNode editedNode) {
            this.editedNode = editedNode;
            return this;
        }

        public NodeEditorView build() {
            UmlNode nodeCopy = editedNode.copy();
            NodeEditorView view = new NodeEditorView(editedNode);

            Stage stage = UtilsFX.createNewStage(owner, Localization.createStringBinding("stage.title.editor"));
            Scene scene = new Scene(new StackPane(), 0, 0);

            onCancel = onCancel.andThen(e -> {
                editedNode.setWidth(nodeCopy.getWidth());
                editedNode.setHeight(nodeCopy.getHeight());
            });

            if (editedNode instanceof CommentNode) {
                editorRoot = new CommentEditorView((CommentNode) editedNode).getRoot();
            } else if (editedNode instanceof ClassNode classNode) {
                editorRoot = new ClassEditorView(classNode).getRoot();
                scene = new Scene(view.getRoot(), ((StackPane) editorRoot).getPrefWidth(), ((StackPane) editorRoot).getPrefHeight() + 50);
                onCancel = onCancel.andThen(e -> {
                    ClassNode copy = (ClassNode) nodeCopy;
                    classNode.setName(copy.getName());
                    classNode.getFields().clear();
                    classNode.getFunctions().clear();
                    classNode.getFields().addAll(copy.getFields());
                    classNode.getFunctions().addAll(copy.getFunctions());
                });
            } else if (editedNode instanceof InterfaceNode interfaceNode) {
                editorRoot = new InterfaceEditorView(interfaceNode).getRoot();
                scene = new Scene(view.getRoot(), ((StackPane) editorRoot).getPrefWidth(), ((StackPane) editorRoot).getPrefHeight() + 50);
                onCancel = onCancel.andThen(e -> {
                    InterfaceNode copy = (InterfaceNode) nodeCopy;
                    interfaceNode.setName(copy.getName());
                    interfaceNode.getFunctions().clear();
                    interfaceNode.getFunctions().addAll(copy.getFunctions());
                });
            } else if (editedNode instanceof PackageNode packageNode) {
                editorRoot = new PackageEditorView(packageNode).getRoot();
                scene = new Scene(view.getRoot(), ((StackPane) editorRoot).getPrefWidth(), ((StackPane) editorRoot).getPrefHeight() + 50);
                onCancel = onCancel.andThen(e -> {
                    PackageNode copy = (PackageNode) nodeCopy;
                    packageNode.setName(copy.getName());
                });
            }

            view.getRoot().setCenter(editorRoot);
            view.getController().setOnApply(onApply.andThen(event -> stage.close()));
            view.getController().setOnCancel(onCancel.andThen(event -> stage.close()));
            stage.setOnCloseRequest(event -> onCancel.accept(event));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            return view;
        }
    }
}
