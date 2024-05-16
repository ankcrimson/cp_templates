Most important thing is always to find the base case
If we know the base case, rest becomes easy because each iteration becomes independent and hence cachable
eg LC72. Edit Distance: Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
```java
class Solution {
    public int findMinDist(char[] w1, char[] w2, int i, int j, int[][] dp){
        if(dp[i][j]>0){return dp[i][j];}
        if(i==w1.length){return dp[i][j]=(w2.length-j);}
        if(j==w2.length){return dp[i][j]=(w1.length-i);}
        if(w1[i]==w2[j]){
            return dp[i][j]=findMinDist(w1,w2,i+1,j+1, dp);
        }else{
            int ans=Integer.MAX_VALUE;
            //insert
            ans = Math.min(ans, findMinDist(w1,w2,i,j+1, dp));
            //delete
            ans = Math.min(ans, findMinDist(w1,w2,i+1,j, dp));
            //replace
            ans = Math.min(ans, findMinDist(w1,w2,i+1,j+1, dp));
            return dp[i][j]=(ans+1);
        }
    }
    public int minDistance(String word1, String word2) {
        int maxLen = Math.max(word1.length(),word2.length());
        int[][] dp = new int[maxLen+1][maxLen+1];
        return findMinDist(word1.toCharArray(), word2.toCharArray(), 0, 0, dp);
    }
}
```

top down and bottom up approach
```java
class Solution {
    public int topDown(int n, int[] dp){
        if(n<0){return 0;}
        if(dp[n]>0){return dp[n];}
        if(n==0){return 1;}
        int ans = topDown(n-1,dp)+topDown(n-2,dp);
        dp[n]=ans;
        return ans;
    }
    public int bottomUp(int n){
        int[] dp = new int[n+1];
        dp[0]=dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    public int climbStairs(int n) {
        // return topDown(n, new int[n+1]);
        return bottomUp(n);
    }
}
```

Note:
- find a recursive soln
- find base case
- apply caching