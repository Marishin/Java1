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
    Animal(String name){
        this.name=name;
    }
    String name="";
    public Boolean swim(float meters){
        return false;
    }

    @Override
    public String toString(){
        return name;
    }
}

class Cat extends Animal{
    Cat(String name){
        super(name);
    }
    public Boolean run(float meters){
        if (0<meters && meters<=200) return true;
        else return false;
    }
    public Boolean jump(float meters){
        if (0<meters && meters<=2) return true;
        else return false;
    }
}
class Dog extends Animal {
    Dog(String name){
        super(name);
    }
    @Override
    public Boolean swim(float meters) {
        if (0 < meters && meters <= 10) return true;
        else return false;
    }

    public Boolean run(float meters) {
        if (0 < meters && meters <= 500) return true;
        return false;
    }

    public Boolean jump(float meters) {
        if (0 < meters && meters <= 0.5) return true;
        else return false;
    }
}
