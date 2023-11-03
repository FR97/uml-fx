package fr97.umlfx.app;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Arrays;
import java.util.List;

public class Theme {

    private static Theme DEFAULT_THEME = new Theme();

    private SimpleObjectProperty<Paint> borderColor = new SimpleObjectProperty<>(Color.BLACK);
    private SimpleObjectProperty<Border> border = new SimpleObjectProperty<>(createBorder(Color.BLACK, 1));
    private SimpleObjectProperty<Border> selectedBorder = new SimpleObjectProperty<>(createBorder(Color.RED, 16));

    private SimpleObjectProperty<Font> font = new SimpleObjectProperty<>(Font.font("Arial", 12));
    private SimpleObjectProperty<Font> boldFont = new SimpleObjectProperty<>(Font.font("Arial", FontWeight.BOLD, 16));
    private SimpleObjectProperty<Paint> selectedColor = new SimpleObjectProperty<>(Color.RED);

    private SimpleObjectProperty<Paint> strokeColor = new SimpleObjectProperty<>(Color.BLACK);
    private DoubleProperty strokeWitdth = new SimpleDoubleProperty(1);

    private SimpleObjectProperty<Paint> nodeBackgroundColor = new SimpleObjectProperty<>(Color.LIGHTSKYBLUE);
    private SimpleObjectProperty<Paint> backgroundColor = new SimpleObjectProperty<>(Color.WHITE);
    private SimpleObjectProperty<Background> background =
        new SimpleObjectProperty<>(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

    public static List<Theme> availableThemes() {
        Theme light = new Theme();
        Theme dark = new Theme();
        return Arrays.asList(light, dark);
    }

    private static Border createBorder(Paint color, double width) {
        return new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(width)));
    }

    public static Theme getDefaultTheme() {
        return DEFAULT_THEME;
    }

    public static void setDefaultTheme(Theme theme) {
        DEFAULT_THEME = theme;
    }

    private Theme() {

    }

    public Paint getBorderColor() {
        return borderColor.get();
    }

    public SimpleObjectProperty<Paint> borderColorProperty() {
        return borderColor;
    }

    public void setBorderColor(Paint borderColor) {
        this.borderColor.set(borderColor);
    }

    public Border getBorder() {
        return border.get();
    }

    public SimpleObjectProperty<Border> borderProperty() {
        return border;
    }

    public void setBorder(Border border) {
        this.border.set(border);
    }

    public Border getSelectedBorder() {
        return selectedBorder.get();
    }

    public SimpleObjectProperty<Border> selectedBorderProperty() {
        return selectedBorder;
    }

    public void setSelectedBorder(Border selectedBorder) {
        this.selectedBorder.set(selectedBorder);
    }

    public Font getFont() {
        return font.get();
    }

    public SimpleObjectProperty<Font> fontProperty() {
        return font;
    }

    public void setFont(Font font) {
        this.font.set(font);
    }

    public Font getBoldFont() {
        return boldFont.get();
    }

    public SimpleObjectProperty<Font> boldFontProperty() {
        return boldFont;
    }

    public void setBoldFont(Font boldFont) {
        this.boldFont.set(boldFont);
    }

    public Paint getSelectedColor() {
        return selectedColor.get();
    }

    public SimpleObjectProperty<Paint> selectedColorProperty() {
        return selectedColor;
    }

    public void setSelectedColor(Paint selectedColor) {
        this.selectedColor.set(selectedColor);
    }

    public Paint getStrokeColor() {
        return strokeColor.get();
    }

    public SimpleObjectProperty<Paint> strokeColorProperty() {
        return strokeColor;
    }

    public void setStrokeColor(Paint strokeColor) {
        this.strokeColor.set(strokeColor);
    }

    public double getStrokeWitdth() {
        return strokeWitdth.get();
    }

    public DoubleProperty strokeWitdthProperty() {
        return strokeWitdth;
    }

    public void setStrokeWitdth(double strokeWitdth) {
        this.strokeWitdth.set(strokeWitdth);
    }

    public Paint getNodeBackgroundColor() {
        return nodeBackgroundColor.get();
    }

    public SimpleObjectProperty<Paint> nodeBackgroundColorProperty() {
        return nodeBackgroundColor;
    }

    public void setNodeBackgroundColor(Paint nodeBackgroundColor) {
        this.nodeBackgroundColor.set(nodeBackgroundColor);
    }

    public Paint getBackgroundColor() {
        return backgroundColor.get();
    }

    public SimpleObjectProperty<Paint> backgroundColorProperty() {
        return backgroundColor;
    }

    public void setBackgroundColor(Paint backgroundColor) {
        this.backgroundColor.set(backgroundColor);
    }

    public Background getBackground() {
        return background.get();
    }

    public SimpleObjectProperty<Background> backgroundProperty() {
        return background;
    }

    public void setBackground(Background background) {
        this.background.set(background);
    }
}
