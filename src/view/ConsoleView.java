package view;

import controllers.CurrentMoveController;
import controllers.MoveController;
import controllers.WinnerController;
import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Game;
import io.hexlet.xo.model.exceptions.AlreadyOccupiedException;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {

    private final CurrentMoveController currentMoveController = new CurrentMoveController();

    private final WinnerController winnerController = new WinnerController();

    private final MoveController moveController = new MoveController();

    public void show(final Game game) {
        System.out.format("Game name: %s\n\n", game.getName());
        final Field field = game.getField();
        for (int x=0; x < field.getSize(); x++) {
            printLine(field, x);
        }
        System.out.println();
    }

    public boolean move(final Game game) {
        final Field field = game.getField();
        final Figure winner = winnerController.getWinner(field);
        if (winner != null) {
            System.out.format("Winner is %s!\n", winner);
            return false;
        }
        final Figure currentFigure = currentMoveController.currentMove(field);
        if (currentFigure == null) {
            System.out.println("dead heat!");
            return false;
        }
        System.out.format("Please enter move point for: %s\n", currentFigure);
        final Point point = askPoint();
        try {
            moveController.applyFigure(point, field, currentFigure);
        } catch (final InvalidPointException e) {
            System.out.println("Point is invalid!");
        } catch (final AlreadyOccupiedException e) {
            System.out.println("Point is already occupied!");
        }
        return true;
    }

    private Point askPoint() {
        return new Point(askCoordinate("X") - 1, askCoordinate("Y") - 1);
    }

    private int askCoordinate(final String coordinateName) {
        System.out.format("Please input %s:", coordinateName);
        final Scanner in = new Scanner(System.in);
        try {
            return in.nextInt();
        } catch (final InputMismatchException e) {
            System.out.println("Enter only numbers!");
            return askCoordinate(coordinateName);
        }
    }

    private void printLine(final Field field, final int x) {
        if (x != 0) {
            printSeparator();
        }
        for (int y=0; y < field.getSize(); y++) {
            if (y != 0) {
                System.out.print(" |");
            }
            final Figure figure;
            try {
                figure = field.getFigure(new Point(x,y));
            } catch (final InvalidPointException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.print(" ");
            System.out.print(figure != null ? figure : " ");

        }
        System.out.println(" ");
    }

    private void printSeparator() {
        System.out.println("___________");
    }


}
