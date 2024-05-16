


2d:
```java
class NumMatrix {

    long[][] sum =new long[201][201];
    public NumMatrix(int[][] matrix) {

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                sum[i][j]=(j>0?sum[i][j-1]:0)+matrix[i][j];
            }
        }
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                sum[i][j]=(i>0?sum[i-1][j]:0)+sum[i][j];
            }
        }

        // for(int i=0;i<matrix.length;i++){
        //     for(int j=0;j<matrix[0].length;j++){
        //         System.out.print(sum[i][j]+" ");
        //     }
        //     System.out.println();
        // }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        long subR=0,subC=0;
        if(row1>0){
            subR=sum[row1-1][col2];
        }
        if(col1>0){
            subC=sum[row2][col1-1];
        }
        long add=0;
        if(row1>0&&col1>0){
            add=sum[row1-1][col1-1];
        }
        return (int)(sum[row2][col2]-subR-subC+add);
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
```


1d:

```java
class NumArray {
    int[] arr=new int[10000];
    public NumArray(int[] nums) {
        arr[0]=nums[0];
        for(int i=1;i<nums.length;i++){
            arr[i]=arr[i-1]+nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        if(left==0){return arr[right];}
        return arr[right]-arr[left-1];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */

```