### LeetCode 402. Remove K Digits

#### Approach1: Tree

```java
class Solution {
    Map<String,String> cache=new HashMap();
    String minsum(char[] digits, int k){
        String currKey=new String(digits)+"_"+k;
        if(cache.containsKey(currKey)){
            return cache.get(currKey);
        }
        if(k==0){
            String ans = new String(digits);
            cache.put(currKey,ans);
            return ans;
        }
        // System.out.println();
        String min = new String(digits,0,digits.length-1);
        for(int i=0;i<digits.length;i++){
            char[] next=new char[digits.length-1];
            for(int j=0,l=0;j<digits.length-1;j++,l++){
                if(i==j){l++;}
                next[j]=digits[l];
                
            }
            
            String nxt=minsum(next,k-1);
            // System.out.println("for "+new String(digits)+", trying:"+nxt+" where min:"+min);
            if(nxt.compareTo(min)<0){
                min=nxt;
            }
        }
        // System.out.println("min from "+new String(digits)+" is "+min);
        // System.out.println();
        cache.put(currKey,min);
        return min;
    }

    public String removeKdigits(String num, int k) {
        char[] numc = num.toCharArray();
        String res = minsum(numc,k);
        char[] resC = res.toCharArray();
        int numZeros=0;
        while(numZeros<resC.length&&resC[numZeros]=='0'){numZeros++;}
        if(resC.length==numZeros){return "0";}
        return new String(resC,numZeros,resC.length-numZeros);
    }
}
```

#### Approach 2: Greedy1:

```java

int getMinCharLargestIndex(char[] arr, int start, int end){
        int min=arr[end];
        int idx=end;
        for(int i=end;i>=start;i--){
            if(arr[i]<=arr[idx]){
                idx=i;
            }
        }
        return idx;

    }
    public String removeKdigits(String num, int k) {
        char[] numC=num.toCharArray();
        int ansSize=numC.length-k;
        char[] ansArray=new char[ansSize];
        int start=0;
        int end=k;
        int leadingZeros=0;
        boolean hasLeadingZeros=true;
        boolean hasAllZeros=true;
        for(int i=0;i<ansArray.length;i++){
            int idx=getMinCharLargestIndex(numC,start,end);
            start=idx+1;
            end++;
            ansArray[i]=numC[idx];
            if(hasLeadingZeros){
                if(numC[idx]=='0'){leadingZeros++;}
            }
            if(numC[idx]!='0'){hasLeadingZeros=false;hasAllZeros=false;}
        }
        if(hasAllZeros){return "0";}
        return new String(ansArray,leadingZeros, ansArray.length-leadingZeros);
    }
```

#### Approach3: Greedy2 Monotonic Stack:

```java
class Solution {
    public String removeKdigits(String num, int k) {
        char[] numC = num.toCharArray();
        Deque<Character> monotonicStack = new LinkedList();
        for(int i=0;i<numC.length;i++){
            Character currTop=monotonicStack.peekLast();
            if(currTop==null||currTop<=numC[i]||k==0){
                monotonicStack.addLast(numC[i]);
            }else{
                while(k>0&&currTop!=null&&currTop>numC[i]){
                    monotonicStack.removeLast();
                    k--;
                    currTop=monotonicStack.peekLast();
                }
                monotonicStack.addLast(numC[i]);
            }
        }
        while(k>0&&monotonicStack.size()>0){k--;monotonicStack.removeLast();}
        int count=monotonicStack.size();

        int elements=0;
        boolean startingZero=true;
        for(int i=0;i<count;i++){
            char element = monotonicStack.pollFirst();
            if(startingZero&&element=='0'){continue;}
            startingZero=false;
            numC[elements++]= element;
        }
        return (elements==0)?"0":new String(numC,0,elements);
    }
}
```