/**
 * Created by zheng wu on 9/26/16.
 */
public class Pokemon {
    private String name;
    private int ID;
    private String type = "Fire";
    private int healthPower = 0;
    private double baseAttackPower = 1;
    public static int NUM_POKEMONS = 0;

       public Pokemon(String name, String type, int healthPower, double baseAttackPower)   {
           this.name = name;
           type = MyUtils.formatStr(type);
           if(type.equals("Fire") || type.equals("Electric")|| type.equals("Water")||type.equals("Grass"))
           this.type = type;
           if(healthPower>0)
           this.healthPower = healthPower;
           if(baseAttackPower>0)
           this.baseAttackPower = baseAttackPower;
           NUM_POKEMONS++;

       }

    public double getBaseAttackPower() {
        return baseAttackPower;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return NUM_POKEMONS-1;
    }

    public String getType() {
        return type;
    }

    public int getHealthPower() {
        return healthPower;
    }


    public boolean setType(String type) {
        type = MyUtils.formatStr(type);
        if (type.equals("Fire") || type.equals("Grass") || type.equals("Water") || type.equals("Electric")) {
            this.type = type;
            return true;
        }
        return false;
    }

    public boolean setHealthPower(int healthPower) {

        if (healthPower >= 0) {
            this.healthPower = healthPower;
            return true;
        }
        return false;
    }

    public boolean setBaseAttackPower(double baseAttackPower) {
        if (baseAttackPower > 0) {
            this.baseAttackPower = baseAttackPower;
            return true;
        }
        return false;
    }

    public String toString(){
        //// TODO: 9/26/16
        String result = "";
        result+= "Name: "+ this.getName()+"\n";
        result+= "ID: "+ this.getId()+"\n";
        result+= "Type: "+ this.getType()+"\n";
        result+= "Health power: "+ this.getHealthPower()+"\n";
        result+= "Base attack power: "+ this.getBaseAttackPower()+"\n";
        return result;

    }
    public boolean isDead(){

        return (healthPower==0);
    }
    public  void  boostHealthPower(int healthPower){
        this.healthPower += healthPower;

    }
    public void reduceHealthPower(int healthPower){
        if(healthPower>=this.healthPower){
            this.healthPower=0;
        }
        else{
            this.healthPower -= healthPower;
        }


    }
    public static int battle(Pokemon p1,
Pokemon p2)  {
        // todo
        //return 1 when 1 win, return 2 when 2 win

        while(p1.getHealthPower()>0 && p2.getHealthPower()>0) {
            p2.reduceHealthPower( (int)(p1.getAttackMultiplier(p1, p2)*p1.getBaseAttackPower()));
            p1.reduceHealthPower((int)(p2.getAttackMultiplier(p2, p1)*p2.getBaseAttackPower()));

        }
        if(p1.getHealthPower()>0) {

            return 1;
        }
        else{

            return 2;
            }

    }

    public static int battleOracle(Pokemon p1, Pokemon p2){
        int initialP1HealthPower = p1.getHealthPower();
        int initialP2HealthPower = p2.getHealthPower();
        while(p1.getHealthPower()>0 && p2.getHealthPower()>0) {
            p2.reduceHealthPower( (int)(p1.getAttackMultiplier(p1, p2)*p1.getBaseAttackPower()));
            p1.reduceHealthPower((int)(p2.getAttackMultiplier(p2, p1)*p2.getBaseAttackPower()));

        }
        if(p1.getHealthPower()>0) {
            p1.setHealthPower(initialP1HealthPower);
            p2.setHealthPower(initialP2HealthPower);
            return 1;
        }
        else{
            p1.setHealthPower(initialP1HealthPower);
            p2.setHealthPower(initialP2HealthPower);
            return 2;
        }

    }
    public static double getAttackMultiplier(Pokemon attacker, Pokemon defender)    {

        //// TODO: 9/26/16
        if(attacker.getType().equals("Fire")){
            if(defender.getType().equals("Fire"))
                return 0.5;
            if(defender.getType().equals("Water"))
                return 0.5;
            if(defender.getType().equals("Electric"))
                return 1;
            if(defender.getType().equals("Grass"))
                return 2;

        }
        else if(attacker.getType().equals("Water")){
            if(defender.getType().equals("Fire"))
                return 2;
            if(defender.getType().equals("Water"))
                return 0.5;
            if(defender.getType().equals("Electric"))
                return 1;
            if(defender.getType().equals("Grass"))
                return 0.5;
        }
        else if(attacker.getType().equals("Grass")){
            if(defender.getType().equals("Fire"))
                return 0.5;
            if(defender.getType().equals("Water"))
                return 2;
            if(defender.getType().equals("Electric"))
                return 1;
            if(defender.getType().equals("Grass"))
                return 0.5;
        }
        else if(attacker.getType().equals("Electric")){
            if(defender.getType().equals("Fire"))
                return 1;
            if(defender.getType().equals("Water"))
                return 2;
            if(defender.getType().equals("Electric"))
                return 0.5;
            if(defender.getType().equals("Grass"))
                return 0.5;
        }
        return 0.0;
    }
}




