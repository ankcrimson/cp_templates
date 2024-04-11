public class Debugging {
    void print2dMatrix(boolean[][] visited, int m, int n){
        System.out.println();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(visited[i][j]?1:0);
            }
            System.out.println();
        }
    }
}
