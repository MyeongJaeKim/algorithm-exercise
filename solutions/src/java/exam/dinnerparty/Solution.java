package exam.dinnerparty;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    static int NUM_PLACES;
    static int COST_LIMIT;

    static Place[] PLACES;
    static int[] CHOSEN;

    static int LARGEST_SATISFACTION;

    static int SATISFACTION_TOTAL;

    static int COST_TOTAL;

    private static void backtrack(int depth) {

        // Stop recursive call
        if (depth == NUM_PLACES) {
            if (SATISFACTION_TOTAL > LARGEST_SATISFACTION) {
                LARGEST_SATISFACTION = SATISFACTION_TOTAL;
            }

            return;
        }

        for (int idx = 0; idx < NUM_PLACES; idx++) {

            if (CHOSEN[idx] == 1) {
                continue;
            }

            int costIfChose = COST_TOTAL + PLACES[idx].cost;

            // Prunning
            if (costIfChose > COST_LIMIT) {
                if (SATISFACTION_TOTAL > LARGEST_SATISFACTION) {
                    LARGEST_SATISFACTION = SATISFACTION_TOTAL;
                }

                return;
            }
            // #1. Chose
            else {
                COST_TOTAL += PLACES[idx].cost;
                SATISFACTION_TOTAL += PLACES[idx].satisfaction;

                CHOSEN[idx] = 1;

                backtrack(depth + 1);
            }

            // #2. Not Chose
            if (CHOSEN[idx] == 1) {
                CHOSEN[idx] = 0;

                COST_TOTAL -= PLACES[idx].cost;
                SATISFACTION_TOTAL -= PLACES[idx].satisfaction;
            }

            backtrack(depth + 1);
        }

    }

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("./bin/exam/dinnerparty/data.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int tests = Integer.parseInt(br.readLine());

        for (int test = 1; test <= tests; test++) {
            String[] raw = br.readLine().split(" ");
            NUM_PLACES = Integer.parseInt(raw[0]);
            COST_LIMIT = Integer.parseInt(raw[1]);

            String[] satRaw = br.readLine().split(" ");
            String[] costRaw = br.readLine().split(" ");

            PLACES = new Place[NUM_PLACES];
            CHOSEN = new int[NUM_PLACES];

            for (int idx = 0; idx < NUM_PLACES; idx++) {
                int satisfaction = Integer.parseInt(satRaw[idx]);
                int cost = Integer.parseInt(costRaw[idx]);

                Place place = new Place();
                place.satisfaction = satisfaction;
                place.cost = cost;

                PLACES[idx] = place;
            }

            Arrays.sort(PLACES, comparator);
            LARGEST_SATISFACTION = 0;

            backtrack(0);

            System.out.print("#");
            System.out.print(test);
            System.out.print(" ");
            System.out.println(LARGEST_SATISFACTION);
        }

        if (br != null) {
            br.close();
        }

        if (isr != null) {
            isr.close();
        }

        if (fis != null) {
            fis.close();
        }
    }

    public static class Place {
        public int satisfaction;
        public int cost;

        public Place() {
            this.cost = 0;
            this.satisfaction = 0;
        }
    }

    static Comparator<Place> comparator = new Comparator<Place>() {
        @Override
        public int compare(Place left, Place right) {
            return left.cost - right.cost;
        }
    };
}
