
Interesting how Lee suggested how to go to the end of LL just like PostOrder run of a tree

This is a very interesting idea and can be applied to reverse LL opreations where we essentially get to work with reverse LL due to recursion

Below soln is inspired by his approach
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNodes(ListNode head) {
        if(head==null){return null;}
        head.next=removeNodes(head.next);
        return (head.next!=null && head.val<head.next.val)?head.next:head;
    }
}
```

https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/solutions/5123187/java-dfs-4ms-beats-77/
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    ListNode dfs(ListNode c){
        if(c.next==null){
            c.val*=2;
            return c;
        }
        ListNode next = dfs(c.next);
        c.val*=2;
        if(next.val>=10){
            next.val-=10;
            c.val++;
        }
        return c;
    }
    public ListNode doubleIt(ListNode head) {
        ListNode x = new ListNode();
        x.next=dfs(head);
        if(x.next.val>=10){
            x.val++;
            x.next.val-=10;
            return x;
        }else{
            return x.next;
        }
    }
}
```

