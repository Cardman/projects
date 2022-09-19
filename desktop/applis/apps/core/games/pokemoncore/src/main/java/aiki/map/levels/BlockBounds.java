package aiki.map.levels;
import code.util.core.IndexConstants;

public class BlockBounds {

    public static final short CST_INVALIDATE = IndexConstants.INDEX_NOT_FOUND_ELT;
    private short xLeftTop;
    private short yLeftTop;
    private short xRightTop;
    private short yRightTop;
    private short xLeftBottom;
    private short yLeftBottom;
    private short xRightBottom;
    private short yRightBottom;
    public void invalidate() {
        boolean invalidate_ = xLeftTop > xRightTop;
        if (yLeftTop>yLeftBottom) {
            invalidate_ = true;
        }
        if (invalidate_) {
            xLeftTop = CST_INVALIDATE;
            yLeftTop = CST_INVALIDATE;
            xRightTop = CST_INVALIDATE;
            yRightTop = CST_INVALIDATE;
            xLeftBottom = CST_INVALIDATE;
            yLeftBottom = CST_INVALIDATE;
            xRightBottom = CST_INVALIDATE;
            yRightBottom = CST_INVALIDATE;
        }
    }
    public boolean isValid() {
        return xLeftTop != CST_INVALIDATE;
    }
    public short getxLeftTop() {
        return xLeftTop;
    }
    public void setxLeftTop(short _xLeftTop) {
        xLeftTop = _xLeftTop;
    }
    public short getyLeftTop() {
        return yLeftTop;
    }
    public void setyLeftTop(short _yLeftTop) {
        yLeftTop = _yLeftTop;
    }
    public short getxRightTop() {
        return xRightTop;
    }
    public void setxRightTop(short _xRightTop) {
        xRightTop = _xRightTop;
    }
    public short getyRightTop() {
        return yRightTop;
    }
    public void setyRightTop(short _yRightTop) {
        yRightTop = _yRightTop;
    }
    public short getxLeftBottom() {
        return xLeftBottom;
    }
    public void setxLeftBottom(short _xLeftBottom) {
        xLeftBottom = _xLeftBottom;
    }
    public short getyLeftBottom() {
        return yLeftBottom;
    }
    public void setyLeftBottom(short _yLeftBottom) {
        yLeftBottom = _yLeftBottom;
    }
    public short getxRightBottom() {
        return xRightBottom;
    }
    public void setxRightBottom(short _xRightBottom) {
        xRightBottom = _xRightBottom;
    }
    public short getyRightBottom() {
        return yRightBottom;
    }
    public void setyRightBottom(short _yRightBottom) {
        yRightBottom = _yRightBottom;
    }

}
