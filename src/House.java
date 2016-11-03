/**
 * Created by zhengwu on 11/2/16.
 */
public class House extends Residence{
    private int numFloors;
    private boolean hasGarage;
    House(String address,int numBedrooms,int numBathrooms,int squareFootage,double monthlyRent,int numFloors,boolean hasGarage){
        super(address,numBedrooms,numBathrooms,squareFootage,monthlyRent);
        this.numFloors=numFloors;
        this.hasGarage=hasGarage;

    }

    public int getNumFloors() {
        return numFloors;
    }

    public boolean hasGarage() {
        return hasGarage;
    }

    public String toString(){
        String result ="";
        result+= "address: " + getAddress()+"\n";
        result+= "numBedrooms: " + getNumBedrooms()+"\n";
        result+= "numBathrooms: " + getNumBathrooms()+"\n";
        result+= "squareFootage: " + getSquareFootage()+"\n";
        result+= "monthlyRent: " + getMonthlyRent()+"\n";
        result+= "numFloors: " + getNumFloors()+"\n";
        result+= "hasGarage: " + hasGarage()+"\n";
        return result;

    }
}
