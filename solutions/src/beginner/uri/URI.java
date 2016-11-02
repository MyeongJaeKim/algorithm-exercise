package uri;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @see https://algospot.com/judge/problem/read/URI
 * @author Architect
 *
 */
public class URI {

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("./bin/uri/uri.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);

        int texts = Integer.parseInt(reader.readLine());

        for (; texts > 0; texts--) {
            String result = new String(strip(reader.readLine().toCharArray()));
            System.out.println(result.trim());
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

    private static char[] strip(char[] text) {
        int srcIdx = 0;
        int srcLastCopied = 0;
        int destIdx = 0;

        char[] result = new char[text.length];

        while (srcIdx < text.length) {
            // Iterate until %
            if (text[srcIdx] != '%') {
                srcIdx++;
            } else {
                // Copy char[] from previous to current idx - 1
                int copyLength = srcIdx - srcLastCopied;

                if (copyLength > 0) {
                    System.arraycopy(text, srcLastCopied, result, destIdx, copyLength);
                    destIdx += copyLength;
                }

                // Convert
                char code = (char) Integer.parseInt(new String(new char[] { text[srcIdx + 1], text[srcIdx + 2] }), 16);
                result[destIdx++] = code;

                // Skip whole '%OO'
                srcIdx += 3;
                srcLastCopied = srcIdx;
            }
        }

        // Remainings
        if (srcLastCopied < srcIdx) {
            System.arraycopy(text, srcLastCopied, result, destIdx, srcIdx - srcLastCopied);
        }

        return result;
    }
}
