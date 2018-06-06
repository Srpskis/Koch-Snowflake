package test;

public class ArraySorting {

    public static void main (String[] args) {

        double[] c = {5, 3 , 10, 9, 2};

        System.out.print("c: ");
        print(c);

        System.out.println();
        System.out.println();
        print(selectionSort1(c, c.length));
    }
//
//    public static double[] selectionSort(double[] list) {
//        for(int startIndex = 0; startIndex < list.length-1; startIndex++) {
//            double currentMin = list[startIndex];
//            int currentMinIndex = startIndex;
//            for(int nextIndex = startIndex+1; nextIndex < list.length; nextIndex++) {
//                if(currentMin > list[nextIndex]) {
//                    currentMin = list[nextIndex];
//                    currentMinIndex = nextIndex;
//                }
//            }
//            if(currentMinIndex != startIndex) {
//                list[currentMinIndex] = list[startIndex];
//                list[startIndex] = currentMin;
//            }
//        }
//        return list;
//    }

    // USING RECURSION:
    public static double[] selectionSort1(double[] list, int length) {
        // if length equals 1
        if(length == 1) {
            // return the array
            return list;
            // else
        }else {
            // until length -1
            for(int i = 0; i < length-1; i++) {
                // if the i-th index is bigger than the next one in line
                if(list[i] > list[i+1]) {
                    // create a temporary list and store the index of list in that temporary variable
                    double temp = list[i];
                    // that temp variable becomes the next one in line
                    list[i] = list[i+1];
                    // and that one become stored in temporary
                    list[i+1] = temp;
                }
            }
            // returns a call to recursive method
            return selectionSort1(list, length-1);
        }
    }

    public static void print(double[] grid) {
        //stands for row
        System.out.print("\t[" + grid[0]);
        for(int i = 1; i < grid.length; i++) {
            System.out.print(", " + grid[i]);
        }
        System.out.print("]");
    }

}


