package model;

import model.Figures.*;

public class FigureFactory {
    public void createFigure(FigureType type) {
        switch (type) {
            case PYRAMID:
                Figure.setInstance(new Pyramid());
                break;
            case LONG_BEAM:
                Figure.setInstance(new LongBeam());
                break;
            case SNAKE:
                Figure.setInstance(new Snake());
                break;
            case BACK_SNAKE:
                Figure.setInstance(new BackSnake());
                break;
            case SQUARE:
                Figure.setInstance(new Square());
                break;
            case LETTER:
                Figure.setInstance(new Letter());
                break;
            case BACK_LETTER:
                Figure.setInstance(new BackLetter());
                break;
        }
    }
}
