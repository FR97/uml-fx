package fr97.umlfx.common;


import fr97.umlfx.api.UmlDiagram;
import fr97.umlfx.api.edge.UmlEdge;
import fr97.umlfx.api.node.UmlNode;
import fr97.umlfx.command.CommandManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class AbstractDiagram implements UmlDiagram {

    private static int instanceCounter = 0;

    private transient final StringProperty name = new SimpleStringProperty();

    private transient final ObservableList<UmlNode> nodes = FXCollections.observableArrayList();

    private transient final ObservableList<UmlEdge> edges = FXCollections.observableArrayList();

    private final String id;

    private final CommandManager commandManager;

    protected AbstractDiagram(String name, CommandManager commandManager) {
        instanceCounter++;
        this.id = ID_PREFIX + instanceCounter;
        this.commandManager = commandManager;
        this.name.set(name);
    }

    protected AbstractDiagram(CommandManager commandManager) {
        instanceCounter++;
        this.id = ID_PREFIX + instanceCounter;
        this.commandManager = commandManager;
        this.name.set(id);
    }

    @Override
    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public ObservableList<UmlNode> getNodes() {
        return nodes;
    }

    @Override
    public ObservableList<UmlEdge> getEdges() {
        return edges;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public String toString() {
        return "AbstractDiagram{" +
                "name=" + name.get() +
                ", nodes=" + nodes +
                ", edges=" + edges +
                ", id='" + id + '\'' +
                '}';
    }

}
