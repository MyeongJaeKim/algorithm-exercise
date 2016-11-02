package weird;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Weird2 {

    private static final String NOT_WEIRD = "not weird";
    private static final String WEIRD = "weird";

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("./bin/weird/weird.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);

        int tests = Integer.parseInt(reader.readLine());

        for (; tests > 0; tests--) {
            int number = Integer.parseInt(reader.readLine());

            System.out.print(Integer.toString(number) + ": ");

            int totalSum = 0;
            int sum = 0;

            boolean noSubset = true;

            for (int n = (number + 1) / 2; n > 0; n--) {
                if (number % n == 0) {
                    totalSum += n;

                    if (noSubset) {
                        sum += n;

                        if (sum > number) {
                            sum -= n;
                        } else if (sum == number) {
                            noSubset = false;
                        }
                    }
                }
            }

            if (totalSum > number && noSubset) {
                System.out.println(WEIRD);
            } else {
                System.out.println(NOT_WEIRD);
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
