/**
 * Java 1. Homework 5
 *@author Vladislav Marishin
 *@version dated Dec 9, 2017
 *@link https://github.com/Marishin/Java1.git
 */
import java.util.*;
class Home5{

    public static void main(String[] args) {
        TicTacToe ttc = new TicTacToe();   // Для передачи размера поля и выигрышной серии отсюда нужно описать массив
        ttc.initMap();                     // в классе TicTacToe с неопределенным размером. Вопрос- как это сделать?

        while (true) {
            ttc.humanTurn();
            ttc.printMap();
            if (ttc.uni_checkWin(ttc.getDOT_X())) {
                System.out.println("YOU WON!");
                break;
            }
            if (ttc.isMapFull()) {
                System.out.println("Sorry, DRAW!");
                break;
            }
            ttc.aiTurn();
            ttc.printMap();
            if (ttc.uni_checkWin(ttc.getDOT_O())) {
                System.out.println("AI WON!");
                break;
            }
        }
        System.out.println("GAME OVER.");

    }
}
class TicTacToe {

    private int size=5;
    private int win_series=3;
    private final char DOT_EMPTY = '.';
    private final char DOT_X = 'x';
    private final char DOT_O = 'o';
    private char[][] map = new char[size][size];
    private Scanner sc = new Scanner(System.in);
    private Random rand = new Random();


    char getDOT_X(){
        return DOT_X;
    }
    char getDOT_O(){
        return DOT_O;
    }

     void initMap() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                map[i][j] = DOT_EMPTY;
    }

    void printMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    void humanTurn() {
        int x, y;
        do {
            System.out.println("Enter X and Y (1..3):");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_O;
    }

    boolean checkWin(char dot) {
        boolean won_gor, won_ver, won_d1, won_d2;
        won_d1=true;
        won_d2=true;
        for(int i=0; i<size; i++){
            if (map[i][i]!=dot)     won_d1=false;                       // Check diagonals
            if (map[i][size-1-i]!=dot)   won_d2=false;                  //
            won_gor=true;
            won_ver=true;
            for(int j=0; j<size; j++){
                if (map[i][j]!=dot) won_gor=false;                      // Check horizontal
                if (map[j][i]!=dot) won_ver=false;                      // Check vertical
            }
            if(won_gor==true || won_ver==true) return true;
        }
        if(won_d1==true || won_d2==true) return true;
        return false;
    }

    boolean uni_checkWin(char dot) {
        int ver=0;
        int gor=0;
        int d1=0;
        int d2=0;
        int CUR_SERIES;
        boolean won;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if (map[i][j]==dot) {
                    CUR_SERIES=1;
                    for (int k = 1; k <= win_series; k++){              // check down
                        if ((i+k) >= size) break;                       // extra
                        if(map[i+k][j] == dot)  CUR_SERIES++;
                        else break;
                    }
                    for (int k = 1; k <= win_series; k++){              // check up
                        if ((i-k) < 0) break;                           // extra
                        if(map[i-k][j] == dot)  CUR_SERIES++;
                        else break;
                    }
                    if (CUR_SERIES>=win_series) return true;
                    CUR_SERIES=1;
                    for (int k = 1; k <= win_series; k++){              // check rigth
                        if ((j+k) >= size) break;                       // extra
                        if(map[i][j+k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    for (int k = 1; k <= win_series; k++){              // check left
                        if ((j-k) < 0) break;                           // extra
                        if(map[i][j-k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    if (CUR_SERIES>=win_series) return true;
                    CUR_SERIES=1;
                    for (int k = 1; k <= win_series; k++){              // diagonal \ down
                        if ((i+k) >= size || (j+k) >= size) break;      // extra
                        if(map[i+k][j+k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    for (int k = 1; k <= win_series; k++){              // diagonal \ up
                        if ((i-k) < 0 || (j-k) < 0 ) break;             // extra
                        if(map[i-k][j-k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    if (CUR_SERIES>=win_series) return true;
                    CUR_SERIES=1;
                    for (int k = 1; k <= win_series; k++){              // diagonal / down
                        if ((i+k) >= size || (j-k) <0 ) break;          // extra
                        if(map[i+k][j-k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    for (int k = 1; k <= win_series; k++){              // diagonal / up
                        if ((i-k) < 0 || (j+k) >= size ) break;         // extra
                        if(map[i-k][j+k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    if (CUR_SERIES>=win_series) return true;
                }
            }
        }
        return false;
    }
    boolean isMapFull() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (map[i][j] == DOT_EMPTY)
                    return false;
        return true;
    }

    private boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size)
            return false;
        if (map[y][x] == DOT_EMPTY)
            return true;
        return false;
    }
}