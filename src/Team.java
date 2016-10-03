

import javax.swing.*;
import java.text.DecimalFormat;


/**
 * Created by zheng wu on 9/23/16.
 * wu673@purdue.edu
 */
public class Team {
    private String name;
    private String location;
    private int nWins = 0;
    private int nLosses = 0;
    private double offense;
    private double defense;

    DecimalFormat numberFormat = new DecimalFormat("##.00");
        //constructor
    public Team(String name, String location){
        this.name= name;
        this.location = location;
        this.defense=luck();
        this.offense=luck();

    }

    public static double luck(){
        double luck = Math.random()*1.0;
        return luck;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getN_Wins() {
        return nWins;
    }

    public int getN_Losses() {
        return nLosses;
    }

    public double getOffense() {
        return offense;
    }

    public double getDefense() {
        return defense;
    }


    public int calcTotalGames(){

        return nLosses+nWins;
    }
    public static Team play (Team team1,boolean isHome,Team team2){

        if(isHome) {
            double homeScore = (team1.getOffense() + team1.getDefense() + 0.2) *
                    luck();
            double awayScore = (team2.getOffense() + team2.getDefense()) * luck();
            if (homeScore > awayScore) {
                team1.IncreasenWins();
                team2.IncreasenLosses();
                return team1;

            } else {
                team2.IncreasenWins();
                team1.IncreasenLosses();
                return team2;
            }
        }
        else{
            double homeScore = (team2.getOffense() + team2.getDefense() + 0.2) *
                    luck();
            double awayScore = (team1.getOffense() + team1.getDefense()) * luck();
            if (homeScore > awayScore) {
                team2.IncreasenWins();
                team1.IncreasenLosses();
                return team2;
            } else {
                team1.IncreasenWins();
                team2.IncreasenLosses();
                return team1;
            }

        }
    }
    public void IncreasenWins(){ nWins++;}
    public void IncreasenLosses(){ nLosses++;}
    public double calcWinRate(){
        return ((double) nWins/(nWins+nLosses));
    }
    public double calcLossRate(){
        return ((double) nLosses/(nWins+nLosses));
    }
    public int calcDifference(){
        return Math.abs(nLosses-nWins);
    }
    public void generateStats(){

        String result = this.getName() + "(" + this.getLocation()+")"+"\n";
        result += "Total games: "+ this.calcTotalGames() +"\n";
        result +="No. wins: "+ getN_Wins()+"("+numberFormat.format(this.calcWinRate()*100)+"%)"+"\n";
        result +="No. losess: "+ getN_Losses()+"("+numberFormat.format(this.calcLossRate()*100)+"%)"+"\n";
        result +="Difference: "+ calcDifference()+"\n";
        JOptionPane.showMessageDialog(null,result);
    }
    public static void main (String[]args){
        DecimalFormat formatter = new DecimalFormat("#0.00");
        String team1 =  JOptionPane.showInputDialog("Enter name and location for home team separated by comma(,)");
        if(team1 == null || !team1.contains(",")){
            JOptionPane.showMessageDialog(null,"Invalid input, please use (,) to separate name and location");
            System.exit(1);
        }
        String CapitalTeam1 = team1.substring(0, 1).toUpperCase() + team1.substring(1).toLowerCase();
        String[] Team1Array = CapitalTeam1.split(",");
        String team2 =  JOptionPane.showInputDialog("Enter name and location for away team separated by comma(,)");
        String team1Name = Team1Array[0];
        String team1Location= Team1Array[1];
        Team homeTeam = new Team(team1Name,team1Location);
        String CapitalTeam2 = team2.substring(0, 1).toUpperCase() + team2.substring(1).toLowerCase();
        String[] Team2Array = CapitalTeam2.split(",");
        String team2Name = Team2Array[0];
        String team2Location= Team2Array[1];
        Team awaytTeam = new Team(team2Name,team2Location);
        String team1String = "Home team is: " + team1Name + " from " + team1Location + " rated " + formatter.format(homeTeam.getOffense())+" (offense) "
                + formatter.format(homeTeam.getDefense()) + " (defense)";
        JOptionPane.showMessageDialog(null,team1String);
        String team2String = "Home team is: " + team2Name + " from " + team2Location + " rated " + formatter.format(awaytTeam.getOffense())+" (offense) "
                + formatter.format(awaytTeam.getDefense()) + " (defense)";
        JOptionPane.showMessageDialog(null,team2String);

        Team winnerRound1 = play(homeTeam,true,awaytTeam);
        String message1 = "Round 1 \n";
        message1 += "Winner is: " + winnerRound1.getName() + " from " + winnerRound1.getLocation() + " rated " + formatter.format(winnerRound1.getOffense())+" (offense) "
                + formatter.format(winnerRound1.getDefense()) + " (defense)";
        JOptionPane.showMessageDialog(null,message1);

        Team winnerRound2 = play(homeTeam,true,awaytTeam);
        String message2 = "Round 2 \n";
        message2 += "Winner is: " + winnerRound2.getName() + "from " + winnerRound2.getLocation() + " rated " + formatter.format(winnerRound2.getOffense())+" (offense) "
                + formatter.format(winnerRound2.getDefense()) + " (defense)";
        JOptionPane.showMessageDialog(null,message2);

        Team winnerRound3 = play(homeTeam,true,awaytTeam);
        String message3 = "Round 3 \n";
        message3 += "Winner is: " + winnerRound3.getName() + " from " + winnerRound3.getLocation() + " rated " + formatter.format(winnerRound3.getOffense())+" (offense) "
                + formatter.format(winnerRound3.getDefense()) + " (defense)";
        JOptionPane.showMessageDialog(null,message3);

        homeTeam.generateStats();
        awaytTeam.generateStats();

    }
}
