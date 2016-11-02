package weird;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @see https://algospot.com/judge/problem/read/WEIRD
 * @author Architect
 *
 */
public class Weird {

    private static final String NOT_WEIRD = "not weird";
    private static final String WEIRD = "weird";

    // Caches
    private static final HashMap<String, Set<Integer>> PROPER_DIVISORS;
    private static final HashMap<String, Integer> SUMS;

    static {
        PROPER_DIVISORS = new HashMap<String, Set<Integer>>();
        SUMS = new HashMap<String, Integer>();

        // Init value 1
        Set<Integer> one = new HashSet<Integer>();
        one.add(1);

        PROPER_DIVISORS.put(str(1), one);
    }

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("./bin/weird/weird.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);

        int tests = Integer.parseInt(reader.readLine());

        for (; tests > 0; tests--) {
            int number = Integer.parseInt(reader.readLine());

            //checkConditions(number);

            if (checkConditions(number)) {
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

    private static String str(int num) {
        return Integer.toString(num);
    }

    private static Set<Integer> findProperDivisors(int originalNum, int mean) {
        // See we knew already
        if (PROPER_DIVISORS.containsKey(str(originalNum))) {
            return PROPER_DIVISORS.get(str(originalNum));
        }

        Set<Integer> propDivs = new HashSet<Integer>();
        propDivs.add(1);

        int stopValue = mean;

        for (int n = 2; n <= stopValue; n++) {
            if (originalNum % n == 0) {
                if (propDivs.contains(n)) {
                    continue;
                }

                Set<Integer> subset = findProperDivisors(n, (n + 1) / 2);
                propDivs.addAll(subset);
                propDivs.add(n);

                int q = originalNum / n;

                // We don't need to go further than quotient val
                stopValue = q;
                propDivs.add(q);
            }
        }

        PROPER_DIVISORS.put(str(originalNum), propDivs);
        return propDivs;
    }

    private static boolean isGreaterThan(int num) {
        Set<Integer> divisors = PROPER_DIVISORS.get(str(num));

        int total = 0;
        for (Integer n : divisors) {
            total += n;
        }

        if (num >= total) {
            return true;
        }

        SUMS.put(str(num), total);
        return false;
    }

    private static boolean isPrimeNumber(int num) {
        if (PROPER_DIVISORS.get(str(num)).size() == 1) {
            return true;
        }
        return false;
    }

    private static boolean[] visited;

    private static ArrayList<Integer> convertToSortedList(Set<Integer> set) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()) {
            Integer value = iter.next();
            list.add(value);
        }

        Collections.sort(list);
        return list;
    }

    private static boolean backtrack(int goalVal, ArrayList<Integer> numbers, int sum, int depthLimit, int depth) {
        if (depth > depthLimit) {
            if (sum == goalVal) {
                return true;
            } else {
                return false;
            }
        }

        for (int i = numbers.size() - 1; i >= 0; i--) {
            int n = numbers.get(i);
            // We don't need to go further anymore
            if (sum + n > goalVal) {
                break;
            }

            if (visited[i] == true) {
                continue;
            }

            visited[i] = true;
            if (backtrack(goalVal, numbers, sum + n, depthLimit, depth + 1)) {
                return true;
            }
            visited[i] = false;
        }

        return false;
    }

    private static boolean canMakeDifferentialValue(int num) {
        int diff = SUMS.get(str(num)) - num;
        Set<Integer> divisors = PROPER_DIVISORS.get(str(num));
        ArrayList<Integer> sortedPropDivs = convertToSortedList(divisors);

        // Make Shorter ArrayList, every element is less than 'diff'
        ArrayList<Integer> shorten = new ArrayList<Integer>();
        for (Integer divisor : sortedPropDivs) {
            if (divisor < diff) {
                shorten.add(divisor);
            } else if (divisor == diff) {
                return true;
            } else {
                break;
            }
        }

        visited = new boolean[shorten.size()];

        for (int n = 2; n <= shorten.size(); n++) {
            if (backtrack(diff, shorten, 0, n, 1)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isSubsetExisted(int num) {
        // Pruning condition
        if (isPrimeNumber(num)) {
            return false;
        }

        if (canMakeDifferentialValue(num)) {
            return true;
        }

        return false;
    }

    private static boolean checkConditions(int num) {
        // Step #1. Find all divisors of given number
        findProperDivisors(num, (num + 1) / 2);

        // Step #2. Check sum of proper divisors are greater then num
        if (isGreaterThan(num)) {
            return false;
        }

        // Step #3. Find first subset of sum of divisors, which is equal to given number
        if (isSubsetExisted(num)) {
            return false;
        }

        return true;
    }
}
