
Combinations:

```java
public long getValForNCoins(int[] coins, long mid){
        int n = coins.length;
        int combs = (1<<n)-1;
        long k=0;
        for(int i=1;i<=combs;i++){
            List<Integer> consider = new LinkedList<>();
            for(int j=0;j<n;j++){
                if(((i>>j)&(1))==1){
                    consider.add(coins[j]);
                }
            }
            System.out.println(consider);
        }
        return k;
    }
```


ODD EVEN - Includsion Exclusion:

```java
public long getValForNCoins(int[] coins, long mid){
        int n = coins.length;
        int combs = (1<<n)-1;
        long k=0;
        for(int i=1;i<=combs;i++){
            long lcm_coins=1;
            int numcoins=0;
            for(int j=0;j<n;j++){
                if(((i>>j)&(1))==1){
                    lcm_coins = lcm(lcm_coins, coins[j]);
                    numcoins++;
                }
            }
            
            if(numcoins%2==0){
                //reduce even
                k-=mid/lcm_coins;
            }else{
                //add odd
                k+=mid/lcm_coins;
            }
        }
        return k;
    }
    ```