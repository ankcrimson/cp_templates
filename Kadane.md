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


Kadane on Circular:
there are two places numbers can be present:
- either max will be in center
    - normal kadane
- or max will be in split between center and end
    - in this case we will use sumTotal-kadaneMinimum
- special case: all negative values will make sumTotal-kadaneMinimum as 0, so we return the normalKadane

```
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        // System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        int maxSum=nums[0];
        int currSum=0;
        int totalSum=0;
        for(int i=0;i<nums.length;i++){
            currSum+=nums[i];
            maxSum=Math.max(maxSum,currSum);
            if(currSum<0){
                currSum=0;
            }
            totalSum+=nums[i];
        }

        int minSum=nums[0];
        currSum=0;
        for(int i=0;i<nums.length;i++){
            currSum+=nums[i];
            minSum=Math.min(minSum,currSum);
            if(currSum>0){
                currSum=0;
            }
        }
        return maxSum<0?maxSum:Math.max(maxSum,totalSum-minSum);
    }
}
```
