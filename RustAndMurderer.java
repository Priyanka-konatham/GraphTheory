/*
 * Detective Rust is investigating a homicide and he wants to chase down the murderer. The 
 * murderer knows he would definitely get caught if he takes the main roads for fleeing, so
 *  he uses the village roads (or side lanes) for running away from the crime scene.
 * Rust knows that the murderer will take village roads and he wants to chase him down. He is 
 * observing the city map, but it doesn't show the village roads (or side lanes) on it and shows
 * only the main roads.The map of the city is a graph consisting N nodes where a specific given
 * node S represents the current position of Rust and the rest of the nodes denote other places
 * in the city, and an edge between two nodes is a main road between two places in the city. 
 * It can be suitably assumed that an edge that doesn't exist/isn't shown on the map is a
 * village road (side lane). That means, there is a village road between two nodes a and b 
 * iff(if and only if) there is no city road between them.
 * In this problem, distance is calculated as number of village roads (side lanes) between 
 * any two places in the city.
 * Rust wants to calculate the shortest distance from his position to all the other places in the 
 * city if he travels only using the village roads (side lanes).
 
 * Input Format:
 * The first line contains T, denoting the number of test cases.T testcases follow. 
 * First line of each test case has two integers N denoting the number of cities in the map and M,
 * denoting the number of roads in the map.
 * The next M lines each consist of two space-separated integers x and y denoting a main road 
 * between city x and city y. The last line has an integer S, denoting the current position of Rust.
 
 * Output Format:
 * For each of T test cases, print a single line consisting of N-1 space separated integers,
 * denoting the shortest distances of the remaining N-1 places from Rust's position (that is 
 * all distances, except the source node to itself) using the village roads/side lanes in 
 * ascending order based on vertex number. 
 */
import java.io.*;
import java.util.*;

public class RustAndMurderer {
    static Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    static StringBuffer sb = new StringBuffer();

    static Map<String, Collection<String>> mainRoadsMap;
    static int[] dists;

    public static void main(String[] args) {
        int testCases = in.nextInt();
        for (int i = 0; i < testCases; i++) {
            processTestCase();
        }
        System.out.println(sb.toString());
    }

    static void processTestCase() {
        int n = in.nextInt();
        readMainRoadsMap();
        int s = in.nextInt();
        traverseMap(n, s);
        writeResults(n, s);
    }

    static void readMainRoadsMap() {
        int m = in.nextInt();
        mainRoadsMap = new HashMap<String, Collection<String>>();
        for (int i = 0; i < m; i++) {
            String x = in.next();
            String y = in.next();
            if (!mainRoadsMap.containsKey(x)) {
                mainRoadsMap.put(x, new ArrayList<String>());
            }
            if (!mainRoadsMap.containsKey(y)) {
                mainRoadsMap.put(y, new ArrayList<String>());
            }
            mainRoadsMap.get(x).add(y);
            mainRoadsMap.get(y).add(x);
        }
    }

    static void traverseMap(int n, int s) {
        List<String> queue = new ArrayList<String>();
        boolean[] visited = new boolean[n+1];
        dists = new int[n + 1];

        queue.add(String.valueOf(s));
        visited[s] = true;

        int counter = 1;
        while (!queue.isEmpty() && counter < n) {
            String v = queue.remove(0);
            int vi = Integer.valueOf(v);
            Collection<String> nbs = mainRoadsMap.containsKey(v) ? mainRoadsMap.get(v) : new ArrayList<String>();
            boolean[] isNb = new boolean[n+1];
            for (String nb : nbs) {
                isNb[Integer.parseInt(nb)] = true;
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i] && !isNb[i]) {
                    dists[i] = dists[vi] + 1;
                    queue.add(String.valueOf(i));
                    visited[i] = true;
                    counter++;
                }
            }
        }
    }

    static void writeResults(int n, int s) {
        for (int i = 1; i < s; i++) {
            sb.append(dists[i]).append(' ');
        }
        for (int i = s + 1; i <= n; i++) {
            sb.append(dists[i]).append(' ');
        }
        sb.append('\n');
    }
}