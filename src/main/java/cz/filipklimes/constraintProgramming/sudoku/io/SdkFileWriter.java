package cz.filipklimes.constraintProgramming.sudoku.io;

import cz.filipklimes.constraintProgramming.sudoku.SudokuInstance;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

public class SdkFileWriter implements Closeable {

    private final OutputStream outputStream;

    public SdkFileWriter(final OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(final SudokuInstance instance) throws IOException {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                outputStream.write(toSdkFormat(instance.get(row, col)));
            }
            outputStream.write('\n');
        }
        outputStream.flush();
    }

    private int toSdkFormat(final int value) {
        if (value == SudokuInstance.NOT_FILLED) {
            return '.';
        }

        return Character.forDigit(value, 10);
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }

}
