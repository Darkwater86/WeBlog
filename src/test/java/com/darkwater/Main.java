package com.darkwater;
import java.util.*;
class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String s = scanner.nextLine();
            String ss[]  = s.split(" ");
            int length = ss.length;
            if(length < 3){
                System.out.println("0");
            }
            if(length == 3){
                System.out.println();
            }
        }
    }
}
//public class Main{
//
//}
//public class Main{
//    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        while(scanner.hasNext()){
//            String sb = scanner.nextLine();
//            for(int i = sb.length()-2;i>=0;i = i-2){
//                if(isOdd(sb.substring(0,i))){
//                    System.out.println(i);
//                    break;
//                }
//            }
//        }
//    }
//    public static boolean isOdd(String sb){
//        int m = sb.length();
//        String s1 = sb.substring(0,m/2);
//        String s2 = sb.substring(m/2,m);
//        if(s1.equals(s2)){
//            return true;
//        }
//        return false;
//    }
//}
//public class Main {
//    public static void main(String[] args) {
//        Obj a = new Obj();
//        Obj b = new Obj();
//        a.val = 1;
//        b.val = 2;
////        int a2 = 1;
////        int b2 = 2;
////        ex(a,b);
////        System.out.println(a.val+":"+b.val);
////        ex(a2,b2);
////        System.out.println(a2+":"+b2);
//        ex(a.val,b.val);
//        System.out.println(a.val+":"+b.val);
//    }
//    public static void ex(Obj a,Obj b){
//        Obj c = b;
//        b = a;
//        a = c;
//        System.out.println(a.val+":ex:"+b.val);
//    }
//    public static void ex(int a,int b){
//        int c = a;
//        a = b;
//        b = c;
//        System.out.println(a+":ex:"+b);
//    }
//
//}
//class Obj{
//    int val;
//}
//    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        while(scanner.hasNext()){
//            String s = scanner.nextLine();
//            String s1 = "";
//            char ss = s.charAt(0);
//            int i = 0;
//            for(int j = 0;j<s.length();j++){
//                if(ss == s.charAt(j)){
//                    ++i;
//                }else{
//                    ss = s.charAt(j);
//                    s1 = s1+i+s.charAt(j-1);
//                    i = 1;
//                }
//            }
//            s1 = s1+i+ss;
//            System.out.println(s1);
//        }
//    }

    //    public static void main(String[] args) {
//        Stack<Integer> stack = new Stack<>();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(3);
//        stack.push(4);
//
//        System.out.println(stack.toString());
//        revice(stack);
//        System.out.println(stack.toString());
//    }
//    private static int getAndRemoveLast(Stack<Integer> stack) {
//        int result = stack.pop();
//        if(stack.isEmpty()) {
//            return result;
//        }
//        int last = getAndRemoveLast(stack);
//        stack.push(result);
//        return last;
//    }
//    private static void revice(Stack<Integer> stack){
//        if(stack.isEmpty()){
//            return;
//        }
//        int result = getAndRemoveLast(stack);
//        revice(stack);
//        stack.push(result);
//    }
//}
//    public static void main(String[] args) {
//        int[] a = {6};
//        mergeSort(a,a.length);
//        if (a.length>0) {
//            for (int a1 :a){
//            System.out.print(a1);}
//        }
//    }
//    private static void merge(int a[],int l,int mid,int r){
//        int n1 = mid -l +1;
//        int n2 = r -mid;
//        int left[] = new int[n1];
//        int right[] = new int[n2];
//        int k = 0;
//        for(int i =l ;i <= mid ;i++){
//            left[k++] = a[i];
//        }
//        k = 0 ;
//        for (int i = mid+1;i <= r; i++){
//            right[k++] = a[i];
//        }
//        int i = 0;
//        int j = 0;
//        k = l ;
//        while(i<n1&&j<n2){
//            if (left[i]<right[j]){
//                a[k++] = left[i++];
//            }else {
//                a[k++] = right[j++];
//            }
//        }
//        for (;i<n1;i++)a[k++] = left[i++];
//        for (;j<n2;j++)a[k++] = right[j++];
//    }
//    private static void msort(int a[],int l,int r){
//        if (l>=r){return;}
//        int mid = (r+l)/2;
//        msort(a,l,mid);
//        msort(a,mid+1,r);
//        merge(a,l,mid,r);
//
//    }
//    private static int[] mergeSort(int[] a,int n){
//        if(n<1){
//            return a;
//        }
//        msort(a,0,n-1);
//        return a;
//    }
//    public static void main(String[] args) {
//        StringBuilder s = new StringBuilder("aaa");
//        s.length();
//        s.toString();
//    }
//    private static
//    public static class ListNode {
//        int val;
//        ListNode next = null;
//
//        ListNode(int val) {
//            this.val = val;
//        }
//    }
//    public static void main(String[] args) {
//        ListNode head = new ListNode(0);
//        ListNode head1 = new ListNode(1);
//        ListNode head2 = new ListNode(2);
//        ListNode head3 = new ListNode(3);
//        ListNode head4 = new ListNode(4);
//        ListNode head5 = new ListNode(5);
//        head.next = head1;
//        head1.next = head2;
//        head2.next = head3;
//        head3.next = head4;
//        head4.next = head5;
//        ReverseList(head);
//    }
//    public static ListNode ReverseList(ListNode head) {
//        if (head == null) {
//            return head;
//        }
//        ListNode ln = new ListNode(0);
//        return xx(head,ln);
//    }
//    public static ListNode xx(ListNode l,ListNode root) {
//        if (null == l.next){
//            return l;
//        }
//        ListNode ll = xx(l.next,root);
//    }
//}