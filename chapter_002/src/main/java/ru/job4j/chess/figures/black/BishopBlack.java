package ru.job4j.chess.figures.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Vlad Dudin
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack extends Figure {

    public BishopBlack(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        boolean valid = false;
        Cell[] steps = new Cell[0];
        int move = Math.abs(source.x - dest.x);
        if (source.y == dest.y + move && source.x == dest.x + move) {
            steps = new Cell[move];
            for (int index = 0; index < steps.length; index++) {
                steps[index] = Cell.findCell(source.x - index - 1, source.y - index - 1);
            }
            valid = true;
        } else if (source.y == dest.y + move && source.x == dest.x - move) {
            steps = new Cell[move];
            for (int index = 0; index < steps.length; index++) {
                steps[index] = Cell.findCell(source.x + index + 1, source.y - index - 1);
            }
            valid = true;
        } else if (source.y == dest.y - move && source.x == dest.x + move) {
            steps = new Cell[move];
            for (int index = 0; index < steps.length; index++) {
                steps[index] = Cell.findCell(source.x - index - 1, source.y + index + 1);
            }
            valid = true;
        } else if (source.y == dest.y - move && source.x == dest.x - move) {
            steps = new Cell[move];
            for (int index = 0; index < steps.length; index++) {
                steps[index] = Cell.findCell(source.x + index + 1, source.y + index + 1);
            }
            valid = true;
        }
        if (!valid) {
            throw new ImpossibleMoveException();
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
