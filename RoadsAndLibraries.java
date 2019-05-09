/*
 * The Ruler of HackerLand believes that every citizen of the country should have access to a
 *  library. Unfortunately, HackerLand was hit by a tornado that destroyed all of its 
 *  libraries and obstructed its roads! As you are the greatest programmer of HackerLand, 
 *  the ruler wants your help to repair the roads and build some new libraries efficiently.

 *  HackerLand has n cities numbered from 1 to n The cities are connected by m bidirectional 
 *  roads. A citizen has access to a library if:
 *  Their city contains a library.
 *  They can travel by road from their city to a city containing a library.

 * Input Format:
 * The first line contains a single integer q, that denotes the number of queries.
 * The subsequent lines describe each query in the following format: 
   - The first line contains four space-separated integers that describe the respective values
     of ,n ,m  and c_lib ,c_road the number of cities, number of roads, cost of a library and 
     cost of a road. 
     Each of the next  lines contains two space-separated integers,  and , that describe a 
     bidirectional road that connects cities.
 * Output Format:
 * For each query, print an integer that denotes minimum cost to make libraries accessible to 
 * all the citizens on a new line.
 */

import java.io.*;
import java.util.*;

public class RoadsAndLibraries {
        static  long p=0;
        static long roadsAndLibraries(int n, long c_lib, long c_road, int[][] cities) {
        ArrayList<Integer>[] adj=new ArrayList[n];
        if(c_lib<c_road)
        {
            return n*c_lib;
        }
        for(int i=0;i<n;i++)
        {
            adj[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<cities.length;i++)
        {
            adj[cities[i][0]-1].add(cities[i][1]-1);
            adj[cities[i][1]-1].add(cities[i][0]-1);
        }
        boolean vis[]=new boolean[n];
        long cost=0;
        for(int i=0;i<n;i++)
        {
            if(!vis[i])
            {
                p=0;
                dfs(i,adj,vis);
                cost+=c_lib;
                cost+=(c_lib<c_road)?((p-1)*c_lib):((p-1)*c_road);
                System.out.println(cost+" "+i+" "+p);
            }
        }
        return cost;
    }
    public static void dfs(int v,ArrayList[] adj,boolean[] vis)
    {
        p++;
        vis[v]=true;
        for(int i=0;i<adj[v].size();i++)
        {
            if(!vis[(int)adj[v].get(i)])
            {
                dfs((int)adj[v].get(i),adj,vis);
            }
        }

    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            p=0;
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            long c_lib = Long.parseLong(nmC_libC_road[2]);

            long c_road = Long.parseLong(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
