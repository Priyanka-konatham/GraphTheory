/*
 * Given a graph which consists of several edges connecting its nodes, find a subgraph of the
 *  given graph with the following properties:
 * The subgraph contains all the nodes present in the original graph.
 * The subgraph is of minimum overall weight (sum of all edges) among all such subgraphs.
 * It is also required that there is exactly one, exclusive path between any two nodes of the 
 * subgraph.
 * One specific node S is fixed as the starting point of finding the subgraph using Prim's 
 * Algorithm. 
 * Find the total weight or the sum of all edges in the subgraph.
 
 *Input Format:
 * The first line has two space-separated integers n and n, the number of nodes and edges in the
 * graph.
 *Each of the next m lines contains three space-separated integers x, y and r, the end nodes of
 *edges[i], and the edge's weight. 
 * The last line has an integer start, denoting the starting node.

 *Output Format
 *Print a single integer denoting the total weight of the subgraph.
 */
import java.io.*;
import java.util.*;

public class PrimsMst {
    static int prims(int n, int[][] edges, int start) {
        int cost=0;
        int a[][]=new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                a[i][j]=Integer.MAX_VALUE;
            }
        }
        for(int i=0;i<edges.length;i++)
        {
            if(a[edges[i][0]-1][edges[i][1]-1]>edges[i][2])
                a[edges[i][0]-1][edges[i][1]-1]=edges[i][2];
            if(a[edges[i][1]-1][edges[i][0]-1]>edges[i][2])
                a[edges[i][1]-1][edges[i][0]-1]=edges[i][2];
        }
        sortbyColumn(edges,2);
        int[] near=new int[n];
        for(int i=0;i<n;i++)near[i]=Integer.MAX_VALUE;
        cost+=edges[0][2];
        near[edges[0][0]-1]=-1;
        near[edges[0][1]-1]=-1;
        for(int i=0;i<n;i++)
        {
            if(near[i]!=-1 && a[i][edges[0][0]-1]<a[i][edges[0][1]-1])
                near[i]=edges[0][0]-1;
            else if(near[i]!=-1)
                near[i]=edges[0][1]-1;
        }
        int v=0;
        for(int i=0;i<n;i++)
        {
            int min=Integer.MAX_VALUE;    
           for(int j=0;j<n;j++)
            if(near[j]!=-1 && a[j][near[j]]<min)
            {
                min=a[j][near[j]];
                v=j;
            }
            near[v]=-1;
            if(min!=Integer.MAX_VALUE)
                cost+=min;
            for(int k=0;k<n;k++)
            {
                if(near[k]!=-1 && a[k][near[k]]>a[k][v])
                    near[k]=v;
            } 
        }
        return cost;
    }
     public static void sortbyColumn(int arr[][], int col) 
    { 
        Arrays.sort(arr, new Comparator<int[]>() { 
            
          @Override              
          public int compare(final int[] entry1,  
                             final int[] entry2) { 
        if (entry1[col] > entry2[col]) 
                return 1; 
            else
                return -1; 
          } 
        });  
    } 
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] edgesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int edgesItem = Integer.parseInt(edgesRowItems[j]);
                edges[i][j] = edgesItem;
            }
        }

        int start = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = prims(n, edges, start);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
