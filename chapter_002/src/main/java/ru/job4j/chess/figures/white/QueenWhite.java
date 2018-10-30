package ru.job4j.chess.figures.white;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Vlad Dudin
 * @version $Id$
 * @since 0.1
 */
public class QueenWhite extends Figure {
    public QueenWhite(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        return new Cell[]{dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new QueenWhite(dest);
    }
}
