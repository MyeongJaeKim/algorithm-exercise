package tutorials.encrypt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @see https://algospot.com/judge/problem/read/ENCRYPT
 * @author Architect
 *
 */
public class Encrypt {

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(
                "./bin/tutorials/encrypted/sample_encrypted.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);

        int tests = Integer.parseInt(reader.readLine());

        for (; tests > 0; tests--) {
            String plaintext = reader.readLine();
            int length = plaintext.length();

            char[] cipher = new char[length];

            for (int idx = 0; idx < length; idx += 2) {
                cipher[idx / 2] = plaintext.charAt(idx);
                if (idx + 1 < length) {
                    cipher[(length + idx + 1) / 2] = plaintext.charAt(idx + 1);
                }
            }

            System.out.println(cipher);
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
