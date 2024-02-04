package model;

import java.awt.*;

import controller.Application;
import controller.FigureState;
import controller.GameState;
import view.Canvas;

public class RectangleThread extends Thread {
    Rectangle parent;
    boolean isActive = true;

    RectangleThread(Rectangle parent) {
        super();
        Canvas.getInstance().repaint();
        this.parent = parent;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                if (Application.getInstance().getGameState() == GameState.SPEED_UP) {
                    Thread.sleep(100);
                } else {
                    Thread.sleep(1000);
                }
                //if (Figure.getInstance().getFigureState() == FigureState.LEFT){
                //    parent.move_left();
                //}
                //if (Figure.getInstance().getFigureState() == FigureState.RIGHT){
                // parent.move_right();
                //}
                //if (Figure.getInstance().getFigureState() == FigureState.DEFAULT){
                if (Figure.getInstance().checkDown()) {
                    Figure.getInstance().move(0, 1);
                }
                else{
                    isActive = false;
                }
                //}
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Desk.getInstance().add(Figure.getInstance());
        //try {
        //Thread.sleep(200);
        //} catch (InterruptedException e) {
        //   e.printStackTrace();
        //}
        if (Application.getInstance().getGameState() != GameState.GAME_OVER){
            Application.getInstance().setGameState(GameState.READY);
        }
        else{
            return;
        }
        Figure.setInstance(null);

    }
}
