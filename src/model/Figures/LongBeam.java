package model.Figures;

import model.Block;
import model.Figure;

import java.awt.*;

public class LongBeam extends Figure {

    @Override
    public void create() {
        Figure.setInstance(this);
    }

    public LongBeam(){
        super();
        setSize(4, 1);
        setFill(Color.CYAN);
        add(new Block(super.x(), super.y(), super.getFill(), this));
        add(new Block(super.x() + 1, super.y(), super.getFill(), this));
        add(new Block(super.x() + 2, super.y(), super.getFill(), this));
        add(new Block(super.x() + 3, super.y(), super.getFill(), this));
        run();
    }
}
