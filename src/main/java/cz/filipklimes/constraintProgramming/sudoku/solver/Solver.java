package cz.filipklimes.constraintProgramming.sudoku.solver;

import cz.filipklimes.constraintProgramming.sudoku.SudokuInstance;

public interface Solver {

    /**
     * Solves given sudoku puzzle.
     *
     * @param instance The instance to solve. Assumed to be incomplete.
     * @return Solved instance or `NULL`, if the input instance could not be solved.
     */
    SudokuInstance solve(SudokuInstance instance);

}
