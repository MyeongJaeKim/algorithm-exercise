package tutorials.merciful;

import java.util.Scanner;

/**
 * @see https://algospot.com/judge/problem/read/MERCY
 */
public class Merciful {
    
    private static final String HELLO_MSG = "Hello Algospot!";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        int loops = sc.nextInt();
        
        for (int idx = 0 ; idx < loops ; idx++)
        {
            System.out.println(HELLO_MSG);
        }
        
        if (sc != null)
        {
            sc.close();
        }
    }
}
