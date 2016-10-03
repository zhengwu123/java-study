/**
 * Question 2 -- Complete the class as specified in the handout
 */
public class Bug {
    private int position;
    private boolean  directionRight = true;


    public Bug(int initialPosition){
        this.position = initialPosition;
    }

    public int getPosition() {
        return position;
    }

    public String getDirection() {

        if(directionRight==true)
            return "right";
            return "left";
    }

    public void turn(){
        if(directionRight==true)
            directionRight = false;
        else if(directionRight==false)
            directionRight=true;

    }

    public void move(){
        if (directionRight== true){
            this.position +=1;}
            else if(directionRight==false){
                this.position -=1;
            }

        }

    public static void main(String[] args) {
        Bug b = new Bug(10);
        System.out.println("bug postion: "+b.getPosition());
        b.move();
        System.out.println("bug postion: "+b.getPosition());
        b.turn();
        System.out.println("bug direction: " +b.getDirection());
        b.move();
        System.out.println("bug position: "+b.getPosition());
    }

    }



