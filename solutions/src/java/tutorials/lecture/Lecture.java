package tutorials.lecture;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @see https://algospot.com/judge/problem/read/LECTURE
 * @author Architect
 *
 */
public class Lecture {

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(
                "./bin/tutorials/lecture/sample_lecture.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);

        int tests = Integer.parseInt(reader.readLine());

        // String Comparator for Arrays.sort()
        Comparator<String> stringComparator = new Comparator<String>() {
            @Override
            public int compare(String left, String right) {
                return left.compareTo(right);
            }
        };

        for (int test = 0; test < tests; test++) {
            String raw = reader.readLine();
            String[] tokens = new String[raw.length() / 2];

            // Split 2 bytes into tokens[]
            for (int idx = 0; idx < raw.length(); idx += 2) {
                tokens[idx / 2] = raw.substring(idx, idx + 2);
            }

            Arrays.sort(tokens, stringComparator);

            // Making result
            StringBuilder sorted = new StringBuilder();
            for (String token : tokens) {
                sorted.append(token);
            }
            System.out.println(sorted.toString());
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
