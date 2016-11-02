package shisensho;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @see https://algospot.com/judge/problem/read/SHISENSHO
 * @author Architect
 *
 */
public class Shisensho {

    private static final String BLANK = " ";

    private static final char EMPTY = '.';

    private static char[][] BOARD;

    private static int ROW_SIZE;

    private static int COL_SIZE;

    private static char CHECK_ALPHABET;

    private static int CHECK_ROW;

    private static int CHECK_COL;

    enum LookupPhase {
        FIRST, SECOND, THIRD
    }

    public static void main(String[] args) throws Exception {
        final FileInputStream fis = new FileInputStream("./bin/shisensho/shisensho.txt");
        System.setIn(fis);

        final InputStreamReader isr = new InputStreamReader(System.in);
        final BufferedReader reader = new BufferedReader(isr);

        int tests = Integer.parseInt(reader.readLine());

        for (; tests > 0; tests--) {
            // Initialize Gameboard
            final String[] sizes = reader.readLine().split(BLANK);
            ROW_SIZE = Integer.parseInt(sizes[0]);
            COL_SIZE = Integer.parseInt(sizes[1]);

            BOARD = new char[ROW_SIZE][COL_SIZE];

            for (int rowIdx = 0; rowIdx < ROW_SIZE; rowIdx++) {
                final char[] data = reader.readLine().toCharArray();
                for (int colIdx = 0; colIdx < COL_SIZE; colIdx++) {
                    BOARD[rowIdx][colIdx] = data[colIdx];
                }
            }

            System.out.println(Integer.toString(findPaths()));
        }

        if (reader != null) {
            reader.close();
        }
        if (isr != null) {
            isr.close();
        }
        if (fis != null) {
            fis.close();
        }
    }

    private static int findPaths() {
        int numOfPaths = 0;

        // Marker can not be located at edge lines
        for (int row = 1; row < ROW_SIZE - 1; row++) {
            for (int col = 1; col < COL_SIZE - 1; col++) {
                CHECK_ALPHABET = BOARD[row][col];

                if (CHECK_ALPHABET != EMPTY) {
                    int ways = lookForPossibles(row, col);

                    if (ways > 0) {
                        // To avoid double check
                        numOfPaths++;
                        BOARD[row][col] = EMPTY;
                    }
                }
            }
        }

        return numOfPaths;
    }

    private static int lookForPossibles(int row, int col) {
        int ways = 0;

        // Up
        CHECK_ROW = row;
        CHECK_COL = col;
        if (checkUpDirection(LookupPhase.FIRST)) {
            // Left
            // from CHECK_COL - 1 to 1
            for (int i = 1; i < col - 1; i++) {
                CHECK_COL = col - i;
                if (checkDownDirection(LookupPhase.THIRD)) {
                    ways++;
                }
            }

            // Right
            for (int i = 1; i < COL_SIZE - col - 1; i++) {
                CHECK_COL = col + i;
                if (checkDownDirection(LookupPhase.THIRD)) {
                    ways++;
                }
            }
        }

        // Down
        CHECK_ROW = row;
        CHECK_COL = col;
        if (checkDownDirection(LookupPhase.FIRST)) {
            // Left
            // from COL - 1 to 1
            for (int i = 1; i < col - 1; i++) {
                CHECK_COL = col - i;
                if (checkUpDirection(LookupPhase.THIRD)) {
                    ways++;
                }
            }

            // Right
            for (int i = 1; i < COL_SIZE - col - 1; i++) {
                CHECK_COL = col + i;
                if (checkUpDirection(LookupPhase.THIRD)) {
                    ways++;
                }
            }
        }

        // Left
        CHECK_ROW = row;
        CHECK_COL = col;
        if (checkLeftDirection(LookupPhase.FIRST)) {
            // Up
            for (int i = 1; i < row - 1; i++) {
                CHECK_ROW = row - i;
                if (checkRightDirection(LookupPhase.THIRD)) {
                    ways++;
                }
            }

            // Down
            for (int i = 1; i < ROW_SIZE - row - 1; i++) {
                CHECK_ROW = row + i;
                if (checkRightDirection(LookupPhase.THIRD)) {
                    ways++;
                }
            }
        }

        // Right
        CHECK_ROW = row;
        CHECK_COL = col;
        if (checkRightDirection(LookupPhase.FIRST)) {
            // Up
            for (int i = 1; i < row - 1; i++) {
                CHECK_ROW = row - i;
                if (checkLeftDirection(LookupPhase.THIRD)) {
                    ways++;
                }
            }

            // Down
            for (int i = 1; i < ROW_SIZE - row - 1; i++) {
                CHECK_ROW = row + i;
                if (checkLeftDirection(LookupPhase.THIRD)) {
                    ways++;
                }
            }
        }

        return ways;
    }

