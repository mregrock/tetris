package model;

import view.MainWindow;

import java.awt.*;
import java.util.ArrayList;

public class Block implements Rectangle {
    private int x, y;
    private Color fill;
    private Figure parent;
    private boolean isActive;

    @Override
    public void run() {

    }

    public Block(int x, int y, Color fill, Figure parent) {
        this.x = x;
        this.y = y;
        this.fill = fill;
        this.isActive = true;
        this.parent = parent;
    }

    boolean checkCollision() {
        return (Desk.getInstance().getChildren()[y + 1][x] != null);
    }

    boolean checkCollisionLeft() {
        return (Desk.getInstance().getChildren()[y][x - 1] != null);
    }

    boolean checkCollisionRight() {
        return (Desk.getInstance().getChildren()[y][x + 1] != null);
    }
    boolean checkCollisionsTurn(int x, int y){
        return (Desk.getInstance().getChildren()[y][x] == null);
    }
    @Override
    public void stop() {

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(this.fill);
        g2.fillRect(x * 41, y * 41, 40, 40);
    }

    @Override
    public void add(Rectangle child) {

    }

    @Override
    public ArrayList<Rectangle> children() {
        return null;
    }

    @Override
    public RectangleThread thread() {
        return null;
    }

    @Override
    public void move(int x, int y) {
        //if (y > 0 && (this.y + 1 == 20 || checkCollision()) && isActive) {
            //for (Rectangle child : this.parent.children()) {
               // this.isActive = false;
           // }
            //this.parent.setStop(true);
        //}
        this.x += x;
        this.y += y;
    }

    public void move_left() {
        this.x += -1;
    }

    public void move_right() {
        this.x += 1;
    }

    @Override
    public boolean checkDown() {
        return this.y + 1 != 20 && !checkCollision();
    }

    @Override
    public boolean checkLeft() {
        return this.x != 0 && !checkCollisionLeft();
    }

    @Override
    public boolean checkRight() {
        return this.x + 1 != 10 && !checkCollisionRight();
    }

    @Override
    public void turn(int d_x, int d_y) {
        int temp_y = this.y - parent.y();
        int temp_x = this.x - parent.x();
        int new_x = parent.x() + d_x;
        int new_y = parent.y() + d_y;
        this.y = new_y + temp_x;
        this.x = new_x + (parent.getHeight() - 1 - (temp_y));
    }

    @Override
    public boolean checkTurn(int d_x, int d_y) {
        int temp_y = this.y - parent.y();
        int temp_x = this.x - parent.x();
        int new_x = parent.x() + d_x;
        int new_y = parent.y() + d_y;
        int ans_y = new_y + temp_x;
        int ans_x = new_x + (parent.getHeight() - 1 - (temp_y));
        if (ans_x >= 10 || ans_x < 0 || ans_y >= 20 || ans_y < 0 || !checkCollisionsTurn(ans_x, ans_y)){
            return false;
        }
        return true;
    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }
}
