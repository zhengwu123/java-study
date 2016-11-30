/**
 * Created by zhengwu on 11/21/16.
 */
public class FoilUser {
    private String username;
    private String password;
    private int score;
    private int fooled;
    private int beenfooled;

    FoilUser(String username,String password){
        this.username= username;
        this.password = password;


    }

    public void setPassword(String password){
        this.password= password;
    }
    public int getScore(){
        return this.score;
    }
    public int getFooled(){
        return this.fooled;
    }
    public int getBeenfooled(){
        return this.beenfooled;
    }
}
