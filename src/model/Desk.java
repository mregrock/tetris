package model;

import com.sun.tools.javac.Main;
import controller.Application;
import controller.GameState;
import view.Canvas;
import view.MainWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Desk implements Rectangle {
    private static Desk instance = null;

    public static Desk getInstance() {
        if (instance == null) {
            instance = new Desk();
        }
        return instance;
    }

    private RectangleThread thread;
    private Rectangle[][] children;

    public Desk() {
        children = new Rectangle[20][10];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                children[i][j] = null;
            }
        }
    }

    @Override
    public void run() {
        thread.start();
    }

    @Override
    public void stop() {
        thread.setActive(false);
    }

    @Override
    public void add(Rectangle child) {
        if (Figure.getInstance() == null){
            return;
        }
        if (Figure.getInstance().checkDown()){
            return;
        }
        int x = Figure.getInstance().x();
        int y = Figure.getInstance().y();
        int height = Figure.getInstance().getHeight();
        Application.getInstance().setGameState(GameState.WAIT);
        for (Rectangle block : child.children()) {
            children[block.y()][block.x()] = block;
            if (block.y() == 0){
                Application.getInstance().setGameState(GameState.GAME_OVER);
                return;
            }
        }
        Desk.getInstance().check(y, y + height - 1);
        Desk.getInstance().check(y, y + height - 1);

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(28, 28, 28));
        g.fillRect(0, 0, MainWindow.weight, MainWindow.height);
        g.setColor(new Color(255, 255, 255, 100));
        for (int y = 40; y < MainWindow.height; y += 41) {
            g.drawRect(0, y, MainWindow.weight, 1);
        }
        for (int x = 40; x < MainWindow.weight; x += 41) {
            g.drawRect(x, 0, 1, MainWindow.height);
        }
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 10; y++) {
                if (children[x][y] != null)
                    children[x][y].draw(g);
            }
        }
    }

    @Override
    public ArrayList<Rectangle> children() {
        return children();
    }

    public void check(int y_up, int y_down) {
        int low_row = -1;
        int count = 0;
        for (int i = y_up; i <= y_down; i++) {
            boolean check_row = true;
            for (int j = 0; j < 10; j++) {
                if (children[i][j] == null) {
                    check_row = false;
                }
            }
            if (check_row) {
                count += 100;
                low_row = i;
                for (int j = 0; j < 10; j++) {
                    children[i][j] = null;
                }
            }
        }
        ScoreTable.getInstance().changeScore(count);
        if (low_row != -1) {
            boolean flag = false;
            while (!flag) {
                flag = true;
                for (int i = y_down - 1; i >= 0; i--) {
                    for (int j = 0; j < 10; j++) {
                        if (children[i][j] != null) {
                            if (children[i][j].checkDown()) {
                                flag = false;
                                children[i][j].move(0, 1);
                                children[i + 1][j] = children[i][j];
                                children[i][j] = null;
                            }
                        }

                    }
                }
            }
        }
    }

    public Rectangle[][] getChildren() {
        return children;
    }

    @Override
    public RectangleThread thread() {
        return thread;
    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public int x() {
        return 0;
    }

    @Override
    public int y() {
        return 0;
    }

    @Override
    public void move_left() {

    }

    @Override
    public void move_right() {

    }

    @Override
    public boolean checkDown() {
        return true;
    }

    @Override
    public boolean checkLeft() {
        return true;
    }

    @Override
    public boolean checkRight() {
        return true;
    }

    @Override
    public void turn(int d_x, int d_y) {

    }

    @Override
    public boolean checkTurn(int d_x, int d_y) {
        return true;
    }
}
