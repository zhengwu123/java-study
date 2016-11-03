import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhengwu on 11/2/16.
 */
public class ResidenceListings  {
    private int numResidences;
    private int maxResidences;
    private LinkedList<Residence>residences = new LinkedList<Residence>();
    public ResidenceListings(){
        this.maxResidences = 10;
        this.numResidences=0;
    }

    public void addResidence(Residence residence){
        if(numResidences<maxResidences) {
            residences.add(residence);
            numResidences++;
        }
        else{
            maxResidences*=2;
            numResidences++;
            residences.add(residence);
        }
        System.out.println(residence.getAddress() +" added to list.");
    }

    public void removeResidence(Residence residence)throws NoSuchResidenceException{
        if(residences.contains(residence)) {
            residences.remove(residence);
            numResidences--;
            System.out.println(residence.getAddress() +" removed.");
        }
        else{
            throw new NoSuchResidenceException("no such a residence.");
        }

    }

    public Residence findResidenceByAddress(String address){
                boolean flag=true;

            for(int i=0;i<residences.size();i++){
                if(residences.get(i).getAddress().equals(address)) {
                    flag=false;
                    System.out.println(address + " found!");
                    return residences.get(i);
                }
            }
        if(flag) {
            try {
                throw new NoSuchResidenceException("address not exist");
            } catch (NoSuchResidenceException e) {
                e.printStackTrace();
            }
        }

        return null;

    }





    public int getNumResidences() {
        return numResidences;
    }

    public int getMaxResidences() {
        return maxResidences;
    }

    public Residence[] getResidences() {
        if(residences.size()==0) {
            Residence[] empty = {};
            return empty;
        }
        Residence[]result = new Residence[maxResidences];
        for(int i=0;i<residences.size();i++){
            result[i]= residences.get(i);
        }
        return result;
    }

    public static void main(String[] args) throws NoSuchResidenceException {
        ResidenceListings listing = new ResidenceListings();
        for (int i = 0; i < 10; i++) {
            House house = new House(String.valueOf(i), 0, 0, 0, 0,1,true);
            listing.addResidence(house);
        }
        System.out.println(listing.findResidenceByAddress("1").toString());

        for (int i = 9; i < 15; i++) {
            Apartment apartment = new Apartment(String.valueOf(i), 0, 0, 0, 0,1);
            listing.addResidence(apartment);
        }
        System.out.println(listing.findResidenceByAddress("10").toString());
        Residence residence = new Residence("MY HOUESE 101", 0, 0, 0, 0);
        listing.addResidence(residence);
        try {
            listing.removeResidence(residence);
        } catch (NoSuchResidenceException e) {
            e.printStackTrace();
        }

    }
}