    private static boolean checkUpDirection(final LookupPhase phase) {
        switch (phase) {
        case FIRST:

            if (CHECK_ROW == 1) {
                CHECK_ROW = 0;
                return true;
            }

            int index = 1;

            while (CHECK_ROW - index > 0 && BOARD[CHECK_ROW - index][CHECK_COL] == EMPTY) {
                index++;
            }

            if (index > 1) {
                CHECK_ROW -= index - 1;
                return true;
            }
            break;

        /*
        // Move up to the edge row (0th row)

        for (int i = 1; i <= CHECK_ROW; i++) {
            char current = BOARD[CHECK_ROW - i][CHECK_COL];

            // There is a tile
            if (current != EMPTY) {
                return false;
            }
        }
        CHECK_ROW = 0;
        return true;
        */
        case SECOND:
            break;
        case THIRD:
            // Check to upper direction until we meet CHECK_ALPHABET
            for (int i = 1; i < CHECK_ROW; i++) {
                char current = BOARD[CHECK_ROW - i][CHECK_COL];

                if (current == CHECK_ALPHABET) {
                    return true;
                }
            }
            break;
        }

        return false;
    }

    private static boolean checkDownDirection(final LookupPhase phase) {
        switch (phase) {
        case FIRST:

            int index = 1;

            while (CHECK_ROW + index < ROW_SIZE && BOARD[CHECK_ROW + index][CHECK_COL] == EMPTY) {
                index++;
            }

            if (index > 1) {
                CHECK_ROW += index - 1;
                return true;
            }

            break;

        /*
        // Move down to the edge row (End row)
        for (int i = 1; i < ROW_SIZE - CHECK_ROW; i++) {
            char current = BOARD[CHECK_ROW + i][CHECK_COL];

            if (current != EMPTY) {
                return false;
            }
        }
        CHECK_ROW = ROW_SIZE - 1;
        return true;
        */
        case SECOND:
            break;
        case THIRD:
            // Check to down direction until we meet CHECK_ALPHABET (to ROW SIZE - 1)
            for (int i = 1; i < ROW_SIZE - 1; i++) {
                char current = BOARD[CHECK_ROW + i][CHECK_COL];

                if (current == CHECK_ALPHABET) {
                    return true;
                }
            }
            break;
        }

        return false;
    }

    private static boolean checkLeftDirection(final LookupPhase phase) {
        switch (phase) {
        case FIRST:

            int index = 1;

            while (CHECK_COL - index > 0 && BOARD[CHECK_ROW][CHECK_COL + index] == EMPTY) {
                index++;
            }

            if (index > 2) {
                CHECK_COL += index;
                return true;
            }

            break;
        /*
        // Move CHECK_COL to 0 th col
        for (int i = 1; i <= CHECK_COL; i++) {
            char current = BOARD[CHECK_ROW][CHECK_COL - i];

            if (current != EMPTY) {
                return false;
            }
        }

        CHECK_COL = 0;
        return true;
        */
        case SECOND:
            break;
        case THIRD:
            // Check to down direction until we meet CHECK_ALPHABET (to 1)
            for (int i = 1; i < CHECK_COL; i++) {
                char current = BOARD[CHECK_ROW][CHECK_COL - i];

                if (current == CHECK_ALPHABET) {
                    return true;
                }
            }

            break;
        }
        return false;
    }

    private static boolean checkRightDirection(final LookupPhase phase) {
        switch (phase) {
        case FIRST:
            // Move to the edge col
            for (int i = 1; i < COL_SIZE - CHECK_COL; i++) {
                char current = BOARD[CHECK_ROW][CHECK_COL + i];

                if (current != EMPTY) {
                    return false;
                }
            }

            CHECK_COL = COL_SIZE - 1;
            return true;
        case SECOND:
            break;
        case THIRD:
            // Check to right direction until we meet CHECK_ALPHA (to COL_SIZE - 1)
            for (int i = 1; i < COL_SIZE - CHECK_COL - 1; i++) {
                char current = BOARD[CHECK_ROW][CHECK_COL + i];

                if (current == CHECK_ALPHABET) {
                    return true;
                }
            }

            break;
        }

        return false;
    }
}
