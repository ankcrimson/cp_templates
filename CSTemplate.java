import java.io.*;
import java.util.*;
//template for codechef
class CSTemplate {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st=new StringTokenizer("");
    String next() {
        while (!st.hasMoreTokens())
            try { 
                st=new StringTokenizer(br.readLine());				               
        } catch (IOException e) {}
        return st.nextToken();
    }
    
    int nextInt() {
        return Integer.parseInt(next());
    }
    long nextLong() {
        return Long.parseLong(next());
    }

    public int logic(int x){
        return 0;
    }
    public static void main(String[] args) {
        CSTemplate o = new CSTemplate();
        int n = o.nextInt();
        for(int i=0;i<n;i++){
            System.out.println(o.logic(o.nextInt()));
        }
    }
}
