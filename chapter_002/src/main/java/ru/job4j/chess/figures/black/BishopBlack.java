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

    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!(Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y))) {
            throw new ImpossibleMoveException();
        }
        int x = source.x;
        int y = source.y;
        Cell[] steps = new Cell[Math.abs(dest.x - x)];
        int deltaX = x < dest.x ? 1 : -1;
        int deltaY = y < dest.y ? 1 : -1;
        for (int i = 0; i < steps.length; i++) {
            x += deltaX;
            y += deltaY;
            steps[i] = Cell.findCell(x, y);
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
