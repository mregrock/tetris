package controller;

import model.Figure;
import model.ScoreTable;
import view.Canvas;
import view.MainWindow;

public class Application extends Thread {
    private static Application instance = null;

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    private GameState gameState = GameState.READY;

    public GameState getGameState() {
        return gameState;
    }

    public Application() {
        MainWindow window = new MainWindow();
        window.setVisible(true);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void run() {
        while (gameState != GameState.GAME_OVER) {
            try {
                if (Application.getInstance().getGameState() == GameState.READY) {
                    Application.getInstance().setGameState(GameState.DEFAULT);
                    Figure.getInstance();
                }
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        ScoreTable.getInstance().setGameOver();
        Canvas.getInstance().repaint();
    }
}
