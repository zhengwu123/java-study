/**
 * Question 2 -- Complete this class as specified
 */
public class Person implements Ageable,Weighable{
    private int age;
    private double weight;
    public Person(int age, double weight){
        this.age=age;
        this.weight=weight;
    }
    @Override
    public int getAge() {
        return age;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}