package exam.dinnerparty;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

import exam.dinnerparty.Solution.Place;

public class Solution2 {

    static int NUM_PLACES;
    static int COST_LIMIT;

    static Place[] PLACES;

    public static void main(String[] args) {
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

            for (int idx = 0; idx < NUM_PLACES; idx++) {
                int satisfaction = Integer.parseInt(satRaw[idx]);
                int cost = Integer.parseInt(costRaw[idx]);

                Place place = new Place(satisfaction, cost);
                PLACES[idx] = place;
            }

            Arrays.sort(PLACES, effCompare);
            Arrays.sort(PLACES, costCompare);

            for (int idx = 0; idx < NUM_PLACES; idx++) {

            }

            System.out.print("#");
            System.out.print(test);
            System.out.print(" ");
            System.out.println();
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
        public long efficiency;

        public Place(int cost, int satisfaction) {
            this.cost = cost;
            this.satisfaction = satisfaction;
            this.efficiency = satisfaction / (long) cost;
        }
    }

    static Comparator<Place> effCompare = new Comparator<Place>() {
        @Override
        public int compare(Place left, Place right) {
            return Double.compare(left.efficiency, right.efficiency);
        }
    };

    static Comparator<Place> costCompare = new Comparator<Place>() {
        @Override
        public int compare(Place left, Place right) {
            return left.cost - right.cost;
        }
    };
}
