/**
 * Question 1B -- Complete the method isSkewSymmetric
 */
public class Matrix {

    /**
     * Checks if the argument is a skew-symmetric matrix.
     *
     * @param mat is a 2-dimensional matrix (array) that is not ragged.
     * @return
     * 		"TRUE" if mat is skew-symmetric. 1
     * 		"FALSE" if mat is not skew-symmetric. 2
     * 		"NULL" if mat is null or empty. 3
     * 		"RECTANGULAR" if mat is not square. 4
     */
    public String isSkewSymmetric(int[][] mat) {
        if(mat ==null || mat.length==0)
            return "NULL";
        if(mat[0].length!= mat.length)
            return "RECTANGULAR";
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat.length;j++){
                if(i==j) {
                    if(mat[i][j]!=0)
                        return "FALSE";
                }
                else{
                   if( mat[i][j] +mat[j][i]!=0)
                       return "FALSE";
                }
            }
        }

        return "TRUE" ; // delete this statement and enter your code here
    }


}
