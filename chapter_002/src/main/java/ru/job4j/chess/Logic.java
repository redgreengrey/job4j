package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * @author Vlad Dudin
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException,
            FigureNotFoundException {
        boolean rst = false;
        Optional<Integer> index = this.findBy(source);
        Optional<Integer> seek = this.findBy(dest);
        if (!index.isPresent()) {
            throw new FigureNotFoundException();
        }
        Cell[] steps = this.figures[index.get()].way(source, dest);
        for (Cell step : steps) {
            Optional<Integer> empty = findBy(step);
            if (findBy(step).isPresent()) {
                throw new OccupiedWayException();
            }
        }
        if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
            rst = true;
            this.figures[index.get()] = this.figures[index.get()].copy(dest);
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private Optional<Integer> findBy(Cell cell) {
//        int rst = -1;
//        for (int index = 0; index != this.figures.length; index++) {
//            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
//                rst = index;
//                break;
//            }
//        }
//        return rst;

        return IntStream.range(0, this.figures.length)
                .filter(x -> this.figures[x] != null && this.figures[x].position().equals(cell)).boxed().findFirst();
    }
}