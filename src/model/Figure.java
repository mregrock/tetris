package model;

import java.awt.*;
import java.util.ArrayList;

import controller.Application;
import controller.FigureState;
import controller.GameState;
import view.Canvas;
import view.MainWindow;

import static controller.FigureState.*;
import static view.MainWindow.weight;

public abstract class Figure implements Rectangle {
    private RectangleThread thread;

    public void setFill(Color fill) {
        this.fill = fill;
    }

    private ArrayList<Rectangle> children;
    private static Figure instance = null;
    private final static FigureFactory figureFactory = new FigureFactory();

    public static void setInstance(Figure instance) {
        if (Figure.instance != null){
            Figure.instance.thread.interrupt();
            Figure.instance.thread = null;
        }
        Figure.instance = instance;
    }

    public boolean isStop() {
        return stop;
    }

    public static Figure getInstance() {
        if (Application.getInstance().getGameState() == GameState.WAIT) {
            return null;
        }
        if (instance == null) {
            double chance = Math.random();
            double chance_need = 1.0 / 7.0;
            if (chance <= chance_need) {
                figureFactory.createFigure(FigureType.PYRAMID);
            } else {
                if (chance <= chance_need * 2) {
                    figureFactory.createFigure(FigureType.LONG_BEAM);
                } else {
                    if (chance <= chance_need * 3) {
                        figureFactory.createFigure(FigureType.SNAKE);
                    } else {
                        if (chance <= chance_need * 4) {
                            figureFactory.createFigure(FigureType.BACK_SNAKE);
                        } else {
                            if (chance <= chance_need * 5) {
                                figureFactory.createFigure(FigureType.SQUARE);
                            } else {
                                if (chance <= chance * 6) {
                                    figureFactory.createFigure(FigureType.LETTER);
                                }
                                else{
                                    figureFactory.createFigure(FigureType.BACK_LETTER);
                                }
                            }
                        }
                    }
                }
            }
        }
        return instance;
    }

    public abstract void create();

    private int x, y, width, height;
    private boolean stop;
    private FigureState figureState;

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    Color fill;

    public Color getFill() {
        return fill;
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
    public void draw(Graphics g) {
        for (Rectangle figure : children) {
            figure.draw(g);
        }
    }

    @Override
    public void add(Rectangle child) {
        children.add(child);
    }

    public int getWidth() {
        return width;
    }

    public FigureState getFigureState() {
        return figureState;
    }

    public int getHeight() {
        return height;
    }

    public void setFigureState(FigureState figureState) {
        this.figureState = figureState;
    }

    public Figure() {
        children = new ArrayList<>();
        thread = new RectangleThread(this);
        this.x = 3;
        this.y = 0;
        this.stop = false;
        this.figureState = FigureState.DEFAULT;
        //add(new Block(x, y, fill, this));
        //add(new Block(x + 1, y, fill, this));
        //add(new Block(x + 2, y, fill, this));
        //add(new Block(x + 1, y + 1, fill, this));
        //this.run();
    }

    @Override
    public ArrayList<Rectangle> children() {
        return children;
    }

    @Override
    public RectangleThread thread() {
        return thread;
    }

    @Override
    public void move(int x, int y) {
        for (Rectangle child : children) {
            child.move(x, y);
        }
        this.x += x;
        this.y += y;
        Canvas.getInstance().repaint();
    }

    @Override
    public void move_left() {
        if (!checkLeft()) {
            return;
        }
        for (Rectangle child : children) {
            child.move_left();
        }
        this.x -= 1;
        Canvas.getInstance().repaint();

    }

    @Override
    public void move_right() {
        if (!checkRight()) {
            return;
        }
        for (Rectangle child : children) {
            child.move_right();
        }
        this.x += 1;
        Canvas.getInstance().repaint();
    }

    @Override
    public boolean checkDown() {
        boolean flag = true;
        for (Rectangle child : children) {
            flag = flag & child.checkDown();
        }
        return flag;
    }

    @Override
    public boolean checkLeft() {
        boolean flag = true;
        for (Rectangle child : children) {
            flag = flag & child.checkLeft();
        }
        return flag;
    }

    @Override
    public boolean checkRight() {
        boolean flag = true;
        for (Rectangle child : children) {
            flag = flag & child.checkRight();
        }
        return flag;
    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }

    @Override
    public void turn(int d_x, int d_y) {
        FigureState predState = RIGHT;
        switch (figureState) {
            case DEFAULT:
                d_y -= 1;
                figureState = FigureState.LEFT;
                predState = FigureState.DEFAULT;
                break;
            case LEFT:
                figureState = FigureState.UP;
                predState = FigureState.LEFT;
                break;
            case UP:
                d_x += 1;
                figureState = FigureState.RIGHT;
                predState = FigureState.UP;
                break;
            case RIGHT:
                d_y += 1;
                d_x -= 1;
                figureState = DEFAULT;
                predState = FigureState.RIGHT;
                break;
        }
        if (!checkTurn(d_x, d_y)) {
            figureState = predState;
            return;
        }
        for (Rectangle child : children) {
            child.turn(d_x, d_y);
        }
        y += d_y;
        x += d_x;
        int temp = this.width;
        this.width = height;
        this.height = temp;
        Canvas.getInstance().repaint();
    }

    @Override
    public boolean checkTurn(int d_x, int d_y) {
        boolean flag = true;
        for (Rectangle child : children) {
            flag &= child.checkTurn(d_x, d_y);
        }
        return flag;
    }
}
