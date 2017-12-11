/**
 * Java 1. Homework 6
 *@author Vladislav Marishin
 *@version dated Dec 12, 2017
 *@link https://github.com/Marishin/Java1.git
 */
import java.util.*;

class HomeWorkTTT6{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int SIZE = 5;
        int WIN_SERIES=4;
        ITicTacToe ttt;

        System.out.println("Choose type of TicTacToe (1 - Classic 2 - Extended) :");
        if(sc.nextInt()==1){
            System.out.println("Classic TicTacToe");
            ttt = new ClassicTicTacToe(3,3);
        }
        else{
            System.out.println("Extended TicTacToe");
            ttt = new ExtendedTicTacToe(SIZE,WIN_SERIES);
        }
//        ITicTacToe[] ttt = {new ClassicTicTacToe(3,3), new ExtendedTicTacToe(SIZE,WIN_SERIES)};
//        for(ITicTacToe item : ttt) {
            ttt. go();
        }
}

interface ITicTacToe{
    void go();
    void initMap(char[][] map);
    void printMap();
    void humanTurn();
    boolean checkWin(char dot);
    int getSIZE();
    int getWIN_SERIES();
    char[][] getMap();
}

abstract class TicTacToe implements ITicTacToe{
    private int SIZE;
    private int WIN_SERIES;
    private final char DOT_X = 'x';
    private final char DOT_O = 'o';
    private final char DOT_EMPTY = '.';
    private char[][] map;
    private Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    TicTacToe(int SIZE, int WIN_SERIES) {
        this.SIZE=SIZE;
        this.WIN_SERIES=WIN_SERIES;
        map = new char[SIZE][SIZE];
    }

    public int getSIZE(){
        return this.SIZE;
    }
    public int getWIN_SERIES(){
        return this.WIN_SERIES;
    }
    public char[][] getMap(){
        return this.map;
    }

    public void go(){
        initMap(map);
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("YOU WON!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW!");
                break;
            }
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("AI WON!");
                break;
            }
        }
        System.out.println("GAME OVER.");
    }

    public void initMap(char[][] map) {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }

    public void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public void humanTurn() {
        int x, y;
        do {
            System.out.println("Enter X and Y (1..3):");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    private boolean isMapFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (map[i][j] == DOT_EMPTY)
                    return false;
        return true;
    }

    private boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE)
            return false;
        if (map[y][x] == DOT_EMPTY)
            return true;
        return false;
    }
}

class ExtendedTicTacToe extends TicTacToe {

    ExtendedTicTacToe(int SIZE, int WIN_SERIES) {
        super(SIZE, WIN_SERIES);
    }

    public boolean checkWin(char dot) {
        int ver=0;
        int gor=0;
        int d1=0;
        int d2=0;
        int CUR_SERIES;
        boolean won;
        char[][] map=getMap();
        int SIZE=getSIZE();
        int WIN_SERIES=getWIN_SERIES();

        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if (map[i][j]==dot) {
                    CUR_SERIES=1;
                    for (int k = 1; k <= WIN_SERIES; k++){          // down
                        if ((i+k) >= SIZE) break;
                        if(map[i+k][j] == dot)  CUR_SERIES++;
                        else break;
                    }
                    for (int k = 1; k <= WIN_SERIES; k++){          // up
                        if ((i-k) < 0) break;
                        if(map[i-k][j] == dot)  CUR_SERIES++;
                        else break;
                    }
                    if (CUR_SERIES>=WIN_SERIES) return true;
                    CUR_SERIES=1;
                    for (int k = 1; k <= WIN_SERIES; k++){          // right
                        if ((j+k) >= SIZE) break;
                        if(map[i][j+k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    for (int k = 1; k <= WIN_SERIES; k++){          // left
                        if ((j-k) < 0) break;
                        if(map[i][j-k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    if (CUR_SERIES>=WIN_SERIES) return true;
                    CUR_SERIES=1;
                    for (int k = 1; k <= WIN_SERIES; k++){          // diagonal \ down
                        if ((i+k) >= SIZE || (j+k) >= SIZE) break;
                        if(map[i+k][j+k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    for (int k = 1; k <= WIN_SERIES; k++){          // diagonal \ up
                        if ((i-k) < 0 || (j-k) < 0 ) break;
                        if(map[i-k][j-k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    if (CUR_SERIES>=WIN_SERIES) return true;
                    CUR_SERIES=1;
                    for (int k = 1; k <= WIN_SERIES; k++){          // diagonal / down
                        if ((i+k) >= SIZE || (j-k) <0 ) break;
                        if(map[i+k][j-k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    for (int k = 1; k <= WIN_SERIES; k++){          // diagonal / up
                        if ((i-k) < 0 || (j+k) >= SIZE ) break;
                        if(map[i-k][j+k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    if (CUR_SERIES>=WIN_SERIES) return true;
                }
            }
        }
        return false;
    }
}

class ClassicTicTacToe extends TicTacToe{


    ClassicTicTacToe(int SIZE, int WIN_SERIES) {
        super(SIZE, WIN_SERIES);
    }

    public boolean checkWin(char dot){
        boolean won_gor,won_ver,won_d1,won_d2;
        won_d1=true;
        won_d2=true;
        char[][] map=getMap();
        int SIZE=getSIZE();

        for(int i=0;i<SIZE; i++){
            if(map[i][i]!=dot)won_d1=false;   // Check diagonals
            if(map[i][SIZE-1-i]!=dot)won_d2=false;   //
            won_gor=true;
            won_ver=true;
            for(int j=0;j<SIZE; j++){
                if(map[i][j]!=dot)won_gor=false;  // Check horizontal
                if(map[j][i]!=dot)won_ver=false;  // Check vertical
            }
            if(won_gor==true||won_ver==true)return true;
        }
        if(won_d1==true||won_d2==true)return true;
        return false;
    }
}