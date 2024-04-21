import java.util.*;
import java.util.stream.Collectors;
public class DijkstraPQ {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        int[] ans = new int[n];
        Arrays.fill(ans,-1);
        Map<Integer,Map<Integer,Integer>> g = new HashMap<>();
        for(int[] edge:edges){
            g.computeIfAbsent(edge[0], m -> new HashMap<>()).compute(edge[1], (k,v)-> v==null||v>edge[2]?edge[2]:v);
            g.computeIfAbsent(edge[1], m -> new HashMap<>()).compute(edge[0], (k,v)-> v==null||v>edge[2]?edge[2]:v);
        }
        //node and weight, comparing with weight
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(x -> x[1]));
        pq.offer(new int[]{0,0});//add first node 0

        while(!pq.isEmpty()){
            //get smallest distance node
            int[] lowest_dist = pq.poll();
            int node = lowest_dist[0];
            int dist = lowest_dist[1];
            if(ans[node]==-1 || ans[node]>dist){
                //second condition comes into pic when we offer next node later when looking at edges
                ans[node]=dist;
            }

            if(g.containsKey(node)){
                Map<Integer,Integer> weights = g.get(node);
                g.remove(node);//visited and min path is known so no longer needed in graph
                
                for(var entry: weights.entrySet()){
                    int to = entry.getKey();
                    int d = entry.getValue();
                    //remove reverse edge
                    if(g.containsKey(to)){
                        g.get(to).remove(node);
                    }
                    //condition as per problem 3112
                    if(dist+d<disappear[to]){
                        pq.offer(new int[]{to, dist+d});
                    }
                }
            }

        }

        return ans;
    }
    public static void main(String[] args) {
        DijkstraPQ d=new DijkstraPQ();
        int[] ans = d.minimumTime(3, new int[][]{{0,1,2},{1,2,1},{0,2,4}}, new int[]{1,3,5});
        System.out.println(Arrays.stream(ans).boxed().collect(Collectors.toList()));

    }
}
/*

int dijkstra(int n, Set<Integer>[] adj,int start, int end){
        // System.out.println(start+","+end);
        boolean[] visited = new boolean[n];
        int[] costs = new int[n];
        Arrays.fill(costs,-1);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
        pq.offer(new int[]{start,0});
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int v = curr[0];
            int curr_cost = curr[1];
            if(costs[v]==-1||curr_cost<costs[v]){
                costs[v]=curr_cost;
            }
            visited[v]=true;
            for(int edge:adj[v]){
                if(!visited[edge]){
                    pq.offer(new int[]{edge,costs[v]+1});
                }
            }
        }
        return costs[end];
    }
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Set<Integer>[] adj = new Set[n];
        for(int i=0;i<adj.length;i++){
            adj[i]=new HashSet<>();
        }
        for(int i=0;i<edges.length;i++){
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }
        return dijkstra(n, adj,source,destination)>=0;
    }

 */