package model.Figures;

import model.Block;
import model.Figure;

import java.awt.*;

public class Square extends Figure {
    @Override
    public void create() {
        Figure.setInstance(this);
    }

    public Square(){
        super();
        super.setSize(2, 2);
        super.setFill(Color.YELLOW);
        add(new Block(super.x(), super.y(), super.getFill(), this));
        add(new Block(super.x() + 1, super.y(), super.getFill(), this));
        add(new Block(super.x() + 1, super.y() + 1, super.getFill(), this));
        add(new Block(super.x(), super.y() + 1, super.getFill(), this));
        run();
    }
}
