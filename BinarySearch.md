Template1: Basic
```java
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left <= right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid - 1; }
  }

  // End Condition: left > right
  return -1;
}
```

Template2: Advanced https://leetcode.com/explore/learn/card/binary-search/126/template-ii/937/

eg find first bad version
```java
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left < right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid; }
  }

  // Post-processing:
  // End Condition: left == right
  if(nums[left] == target) return left;
  return -1;
}
```

example https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/solutions/158940/beat-100-very-simple-python-very-detailed-explanation/


Template 3: https://leetcode.com/explore/learn/card/binary-search/135/template-iii/936/
```java
int binarySearch(int[] nums, int target) {
    if (nums == null || nums.length == 0)
        return -1;

    int left = 0, right = nums.length - 1;
    while (left + 1 < right){
        // Prevent (left + right) overflow
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            left = mid;
        } else {
            right = mid;
        }
    }

    // Post-processing:
    // End Condition: left + 1 == right
    if(nums[left] == target) return left;
    if(nums[right] == target) return right;
    return -1;
}
```









where to insert a number if not exists:

```java
class Solution {
    int bsearch(int[] nums, int tgt, int start, int end){
        if(start>end){return start;}
        int mid=(int)((start+end)/2);
        if(tgt==nums[mid]){
            return mid;
        }
        if(tgt>nums[mid]){
            return bsearch(nums,tgt,mid+1,end);
        }else{
            return bsearch(nums,tgt,start,mid-1);
        }
    }
    public int searchInsert(int[] nums, int target) {
        return bsearch(nums,target,0,nums.length-1);
    }
}
```

OR

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int a=0, b=nums.length-1;
        while(a<=b){
            int m = (a+b)/2;
            if(nums[m]==target){
                return m;
            }
            if(nums[m]<target){
                a=m+1;
            }else{
                b=m-1;
            }
        }
        return a;
    }
}
```

OR

```java

```


Binary Search to get index if it exists:
```java
class Solution {
    public int search(int[] nums, int target) {
        int l=0, r=nums.length-1;
        while(l<=r){
            int m=(l+r)/2;
            if(nums[m]==target){
                return m;
            }
            if(nums[m]>target){
                r=m-1;
            }else{
                l=m+1;
            }
        }
        return -1;
    }
}
```

rotated sorted array search
```java
class Solution {
    public int search(int[] nums, int target) {
        int l=0,r=nums.length-1,n=nums.length;
        int pivot=0;
        //find the pivot
        while(l<=r){
            int m=(l+r)/2;
            if(m>0&&nums[m-1]>nums[m]){pivot=m;break;}
            if(nums[r]>nums[m]){
                r=m-1;
            }
            else{
                l=m+1;
            }
        }
        //find the number
        l=0;
        r=nums.length-1;
        if(pivot==0){}
        else if(target>=nums[0] && target<=nums[pivot-1]){
            r=pivot-1;
        }else{
            l=pivot;
        }
        while(l<=r){
            int m=(l+r)/2;
            if(nums[m]==target){
                return m;
            }
            if(nums[m]<target){
                l=m+1;
            }else{
                r=m-1;
            }
        }

        return -1;
    }
}
```

2nd template
```java
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int l=0, r=n;
        while(l<r){
            int m=l+(r-l)/2;
            if(isBadVersion(m)){
                r=m;
            }else{
                l=m+1;
            }
        }
        // if(!isBadVersion(l)){return l+1;}
        return l;
    }
}
```

Template2 example: Find pivot in rotated sorted array:
```java
class Solution {
    public int findMin(int[] nums) {
        int l=0, r=nums.length-1;
        while(l<r){
            int m = l+(r-l)/2;
            if(nums[m]>nums[r]){
                l=m+1;
            }else{
                r=m;
            }
        }
        return nums[l];
    }
}
```