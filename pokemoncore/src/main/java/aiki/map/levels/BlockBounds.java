package aiki.map.levels;
import code.util.CustList;
import code.util.ints.Displayable;

public class BlockBounds implements Displayable {

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
    public String display() {
        StringBuilder display_ = new StringBuilder();
        display_.append(xLeftTop).append(SEP_COORDS);
        display_.append(yLeftTop).append(SEP_PTS);
        display_.append(xRightTop).append(SEP_COORDS);
        display_.append(yRightTop).append(SEP_PTS);
        display_.append(xLeftBottom).append(SEP_COORDS);
        display_.append(yLeftBottom).append(SEP_PTS);
        display_.append(xRightBottom).append(SEP_COORDS);
        display_.append(yRightBottom).append(SEP_PTS);
        return display_.toString();
    }
}
