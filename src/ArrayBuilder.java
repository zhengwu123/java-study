/**
 * Created by zhengwu on 10/14/16.
 */
public class ArrayBuilder {
    char [][] letterArray;
    char init;
    public ArrayBuilder(char baseLetter, int n, int m){
        letterArray = new char[n][m];
        init = baseLetter;

    }
    public void build(){
        for(int i=0;i<letterArray.length;i++){
            for(int j=0;j<letterArray[0].length;j++){
          //if(letterArray[i][j]!='\0')
              //continue;
                        //if(i==0 && j>0)
                        if(i==0) {
                            if(init >= 'a' && init <='z') {
                                letterArray[i][j] = (char) (init);
                                init += 1;
                            }
                            else if(init >= 'A' && init <='Z') {
                                letterArray[i][j] = (char) (init);
                                init += 1;
                            }
                            else {
                                char tmp = init;
                                letterArray[i][j] = (char) (tmp - 26);
                                init += 1;
                            }
                        }
                else{
                            char temp = (char)(letterArray[i-1][j]+1);
                            if(temp >= 'a' && temp <='z') {
                                letterArray[i][j] = (char) (temp);

                            }
                            else if(temp >= 'A' && temp <='Z') {
                                letterArray[i][j] = (char) (temp);

                            }
                            else {
                                temp = (char) (temp - 26);
                                letterArray[i][j] = temp;
                            }

                        }

            }

        }

    }

    public char[][] getLetterArray(){
        char[][]result = new char[letterArray.length][letterArray[0].length];
        //todo
        for(int i=0;i<result.length;i++){
            for(int j=0;j<result[0].length;j++){
                result[i][j]= letterArray[i][j];
            }
        }
        return result;
    }

    public void printLetterArray(){
        //todo
        for(int i=0;i<letterArray.length;i++){
            for(int j=0;j<letterArray[0].length;j++){
                System.out.print(letterArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArrayBuilder ab = new ArrayBuilder('A',7,8);
        ab.build();
        ab.printLetterArray();
        char b  = 'b';
        //System.out.println((char)(b+24));
    }
}
