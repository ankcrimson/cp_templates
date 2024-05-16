import java.util.*;
public class LC1971_DSU {
    
    class DSU{
        // https://cp-algorithms.com/data_structures/disjoint_set_union.html
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
            //find parent with path compression
            return parent[node] = find(parent[node]);
        }

        public void union(int a,int b){
            int aParent= find(a) ,bParent = find(b); 
            //union making the smaller set go into larger, ie. unionbysize
            if(size[aParent]>=size[bParent]){
                parent[bParent] = aParent;
                size[aParent]+=size[bParent];
                //no need to set size of b to 0 as it will be ignored
            }
            else{
                parent[aParent] = bParent;
                size[bParent]+=size[aParent];
            }
        }

        //todo add getSize()
    }
        public boolean validPath(int n, int[][] edges, int source, int destination) {
        DSU dsu = new DSU(n);
        for(int[] edge:edges){
            dsu.union(edge[0],edge[1]);
        }
        return dsu.find(source)==dsu.find(destination);
    }
    
}

/*
 class Solution {
    class DSU{
        int[] parent;
        int[] size;
        public DSU(int N){
            parent = new int[N];
            size = new int[N];
            Arrays.fill(size,1);
            for(int i=0;i<N;i++){
                parent[i]=i;
            }
        }
        public void union(int a, int b){
            int pa = find(a);
            int pb = find(b);
            if(pa==pb)return;
            if(size[pa]>size[pb]){
                parent[pb]=pa;
            }else{
                parent[pa]=pb;
            }
        }
        public int find(int x){
            if(parent[x]==x){
                return x;
            }
            return parent[x]=find(parent[x]);
        }
    }
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        DSU d = new DSU(n);
        for(int[] edge:edges){
            d.union(edge[0],edge[1]);
        }
        return d.find(source)==d.find(destination);
    }
} 
 */
