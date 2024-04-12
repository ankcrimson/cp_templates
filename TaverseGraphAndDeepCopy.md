

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    Map<Node,Node> srcTgtMap=new HashMap();
    public void clone(Node src, Node tgt, Set<Node> visited){
        if(visited.contains(src)){return;}
        visited.add(src);
        tgt.val=src.val;
        for(Node s:src.neighbors){
            Node n = srcTgtMap.getOrDefault(s, new Node(s.val));
            srcTgtMap.put(s,n);
            tgt.neighbors.add(n);
        }
        for(int i=0;i<src.neighbors.size();i++){
            clone(src.neighbors.get(i), tgt.neighbors.get(i), visited);
        }
    }

    // public void print(Node r, Set<Node> visited){
    //     if(visited.contains(r)){
    //         return;
    //     }
    //     visited.add(r);
    //     String curr = "r:"+r.val+", neighbors: ";
    //     for(Node n: r.neighbors){
    //         curr+=n.val+" ";
    //     }
    //     System.out.println(curr);
    //     for(Node n: r.neighbors){
    //         print(n, visited);
    //     }
        
    // }

    public Node cloneGraph(Node node) {
        if(node==null){return null;}
        Node tgt = new Node(node.val);
        srcTgtMap.put(node,tgt);
        Set<Node> visited=new HashSet();
        clone(node,tgt,visited);

        // System.out.println("Original");
        // visited=new HashSet();
        // print(node,visited);
        // System.out.println("New");
        // visited=new HashSet();
        // print(tgt,visited);

        return tgt;
    }
}
```