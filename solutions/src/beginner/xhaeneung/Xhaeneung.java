package xhaeneung;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @see https://algospot.com/judge/problem/read/XHAENEUNG
 * @author Architect
 *
 */
public class Xhaeneung {
    // ASCii summations of both FIVE and NINE are only same
    private static final int ZERO = 'z' + 'e' + 'r' + 'o';
    private static final int ONE = 'o' + 'n' + 'e';
    private static final int TWO = 't' + 'w' + 'o';
    private static final int THREE = 't' + 'h' + 'r' + 'e' + 'e';
    private static final int FOUR = 'f' + 'o' + 'u' + 'r';
    private static final int FIVE = 'f' + 'i' + 'v' + 'e';
    private static final int SIX = 's' + 'i' + 'x';
    private static final int SEVEN = 's' + 'e' + 'v' + 'e' + 'n';
    private static final int EIGHT = 'e' + 'i' + 'g' + 'h' + 't';
    private static final int NINE = 'n' + 'i' + 'n' + 'e';
    private static final int TEN = 't' + 'e' + 'n';

    private static final ArrayList<Integer> NUMBERS;

    static {
        NUMBERS = new ArrayList<Integer>(11);
        NUMBERS.add(ZERO);
        NUMBERS.add(ONE);
        NUMBERS.add(TWO);
        NUMBERS.add(THREE);
        NUMBERS.add(FOUR);
        NUMBERS.add(FIVE);
        NUMBERS.add(SIX);
        NUMBERS.add(SEVEN);
        NUMBERS.add(EIGHT);
        NUMBERS.add(NINE);
        NUMBERS.add(TEN);
    }

    //private static final int[] NUMBERS = { ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE };

    private static final String SUM = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";

    private static final String YES = "Yes";
    private static final String NO = "No";
    private static final String BLANK = " ";

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("./bin/xhaeneung/xhaeneung.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);

        int equations = Integer.parseInt(reader.readLine());

        for (; equations > 0; equations--) {
            String[] equation = reader.readLine().split(BLANK);

            String op1 = equation[0];
            String op2 = equation[2];
            String operand = equation[1];
            String result = equation[4];

            if (evaluation(op1, op2, operand, result)) {
                System.out.println(YES);
            } else {
                System.out.println(NO);
            }
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

    private static int convertNumber(String str) {
        int asciiSum = 0;
        for (char c : str.toCharArray()) {
            asciiSum += c;
        }

        int result = -1;
        // decide five and nine first
        if (asciiSum == FIVE) {
            if (str.contains("f")) {
                result = 5;
            } else {
                result = 9;
            }
        } else {
            for (int idx = 0; idx < NUMBERS.size(); idx++) {
                if (asciiSum == NUMBERS.get(idx)) {
                    result = idx;
                }
            }
        }
        return result;
    }

    private static boolean evaluation(String _op1, String _op2, String operand, String _result) {
        // Getting real number
        int op1 = convertNumber(_op1);
        int op2 = convertNumber(_op2);
        int result = convertNumber(_result);

        if (op1 == -1 || op2 == -1 || result == -1) {
            return false;
        }

        int calculation = -1;

        // Getting real calculation result
        switch (operand) {
        case SUM:
            calculation = op1 + op2;
            break;
        case MINUS:
            calculation = op1 - op2;
            break;
        case MULTIPLY:
            calculation = op1 * op2;
            break;
        default:
            return false;
        }

        if (result != calculation) {
            return false;
        }

        return true;
    }
}
