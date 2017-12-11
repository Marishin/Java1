/**
 * Java 1. Homework 6
 *@author Vladislav Marishin
 *@version dated Dec 11, 2017
 *@link https://github.com/Marishin/Java1.git
 */
public class HomeWork6 {

    public static void main(String[] args){
        float meters=500F;
        System.out.println(meters);
        IAnimal[] animals = {new Cat("Cat "), new Dog("Dog ")};
        for (IAnimal item : animals){
            System.out.println(item);
            System.out.println(" run:" + item.run(meters));
            System.out.println(" swim:" + item.swim(meters));
            System.out.println(" jump:" + item.jump(meters));
        }
    }
}

interface IAnimal{

    Boolean swim(float meters);
    Boolean run(float meters);
    Boolean jump(float meters);
}
abstract class Animal implements IAnimal{
    String name;
    float max_run;
    float max_jump;
    float max_swim;

    Animal(String name){
        this.name=name;
    }
    public Boolean run(float meters){
        if (0 <= meters && meters <= max_run) return true;
        else return false;
    }
    public Boolean jump(float meters){
        if (0 <= meters && meters <= max_jump) return true;
        else return false;
    }
    @Override
    public Boolean swim(float meters) {
        if (0 <= meters && meters <= max_swim) return true;
        else return false;
    }
    @Override
    public String toString(){
        return name;
    }
}

class Cat extends Animal{
    Cat(String name){
        super(name);
        super. max_run=200;
        super.max_jump=2;
        super.max_swim=-1;
    }
}
class Dog extends Animal {
    Dog(String name){
        super(name);
        super.max_run=500;
        super.max_jump=0.5F;
        super.max_swim=10;
    }
}
