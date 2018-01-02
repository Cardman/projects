package minirts.rts;

import code.util.ints.Displayable;
import code.util.ints.Equallable;



public final class Rect implements Equallable<Rect>, Displayable {

    public static final int NB_POINTS = 4;
    private static final String SEPARATOR = ",";
    private int left;
    private int top;
    private int width;
    private int height;

    public Rect() {
    }

    public Rect(int _left, int _top, int _width, int _height) {
        left = _left;
        top = _top;
        width = _width;
        height = _height;
    }

    public Rect intersect(Rect _r) {
        if (getLeft() > _r.getRight()) {
            return new Rect();
        }
        if (getRight() < _r.getLeft()) {
            return new Rect();
        }
        if (getTop() > _r.getBottom()) {
            return new Rect();
        }
        if (getBottom() < _r.getTop()) {
            return new Rect();
        }
        Rect r_ = new Rect();
        r_.left = Math.max(getLeft(), _r.getLeft());
        r_.top = Math.max(getTop(), _r.getTop());
        int right_ = Math.min(getRight(), _r.getRight());
        int bottom_ = Math.min(getBottom(), _r.getBottom());
        r_.width = right_ - r_.getLeft() + 1;
        r_.height = bottom_ - r_.getTop() + 1;
        return r_;
    }

    public boolean containsInside(CustPoint _pt) {
        if (getLeft() > _pt.getX()) {
            return false;
        }
        if (getRight() < _pt.getX()) {
            return false;
        }
        if (getTop() > _pt.getY()) {
            return false;
        }
        if (getBottom() < _pt.getY()) {
            return false;
        }
        return true;
    }

    public boolean intersects(Rect _r) {
        if (getLeft() > _r.getRight()) {
            return false;
        }
        if (getRight() < _r.getLeft()) {
            return false;
        }
        if (getTop() > _r.getBottom()) {
            return false;
        }
        if (getBottom() < _r.getTop()) {
            return false;
        }
        return true;
    }

    public boolean isEmpty() {
        return width == 0 || height == 0;
    }

    public boolean isValid() {
        return width >= 0 && height >= 0;
    }

    public int getLeft() {
        return left;
    }
    public int getRight() {
        return left + width - 1;
    }
    public void setLeft(int _left) {
        left = _left;
    }
    public int getTop() {
        return top;
    }
    public int getBottom() {
        return top + height - 1;
    }
    public void setTop(int _top) {
        top = _top;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int _width) {
        width = _width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int _height) {
        height = _height;
    }
    @Override
    public boolean eq(Rect _r) {
        if (_r.left != left) {
            return false;
        }
        if (_r.top != top) {
            return false;
        }
        if (_r.width != width) {
            return false;
        }
        if (_r.height != height) {
            return false;
        }
        return true;
    }
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(left);
        str_.append(SEPARATOR);
        str_.append(top);
        str_.append(SEPARATOR);
        str_.append(width);
        str_.append(SEPARATOR);
        str_.append(height);
        return str_.toString();
    }
}
