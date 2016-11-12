/**
 * Created by zhengwu on 11/11/16.
 */
public class FoilMakerModel {
    private String userToken;
    private String gameToken;

    public String getGameToken() {
        return gameToken;
    }

    public String getUserToken() {

        return userToken;
    }

    public void setGameToken(String gameToken) {
        this.gameToken = gameToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String simplifyResult(String fs){
        StringBuilder sb = new StringBuilder();
        for( int i=0;i<fs.length()-1;i++){
            if(Character.isLetter(fs.charAt(i))||fs.charAt(i)=='.'||fs.charAt(i)=='!'||fs.charAt(i)==' ')
                continue;
            if(fs.charAt(i)==fs.charAt(i+1)&& fs.charAt(i)=='-')
                continue;
            sb.append(fs.charAt(i));

        }
        sb.append(fs.charAt(fs.length()-1));
        return sb.toString();


    }

}
