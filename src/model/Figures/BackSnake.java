package model.Figures;

import model.Block;
import model.Figure;

import java.awt.*;

public class BackSnake extends Figure {
    @Override
    public void create() {
        Figure.setInstance(this);
    }

    public BackSnake(){
        super();
        setSize(3, 2);
        setFill(Color.GREEN);
        add(new Block(super.x(), super.y() + 1, super.getFill(), this));
        add(new Block(super.x() + 1, super.y() + 1, super.getFill(), this));
        add(new Block(super.x() + 1, super.y(), super.getFill(), this));
        add(new Block(super.x() + 2, super.y(), super.getFill(), this));
        run();
    }

}
