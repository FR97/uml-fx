package fr97.umlfx.common.node.comment;


import fr97.umlfx.api.node.UmlEditableNode;
import fr97.umlfx.common.node.AbstractNode;
import fr97.umlfx.utils.ArgumentChecker;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CommentNode extends AbstractNode implements UmlEditableNode<CommentNode> {

    private final StringProperty text = new SimpleStringProperty();

    public CommentNode() {
        this("");
    }

    public CommentNode(String text) {
        ArgumentChecker.notNull(text, "text can't be null");
        this.text.set(text);
        this.setWidth(180);
        this.setHeight(140);
    }

    public StringProperty textProperty() {
        return text;
    }

    public String getText() {
        return text.get();
    }

    public void setText(String text) {
        this.text.set(text);
    }

    @Override
    public CommentNode copy() {

        CommentNode copy = new CommentNode(getText());

        copy.setStart(this.getStart().copy());
        copy.setWidth(this.getWidth());
        copy.setHeight(this.getHeight());
        copy.setTranslateX(this.getTranslateX());
        copy.setTranslateY(this.getTranslateY());
        copy.setWidthScale(this.getWidthScale());
        copy.setHeightScale(this.getHeightScale());

        return copy;
    }

    @Override
    public CommentNode toEditable() {
        return copy();
    }

    @Override
    public void applyEdited(CommentNode edited) {
        this.text.set(edited.getText());
        this.setWidth(edited.getWidth());
        this.setHeight(edited.getHeight());
        this.setTranslateX(edited.getTranslateX());
        this.setTranslateY(edited.getTranslateY());
        this.setWidthScale(edited.getWidthScale());
        this.setHeightScale(edited.getHeightScale());
    }
}
