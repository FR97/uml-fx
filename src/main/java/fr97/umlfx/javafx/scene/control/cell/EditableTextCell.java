package fr97.umlfx.javafx.scene.control.cell;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * Extends {@link TextFieldTableCell}, uses TextField for editing cell value,
 * allows using {@link CellValueConverter} which makes instantiating this very easy
 * @param <S>
 * @param <T>
 *
 * @author Filip
 *
 */
public class EditableTextCell<S, T> extends TextFieldTableCell<S, T> {

    private TextField textField;
    private final CellValueConverter<T> converter;

    public EditableTextCell(CellValueConverter<T> converter) {
        this.converter = converter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText(getString());
        setGraphic(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(getString());
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null)
                    textField.setText(getString());

                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnAction((e) -> commitEdit(converter.convert(textField.getText())));
        textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                commitEdit(converter.convert(textField.getText()));
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
