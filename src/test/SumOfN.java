package test;

public class SumOfN {

    public static void main(String[] args){

        System.out.println(sum(10));

        System.out.println(oddSum(10));
    }

    public static int sum(int n) {

        if (n == 0) {
            return 0;

        } else if (n == 1) {
            return 1;

        } else {
            return n + sum(n - 1);
        }
    }

    public static int oddSum(int n) {

        if (n == 0) {
            return 0;

        } else {

            return oddSum(n - 1) + (2 * n - 1);
        }
    }
}
