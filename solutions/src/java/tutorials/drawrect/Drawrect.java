package tutorials.drawrect;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @see https://algospot.com/judge/problem/read/DRAWRECT
 * @author Architect
 *
 */
public class Drawrect {

    private static final String BLANK = " ";

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(
                "./bin/tutorials/drawrect/sample_drawrect.txt");
        System.setIn(fis);

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));

        int tests = Integer.parseInt(reader.readLine());

        for (int idx = 0; idx < tests; idx++) {
            String[] first = reader.readLine().split(BLANK);
            String[] second = reader.readLine().split(BLANK);
            String[] third = reader.readLine().split(BLANK);

            int[][] vertices = new int[3][2];
            vertices[0][0] = Integer.parseInt(first[0]);
            vertices[0][1] = Integer.parseInt(first[1]);
            vertices[1][0] = Integer.parseInt(second[0]);
            vertices[1][1] = Integer.parseInt(second[1]);
            vertices[2][0] = Integer.parseInt(third[0]);
            vertices[2][1] = Integer.parseInt(third[1]);

            // find missing X
            // int missingX = vertices[0][0] ^ vertices[1][0] ^ vertices[2][0];
            int missingX = 0;

            if (vertices[1][0] == vertices[2][0]) {
                missingX = vertices[0][0];
            } else if (vertices[0][0] == vertices[1][0]) {
                missingX = vertices[2][0];
            } else {
                missingX = vertices[1][0];
            }

            // find missing Y
            // int missingY = vertices[0][1] ^ vertices[1][1] ^ vertices[2][1];
            int missingY = 0;

            if (vertices[1][1] == vertices[2][1]) {
                missingY = vertices[0][1];
            } else if (vertices[0][1] == vertices[1][1]) {
                missingY = vertices[2][1];
            } else {
                missingY = vertices[1][1];
            }

            StringBuilder sb = new StringBuilder();
            sb.append(missingX).append(BLANK).append(missingY);
            System.out.println(sb.toString());
        }

        if (reader != null) {
            reader.close();
        }

        if (fis != null) {
            fis.close();
        }
    }
}
