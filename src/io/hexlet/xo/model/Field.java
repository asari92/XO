package io.hexlet.xo.model;

import io.hexlet.xo.model.exceptions.AlreadyOccupiedException;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;

public class Field {

    private static final int FIELD_SIZE = 3;
    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 3;

    private final Figure[][] field = new Figure[FIELD_SIZE][FIELD_SIZE];

    public int getSize() {
        return  FIELD_SIZE;
    }

    public Figure getFigure(final Point point) throws InvalidPointException {
        if (!checkPoint(point)) {
            throw new InvalidPointException();
        }
        return field[point.x][point.y];
    }

    public void setFigure(final Point point, final Figure figure) throws InvalidPointException,
                                                                        AlreadyOccupiedException {
        if (!checkPoint(point)) {
            throw new InvalidPointException();
        }
        if (field[point.x][point.y] != null) {
            throw new AlreadyOccupiedException();
        }
        field[point.x][point.y] = figure;
    }

    private boolean checkPoint(final Point point) {
        return checkOrdinate(point.x) && checkOrdinate(point.y);
    }

    private boolean checkOrdinate(final int ordinate) {
        return ordinate >= MIN_COORDINATE && ordinate < MAX_COORDINATE;
    }

}
