Dijkstra Solution:

```java
class Solution {
    public boolean onediff(char[] a, char[] b){
        int diff=0;
        for(int i=0;i<8;i++){
            if(a[i]!=b[i]){
                diff++;
                if(diff>1){
                    return false;
                }
            }
        }
        return diff==1;
    }
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

    public int minMutation(String startGene, String endGene, String[] bank) {
        int start = -1,end=-1;
        for(int i=0;i<bank.length;i++){
            if(startGene.equals(bank[i])){start=i;}
            if(endGene.equals(bank[i])){end=i;}
        }
        //tgt invalid
        if(end==-1){
            return -1;
        }
        boolean missingStart=start==-1;
        char[][] bankchars=new char[missingStart?bank.length+1:bank.length][];
        for(int i=0;i<bank.length;i++){
            bankchars[i]=bank[i].toCharArray();
        }
        if(missingStart){
            start=bankchars.length-1;
            bankchars[start]=startGene.toCharArray();
            
        }
        
        Set<Integer>[] adj = new Set[bankchars.length];
        for(int i=0;i<adj.length;i++){
            adj[i]=new HashSet<>();
        }
        for(int i=0;i<bankchars.length;i++){
            for(int j=i+1;j<bankchars.length;j++){
                if(onediff(bankchars[i],bankchars[j])){
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        return dijkstra(bankchars.length, adj,start,end);
    }
    
}
```

bfs
```java
lass Solution {
    public boolean onediff(char[] a, char[] b){
        int diff=0;
        for(int i=0;i<8;i++){
            if(a[i]!=b[i]){
                diff++;
                if(diff>1){
                    return false;
                }
            }
        }
        return diff==1;
    }

    int bfs(String start, String end, String[] bank){
        Deque<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int steps = -1;
        q.addLast(start);
        while(q.size()>0){
            steps++;
            int size=q.size();
            while(size>0){
                size--;
                String curr = q.removeFirst();
                visited.add(curr);
                if(curr.equals(end)){
                    return steps;
                }
                for(String s:bank){
                    if(!visited.contains(s)){
                        if(onediff(curr.toCharArray(), s.toCharArray())){
                            q.addLast(s);
                        }
                    }
                }
            }
            
        }
        return -1;
    }
    public int minMutation(String startGene, String endGene, String[] bank) {
        return bfs(startGene,endGene,bank);
    }
}
```