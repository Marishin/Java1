/**
 * Java 1. Homework 7
 *@author Vladislav Marishin
 *@version dated Dec 15, 2017
 *@link https://github.com/Marishin/Java1.git
 */
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import java.awt.*;

class HomeWotk7{

     public static void main(String[] args) {
         Random rand = new Random();
         int val_cats = 5;
         int val_food = 100;
         int max_appetite = 50;
         int add_food = 100;
         Plate plate = new Plate(val_food);
         Cat[] cats = new Cat[val_cats];

         System.out.println(plate);
         for (int i = 0; i < val_cats; i++) {
             cats[i] = new Cat("Cat" + (i + 1), rand.nextInt(max_appetite));
             if (cats[i].eat(plate)) cats[i].setFull(true);
             System.out.println("Cat " + (i+1) + " (appetite " + cats[i].getAppetite() + ")  " + cats[i].getFull());
         }
         plate.addFood(add_food);
         System.out.println(plate + " (after refill)");
         CatsWindow myWindow = new CatsWindow();
         Panel panel = new Panel();
//       panel.paint();
    }
}
class Panel extends JPanel { // for painting
    @Override
    public void paint(Graphics g) {                 // Не хватило времени разобраться с прорисовкой
        g.setColor(Color.BLACK);
        g.drawOval(1,1,100,100);
        g.drawLine(2, 2, 200, 200);
        paint(g);
    }
}

class CatsWindow extends JFrame {
    public CatsWindow() {
        setBounds(300, 300, 400, 400);
        setTitle("Cats dinner");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton[] jbs = new JButton[3];
        JPanel panel = new JPanel();
        String[] jbs_name={"New Cats", "Feed Cats", "Add Food"};
        setLayout(new FlowLayout());
        for (int i = 0; i < jbs.length; i++) {
            jbs[i] = new JButton(jbs_name[i]);
            jbs[i].setAlignmentX(RIGHT_ALIGNMENT);
            add(jbs[i]);
        }
        jbs[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        jbs[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jbs[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }
}

class Cat {
    private String name;
    private int appetite;
    private boolean full;

    public int getAppetite() {
        return appetite;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public boolean getFull() {
        return full;
    }

    Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        full=false;
    }

    boolean eat(Plate plate) {
        return plate.decreaseFood(appetite);
    }
}

class Plate {
    private int food;

    Plate(int food) {
        this.food = food;
    }

    boolean decreaseFood(int food) {
        if (this.food>=food) {
            this.food -= food;
           return true;
        }
        return false;
    }

    void addFood(int food) {
        this.food += food;
    }

    @Override
    public String toString() {
        return "Plate: " + food;
    }
}
