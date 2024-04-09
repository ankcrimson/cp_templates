public class QuickSearch {

    public static int search(int[] sorted_data, int start, int end, int value){
        if(start>end){
            return -1;
        }
        int mid=(start+end)/2;
        if(sorted_data[mid]==value){
            return mid;
        }else if(value>sorted_data[mid]){
            return search(sorted_data, mid+1, end, value);
        }else{
            return search(sorted_data, start, mid-1, value);
        }
    }

    public static void main(String[] args){
        int[] data = new int[]{1,4,7,9,500,3223};
        System.out.println(search(data, 0, data.length-1, 4));
        System.out.println(search(data, 0, data.length-1, 90));
        System.out.println(search(data, 0, data.length-1, 9000000));
        System.out.println(search(data, 0, data.length-1, -4));
        System.out.println(search(data, 0, data.length-1, 500));
    }
}