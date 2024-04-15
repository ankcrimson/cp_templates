
#### TopSort using Kahn's algo 

Ref: https://www.youtube.com/watch?v=cIBFEhD77b4


LC208
```java
List<Integer> topSort(Set<Integer>[] adj,int[] indegrees){
        List<Integer> tsort = new ArrayList();
        Deque<Integer> free = new LinkedList<>();
        for(int i=0;i<indegrees.length;i++){
            if(indegrees[i]==0){
                free.addLast(i);
            }
        }
        while(!free.isEmpty()){
            int val = free.removeFirst();
            tsort.add(val);
            if(adj[val]!=null){
                // System.out.println("adj:"+adj[val]);
                for(int i:adj[val]){
                    indegrees[i]--;
                    if(indegrees[i]==0){
                        free.addLast(i);
                    }
                }
            }
        }
        return tsort;
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        int[] indegrees = new int[numCourses];

        Set<Integer>[] adj = new Set[numCourses];
        for(int[] pre:prerequisites){
            int a = pre[0], b=pre[1];
            if(adj[b]==null){
                adj[b]=new HashSet();
            }
            Set<Integer> adjS=adj[b];
            if(!adjS.contains(a)){
                adjS.add(a);
                indegrees[a]++;
            }
        }
        List<Integer> tsort = topSort(adj,indegrees);
        // System.out.println(tsort);
        if(tsort.size()<numCourses){
            return new int[0];
        }else{
            int[] ans = new int[numCourses];
            for(int i=0;i<numCourses;i++){
                ans[i]=tsort.get(i);
            }
            return ans;
        }
    }
```

LC207
```java
class Solution {

    //topsort with indegrees pre-populated and adjacency list
    public List<Integer> topsort(int[] indegrees, List<Integer>[] adj){
        Deque<Integer> queue = new LinkedList();
        //find those with no dependencies
        for(int i=0;i<indegrees.length;i++){
            if(indegrees[i]==0){
                queue.addLast(i);
            }
        }
        List<Integer> topsort = new LinkedList();
        //for each item without dependency, remove from queue, reduce indegree, 
        while(queue.size()>0){
            int top = queue.removeFirst(); //0
            topsort.add(top);
            //decrement indegree and add to queue for downstream
            if(adj[top]!=null){
                for(int itm:adj[top]){
                    indegrees[itm]--;
                    if(indegrees[itm]==0){
                        queue.addLast(itm);
                    }
                }
            }
        }
        return topsort;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //who is depnendent on who
        List<Integer>[] adj = new List[numCourses];
        //number of dependencies
        int[] indegrees=new int[numCourses];
        //create adjacency list
        for(int i=0;i<prerequisites.length;i++){ //[1,0]
            int b = prerequisites[i][0]; //0
            int a = prerequisites[i][1]; //1
            if(adj[a]==null){
                adj[a]=new LinkedList();
            }
            adj[a].add(b);
            indegrees[b]++;
        } //adj: 0 -> 1, indegrees[0,1]=0,1
        //topsort using kahn algo
        List<Integer> topsort=topsort(indegrees,adj);
        // System.out.println(topsort);
        return topsort.size()==numCourses;
    }
}
```