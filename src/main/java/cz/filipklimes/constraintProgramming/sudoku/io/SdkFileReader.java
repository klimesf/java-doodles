package cz.filipklimes.constraintProgramming.sudoku.io;

import cz.filipklimes.constraintProgramming.sudoku.SudokuInstance;

import java.io.*;

public class SdkFileReader implements Closeable {

    private final InputStream inputStream;

    public SdkFileReader(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public SudokuInstance read() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        final int data[] = new int[9 * 9];
        int dataIterator = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("#")) {
                continue;
            }

            if (line.length() != 9) {
                throw new RuntimeException("Corrupted file: each row must have 9 columns");
            }

            for (int i = 0; i < 9; i++, dataIterator++) {
                char ch = line.charAt(i);
                if (ch < '0' || ch > '9') {
                    data[dataIterator] = SudokuInstance.NOT_FILLED;
                } else {
                    data[dataIterator] = Character.getNumericValue(ch);
                }
            }
        }

        return SudokuInstance.fromData(data);
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

}
