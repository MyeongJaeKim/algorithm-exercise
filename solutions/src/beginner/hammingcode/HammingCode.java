package hammingcode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class HammingCode {
    private static int[][] XOR_POSITIONS = { { 1, 3, 5, 7 }, { 2, 3, 6, 7 }, { 4, 5, 6, 7 } };
    private static int[] DECODED_DIGITS = { 3, 5, 6, 7 };

    private static int SYNDROME_LEN = 3;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("./bin/hammingcode/hammingcode.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);

        int tests = Integer.parseInt(reader.readLine());

        for (; tests > 0; tests--) {
            char[] receivedMsg = reader.readLine().toCharArray();

            int[] syndrome = new int[SYNDROME_LEN];

            for (int round = 0; round < 3; round++) {
                int syndromeIdx = SYNDROME_LEN - round - 1;
                syndrome[syndromeIdx] = receivedMsg[XOR_POSITIONS[round][0] - 1];
                for (int idx = 1; idx < 4; idx++) {
                    syndrome[syndromeIdx] = syndrome[syndromeIdx] ^ receivedMsg[XOR_POSITIONS[round][idx] - 1];
                }
            }

            int corruptedDigit = convertToDecimal(syndrome) - 1;

            if (corruptedDigit > 0) {
                char digit = receivedMsg[corruptedDigit];

                if (digit == '1') {
                    receivedMsg[corruptedDigit] = '0';
                } else {
                    receivedMsg[corruptedDigit] = '1';
                }
            }

            for (int i = 0; i < 4; i++) {
                System.out.print(receivedMsg[DECODED_DIGITS[i] - 1]);
            }

            System.out.println();
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

    private static int convertToDecimal(int[] binaryDigits) {
        int decimal = 0;
        int length = binaryDigits.length - 1;

        for (int pow = 0; pow < binaryDigits.length; pow++) {
            if (binaryDigits[pow] == 1) {
                decimal += Math.pow(2, length - pow);
            }
        }
        return decimal;
    }
}
