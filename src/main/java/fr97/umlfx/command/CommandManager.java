package fr97.umlfx.command;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Stack;

/**
 * CommandManager is responsible for executing and undoing commands
 * It also keeps track of executed and undone commands
 * It is also responsible for disabling undo and redo menu items when there is no command to undo or redo
 *
 * @see Command
 * @see CompoundCommand
 */
public class CommandManager {

    private final Stack<Command> commandHistory = new Stack<>();
    private final Stack<Command> undoneCommandHistory = new Stack<>();
    private final IntegerProperty undoSize = new SimpleIntegerProperty(0);
    private final IntegerProperty redoSize = new SimpleIntegerProperty(0);

    private boolean isWorking = false;
    public CommandManager() {

    }

    /**
     * Executes given command and adds it to list of executed commands,
     * use {@link CommandManager#addExecuted(Command)} if command is already executed
     * If {@link CommandManager#isWorking} this method will instantly return without executing and adding command
     *
     * @param command given command
     */
    public void execute(Command command) {
        if (isWorking)
            return;
        command.execute();
        commandHistory.push(command);
        undoSize.set(commandHistory.size());
    }

    /**
     * Adds given command to list of executed commands without executing it
     * If {@link CommandManager#isWorking} this method will instantly return without adding command
     *
     * @param command given command
     */
    public void addExecuted(Command command) {
        if (isWorking)
            return;
        commandHistory.push(command);
        undoSize.set(commandHistory.size());
    }

    /**
     * Calls undo on last command of executed commands,
     * if there isn't any executed command then it does nothing
     */
    public void undo() {
        isWorking = true;
        if (commandHistory.empty())
            return;

        Command toUndo = commandHistory.pop();
        undoSize.set(commandHistory.size());
        toUndo.undo();
        undoneCommandHistory.push(toUndo);
        redoSize.set(undoneCommandHistory.size());
        isWorking = false;
    }

    /**
     * Calls execute on last undone command of this command manager,
     * if there isn't any undone command then it does nothing
     */
    public void redo() {
        isWorking = true;
        if (undoneCommandHistory.empty())
            return;

        Command toRedo = undoneCommandHistory.pop();
        redoSize.set(undoneCommandHistory.size());
        toRedo.execute();
        commandHistory.push(toRedo);
        undoSize.set(commandHistory.size());
        isWorking = false;
    }

    public int getUndoSize() {
        return undoSize.get();
    }

    public IntegerProperty undoSizeProperty() {
        return undoSize;
    }

    public void setUndoSize(int undoSize) {
        this.undoSize.set(undoSize);
    }

    public int getRedoSize() {
        return redoSize.get();
    }

    public IntegerProperty redoSizeProperty() {
        return redoSize;
    }

    public void setRedoSize(int redoSize) {
        this.redoSize.set(redoSize);
    }

    /**
     * While this is true, calling {@link CommandManager#execute(Command)} or {@link CommandManager#addExecuted(Command)}
     * will instantly return
     *
     * @return true if this CommandManager is currently undoing or redoing some command, otherwise false
     */
    public boolean isWorking() {
        return isWorking;
    }
}
