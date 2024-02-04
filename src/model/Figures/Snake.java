package model.Figures;

import model.Block;
import model.Figure;

import java.awt.*;

public class Snake extends Figure {
    @Override
    public void create() {
        Figure.setInstance(this);
    }

    public Snake(){
        super();
        setSize(3, 2);
        setFill(Color.RED);
        add(new Block(super.x(), super.y(), super.getFill(), this));
        add(new Block(super.x() + 1, super.y(), super.getFill(), this));
        add(new Block(super.x() + 1, super.y() + 1, super.getFill(), this));
        add(new Block(super.x() + 2, super.y() + 1, super.getFill(), this));
        run();
    }
}
