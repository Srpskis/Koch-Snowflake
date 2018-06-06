package test;

public class PowerN2 {

    public static void main(String[] args){

        System.out.println(power(Math.E,5));

    }

    public static double power(double x, int n){

        if (n == 0){
            return 1;
        }
        if (n == 1)
            return x;
        else if (n % 2 == 0)  {
            double c = power(x, n / 2);
            return c * c;
        } else {
            double c = power(x, (n - 1) / 2);
            return c * c * x;
        }

    }
}
