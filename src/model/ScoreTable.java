package model;

import java.awt.*;

public class ScoreTable {
    private static ScoreTable instance;
    private String label;
    int count = 0;
    public ScoreTable(){
        label = "0";
    }
    public static ScoreTable getInstance(){
        if (instance == null){
            instance = new ScoreTable();
        }
        return instance;
    }
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g2.drawString(label, 420, 100);
    }
    public void changeScore(int score){
        count += score;
        label = Integer.toString(count);
    }
    public void setGameOver(){
        label = "GG";
    }
}
