import java.io.File;

/**
 * Created by zhengwu on 11/30/16.
 */
public class Recursion {

    public static int determinant(int[][] matrix) {
        int det = 0;
        int sign;
        if (matrix.length == 1)
            return matrix[0][0];
        for (int i = 0; i < matrix.length; i++) { //finds determinant using row-by-row expansion
            int[][] smatrix = new int[matrix.length - 1][matrix.length - 1]; //creates smaller matrix- values not in same row, column
            for (int j = 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    if (k < i) {
                        smatrix[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        smatrix[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }
            if (i % 2 == 0) { //sign changes based on i
                sign = 1;
            } else {
                sign = -1;
            }
            det += sign * matrix[0][i] * (determinant(smatrix));
        }
        return (det); //returns determinant value. once stack is finished, returns final determinant.
    }

    public static int filecount(File f) {
        int count = 0;
//base case
        if (f.isFile()) {
            return 1;
        }
        //recursive case
        else {
            File[] children = f.listFiles();
            for (File f1 : children) {
                count += filecount(f1);
            }
            return count;
        }
    }
}
