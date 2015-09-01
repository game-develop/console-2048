package borsch.console;

public class Main {

    public static void main(String[] args) {
        Game2048 game = new Game2048();
        int i = 10;
        while (game.isRunning()){
            game.updateTable();
            game.action();
            game.random();
        }
    }

}
