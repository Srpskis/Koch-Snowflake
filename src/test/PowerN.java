package test;

public class PowerN {

    public static void main(String[] args) {

        System.out.println(powerN(2, 2));


    }

    public static int powerN(int base, int power) {
        if (power == 0){
            return 1;
        }
        if (power == 1)
            return base;
        else if (power % 2 == 0) {
            int x = powerN(base, power / 2);
            return x * x;
        } else {
            int x = powerN(base, (power - 1) / 2);
            return x * x * base;
        }
    }
}