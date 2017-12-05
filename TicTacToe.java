/**
 * Java 1. Homework 4
 *@author Vladislav Marishin
 *@version dated Dec 5, 2017
 *@link https://github.com/Marishin/Java1.git
 */
import java.util.*;

class TicTacToe {

    final int SIZE = 5;
    final int WIN_SERIES=3;
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
    char[][] map = new char[SIZE][SIZE];
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public static void main(String[] args) {
        new TicTacToe();
    }

    TicTacToe() {
        initMap();
        while (true) {
            humanTurn();
            printMap();
            if (uni_checkWin(DOT_X)) {
                System.out.println("YOU WON!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW!");
                break;
            }
            aiTurn();
            printMap();
            if (uni_checkWin(DOT_O)) {
                System.out.println("AI WON!");
                break;
            }
        }
        System.out.println("GAME OVER.");
    }

    void initMap() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }

    void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
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
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_O;
    }

    boolean checkWin(char dot) {
        boolean won_gor, won_ver, won_d1, won_d2;
        won_d1=true;
        won_d2=true;
        for(int i=0; i<SIZE; i++){
            if (map[i][i]!=dot)     won_d1=false;                       // Check diagonals
            if (map[i][SIZE-1-i]!=dot)   won_d2=false;                  //
            won_gor=true;
            won_ver=true;
            for(int j=0; j<SIZE; j++){
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

        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if (map[i][j]==dot) {
                    CUR_SERIES=1;
                    for (int k = 1; k <= WIN_SERIES; k++){              // check down
                        if ((i+k) >= SIZE) break;                       // extra
                        if(map[i+k][j] == dot)  CUR_SERIES++;
                        else break;
                    }
                    for (int k = 1; k <= WIN_SERIES; k++){              // check up
                        if ((i-k) < 0) break;                           // extra
                        if(map[i-k][j] == dot)  CUR_SERIES++;
                        else break;
                    }
                    if (CUR_SERIES>=WIN_SERIES) return true;
                    CUR_SERIES=1;
                    for (int k = 1; k <= WIN_SERIES; k++){              // check rigth
                        if ((j+k) >= SIZE) break;                       // extra
                        if(map[i][j+k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    for (int k = 1; k <= WIN_SERIES; k++){              // check left
                        if ((j-k) < 0) break;                           // extra
                        if(map[i][j-k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    if (CUR_SERIES>=WIN_SERIES) return true;
                    CUR_SERIES=1;
                    for (int k = 1; k <= WIN_SERIES; k++){              // diagonal \ down
                        if ((i+k) >= SIZE || (j+k) >= SIZE) break;      // extra
                        if(map[i+k][j+k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    for (int k = 1; k <= WIN_SERIES; k++){              // diagonal \ up
                        if ((i-k) < 0 || (j-k) < 0 ) break;             // extra
                        if(map[i-k][j-k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    if (CUR_SERIES>=WIN_SERIES) return true;
                    CUR_SERIES=1;
                    for (int k = 1; k <= WIN_SERIES; k++){              // diagonal / down
                        if ((i+k) >= SIZE || (j-k) <0 ) break;          // extra
                        if(map[i+k][j-k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    for (int k = 1; k <= WIN_SERIES; k++){              // diagonal / up
                        if ((i-k) < 0 || (j+k) >= SIZE ) break;         // extra
                        if(map[i-k][j+k] == dot)  CUR_SERIES++;
                        else break;
                    }
                    if (CUR_SERIES>=WIN_SERIES) return true;
                }
            }
        }
        return false;
    }
    boolean isMapFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (map[i][j] == DOT_EMPTY)
                    return false;
        return true;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE)
            return false;
        if (map[y][x] == DOT_EMPTY)
            return true;
        return false;
    }
}