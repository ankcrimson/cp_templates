Bitwise exploring/searching the space:

finding the integer part of sqrt of a number
```java
class Solution {
    public int mySqrt(int x) {
        int ans=0;
        for(long i=1<<20;i>0;i=i>>1){
            if((ans|i)*(ans|i)<=x){ans=ans|(int)i;}
        }
        return ans;
    }
}
```

number of set 1 bits
```java
class Solution {
    public int hammingWeight(int n) {
        int ans=0;
        while(n>0){
            if((n&1)==1){ans++;}
            n=n>>1;
        }
        return ans;
    }
}
```