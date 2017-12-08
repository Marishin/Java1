/**
 * Java 1. Homework 5
 *@author Vladislav Marishin
 *@version dated Dec 9, 2017
 *@link https://github.com/Marishin/Java1.git
 */
class HomeWork5{
    public static void main(String[] args){
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Ivanov Ivan", "engineer",    "ivan@mailbox.com", "892312310",30000, 30);
        persArray[1] = new Person("Petrov Petr", "student",    "petr@mailbox.com", "892312311",30001, 22);
        persArray[2] = new Person("Sidorov Sidor","economist",   "sidor@mailbox.com", "892312312",30002, 40);
        persArray[3] = new Person("Victorov Viktor","driver", "viktor@mailbox.com", "892312313",30003, 33);
        persArray[4] = new Person("Sergeev Sergey", "boss", "sergey@mailbox.com", "892312314",30004, 44);

        print_persons_40plus(persArray);
    }
    private static void print_persons_40plus(Person parray[]) {
        System.out.println();
        System.out.println("Persons after 40 :");
        for(Person i:parray)
            if (i.getAge(i)>=40) System.out.println(i.getName(i) + ' ' + i.getPosition(i) + ' ' + i.getEmail(i) + ' ' + i.getTel(i) + ' ' + i.getSalary(i) + ' ' + i.getAge(i));
    }
}

class Person {
    private String name;
    private String position;
    private String email;
    private String tel;
    private int salary;
    private int age;

    Person(String name, String position, String email, String tel, int salary, int age){
        this.name=name;
        this.position=position;
        this.email=email;
        this.tel=tel;
        this.salary=salary;
        this.age=age;

        print_person();
    }

    private void print_person() {
        System.out.println(name + ' ' + position + ' ' + email + ' ' + tel + ' ' + salary + ' ' + age);
    }

    public String getName(Person person){
        return person.name;
    }
    public String getPosition(Person person){
        return person.position;
    }
    public String getEmail(Person person) {
        return person.email;
    }
    public String getTel(Person person){
        return person.tel;
    }
    public int getSalary(Person person){
        return person.salary;
    }
    public int getAge(Person person){
        return person.age;
    }
}
