package cz.filipklimes.constraintProgramming.sudoku.print;

import cz.filipklimes.constraintProgramming.sudoku.SudokuInstance;

public class SudokuPrettyPrinter {

    public static String print(final SudokuInstance instance, final SudokuInstance solution) {
        final StringBuilder sb = new StringBuilder();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (instance.get(row, col) == SudokuInstance.NOT_FILLED) {
                    sb.append("_").append(" ");
                } else {
                    sb.append(instance.get(row, col)).append(" ");
                }
                if (col % 3 == 2 && col != 8) {
                    sb.append("| ");
                }
            }

            if (row == 4) {
                sb.append("  =>  ");
            } else {
                sb.append("      ");
            }

            for (int col = 0; col < 9; col++) {
                sb.append(solution.get(row, col)).append(" ");
                if (col % 3 == 2 && col != 8) {
                    sb.append("| ");
                }
            }

            sb.append("\n");
            if (row % 3 == 2 && row != 8) {
                sb.append("---------------------       ---------------------\n");
            }
        }

        return sb.toString();
    }

}
