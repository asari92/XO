package controllers;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.InvalidPointException;
import org.omg.PortableServer.POA;

import java.awt.*;

public class WinnerController {

    public Figure getWinner(Field field) {
        final int size = field.getSize();
        try {
            for (int i = 0; i < size; i++) {
//                if (check(field, new Point(i, 0), new IPointGenerator() {
//                    @Override
//                    public Point next(Point p) {
//                        return new Point(p.x, p.y + 1);
//                    }
//                }))
                if (check(field, new Point(i, 0), p -> new Point(p.x, p.y + 1)))
                    return field.getFigure(new Point(i,0));
            }

            for (int i = 0; i < size; i++) {
                if (check(field, new Point(0, i), new IPointGenerator() {
                    @Override
                    public Point next(Point p) {
                        return new Point(p.x + 1, p.y);
                    }
                }))
                    return field.getFigure(new Point(0,i));
            }

            if (check(field, new Point(0, 0), new IPointGenerator() {
                @Override
                public Point next(Point p) {
                    return new Point(p.x + 1, p.y + 1);
                }
            }))
                return field.getFigure(new Point(0,0));

            if (check(field, new Point(0, 2), new IPointGenerator() {
                @Override
                public Point next(Point p) {
                    return new Point(p.x + 1, p.y - 1);
                }
            }))
                return field.getFigure(new Point(0,2));

        } catch (InvalidPointException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean check(final Field field, final Point currentPoint, final IPointGenerator pointGenerator) {
        final Figure currentFigure;
        final Figure nextFigure;
        final Point nextPoint = pointGenerator.next(currentPoint);
        try {
            currentFigure = field.getFigure(currentPoint);

            if (currentFigure == null) return false;

            nextFigure = field.getFigure(nextPoint);
        } catch (final InvalidPointException e) {
            return true;
        }



        if (currentFigure != nextFigure) return false;

        return check(field, nextPoint, pointGenerator);

    }

    private interface IPointGenerator {

        Point next(final Point point);

    }

}
