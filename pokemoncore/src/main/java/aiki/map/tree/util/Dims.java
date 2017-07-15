package aiki.map.tree.util;
import code.util.Numbers;
import code.util.ints.Equallable;

public final class Dims implements Equallable<Dims> {

    private short width;

    private short height;

    public Dims() {
    }

    public Dims(int _width, int _height) {
        width = (short) _width;
        height = (short) _height;
    }

    public short getWidth() {
        return width;
    }

    public void setWidth(short _width) {
        width = _width;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short _height) {
        height = _height;
    }

    @Override
    public boolean eq(Dims _g) {
        if (!Numbers.eq(height, _g.height)) {
            return false;
        }
        if (!Numbers.eq(width, _g.width)) {
            return false;
        }
        return true;
    }
}
