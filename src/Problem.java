/**
 * Created by zheng on 9/23/16.
 */
import java.util.Scanner;
public class Problem {

        public static void main(String[] args)
        {

            Scanner stdin = new Scanner(System.in);
            while(stdin.hasNextLine())
            {
                System.out.println(stdin.nextLine());
                String input1 = stdin.nextLine();
                String input2 = stdin.nextLine();
                int base1 = findcase(input1);
                int base2 = findcase(input2);
                int X_10 = Integer.valueOf(input1,base1);
                int Y_10 = Integer.valueOf(input2,base2);
                int result = X_10+Y_10;
                System.out.println(result);
            }
            stdin.close();
        }
        static int findcase(String str){
            boolean haveChar = false;
            int temp;
            char temChar;
            int maxint = 0;
            int maxChar ='A';
            for(int i =0;i<str.length();i++){
                if(Character.isDigit(str.charAt(i))){
                    int digit = Integer.parseInt(str.charAt(i)+"");
                    temp = digit;
                    if(temp>maxint)
                        maxint=temp;
                }
                else if ((str.charAt(i) >= 'a' && str.charAt(i) <= 'f') || (str.charAt(i) >= 'A' && str.charAt(i) <= 'F')){
                    haveChar=true;
                      temChar = Character.toUpperCase(str.charAt(i));
                    if (temChar>maxChar){
                        maxChar = temChar;
                    }

                }

            }
            if(haveChar){
                if (maxChar=='A')
                    return 11;
                if (maxChar=='B')
                    return 12;
                if (maxChar=='C')
                    return 13;
                if (maxChar=='D')
                    return 14;
                if (maxChar=='E')
                    return 15;
                if (maxChar=='F')
                    return 16;
            }
            return maxint+1;
        }

    }

