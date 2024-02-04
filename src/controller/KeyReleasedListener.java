package controller;

import model.Figure;

import java.awt.event.KeyEvent;

public class KeyReleasedListener {
    public static void actionPerformed(KeyEvent event) {
        String key = event.getKeyText(event.getKeyCode());
        GameState newState = null;
        GameState state = Application.getInstance().getGameState();
        if (state == GameState.WAIT || state == GameState.READY){
            return;
        }
        if (key.equals("Down")) {
            newState = GameState.DEFAULT;
            Application.getInstance().setGameState(newState);
        }
        if (key.equals("Left") || key.equals("Right")){
            Figure.getInstance().setFigureState(FigureState.DEFAULT);
        }
    }
}
