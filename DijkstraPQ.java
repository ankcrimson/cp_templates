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
