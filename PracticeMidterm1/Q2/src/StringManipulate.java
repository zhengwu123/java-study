/**
 * Question 2 -- Complete the method haveSameChars
 */

public class StringManipulate {


    public static boolean haveSameChars(String s1, String s2) {

        if (s1 == "" && s2 == "")
            return true;
        if (s1 == null && s2 != null) {
            return false;
        }
        if (s1 != null && s2 == null) {
            return false;

        }
        if(s1.length()!=s2.length())
            return false;

        int [] tracker = new int[256];
        for(int i =0;i<s1.length();i++){
            tracker[s1.charAt(i)]++;
        }
        for(int j =0;j<s2.length();j++){
            tracker[s2.charAt(j)]--;
                if(tracker[s2.charAt(j)]<0)
                    return false;
            }
            return true;
        }
}

