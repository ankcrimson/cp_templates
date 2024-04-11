class TreeSearch{
    public boolean search(Node n, int val){
        if(n==null){
            return false;
        }
        if(n.val==val){
            return true;
        }
        if(search(n.left, val)){
            return true;
        }
        return search(n.right, val);
    }
}