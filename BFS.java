import java.util.*;

public class BFS{
    public int bfs(int[] board1d,int start,int end){
        Set<Integer> currlvl=new HashSet();
        Set<Integer> nxtlvl=new HashSet();
        Set<Integer> visited=new HashSet();
        currlvl.add(start);
        int ans=0;
        while(currlvl.size()>0){
            currlvl.removeAll(visited);
            // System.out.println("processing at "+ans+": "+currlvl);
            for(int pos:currlvl){
                for(int i=pos+1;i<=Math.min(pos+6,end);i++){
                    int nextpos=(board1d[i]==-1)?i:board1d[i];
                    // System.out.println(i+":"+board1d[i]);
                    if(nextpos==end){
                        return ans+1;
                    }
                    if(!visited.contains(nextpos)){
                        nxtlvl.add(nextpos);
                    }
                }
                visited.add(pos);
            }
            currlvl=nxtlvl;
            nxtlvl=new HashSet();
            ans++;
        }
        return -1;
    }
    public int snakesAndLadders(int[][] board) {
        int start=1, end = board.length*board.length;
        int[] board1d = new int[end+1];
        int boardCurr=1;
        boolean leftToRight=true;
        for(int i=board.length-1;i>=0;i--){
            if(leftToRight){
                for(int j=0;j<board[0].length;j++){
                    board1d[boardCurr++]=board[i][j];
                }
            }else{
                for(int j=board[0].length-1;j>=0;j--){
                    board1d[boardCurr++]=board[i][j];
                }
            }
            leftToRight=!leftToRight;
        }
        // System.out.println("end:"+(boardCurr-1));
        return bfs(board1d,1,boardCurr-1);
    }
    public static void main(String[] args) {
        BFS obj = new BFS();
        int[][] b = new int[][]{
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,35,-1,-1,13,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,15,-1,-1,-1,-1}
        };
        System.out.println(obj.snakesAndLadders(b));
        
    }
}
