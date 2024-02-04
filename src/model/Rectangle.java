package model;

import java.awt.*;
import java.util.ArrayList;

public interface Rectangle {
    public void run();
    public void stop();
    public void draw(Graphics g);
    public void add(Rectangle child);
    public ArrayList<Rectangle> children();
    public RectangleThread thread();
    public void move(int x, int y);
    public int x();
    public int y();
    public void move_left();
    public void move_right();
    public boolean checkDown();
    public boolean checkLeft();
    public boolean checkRight();
    public void turn(int d_x, int d_y);
    public boolean checkTurn(int d_x, int d_y);
}
