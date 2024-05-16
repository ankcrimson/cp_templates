public class LC67AddBinary {
    public String addBinary(String a, String b) {
        int m=a.length(), n=b.length();
        char[] res = new char[Math.max(m,n)+1];
        int carry=0;
        int i=m-1, j=n-1, k=res.length-1;
        for(;i>=0||j>=0;i--,j--){
            int sum = 0;
            if(i>=0){
                sum+=a.charAt(i)-'0';
            }
            if(j>=0){
                sum+=b.charAt(j)-'0';
            }
            if(carry>0){
                sum+=carry;
            }
            if(sum==2){
                carry=1;
                sum=0;
            }else if(sum==3){
                carry=1;
                sum=1;
            }else{
                carry=0;
            }
            res[k--]=(char)(sum+'0');
        }
        if(carry==1){res[0]='1';}
        return (res[0]=='1')?new String(res):new String(res,1, res.length-1);
    }
}