/**
 * Created by zhengwu on 11/21/16.
 */
public class FoilUser {
    private String username;
    private String password;
    private int score;
    private int fooled;
    private int beenfooled;

    FoilUser(String username,String password,int score,int fooled,int beenfooled){
        this.username= username;
        this.password = password;
        this.score = score;
        this.fooled = fooled;
        this.beenfooled = beenfooled;

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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setFooled(int fooled) {
        this.fooled = fooled;
    }

    public void setBeenfooled(int beenfooled) {
        this.beenfooled = beenfooled;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {
        return password;
    }

    public void increaseScore(int val){
        score+=val;
    }
    public void increaseFooled(){
        fooled++;
    }
    public void increaseBeenFooled(){
        beenfooled++;
    }

    @Override
    public String toString() {
        return username +":"+password+":"+score+":"+fooled+":"+beenfooled;
    }
}
