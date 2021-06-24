package aiki.gui.components.labels;
import java.awt.Color;
import java.awt.Dimension;

import aiki.map.enums.Direction;
import code.gui.PaintableLabel;
import code.gui.images.AbstractImage;
import code.util.core.NumberUtil;

public class KeyPad extends PaintableLabel {

    private Direction direction;

    public KeyPad(Direction _direction) {
        direction = _direction;
        setPreferredSize(new Dimension(32, 32));
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(Color.YELLOW);
        _g.fillRect(0, 0, getWidth(), getHeight());
        _g.setColor(Color.BLACK);
        if (direction == Direction.UP) {
            _g.fillPolygon(NumberUtil.wrapIntArray(0, getWidth()/2, getWidth()), NumberUtil.wrapIntArray(getHeight()/2, 0, getHeight()/2), 3);
        } else if (direction == Direction.DOWN) {
            _g.fillPolygon(NumberUtil.wrapIntArray(0, getWidth()/2, getWidth()), NumberUtil.wrapIntArray(getHeight()/2, getHeight(), getHeight()/2), 3);
        } else if (direction == Direction.LEFT) {
            _g.fillPolygon(NumberUtil.wrapIntArray(getWidth()/2, 0, getWidth()/2), NumberUtil.wrapIntArray(0, getHeight()/2, getHeight()), 3);
        } else if (direction == Direction.RIGHT) {
            _g.fillPolygon(NumberUtil.wrapIntArray(getWidth()/2, getWidth(), getWidth()/2), NumberUtil.wrapIntArray(0, getHeight()/2, getHeight()), 3);
        }
    }
}
