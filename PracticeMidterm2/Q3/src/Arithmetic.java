/**
 * Question 2 -- Complete this class as specified
 */


public class Arithmetic {

    public int modulus(int a, int b) {
        int module=0;
        try {
            module= a - b * truncatedDivision(a, b);
        } catch (DenominatorZeroException e) {

            e.printStackTrace();
            return -1;
        }
        return module;
    }


    public int truncatedDivision(int a, int b) throws DenominatorZeroException{
        if(b==0){

                throw new DenominatorZeroException();
        }
        int q = a/b;
        return q;

}

}