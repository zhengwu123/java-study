/**
 * Created by zheng wu on 9/26/16.
 */
public class MyUtils {


    public static String formatStr(String str){

        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static boolean isNumeric2(String str){
        if(str ==null|| str.isEmpty())
            return false;

        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static boolean isNumeric(String str)
    {
        if(str==null || str.isEmpty())
            return false;
        //regex approach
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static void main(String[] args){
        String pi = "3.1415926";
        String empty = "";
        System.out.println(isNumeric2(empty));

    }
}
