Can be used to insert and search string

```java
public class Trie {
    class N{
        public N[] next = new N[27];
    }

    N root;
    public Trie() {
        root = new N();
    }
    
    public void insert(String word) {
        char[] str = word.toCharArray();
        N curr=root;
        for(int i=0;i<str.length;i++){
            if(curr.next[str[i]-'a']==null){
                curr.next[str[i]-'a'] = new N();
            }
            curr=curr.next[str[i]-'a'];
        }
        curr.next[26]=new N();//denotes ending char
    }
    
    public boolean search(String word) {
        char[] w = word.toCharArray();
        N r = root;
        for(int i=0;i<w.length;i++){
            if(r.next[w[i]-'a']==null){
                return false;
            }
            r=r.next[w[i]-'a'];
        }
        if(r.next[26]!=null){
            return true;}
        else{
            return false;}
    }
    
    public boolean startsWith(String prefix) {
        char[] w = prefix.toCharArray();
        N r = root;
        for(int i=0;i<w.length;i++){
            if(r.next[w[i]-'a']==null){
                return false;
            }
            r=r.next[w[i]-'a'];
        }
        return true;
    }
}
```


More generic version


```java
class Trie {
    int NUMCHARS=26;
    char startingChar='a';

    class N{
        public N[] next = new N[NUMCHARS+1];//+1 for ending char
    }

    N root;
    public Trie() {
        root = new N();
    }
    
    public void insert(String word) {
        char[] str = word.toCharArray();
        N curr=root;
        for(int i=0;i<str.length;i++){
            if(curr.next[str[i]-startingChar]==null){
                curr.next[str[i]-startingChar] = new N();
            }
            curr=curr.next[str[i]-startingChar];
        }
        curr.next[NUMCHARS]=new N();//denotes ending char
    }
    
    public boolean search(String word) {
        char[] w = word.toCharArray();
        N r = root;
        for(int i=0;i<w.length;i++){
            if(r.next[w[i]-startingChar]==null){
                return false;
            }
            r=r.next[w[i]-startingChar];
        }
        if(r.next[NUMCHARS]!=null){
            return true;}
        else{
            return false;}
    }
    
    public boolean startsWith(String prefix) {
        char[] w = prefix.toCharArray();
        N r = root;
        for(int i=0;i<w.length;i++){
            if(r.next[w[i]-startingChar]==null){
                return false;
            }
            r=r.next[w[i]-startingChar];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
```


another example - finding words in crossward with trie and bfs

```java
class Solution {
    class T{
        T[] next = new T[27];
        String word;
    }

    void createTrie(T root, String[] words){
        for(String word:words){
            T curr = root;
            char[] wA = word.toCharArray();
            for(char ch:wA){
                int i = ch-'a';
                if(curr.next[i]==null){
                    curr.next[i]=new T();
                }
                curr =curr.next[i];
            }
            curr.next[26]=new T();
            curr.next[26].word=word;
        }
    }

    void dfs(Set<String> words, char[][] board, int i, int j, T r){
        if( i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]=='#'||r==null){
            return;
        }
        int ch = board[i][j]-'a';
        if(r.next[ch]==null){return;}

        r = r.next[ch];
        
        if(r.next[26]!=null){
            // System.out.println(board[i][j]+" "+ r.next[26].word);
            words.add(r.next[26].word);
        }

        // if(curr.next[ch]==null){return;}

        char tmp = board[i][j];
        board[i][j]='#';
        dfs(words,board,i+1,j,r);
        dfs(words,board,i-1,j,r);
        dfs(words,board,i,j+1,r);
        dfs(words,board,i,j-1,r);
        board[i][j]=tmp;
    }

    public List<String> findWords(char[][] board, String[] words) {
        T root=new T();
        createTrie(root, words);
        Set<String> ans =new HashSet();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                dfs(ans, board, i, j, root);
            }
        }
        return new ArrayList<>(ans);
    }
}

```