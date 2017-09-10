package aiki.map.levels;
import aiki.DataBase;
import code.util.CustList;

public class BlockBounds {

    public static final short INVALIDATE = CustList.INDEX_NOT_FOUND_ELT;
    public static final String SEP_COORDS = ",";
    public static final String SEP_PTS = ";";
    private short xLeftTop;
    private short yLeftTop;
    private short xRightTop;
    private short yRightTop;
    private short xLeftBottom;
    private short yLeftBottom;
    private short xRightBottom;
    private short yRightBottom;
    public void invalidate() {
        boolean invalidate_ = false;
        if (xLeftTop>xRightTop) {
            invalidate_ = true;
        }
        if (yLeftTop>yLeftBottom) {
            invalidate_ = true;
        }
        if (invalidate_) {
            xLeftTop = INVALIDATE;
            yLeftTop = INVALIDATE;
            xRightTop = INVALIDATE;
            yRightTop = INVALIDATE;
            xLeftBottom = INVALIDATE;
            yLeftBottom = INVALIDATE;
            xRightBottom = INVALIDATE;
            yRightBottom = INVALIDATE;
        }
    }
    public boolean isValid() {
        return xLeftTop != INVALIDATE;
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

    @Override
    public String toString() {
        String display_ = DataBase.EMPTY_STRING;
        display_ += xLeftTop +SEP_COORDS;
        display_ += yLeftTop +SEP_PTS;
        display_ += xRightTop +SEP_COORDS;
        display_ += yRightTop +SEP_PTS;
        display_ += xLeftBottom +SEP_COORDS;
        display_ += yLeftBottom +SEP_PTS;
        display_ += xRightBottom +SEP_COORDS;
        display_ += yRightBottom +SEP_PTS;
        return display_;
    }
}
