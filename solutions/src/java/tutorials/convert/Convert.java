package tutorials.convert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @see https://algospot.com/judge/problem/read/CONVERT
 * @author Architect
 *
 */
public class Convert {

    private static final String BLANK = " ";

    private static final String KILOGRAM = "kg";
    private static final String POUND = "lb";
    private static final String GALON = "g";
    private static final String LITER = "l";

    private static final double TO_POUND = 2.2046;
    private static final double TO_KILOGRAM = 0.4536;
    private static final double TO_GALON = 0.2642;
    private static final double TO_LITER = 3.7854;

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(
                "./bin/tutorials/conversions/sample_conversions.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);

        int tests = Integer.parseInt(reader.readLine());

        for (int test = 1; test <= tests; test++) {
            String[] raw = reader.readLine().split(BLANK);

            double amount = Double.parseDouble(raw[0]);

            switch (raw[1]) {
            case GALON:
                System.out.printf("%d %.4f %s\n", test, (amount * TO_LITER),
                        LITER);
                break;
            case LITER:
                System.out.printf("%d %.4f %s\n", test, (amount * TO_GALON),
                        GALON);
                break;
            case KILOGRAM:
                System.out.printf("%d %.4f %s\n", test, (amount * TO_POUND),
                        POUND);
                break;
            case POUND:
                System.out.printf("%d %.4f %s\n", test, (amount * TO_KILOGRAM),
                        KILOGRAM);
                break;
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
}
