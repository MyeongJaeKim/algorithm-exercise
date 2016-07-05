package tutorials.hotsummer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @see https://algospot.com/judge/problem/read/HOTSUMMER
 * @author Architect
 *
 */
public class HotSummer {

    private static final String BLANK = " ";

    private static final String YES = "YES";
    private static final String NO = "NO";

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(
                "./bin/tutorials/hotsummer/hotsummer.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);

        int buildings = Integer.parseInt(reader.readLine());

        for (; buildings > 0; buildings--) {
            int goalUsage = Integer.parseInt(reader.readLine());
            String[] predictions = reader.readLine().split(BLANK);

            int total = 0;
            boolean safe = true;

            for (int idx = 0; idx < predictions.length; idx++) {
                total += Integer.parseInt(predictions[idx]);
                if (total > goalUsage) {
                    safe = false;
                    break;
                }
            }

            if (safe) {
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
}
