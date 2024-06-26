package code.minirts;

import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.minirts.rts.RtsDirection;




public final class RtsKeyPad extends AbsMetaLabelRts {

    private final RtsDirection direction;

    public RtsKeyPad(RtsDirection _direction, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        direction = _direction;
        setPreferredSize(new MetaDimension(32, 32));
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.YELLOW);
        _g.fillRect(0, 0, getWidth(), getHeight());
        _g.setColor(GuiConstants.BLACK);
        if (direction == RtsDirection.UP) {
            _g.fillPolygon(new int[]{0, getWidth()/2, getWidth()}, new int[]{getHeight()/2, 0, getHeight()/2}, 3);
        } else if (direction == RtsDirection.DOWN) {
            _g.fillPolygon(new int[]{0, getWidth()/2, getWidth()}, new int[]{getHeight()/2, getHeight(), getHeight()/2}, 3);
        } else if (direction == RtsDirection.LEFT) {
            _g.fillPolygon(new int[]{getWidth()/2, 0, getWidth()/2}, new int[]{0, getHeight()/2, getHeight()}, 3);
        } else {
            _g.fillPolygon(new int[]{getWidth()/2, getWidth(), getWidth()/2}, new int[]{0, getHeight()/2, getHeight()}, 3);
        }
    }
}
