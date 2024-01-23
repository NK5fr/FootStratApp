package fr.nk5.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {

    public Ball(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius);
        setFill(Color.GRAY);
    }

}
