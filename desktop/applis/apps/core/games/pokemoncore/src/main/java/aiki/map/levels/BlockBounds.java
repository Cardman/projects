package aiki.map.levels;
import code.util.core.IndexConstants;

public class BlockBounds {

    public static final int CST_INVALIDATE = IndexConstants.INDEX_NOT_FOUND_ELT;
    private int xLeftTop;
    private int yLeftTop;
    private int xRightTop;
    private int yRightTop;
    private int xLeftBottom;
    private int yLeftBottom;
    private int xRightBottom;
    private int yRightBottom;
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
    public int getxLeftTop() {
        return xLeftTop;
    }
    public void setxLeftTop(int _xLeftTop) {
        xLeftTop = _xLeftTop;
    }
    public int getyLeftTop() {
        return yLeftTop;
    }
    public void setyLeftTop(int _yLeftTop) {
        yLeftTop = _yLeftTop;
    }
    public int getxRightTop() {
        return xRightTop;
    }
    public void setxRightTop(int _xRightTop) {
        xRightTop = _xRightTop;
    }
    public int getyRightTop() {
        return yRightTop;
    }
    public void setyRightTop(int _yRightTop) {
        yRightTop = _yRightTop;
    }
    public int getxLeftBottom() {
        return xLeftBottom;
    }
    public void setxLeftBottom(int _xLeftBottom) {
        xLeftBottom = _xLeftBottom;
    }
    public int getyLeftBottom() {
        return yLeftBottom;
    }
    public void setyLeftBottom(int _yLeftBottom) {
        yLeftBottom = _yLeftBottom;
    }
    public int getxRightBottom() {
        return xRightBottom;
    }
    public void setxRightBottom(int _xRightBottom) {
        xRightBottom = _xRightBottom;
    }
    public int getyRightBottom() {
        return yRightBottom;
    }
    public void setyRightBottom(int _yRightBottom) {
        yRightBottom = _yRightBottom;
    }

}
