import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    BufferedReader br = null;
    private int QueryCount=0;
    private String username;
    private int realQcount=0;
        int linecount=0;
    public void parse( String  filePath) throws WrongFileFormatException, WrongNumberOfQueriesException, InvalidInputException, MalformedQueryException, IOException {

        String sCurrentLine;

        br = new BufferedReader(new FileReader(filePath));

        while ((sCurrentLine = br.readLine()) != null) {
            linecount++;
            if(linecount==2)
                username = sCurrentLine;
            if(linecount==5){
                QueryCount= Integer.parseInt(sCurrentLine);
                if(QueryCount<1) {
                    throw new InvalidInputException(" Invalid input N smaller than 1");
                }
            }
            if(sCurrentLine.contains("SELECT")||sCurrentLine.contains("UPDATE")||sCurrentLine.contains("INSERT")||sCurrentLine.contains("DELETE")){
                System.out.println("am i getting here?");
                realQcount++;
            }
            if((linecount==1 && !sCurrentLine.contains("C")) || (linecount==3 && !sCurrentLine.contains("c"))||(linecount==4 && !sCurrentLine.contains("N")) ||(linecount==6 && !sCurrentLine.contains("n")) || (linecount==7 && !sCurrentLine.contains("Q"))){
                throw new WrongFileFormatException(" bad format, not valid C,C or N,n OR Q,q pair");
            }
            if( linecount >7  ){
                if(sCurrentLine.length()>10){
                    if(!sCurrentLine.contains("SELECT")&&!sCurrentLine.contains("UPDATE")&&!sCurrentLine.contains("INSERT")&&!sCurrentLine.contains("DELETE")){
                        throw new MalformedQueryException("Bad SQL syntax");
                    }

                }

            }
            System.out.println(linecount + sCurrentLine);

        }
        if (realQcount!=QueryCount){
            throw new WrongNumberOfQueriesException("Query count not match!");
        }


    }

    public String getUserName(){
            return username;
    }

    public int getNumQueries(){
        return realQcount;

    }

    public static void main(String[] args) {
        Parser p = new Parser();
        try {
            p.parse("/Users/new/Desktop/cs180/file10.sql");
        } catch (WrongFileFormatException e) {
            e.printStackTrace();
        } catch (WrongNumberOfQueriesException e) {
            e.printStackTrace();
        } catch (InvalidInputException e) {
            e.printStackTrace();
        } catch (MalformedQueryException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(p.getUserName());
    }
}
