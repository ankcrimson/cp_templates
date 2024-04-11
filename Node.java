public class Node {
    int val=0;
    Node left=null;
    Node right=null;
    Node(){}
    Node(Node l, Node r, int val){
        this.left=l;
        this.right=r;
        this.val=val;
    }
}
