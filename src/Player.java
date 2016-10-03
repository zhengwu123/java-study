/**
 * Created by new on 9/12/16.
 */
public class Player {
    private String name;
    private double positionX;
    private double positionY;
    public Player(String name){
        this.name = name;
        this.positionX = 0;
        this.positionY =0;
    }
    public Player(String name,double positionX,double positionY){
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public String getName(){

        return this.name;
    }
    public double getPositionX(){

        return this.positionX;
    }
    public double getPositionY(){

        return this.positionY;
    }
    public void setName(String name){
        this.name = name;

    }
    public void moveX(double offsetX){
        this.positionX += offsetX;
    }
    public void moveY(double offsetY){
        this.positionY += offsetY;
    }
    public void moveInDirection(double theta, double distance){
            this.positionX += distance * Math.cos(Math.toRadians(theta));
            this.positionY += distance * Math.sin(Math.toRadians(theta));

    }
    public boolean hasSamePositionAs(Player player){
        if(Math.abs(Math.sqrt(Math.pow(this.positionX-player.getPositionX(),2)+Math.pow(this.positionY-player.getPositionY(),2)))<0.001){

            return true;
        }
        return false;
    }
    public double distanceFrom (Player player){

        return Math.abs(Math.sqrt(Math.pow(this.positionX-player.getPositionX(),2)+Math.pow(this.positionY-player.getPositionY(),2)));
    }

    public static void main(String [] args){
       Player player = new Player("AGO",1,1);
        player.moveInDirection(45,5);
        System.out.print(player.getPositionX());
        System.out.println();
        System.out.print(player.getPositionY());
    }
}
