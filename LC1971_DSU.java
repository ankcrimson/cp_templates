import java.util.*;
public class LC1971_DSU {

    class DSU{
        int parent[] , size[] , n;
        
        DSU(int n){
            this.n = n;
            parent = new int[n];
            size = new int[n];
            Arrays.fill(size,1);
            for(int i=0;i<n;i++){
                parent[i] = i;
            }
        }

        public int find(int node){
            if(node==parent[node])  return node;
            return parent[node] = find(parent[node]);
        }

        public void union(int a,int b){
            int aParent= find(a) ,bParent = find(b); 
            if(size[aParent]>=size[bParent]){
                parent[bParent] = aParent;
                size[aParent]+=size[bParent];
            }
            else{
                parent[aParent] = bParent;
                size[bParent]+=size[aParent];
            }
        }
    }
        public boolean validPath(int n, int[][] edges, int source, int destination) {
        DSU dsu = new DSU(n);
        for(int[] edge:edges){
            dsu.union(edge[0],edge[1]);
        }
        return dsu.find(source)==dsu.find(destination);
    }
    
}
