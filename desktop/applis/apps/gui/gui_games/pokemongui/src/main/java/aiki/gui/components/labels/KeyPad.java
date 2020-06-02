package aiki.gui.components.labels;
import java.awt.Color;
import java.awt.Dimension;

import aiki.map.enums.Direction;
import code.gui.CustGraphics;
import code.gui.PaintableLabel;
import code.util.*;

public class KeyPad extends PaintableLabel {

    private Direction direction;

    public KeyPad(Direction _direction) {
        direction = _direction;
        setPreferredSize(new Dimension(32, 32));
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        _g.setColor(Color.YELLOW);
        _g.fillRect(0, 0, getWidth(), getHeight());
        _g.setColor(Color.BLACK);
        if (direction == Direction.UP) {
            _g.fillPolygon(Numbers.wrapIntArray(0, getWidth()/2, getWidth()), Numbers.wrapIntArray(getHeight()/2, 0, getHeight()/2), 3);
        } else if (direction == Direction.DOWN) {
            _g.fillPolygon(Numbers.wrapIntArray(0, getWidth()/2, getWidth()), Numbers.wrapIntArray(getHeight()/2, getHeight(), getHeight()/2), 3);
        } else if (direction == Direction.LEFT) {
            _g.fillPolygon(Numbers.wrapIntArray(getWidth()/2, 0, getWidth()/2), Numbers.wrapIntArray(0, getHeight()/2, getHeight()), 3);
        } else if (direction == Direction.RIGHT) {
            _g.fillPolygon(Numbers.wrapIntArray(getWidth()/2, getWidth(), getWidth()/2), Numbers.wrapIntArray(0, getHeight()/2, getHeight()), 3);
        }
    }
}
