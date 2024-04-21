## Kadane

#### Maximum Subarray Sum:

start with sum=0 and maxSum as -inf
add curr number to sum
if sum > maxSum, then set maxSum to sum
if sum<0 then set sum to 0
```java
    public int maxSubArray(int[] nums) {
        int maxSum=Integer.MIN_VALUE;
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(sum>maxSum){maxSum=sum;}
            if(sum<0){sum=0;}
        }
        return maxSum;
    }
```

