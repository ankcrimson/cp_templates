import java.util.*;
// https://cp-algorithms.com/graph/dijkstra.html
public class Dijkstra {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        //create graph
        Map<Integer,List<Pair<Integer,Integer>>> nodes=new HashMap();
        for(int[] edge: edges){
            List<Pair<Integer,Integer>> node = nodes.getOrDefault(edge[0],new LinkedList());
            node.add(new Pair(edge[1],edge[2]));//to and distance
            nodes.put(edge[0],node);
            //add reverse too as it is undirected
            List<Pair<Integer,Integer>> node2 = nodes.getOrDefault(edge[1],new LinkedList());
            node2.add(new Pair(edge[0],edge[2]));//to and distance
            nodes.put(edge[1],node2);
        }
        //dijkstra algo
        //initialize visited
        boolean[] visited=new boolean[n];
        int[] distances = new int[n];
        for(int i=1;i<n;i++){
            distances[i]=Integer.MAX_VALUE;
        }
        // visited[0]=true;
        distances[0]=0;
        for(int i=0;i<n;i++){//each node
            int unmarked_n_with_lowest_val=0;
            int lowest_val=Integer.MAX_VALUE;
            for(int j=0;j<n;j++){//find unmarked min dist
                if(visited[j]==false && distances[j]<lowest_val){
                    lowest_val=distances[j];
                    unmarked_n_with_lowest_val=j;
                }
            }
            visited[unmarked_n_with_lowest_val]=true;
            //attempt to optimize the distance
            List<Pair<Integer,Integer>> curr_edges=nodes.get(unmarked_n_with_lowest_val);
            if(curr_edges!=null){
                for(int j=0;j<curr_edges.size();j++){
                    Pair<Integer,Integer> node = curr_edges.get(j);
                    if(distances[unmarked_n_with_lowest_val]+node.getValue()<distances[node.getKey()]){
                        distances[node.getKey()] = distances[unmarked_n_with_lowest_val]+node.getValue();
                    }
                }
            }
        }

        return distances;
    }
}
