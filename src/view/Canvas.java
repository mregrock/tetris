package view;

import model.Desk;
import model.Figure;
import model.ScoreTable;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    private static Canvas instance = null;

    public static Canvas getInstance() {
        if (instance == null)
            instance = new Canvas();
        return instance;
    }


    private Canvas() {
        super(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Desk.getInstance().draw(g);
        Figure.getInstance().draw(g);
        ScoreTable.getInstance().draw(g);
    }

    public void setPos(float x, float y) {
        repaint();
    }

}