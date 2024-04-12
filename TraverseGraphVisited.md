
#### good example of visited as used in LC399

```java
class Solution {
    //each node has multiple edges, each edge weight has value of a/b
    class Node{
        String name;
        Map<Node,Double> edges=new HashMap();
        public Node(String name){
            this.name=name;
        }
    }

    public double findAnswer(Node src, Node tgt, double current, Set<Node> visited){
        if(src==tgt){
            return current;
        }
        visited.add(src);
        for(Node edge:src.edges.keySet()){
            if(!visited.contains(edge)){
                double ans = findAnswer(edge,tgt,current*src.edges.get(edge),visited);
                if(ans>0){return ans;}
            }
        }
        return -1;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //create the graph
        Map<String,Node> nodes = new HashMap();
        for(int i=0;i<values.length;i++){
            List<String> equation=equations.get(i);
            String aStr=equation.get(0), bStr=equation.get(1);
            Node 
            a = nodes.getOrDefault(aStr, new Node(aStr)),
            b = nodes.getOrDefault(bStr, new Node(bStr));
            a.edges.put(b,values[i]);
            b.edges.put(a,1.0/values[i]);
            nodes.put(aStr,a);
            nodes.put(bStr,b);
        }

        double[] ans =new double[queries.size()];
        for(int i=0;i<ans.length;i++){
            String aStr = queries.get(i).get(0), bStr=queries.get(i).get(1);
            Node a = nodes.getOrDefault(aStr,null), b=nodes.getOrDefault(bStr,null);
            if(a==null||b==null){
                ans[i]=-1;
                continue;
            }
            Set<Node> visited=new HashSet();
            ans[i] = findAnswer(a,b,1,visited);
        }
        return ans;

    }
}
```