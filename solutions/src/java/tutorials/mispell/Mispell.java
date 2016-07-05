package tutorials.mispell;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @see https://algospot.com/judge/problem/read/MISPELL
 * @author Architect
 *
 */
public class Mispell {

    private static final String BLANK = " ";

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(
                "./bin/tutorials/mispell/sample_mispell.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);

        int tests = Integer.parseInt(reader.readLine());

        for (int test = 1; test <= tests; test++) {
            String[] read = reader.readLine().split(BLANK);

            int mispelledIdx = Integer.parseInt(read[0]) - 1;
            String text = read[1];

            StringBuilder sb = new StringBuilder();
            sb.append(test).append(BLANK);
            sb.append(text.substring(0, mispelledIdx)).append(
                    text.substring(mispelledIdx + 1, text.length()));
            System.out.println(sb.toString());
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
