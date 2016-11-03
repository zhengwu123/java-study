/**
 * Created by zhengwu on 11/2/16.
 */
public class Apartment extends Residence{
    private int floorNumber;

    public Apartment(String address,int numBedrooms,int numBathrooms,int squareFootage,double monthlyRent,int floorNumber){
        super(address,numBedrooms,numBathrooms,squareFootage,monthlyRent);
        this.floorNumber=floorNumber;


    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public String toString(){

        String result ="";
        result+= "address: " + getAddress()+"\n";
        result+= "numBedrooms: " + getNumBedrooms()+"\n";
        result+= "numBathrooms: " + getNumBathrooms()+"\n";
        result+= "squareFootage: " + getSquareFootage()+"\n";
        result+= "monthlyRent: " + getMonthlyRent()+"\n";
        result+= "floorNumber: " + getFloorNumber()+"\n";
        return result;

    }
}
