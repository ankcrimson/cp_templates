import java.util.*;
import java.util.stream.Collectors;
class HistogramAreaViaStacks {
/*
Intution: from video
every time the largest area will include 1 block in full
so
every time we see what is the max area if we include current block
for that we need to find where is the left and right boundary if we are to include current block
*/
    public static int largestRectangleArea(int[] heights) {
        Deque<Integer> leftStack = new LinkedList();//left boundary stack
        Deque<Integer> rightStack = new LinkedList();//right boundary stack
        int[] startLeft=new int[heights.length];
        int[] startRight = new int[heights.length];
        for(int i=0,j=heights.length-1;i<heights.length;i++,j--){
            if(leftStack.size()==0){
                leftStack.addLast(0);
                startLeft[i]=leftStack.peekLast();
            }
            else{
                int tmp=i;
                while(leftStack.size()>0 && heights[leftStack.peekLast()]>heights[i]){
                    tmp=leftStack.removeLast();
                }
                leftStack.addLast(tmp);
                startLeft[i]=leftStack.peekLast();
                leftStack.addLast(i);
            }
            // System.out.println("processing:"+heights[j]);
            if(rightStack.size()==0){
                rightStack.addLast(heights.length-1);
                startRight[j]=rightStack.peekLast();
            }else{
                int tmp=j;
                while(rightStack.size()>0&&heights[rightStack.peekLast()]>=heights[j]){
                    tmp = rightStack.removeLast();
                }
                rightStack.addLast(tmp);
                startRight[j]=rightStack.peekLast();
                rightStack.addLast(j);
            }


        }
        int maxSum=0;
        for(int i=0;i<startLeft.length;i++){
            int sum=heights[i]*(startRight[i]-startLeft[i]+1);
            maxSum=Math.max(sum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args){
        int maxSum = largestRectangleArea(new int[]{2,1,5,6,2,3});
        System.out.println(maxSum);
        maxSum = largestRectangleArea(new int[]{2,1,5,6,4,3});
        System.out.println(maxSum);
        maxSum = largestRectangleArea(new int[]{2,1,5,0,4,3});
        System.out.println(maxSum);
        maxSum = largestRectangleArea(new int[]{1,1});
        System.out.println(maxSum);
    }
}