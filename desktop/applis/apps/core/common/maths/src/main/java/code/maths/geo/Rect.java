package code.maths.geo;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.Displayable;


public final class Rect implements HasEdges, Displayable {

    public static final int NB_POINTS = 4;
    private static final String SEPARATOR = ",";
    private Rate left = Rate.zero();
    private Rate top = Rate.zero();
    private Rate width = Rate.zero();
    private Rate height = Rate.zero();

    public Rect() {
    }

    public Rect(Rate _left, Rate _top, Rate _width, Rate _height) {
        left = _left;
        top = _top;
        width = _width;
        height = _height;
    }

    public static Rect newRect(String _input) {
        StringList elts_ = StringUtil.splitStrings(_input, SEPARATOR);
        Rate left_ = Rate.newRate(elts_.first());
        Rate top_ = Rate.newRate(elts_.get(1));
        Rate width_ = Rate.newRate(elts_.get(2));
        Rate height_ = Rate.newRate(elts_.last());
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
        RatePoint topLeft_ = new RatePoint(getLeft(), getTop());
        RatePoint topRight_ = new RatePoint(getRight(), getTop());
        RatePoint bottomRight_ = new RatePoint(getRight(), getBottom());
        RatePoint bottomLeft_  = new RatePoint(getLeft(), getBottom());
        l_.add(new Edge(topLeft_, topRight_));
        l_.add(new Edge(topRight_, bottomRight_));
        l_.add(new Edge(bottomRight_, bottomLeft_));
        l_.add(new Edge(bottomLeft_, topLeft_));
        return l_;
    }

    @Override
    public CustList<RatePoint> getPoints() {
        CustList<RatePoint> l_ = new CustList<RatePoint>();
        RatePoint topLeft_ = new RatePoint(getLeft(), getTop());
        RatePoint topRight_ = new RatePoint(getRight(), getTop());
        RatePoint bottomRight_ = new RatePoint(getRight(), getBottom());
        RatePoint bottomLeft_  = new RatePoint(getLeft(), getBottom());
        l_.add(topLeft_);
        l_.add(topRight_);
        l_.add(bottomRight_);
        l_.add(bottomLeft_);
        return l_;
    }

    public Rect intersect(Rect _r) {
        if (Rate.strGreater(getLeft(), _r.getRight())) {
            return new Rect();
        }
        if (Rate.strLower(getRight(),_r.getLeft())) {
            return new Rect();
        }
        if (Rate.strGreater(getTop(), _r.getBottom())) {
            return new Rect();
        }
        if (Rate.strLower(getBottom(), _r.getTop())) {
            return new Rect();
        }
        Rect r_ = new Rect();
        r_.left = max(getLeft(), _r.getLeft());
        r_.top = max(getTop(), _r.getTop());
        Rate right_ = min(getRight(), _r.getRight());
        Rate bottom_ = min(getBottom(), _r.getBottom());
        r_.width = Rate.plus(Rate.minus(right_, r_.getLeft()),Rate.one());
        r_.height = Rate.plus(Rate.minus(bottom_, r_.getTop()),Rate.one());
        return r_;
    }

    private static Rate min(Rate _one, Rate _two) {
        if (Rate.strGreater(_one, _two)) {
            return _two;
        }
        return _one;
    }
    private static Rate max(Rate _one, Rate _two) {
        if (Rate.strGreater(_one, _two)) {
            return _one;
        }
        return _two;
    }

    public boolean containsInside(RatePoint _pt) {
        if (Rate.strGreater(getLeft(), _pt.getXcoords())) {
            return false;
        }
        if (Rate.strLower(getRight(), _pt.getXcoords())) {
            return false;
        }
        if (Rate.strGreater(getTop(),_pt.getYcoords())) {
            return false;
        }
        return !Rate.strLower(getBottom(), _pt.getYcoords());
    }

    public boolean intersects(Rect _r) {
        if (Rate.strGreater(getLeft(), _r.getRight())) {
            return false;
        }
        if (Rate.strLower(getRight(), _r.getLeft())) {
            return false;
        }
        if (Rate.strGreater(getTop(), _r.getBottom())) {
            return false;
        }
        return !Rate.strLower(getBottom(), _r.getTop());
    }

    public boolean isEmpty() {
        return width.isZero() || height.isZero();
    }

    public boolean isValid() {
        return width.isZeroOrGt() && height.isZeroOrGt();
    }

    public Rate getLeft() {
        return left;
    }
    public Rate getRight() {
        return Rate.minus(Rate.plus(left, width),  Rate.one());
    }
    public void setLeft(Rate _left) {
        left = _left;
    }
    public Rate getTop() {
        return top;
    }
    public Rate getBottom() {
        return Rate.minus(Rate.plus(top, height),  Rate.one());
    }
    public void setTop(Rate _top) {
        top = _top;
    }
    public Rate getWidth() {
        return width;
    }
    public void setWidth(Rate _width) {
        width = _width;
    }
    public Rate getHeight() {
        return height;
    }
    public void setHeight(Rate _height) {
        height = _height;
    }

    public boolean eq(Rect _obj) {
        if (!_obj.left.eq(left)) {
            return false;
        }
        if (!_obj.top.eq(top)) {
            return false;
        }
        if (!_obj.width.eq(width)) {
            return false;
        }
        return _obj.height.eq(height);
    }
    @Override
    
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(left.toNumberString());
        str_.append(SEPARATOR);
        str_.append(top.toNumberString());
        str_.append(SEPARATOR);
        str_.append(width.toNumberString());
        str_.append(SEPARATOR);
        str_.append(height.toNumberString());
        return str_.toString();
    }
}
