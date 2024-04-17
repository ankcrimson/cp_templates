public class LC1201UglyNumber_LCM_GCD_BinarySearch {
    //java does not have inbuilt LCM or GCD, so we use euclid-algorithm for gcd to get our lcm
    private static long gcd(long a, long b){
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    private static long gcd(long[] input){
        long result = input[0];
        for(int i = 1; i < input.length; i++) result = gcd(result, input[i]);
        return result;
    }
    private static long lcm(long a, long b){
        return a * (b / gcd(a, b));
    }

    private static long lcm(long[] input){
        long result = input[0];
        for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }

    //inclusion exclusion
    long numOfOccurances(long n,int a,int b,int c){
        return 
        n/a
        +n/b
        +n/c
        -n/lcm(a,b)
        -n/lcm(b,c)
        -n/lcm(a,c)
        +n/lcm(new long[]{a,b,c});
    }

    //search that ends when l==r, runs over 1->very long number, and checks if condition required is resulting in n
    long numNumsSmallerThanK(int n, int a, int b, int c){
        long l=1, r=(long) 2e18;
        long ans=0;
        while(l<=r){
            long mid = (l+r)/2;
            long cnt = numOfOccurances(mid, a, b, c);
            if(cnt<n){
                l=mid+1;
            }else{
                ans = mid;
                r = mid-1;
            }
        }
        return ans;
    }

    public int nthUglyNumber(int n, int a, int b, int c) {
        return (int) numNumsSmallerThanK(n, a, b, c);
    }

    public static void main(String[] args) {
        LC1201UglyNumber_LCM_GCD_BinarySearch lc1201UglyNumber = new LC1201UglyNumber_LCM_GCD_BinarySearch();
        // System.out.println(lc1201UglyNumber.numNumsSmallerThanK(2, 3));
        System.out.println(lc1201UglyNumber.nthUglyNumber(3,2,3,5));
        System.out.println(lc1201UglyNumber.nthUglyNumber(4,2,3,4));
        System.out.println(lc1201UglyNumber.nthUglyNumber(5,2,11,13));
        System.out.println(lc1201UglyNumber.nthUglyNumber(1000000000,2,217983653,336916467));
        
    }
}