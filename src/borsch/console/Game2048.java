package borsch.console;

import java.util.Random;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: borsch
 * Date: 8/31/2015
 * Time: 6:21 PM
 */
public class Game2048 {

    private final int FIELD_SIZE;

    private final int[][] points;
    private Scanner scanner;
    private Random random;
    private boolean running;

    public Game2048(){
        this(4);
    }

    public Game2048(int gridSize){
        this.FIELD_SIZE = gridSize;
        this.scanner = new Scanner(System.in);
        this.running = true;
        this.points = new int[FIELD_SIZE][FIELD_SIZE];
        this.random = new Random();
        random();
    }

    public void action(){
        String input = scanner.next();
        if(input.equalsIgnoreCase("w")){
            moveUp();
        } else if(input.equalsIgnoreCase("s")){
            moveDown();
        } else if(input.equalsIgnoreCase("d")){
            moveRight();
        } else if(input.equalsIgnoreCase("a")){
            moveLeft();
        }
    }

    public void updateTable(){
        pln();
        for (int i = 0; i < FIELD_SIZE * SPACE * 2; ++i)
            p("-");
        pln();
        for(int i = 0; i < FIELD_SIZE; ++i){
            pln();
            for(int j = 0; j < FIELD_SIZE; ++j){
                pf("%" + SPACE + "s |", points[i][j] == 0 ? "" : points[i][j]);
            }
            pln();
            if(i < FIELD_SIZE - 1) {
                for (int j = 0; j < FIELD_SIZE * SPACE * 1.3; ++j) {
                    p("-");
                }
            }
        }
    }

    private void pf(String format, Object ... args){
        System.out.printf(format, args);
    }

    private void p(String s){
        System.out.print(s);
    }

    private void pln(){
        System.out.println();
    }

    public boolean isRunning(){
        running = checkIfGameIsRunning();
        return running;
    }

    private boolean checkIfGameIsRunning(){
        for(int i = 0; i < FIELD_SIZE - 1; ++i){
            for(int j = 0; j < FIELD_SIZE; ++j){
                if(points[i][j] == points[i+1][j])
                    return true;

                if(points[i][j] == 0)
                    return true;
            }
        }

        for(int i = 0; i < FIELD_SIZE; ++i){
            for(int j = 0; j < FIELD_SIZE - 1; ++j){
                if(points[i][j] == points[i][j+1])
                    return true;
            }
        }
        return false;
    }

    private void moveUp(){
        for(int i = 0; i < FIELD_SIZE; ++i){
            for(int j = 0; j < FIELD_SIZE; ++j){
                if(points[i][j] == 0)
                    continue;

                int zero = i;
                while(zero > 0 && points[zero-1][j] == 0){
                    --zero;
                }

                int swapIndex = zero > 0 ? zero - 1 : 0;

                if(points[swapIndex][j] == points[i][j] && swapIndex != i){
                    points[swapIndex][j] *= 2;
                    points[i][j] = 0;
                } else if(points[zero][j] == 0){
                    points[zero][j] = points[i][j];
                    points[i][j] = 0;
                }
            }
        }
    }

    private void moveDown(){
        for(int i = FIELD_SIZE - 1; i >= 0; --i){
            for(int j = 0; j < FIELD_SIZE; ++j){
                if(points[i][j] == 0)
                    continue;

                int zero = i;
                while(zero < FIELD_SIZE - 1 && points[zero+1][j] == 0){
                    ++zero;
                }

                int swapIndex = zero < FIELD_SIZE - 1 ? zero + 1 : FIELD_SIZE - 1;

                if(points[swapIndex][j] == points[i][j] && swapIndex != i){
                    points[swapIndex][j] *= 2;
                    points[i][j] = 0;
                } else if(points[zero][j] == 0){
                    points[zero][j] = points[i][j];
                    points[i][j] = 0;
                }
            }
        }
    }

    private void moveRight() {
        for(int i = 0; i < FIELD_SIZE; ++i){
            for(int j = FIELD_SIZE - 1; j >= 0; --j){
                if(points[i][j] == 0)
                    continue;

                int zero = j;
                while(zero < FIELD_SIZE - 1 && points[i][zero+1] == 0){
                    ++zero;
                }

                int swapIndex = zero < FIELD_SIZE - 1 ? zero + 1 : FIELD_SIZE - 1;

                if(points[i][swapIndex] == points[i][j] && swapIndex != j){
                    points[i][swapIndex] *= 2;
                    points[i][j] = 0;
                } else if(points[i][zero] == 0){
                    points[i][zero] = points[i][j];
                    points[i][j] = 0;
                }
            }
        }
    }

    private void moveLeft(){
        for(int i = 0; i < FIELD_SIZE; ++i){
            for(int j = 0; j < FIELD_SIZE; ++j){
                if(points[i][j] == 0)
                    continue;

                int zero = j;
                while(zero > 0 && points[i][zero-1] == 0){
                    --zero;
                }

                int swapIndex = zero > 0 ? zero - 1 : 0;

                if(points[i][swapIndex] == points[i][j] && swapIndex != j){
                    points[i][swapIndex] *= 2;
                    points[i][j] = 0;
                } else if(points[i][zero] == 0){
                    points[i][zero] = points[i][j];
                    points[i][j] = 0;
                }
            }
        }
    }

    public void random(){
        boolean end = false;
        int i = 0, j = 0;
        while(!end){
            i = random.nextInt(4);
            j = random.nextInt(4);
            if(points[i][j] == 0)
                end = true;
        }
        if(Math.random()/2 > 0.33){
            points[i][j] = FOUR;
        } else {
            points[i][j] = TWO;
        }
    }

    private static final int
            TWO = 2,
            FOUR = 4,
            SPACE = 6;

}
