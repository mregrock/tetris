package view;

import controller.KeyReleasedListener;
import controller.OnKeyListener;
import model.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {
    public final static int height = 859;
    public final static int weight = 409;
    public MainWindow() {
        super();
        setTitle("Tetris");
        setSize(weight + 100, height);
        setLocation(600, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = Canvas.getInstance();
        add(canvas, BorderLayout.CENTER);
        Canvas.getInstance().repaint();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                OnKeyListener.actionPerformed(e);
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                KeyReleasedListener.actionPerformed(e);
            }
        });
    }
}
