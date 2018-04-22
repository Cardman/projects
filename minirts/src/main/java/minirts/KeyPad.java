package minirts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import minirts.rts.Direction;
import code.gui.PaintableLabel;

public class KeyPad extends PaintableLabel {

    private Direction direction;

    public KeyPad(Direction _direction) {
        direction = _direction;
        setPreferredSize(new Dimension(32, 32));
    }

    @Override
    public void paintComponent(Graphics _g) {
        _g.setColor(Color.YELLOW);
        _g.fillRect(0, 0, getWidth(), getHeight());
        _g.setColor(Color.BLACK);
        if (direction == Direction.UP) {
            _g.fillPolygon(new int[]{0, getWidth()/2, getWidth()}, new int[]{getHeight()/2, 0, getHeight()/2}, 3);
        } else if (direction == Direction.DOWN) {
            _g.fillPolygon(new int[]{0, getWidth()/2, getWidth()}, new int[]{getHeight()/2, getHeight(), getHeight()/2}, 3);
        } else if (direction == Direction.LEFT) {
            _g.fillPolygon(new int[]{getWidth()/2, 0, getWidth()/2}, new int[]{0, getHeight()/2, getHeight()}, 3);
        } else if (direction == Direction.RIGHT) {
            _g.fillPolygon(new int[]{getWidth()/2, getWidth(), getWidth()/2}, new int[]{0, getHeight()/2, getHeight()}, 3);
        }
    }
}
