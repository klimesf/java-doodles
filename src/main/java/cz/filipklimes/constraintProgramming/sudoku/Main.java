package cz.filipklimes.constraintProgramming.sudoku;

import cz.filipklimes.constraintProgramming.sudoku.io.SdkFileReader;
import cz.filipklimes.constraintProgramming.sudoku.io.SdkFileWriter;
import cz.filipklimes.constraintProgramming.sudoku.solver.BacktrackingSolver;
import cz.filipklimes.constraintProgramming.sudoku.solver.Solver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException {
        final Solver solver = new BacktrackingSolver();

        solve(solver, "easy", "sudoku/easy.sdk", "sudoku/easy.solved.sdk");
        solve(solver, "easy2", "sudoku/easy2.sdk", "sudoku/easy2.solved.sdk");
        solve(solver, "medium", "sudoku/medium.sdk", "sudoku/medium.solved.sdk");
        solve(solver, "hard", "sudoku/hard.sdk", "sudoku/hard.solved.sdk");
    }

    private static void solve(
        final Solver solver,
        final String name,
        final String inputFile,
        final String outputFile
    ) throws IOException {
        final SudokuInstance instance;

        try (SdkFileReader sdkReader = new SdkFileReader(new FileInputStream(inputFile))) {
            instance = sdkReader.read();
        }

        long start = System.nanoTime();
        final SudokuInstance solution = solver.solve(instance);
        long elapsed = System.nanoTime() - start;

        System.out.printf("%-6s [%5s] %4dms%n", name, solution != null, TimeUnit.NANOSECONDS.toMillis(elapsed));

        if (solution != null) {
            try (SdkFileWriter sdkWriter = new SdkFileWriter(new FileOutputStream(outputFile))) {
                sdkWriter.write(solution);
            }
        }
    }

}
