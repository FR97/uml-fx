package fr97.umlfx.classdiagram.node.packagenode;


import fr97.umlfx.api.node.UmlParentNode;
import fr97.umlfx.api.node.UmlNamedNode;
import fr97.umlfx.api.node.UmlNode;
import fr97.umlfx.classdiagram.node.classnode.ClassNode;
import fr97.umlfx.classdiagram.node.interfacenode.InterfaceNode;
import fr97.umlfx.common.Stereotype;
import fr97.umlfx.common.node.AbstractNode;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * PackageNode implementation of {@link UmlNode}, this node is also an {@link UmlParentNode}
 * meaning that it can be container for other nodes
 */
public class PackageNode extends AbstractNode implements UmlNamedNode, UmlParentNode {

    private final StringProperty name = new SimpleStringProperty(getId());

    private final ObjectProperty<Stereotype> stereotype = new SimpleObjectProperty<>(Stereotype.PACKAGE);
    private final Set<Class<? extends UmlNode>> acceptedNodes = new HashSet<>(Arrays.asList(ClassNode.class, InterfaceNode.class, PackageNode.class));

    private final Set<Stereotype> acceptedStereotypes = Set.of(Stereotype.PACKAGE, Stereotype.MODULE);
    private final ObservableList<UmlNode> children = FXCollections.observableArrayList();

    public PackageNode() {
        initCustomSize();
    }

    public PackageNode(String name) {
        if (name != null)
            this.name.set(name);
        initCustomSize();
    }

    public void initCustomSize(){
        setWidth(300);
        setHeight(200);
    }

    @Override
    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public PackageNode copy() {
        PackageNode copy = new PackageNode(getName());

        copy.setStart(this.getStart().copy());
        copy.setWidth(this.getWidth());
        copy.setHeight(this.getHeight());
        copy.setTranslateX(this.getTranslateX());
        copy.setTranslateY(this.getTranslateY());
        copy.setWidthScale(this.getWidthScale());
        copy.setHeightScale(this.getHeightScale());

        for (UmlNode child : children) {
            copy.addChild(child.copy());
        }

        return copy;
    }

    @Override
    public void setTranslateX(double x) {
        super.setTranslateX(x);
        for(UmlNode child : children){
            child.setTranslateX(Math.abs(getTranslateX()-child.getTranslateX())+x);
        }
    }

    @Override
    public void setTranslateY(double y) {
        super.setTranslateY(y);
        for(UmlNode child : children){
            child.setTranslateY(Math.abs(getTranslateY()-child.getTranslateY())+y);
        }
    }

    @Override
    public ObservableList<UmlNode> getChildren() {
        return children;
    }

    @Override
    public Set<Class<? extends UmlNode>> acceptedChildren() {
        return acceptedNodes;
    }

    public ObjectProperty<Stereotype> stereotypeProperty() {
        return stereotype;
    }

    public Stereotype getStereotype() {
        return stereotype.get();
    }

    void setStereoType(Stereotype stereotype) {
        if (!acceptedStereotypes.contains(stereotype)){
            throw new IllegalArgumentException("PackageNode can only have Stereotype PACKAGE or MODULE");
        }

        this.stereotype.set(stereotype);
    }
}
