/*
 *Consider an undirected graph where each edge is the same weight. Each of the nodes is 
 *labeled consecutively.
 *You will be given a number of queries. For each query, you will be given a list of edges 
 *describing an undirected graph. After you create a representation of the graph, you must 
 *determine and report the shortest distance to each of the other nodes from a given starting 
 *position using the breadth-first search algorithm (BFS). Distances are to be reported in node
 *number order, ascending. If a node is unreachable, print -1 for that node.Each of the edges
 *weighs 6 units of distance.
 
 *Input Format:
 *The first line contains an integer q, the number of queries. Each of the following q sets of 
 *lines has the following format:
 *The first line contains two space-separated integers n and m, the number of nodes and edges 
 *in the graph.
 *Each line i of the m subsequent lines contains two space-separated integers, u and v, 
 *describing an edge connecting node u to node v.
 *The last line contains a single integer, s, denoting the index of the starting node.
 
 *Output Format:
 *For each of the q queries, print a single line of n-1 space-separated integers denoting 
 *the shortest distances to each of the n-1 other nodes from starting position s. These 
 *distances should be listed sequentially by node number but should not include node s. 
 *If some node is unreachable from s, print -1 as the distance to that node.
 */
import java.io.*;
import java.util.*;

public class BFSShortestReach {
    static int[] bfs(int n, int m, int[][] edges, int s) {
        int a[][]=new int[n][n];
        for(int i=0;i<edges.length;i++)
        {
            a[edges[i][0]-1][edges[i][1]-1]=1;
            a[edges[i][1]-1][edges[i][0]-1]=1;
        }
        int v[]=new int[n];
        LinkedList<Integer> q=new LinkedList<Integer>();
        v[s-1]=1;
        q.add(0);
        q.add(s-1);
        int l=0;
        while(q.size()!=0)
        {
            l=q.poll();
            s=q.poll();
            for(int i=0;i<n;i++)
            {
                if(a[s][i]==1 && v[i]==0)
                {
                    q.add(l+1);
                    q.add(i);
                    v[i]=(l+1)*6;
                }
            }
        }
        int[] ans =new int[n-1];
        for(int i=0,j=0;i<n;i++)
        {
            if(v[i]!=1 && v[i]!=0)
            {
                ans[j]=v[i];
            }
            else if(v[i]==0)
            {
                ans[j]=-1;
            }
            else
            {
                j--;
            }
            j++;
        }
        return ans;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
