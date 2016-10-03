/**
 * Question 3 -- Complete the method differentiate.
 */
public class Calculus {

    public String differentiate(String func) {
        if (func == null)
            return null;
        if (func.length() == 0)
            return "";
        String prefix = "";
        String suffix = "";
        if (func.contains("x^")){
            for (int i = 0; i < func.length() - 1; i++) {
                if (func.charAt(i) == 'x' && func.charAt(i + 1) == '^') {

                    prefix += func.substring(0, i);
                    suffix += func.substring(i + 2);
                    break;
                }
            }
            int a = Integer.parseInt(prefix);
            int b = Integer.parseInt(suffix);

            int c = a * b;
            int d = b - 1;
            return c + "x^" + d;

        }
        return null;
    }


}
