package ru.job4j.chess.figures;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
 *
 * @author Vlad Dudin
 * @version $Id$
 * @since 0.1
 */
public abstract class Figure {
    private final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public Cell position() {
        return this.position;
    }

    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    public abstract Figure copy(Cell dest);
}
