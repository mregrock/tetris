package controller;

import model.Figure;

import java.awt.event.KeyEvent;

public class OnKeyListener {
    public static void actionPerformed(KeyEvent event) {
        String key = event.getKeyText(event.getKeyCode());
        GameState newState = null;
        GameState state = Application.getInstance().getGameState();
        if (state == GameState.WAIT || state == GameState.READY){
            return;
        }
        if (key.equals("Down")) {
            newState = GameState.SPEED_UP;
            Application.getInstance().setGameState(newState);
        }
        if (key.equals("Left")) {
            if (!Figure.getInstance().isStop()) {
                Figure.getInstance().move_left();
                Figure.getInstance().setFigureState(FigureState.LEFT);
            }
        }
        if (key.equals("Right")) {
            if (!Figure.getInstance().isStop()) {
                Figure.getInstance().move_right();
                Figure.getInstance().setFigureState(FigureState.RIGHT);
            }
        }
        if (key.equals("Up")){
            if (!Figure.getInstance().isStop()){
                Figure.getInstance().turn(0, 0);
            }
        }
    }
}
