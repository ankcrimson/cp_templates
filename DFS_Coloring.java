public class DFS_Coloring {
    // color a board based on visited from starting color to ending color checking links
    //ref: https://leetcode.com/problems/surrounded-regions/solutions/5008766/coloring-from-border-with-some-details
    
    public void dfs_color(char[][] board, boolean[][] visited, int i, int j, char findcolor, char tocolor){
        if(visited[i][j]){return;}
        if(board[i][j]!=findcolor){
            visited[i][j]=true;
            return;
        }
        // System.out.println("coloring "+i+", "+j);
        visited[i][j]=true;
        board[i][j]=tocolor;
        if(i>0){
            dfs_color(board,visited,i-1,j,findcolor,tocolor);
        }
        if(i<board.length-1){
            dfs_color(board,visited,i+1,j,findcolor,tocolor);
        }
        if(j>0){
            dfs_color(board,visited,i,j-1,findcolor,tocolor);
        }
        if(j<board[0].length-1){
            dfs_color(board,visited,i,j+1,findcolor,tocolor);
        }
    }
}