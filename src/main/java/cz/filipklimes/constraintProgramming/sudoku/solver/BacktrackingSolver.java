package cz.filipklimes.constraintProgramming.sudoku.solver;

import cz.filipklimes.constraintProgramming.sudoku.SudokuInstance;

public class BacktrackingSolver implements Solver {

    @Override
    public SudokuInstance solve(final SudokuInstance instance) {
        return backtrack(instance);
    }

    public SudokuInstance backtrack(final SudokuInstance instance) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!instance.isFilled(row, col)) {
                    return backtrack(instance, new Point(row, col));
                }
            }
        }

        return instance.isComplete() && instance.isValid()
            ? instance
            : null;
    }

    public SudokuInstance backtrack(final SudokuInstance instance, final Point toFill) {
        if (!instance.isValid()) {
            return null;
        }

        if (instance.isComplete()) {
            return instance;
        }

        if (toFill == null) {
            return instance.isValid()
                ? instance
                : null;
        }

        for (int i = 1; i <= 9; i++) {
            Point nextToFill = next(instance, toFill);

            final SudokuInstance result = backtrack(
                instance.immutableFill(toFill.getRow(), toFill.getCol(), i),
                nextToFill
            );

            if (result != null) {
                return result;
            }
        }

        return null;
    }

    private Point next(SudokuInstance instance, Point toFill) {
        Point nextToFill = toFill.iterate();
        while (nextToFill != null && nextToFill.get(instance) != SudokuInstance.NOT_FILLED) {
            nextToFill = nextToFill.iterate();
        }
        return nextToFill;
    }

    private final static class Point {
        private final int row;
        private final int col;

        public Point(final int row, final int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public Point iterate() {
            int newRow = row;
            int newCol = col;

            newCol++;
            if (newCol > 8) {
                newCol = 0;
                newRow++;
            }

            if (newRow > 8) {
                return null;
            }

            return new Point(newRow, newCol);
        }

        public int get(SudokuInstance instance) {
            return instance.get(row, col);
        }

    }

}
