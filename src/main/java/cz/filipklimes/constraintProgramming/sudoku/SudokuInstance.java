package cz.filipklimes.constraintProgramming.sudoku;

import java.util.Arrays;

public class SudokuInstance {

    public static int NOT_FILLED = 0;

    private final int[] data;

    private SudokuInstance(final int[] data) {
        this.data = Arrays.copyOf(data, data.length);
    }

    public int get(final int row, final int col) {
        return data[toInternalRepresentation(row, col)];
    }

    public boolean isFilled(final int row, final int col) {
        return get(row, col) != NOT_FILLED;
    }

    public SudokuInstance immutableFill(final int row, final int col, final int value) {
        final SudokuInstance copy = new SudokuInstance(this.data);
        copy.fill(row, col, value);
        return copy;
    }

    public boolean isValid() {
        // Validate rows
        for (int row = 0; row < 9; row++) {
            boolean[] present = new boolean[10];
            for (int col = 0; col < 9; col++) {
                int value = data[toInternalRepresentation(row, col)];
                if (value == NOT_FILLED) {
                    continue;
                }

                if (present[value]) {
                    return false;
                }

                present[value] = true;
            }
        }

        // Validate cols
        for (int col = 0; col < 9; col++) {
            boolean[] present = new boolean[10];
            for (int row = 0; row < 9; row++) {
                int value = data[toInternalRepresentation(row, col)];
                if (value == NOT_FILLED) {
                    continue;
                }

                if (present[value]) {
                    return false;
                }

                present[value] = true;
            }
        }

        // Validate sectors
        for (int sec = 0; sec < 9; sec++) {
            boolean[] present = new boolean[10];
            int startCol = (sec % 3) * 3;
            int startRow = (sec / 3) * 3;

            for (int row = startRow; row < startRow + 3; row++) {
                for (int col = startCol; col < startCol + 3; col++) {
                    int value = data[toInternalRepresentation(row, col)];
                    if (value == NOT_FILLED) {
                        continue;
                    }

                    if (present[value]) {
                        return false;
                    }

                    present[value] = true;
                }
            }
        }
        return true;
    }

    public boolean isComplete() {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == NOT_FILLED) {
                return false;
            }
        }

        return true;
    }

    private void fill(final int row, final int col, final int value) {
        if (value < 1 || value > 9) {
            throw new IllegalArgumentException("value must be between 1 and 9");
        }
        if (isFilled(row, col)) {
            throw new IllegalStateException("this place is already filled");
        }

        data[toInternalRepresentation(row, col)] = value;
    }

    private int toInternalRepresentation(final int row, final int col) {
        if (row < 0 || row > 8) {
            throw new IllegalArgumentException("row must be between 0 and 8");
        }
        if (col < 0 || col > 8) {
            throw new IllegalArgumentException("col must be between 0 and 8");
        }

        return row * 9 + col;
    }

    public static SudokuInstance fromData(final int data[]) {
        if (data == null || data.length != 9 * 9) {
            throw new IllegalArgumentException("9x9 puzzle is required");
        }
        for (int i = 0; i < data.length; i++) {
            if (data[i] != NOT_FILLED && (data[i] < 1 || data[i] > 9)) {
                throw new IllegalArgumentException("data must be between 1 and 9 or NOT_FILLED");
            }
        }

        return new SudokuInstance(data);
    }

}
