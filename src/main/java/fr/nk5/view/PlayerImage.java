package fr.nk5.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Objects;

public class PlayerImage extends Circle {
    private String name;
    private Color originalColor;

    public PlayerImage(double centerX, double centerY, double radius, Color color, String name) {
        super(centerX, centerY, radius, color);
        this.name = name;
        this.originalColor = color;
    }

    public void setOriginalColor(){
        setFill(originalColor);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerImage that = (PlayerImage) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
