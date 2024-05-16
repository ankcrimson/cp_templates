Using BigInteger

```java
import java.math.BigInteger;
class Solution {
    public int maxNiceDivisors(int primeFactors) {
        int M=1000000007;
        BigInteger ans=BigInteger.valueOf(0);
        if(primeFactors<4){return primeFactors;}
        if(primeFactors%3==0){
            ans = BigInteger.valueOf(3).modPow(BigInteger.valueOf(primeFactors/3), BigInteger.valueOf(M));// pow(3,(primeFactors/3), M);
        }
        else if(primeFactors%3==1){
            ans = BigInteger.valueOf(4).multiply(BigInteger.valueOf(3).modPow(BigInteger.valueOf((primeFactors/3)-1), BigInteger.valueOf(M)));//4*(pow(3,(int)(primeFactors/3)-1, M));
        }
        else if(primeFactors%3==2){
            ans = BigInteger.valueOf(2).multiply(BigInteger.valueOf(3).modPow(BigInteger.valueOf(primeFactors/3), BigInteger.valueOf(M)));//2*pow(3,(primeFactors/3), M);
        }
        return ans.mod(BigInteger.valueOf(M)).intValue();
    }
}
```

power example

```java
class Solution {
    public int maxNiceDivisors(int primeFactors) {
        int M=1000000007;
        long ans=0;
        if(primeFactors<4){return primeFactors;}
        if(primeFactors%3==0){
            ans = pow(3,(int)(primeFactors/3), M);
        }
        else if(primeFactors%3==1){
            ans = 4*(pow(3,(int)(primeFactors/3)-1, M));
        }
        else if(primeFactors%3==2){
            ans = 2*pow(3,(primeFactors/3), M);
        }
        return (int)(ans%M);
    }

    private long pow(long num, long exp, int M){
        if (exp == 1) return num;
        if (exp == 0) return 1;
        
        long k1 = pow(num, exp / 2, M);
        if (exp % 2 == 0) {
            return (k1 * k1) % M;
        } else {
            return (k1 * k1 * num) % M;
        }
    }
}
```