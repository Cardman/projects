package code.maths.geo;
import code.maths.Rate;
import code.maths.matrix.Matrix;
import code.maths.matrix.Vect;
import code.util.core.IndexConstants;

public final class CustLine {

    private Rate xRate = Rate.zero();

    private Rate yRate = Rate.zero();

    private Rate cst = Rate.zero();

    private boolean defined;

    public CustLine() {
    }

    public CustLine(Rate _xRate, Rate _yRate, Rate _cst) {
        xRate = _xRate;
        yRate = _yRate;
        cst = _cst;
        if (!xRate.isZero() || !yRate.isZero()) {
            defined = true;
        }
    }

    public CustLine(CustPoint _one, CustPoint _two) {
        Matrix m_ = new Matrix();
        Vect v_ = new Vect();
        v_.add(new Rate(_one.getXcoords()));
        v_.add(new Rate(_one.getYcoords()));
        m_.addLineRef(v_);
        v_ = new Vect();
        v_.add(new Rate(_two.getXcoords()));
        v_.add(new Rate(_two.getYcoords()));
        m_.addLineRef(v_);
        Matrix p_ = m_.inv();
        Matrix i_ = new Matrix();
        v_ = new Vect();
        v_.add(Rate.one());
        i_.addLineRef(v_);
        v_ = new Vect();
        v_.add(Rate.one());
        i_.addLineRef(v_);
        Matrix res_ = p_.multMatrix(i_);
        xRate = res_.cell(0, 0);
        yRate = res_.cell(1, 0);
        Rate y_ = new Rate(_one.getYcoords());
        Rate x_ = new Rate(_one.getXcoords());
        Rate y2_ = new Rate(_two.getYcoords());
        Rate x2_ = new Rate(_two.getXcoords());
        def(m_, y_, x_, y2_, x2_, _one.eq(_two));
    }

    public CustLine(RatePoint _one, RatePoint _two) {
        Matrix m_ = new Matrix();
        Vect v_ = new Vect();
        v_.add(_one.getXcoords());
        v_.add(_one.getYcoords());
        m_.addLineRef(v_);
        v_ = new Vect();
        v_.add(_two.getXcoords());
        v_.add(_two.getYcoords());
        m_.addLineRef(v_);
        Matrix p_ = m_.inv();
        Matrix i_ = new Matrix();
        v_ = new Vect();
        v_.add(Rate.one());
        i_.addLineRef(v_);
        v_ = new Vect();
        v_.add(Rate.one());
        i_.addLineRef(v_);
        Rate y_ = new Rate(_one.getYcoords());
        Rate x_ = new Rate(_one.getXcoords());
        Rate y2_ = new Rate(_two.getYcoords());
        Rate x2_ = new Rate(_two.getXcoords());
        Matrix res_ = p_.multMatrix(i_);
        xRate = res_.cell(0, 0);
        yRate = res_.cell(1, 0);
        def(m_, y_, x_, y2_, x2_, _one.eq(_two));
    }

    private void defined(boolean _eq) {
        if (_eq) {
            defined = false;
        }
    }

    private void def(Matrix _m, Rate _y, Rate _x, Rate _y2, Rate _x2, boolean _eq) {
        if (_m.quickRank() == _m.nbLines()) {
            cst = Rate.one();
            defined = true;
        } else {
            init(_y, _x, _y2, _x2);
            defined(_eq);
        }
    }

    private void init(Rate _y, Rate _x, Rate _y2, Rate _x2) {
        if (!_y.isZero()) {
            xRate = Rate.one();
            yRate = Rate.divide(_x, _y).opposNb();
            defined = true;
        } else if (!_x.isZero()) {
            yRate = Rate.one();
            xRate = Rate.divide(_y, _x).opposNb();
            defined = true;
        } else {
            if (!_y2.isZero()) {
                xRate = Rate.one();
                yRate = Rate.divide(_x2, _y2).opposNb();
                defined = true;
            } else if (!_x2.isZero()) {
                yRate = Rate.one();
                xRate = Rate.divide(_y2, _x2).opposNb();
                defined = true;
            }
        }
    }

    public RatePoint incPoint(RatePoint _rate) {
        RatePoint r_ = new RatePoint(_rate);
        int i_;
        i_ = IndexConstants.FIRST_INDEX;
        long dx_ = 0;
        long dy_ = 0;
        if (!xRate.isZero()) {
            Rate signed_ = Rate.divide(Rate.minus(cst, yRate), xRate);
            Rate exact_ = signed_.absNb();
            if (exact_.greaterOrEqualsOne()) {
                dx_ = exact_.intPart().ll();
            } else {
                dx_ = 1;
            }
            dx_ *= signed_.signum().ll();
        }
        if (!yRate.isZero()) {
            Rate signed_ = Rate.divide(Rate.minus(cst, xRate), yRate);
            Rate exact_ = signed_.absNb();
            if (exact_.greaterOrEqualsOne()) {
                dy_ = exact_.intPart().ll();
            } else {
                dy_ = 1;
            }
            dy_ *= signed_.signum().ll();
        }
        while (i_ < dx_) {
            r_.getXcoords().addNb(Rate.one());
            i_++;
        }
        i_ = IndexConstants.FIRST_INDEX;
        while (i_ < dy_) {
            r_.getYcoords().addNb(Rate.one());
            i_++;
        }
        Rate x_ = getAbs(r_);
        Rate y_ = getOrd(r_);
        return RatePoint.newRefRatePoint(x_, y_);
    }

    public RatePoint incPoint(RatePoint _rate, int _dx, int _dy) {
        RatePoint r_ = new RatePoint(_rate);
        int i_;
        i_ = IndexConstants.FIRST_INDEX;
        while (i_ < _dx) {
            r_.getXcoords().addNb(Rate.one());
            i_++;
        }
        i_ = IndexConstants.FIRST_INDEX;
        while (i_ < _dy) {
            r_.getYcoords().addNb(Rate.one());
            i_++;
        }
        Rate x_ = getAbs(r_);
        Rate y_ = getAbs(r_);
        return RatePoint.newRefRatePoint(x_, y_);
    }

    public Rate getOrd(RatePoint _rate) {
        if (yRate.isZero()) {
            return new Rate(_rate.getYcoords());
        }
        return Rate.divide(Rate.minus(cst, Rate.multiply(_rate.getXcoords(), xRate)), yRate);
    }

    public Rate getAbs(RatePoint _rate) {
        if (xRate.isZero()) {
            return new Rate(_rate.getXcoords());
        }
        return Rate.divide(Rate.minus(cst, Rate.multiply(_rate.getYcoords(), yRate)), xRate);
    }

    public RatePoint intersect(CustLine _c) {
        Matrix vect_;
        Matrix mat_ = new Matrix();
        Vect v_ = new Vect();
        v_.add(xRate);
        v_.add(yRate);
        mat_.addLineRef(v_);
        v_ = new Vect();
        v_.add(_c.xRate);
        v_.add(_c.yRate);
        mat_.addLineRef(v_);
        Matrix matTwo_ = new Matrix();
        v_ = new Vect();
        v_.add(cst);
        matTwo_.addLineRef(v_);
        v_.add(_c.cst);
        matTwo_.addLineRef(v_);
        vect_=mat_.inv().multMatrix(matTwo_);
        return new RatePoint(vect_.cell(0,0),vect_.cell(1,0));
    }

    public boolean isDefined() {
        return defined;
    }

    public Rate getxRate() {
        return xRate;
    }

    public Rate getyRate() {
        return yRate;
    }

    public Rate getCst() {
        return cst;
    }
}
