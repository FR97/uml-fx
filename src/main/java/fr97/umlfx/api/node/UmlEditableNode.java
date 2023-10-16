package fr97.umlfx.api.node;

public interface UmlEditableNode<T extends UmlEditableNode<T>> extends UmlNode {

    T toEditable();

    void applyEdited(T t);

}
