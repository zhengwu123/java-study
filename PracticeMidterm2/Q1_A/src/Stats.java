/**
 * Question 1A -- Complete the method mode
 */
public class Stats {

    /**
     * This method determines the mode of a given sorted list of int values.
     *
     * @param list is a sorted 1-dimensional array (list).
     * @return mode of the given list.
     */
    public static int mode(int[] list) {
        if (list.length == 1)
            return list[0];

        int count = 1;
        int cur = 0;
        int max = 0;
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i] == list[i + 1]) {
                count++;


            } else {
                count = 1;
            }
            if (count > max) {
                max = count;
                cur = list[i];
            }

        }
        return cur;
    }
}