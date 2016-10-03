/**
 * Created by zheng wu on 9/20/16.
 */
public class Movie {
    private  String name;
    private  double criticRating;
    private  double usersRating;
    private  int numUsersRatings;

    public Movie(String name, double criticRating, double usersRating, int numUsersRatings){
        this.name = name;
        if(criticRating>=1 && criticRating<=5) {
            this.criticRating = criticRating;
        }
        else{
            this.criticRating=5;
        }
        if(usersRating>=1 && usersRating<=5) {
            this.usersRating = usersRating;
        }
        else{
            this.usersRating = 5;
        }
        this.numUsersRatings = numUsersRatings;
    }

    public String getName(){
        return this.name;
    }

    public double getCriticRating(){
        return this.criticRating;
    }

    public double getUsersRating(){
        return this.usersRating;
    }

    public int getNumUsersRatings(){
        return this.numUsersRatings;
    }
    public int getReviewRange(){
        if(this.numUsersRatings>=0 && this.numUsersRatings<=1000) {
            return 1;
        }
        else if(this.numUsersRatings>=1001 && this.numUsersRatings<=5000){
            return 2;
        }
        else if(this.numUsersRatings>=5001 && this.numUsersRatings<=10000){
            return 3;
        }
        else if(this.numUsersRatings>=10001 && this.numUsersRatings<=15000){
            return 4;
        }
        else if(this.numUsersRatings>=15001 && this.numUsersRatings<=20000){
            return 5;
        }
        else if(this.numUsersRatings>=20001 && this.numUsersRatings<=25000){
            return 6;
        }
        else if(this.numUsersRatings>=25001 && this.numUsersRatings<=30000){
            return 7;
        }
        else if(this.numUsersRatings>=30001 && this.numUsersRatings<=50000){
            return 8;
        }
        else if(this.numUsersRatings>=50001 && this.numUsersRatings<=100000){
            return 9;
        }
        else{
            return 10;
        }
    }
    public static int compareMovies(Movie movie1, Movie movie2){
        if(movie1.getCriticRating()> movie2.getCriticRating() ) {
            if (movie1.getUsersRating() >= movie2.getUsersRating()) {
                return 1;
            } else if (movie1.getUsersRating() < movie2.getUsersRating()) {

                double smartscore1 = 0.5 * movie1.getCriticRating() + 0.3 * movie1.getUsersRating() + 0.1 * movie1.getReviewRange();
                double smartscore2 = 0.5 * movie2.getCriticRating() + 0.3 * movie2.getUsersRating() + 0.1 * movie1.getReviewRange();
                if (smartscore1 > smartscore2) {
                    return 1;
                } else if (smartscore2 > smartscore1) {
                    return 2;
                } else if (smartscore1 == smartscore2) {
                    return 0;
                }

            }
        }
        else if(movie2.getCriticRating()> movie1.getCriticRating()){
            if (movie2.getUsersRating() >= movie2.getUsersRating()) {
                return 2;
            }
            else if (movie2.getUsersRating() < movie1.getUsersRating()) {

                double smartscore1 = 0.5 * movie1.getCriticRating() + 0.3 * movie1.getUsersRating() + 0.1 * movie1.getReviewRange();
                double smartscore2 = 0.5 * movie2.getCriticRating() + 0.3 * movie2.getUsersRating() + 0.1 * movie1.getReviewRange();
                if (smartscore1 > smartscore2) {
                    return 1;
                } else if (smartscore2 > smartscore1) {
                    return 2;
                } else if (smartscore1 == smartscore2) {
                    return 0;
                }

            }

        }
        else if(movie1.getCriticRating()==movie2.getCriticRating() ){

            if(movie1.getUsersRating()>movie2.getUsersRating()){
                return 1;
            }
            if(movie2.getUsersRating()>movie1.getUsersRating()){
                return 2;
            }
            else {
                return 0;
            }
        }
            return 2;


    }
    public boolean addUserRating(int newRating){
        if(newRating>=1 && newRating<=5){
            this.usersRating=(this.usersRating*numUsersRatings +usersRating)/(numUsersRatings++);
            return true;
        }
        return false;
    }

}
