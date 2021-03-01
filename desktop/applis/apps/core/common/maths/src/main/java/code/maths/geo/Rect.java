package code.maths.geo;
import code.util.CustList;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;


public final class Rect implements HasEdges, Displayable {

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

    
    public static Rect newRect(String _input) {
        StringList elts_ = StringUtil.splitStrings(_input, SEPARATOR);
        int left_ = NumberUtil.parseInt(elts_.first());
        int top_ = NumberUtil.parseInt(elts_.get(1));
        int width_ = NumberUtil.parseInt(elts_.get(2));
        int height_ = NumberUtil.parseInt(elts_.last());
        Rect r_ = new Rect();
        r_.left = left_;
        r_.top = top_;
        r_.width = width_;
        r_.height = height_;
        return r_;
    }

    @Override
    public CustList<Edge> getEdges() {
        CustList<Edge> l_ = new CustList<Edge>();
        CustPoint topLeft_ = new CustPoint(getLeft(), getTop());
        CustPoint topRight_ = new CustPoint(getRight(), getTop());
        CustPoint bottomRight_ = new CustPoint(getRight(), getBottom());
        CustPoint bottomLeft_  = new CustPoint(getLeft(), getBottom());
        l_.add(new Edge(topLeft_, topRight_));
        l_.add(new Edge(topRight_, bottomRight_));
        l_.add(new Edge(bottomRight_, bottomLeft_));
        l_.add(new Edge(bottomLeft_, topLeft_));
        return l_;
    }

    @Override
    public CustList<CustPoint> getPoints() {
        CustList<CustPoint> l_ = new CustList<CustPoint>();
        CustPoint topLeft_ = new CustPoint(getLeft(), getTop());
        CustPoint topRight_ = new CustPoint(getRight(), getTop());
        CustPoint bottomRight_ = new CustPoint(getRight(), getBottom());
        CustPoint bottomLeft_  = new CustPoint(getLeft(), getBottom());
        l_.add(topLeft_);
        l_.add(topRight_);
        l_.add(bottomRight_);
        l_.add(bottomLeft_);
        return l_;
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
        if (getLeft() > _pt.getXcoords()) {
            return false;
        }
        if (getRight() < _pt.getXcoords()) {
            return false;
        }
        if (getTop() > _pt.getYcoords()) {
            return false;
        }
        return getBottom() >= _pt.getYcoords();
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
        return getBottom() >= _r.getTop();
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

    public boolean eq(Rect _obj) {
        if (_obj.left != left) {
            return false;
        }
        if (_obj.top != top) {
            return false;
        }
        if (_obj.width != width) {
            return false;
        }
        return _obj.height == height;
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
