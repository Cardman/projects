package code.maths.geo;
import code.maths.Rate;
import code.maths.matrix.Matrix;
import code.maths.matrix.Vect;
import code.util.CustList;

public class CustLine {

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
        if (m_.quickRank() == m_.nbLines()) {
            cst = Rate.one();
            defined = true;
        } else {
            Rate y_ = new Rate(_one.getYcoords());
            Rate x_ = new Rate(_one.getXcoords());
            if (!y_.isZero()) {
                xRate = Rate.one();
                yRate = Rate.divide(x_, y_).opposNb();
                defined = true;
            } else if (!x_.isZero()){
                yRate = Rate.one();
                xRate = Rate.divide(y_, x_).opposNb();
                defined = true;
            } else {
                Rate y2_ = new Rate(_two.getYcoords());
                Rate x2_ = new Rate(_two.getXcoords());
                if (!y2_.isZero()) {
                    xRate = Rate.one();
                    yRate = Rate.divide(x2_, y2_).opposNb();
                    defined = true;
                } else if (!x2_.isZero()){
                    yRate = Rate.one();
                    xRate = Rate.divide(y2_, x2_).opposNb();
                    defined = true;
                }
            }
            if (_one.eq(_two)) {
                defined = false;
            }
        }
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
        Matrix res_ = p_.multMatrix(i_);
        xRate = res_.cell(0, 0);
        yRate = res_.cell(1, 0);
        if (m_.quickRank() == m_.nbLines()) {
            cst = Rate.one();
            defined = true;
        } else {
            Rate y_ = new Rate(_one.getYcoords());
            Rate x_ = new Rate(_one.getXcoords());
            if (!y_.isZero()) {
                xRate = Rate.one();
                yRate = Rate.divide(x_, y_).opposNb();
                defined = true;
            } else if (!x_.isZero()){
                yRate = Rate.one();
                xRate = Rate.divide(y_, x_).opposNb();
                defined = true;
            } else {
                Rate y2_ = new Rate(_two.getYcoords());
                Rate x2_ = new Rate(_two.getXcoords());
                if (!y2_.isZero()) {
                    xRate = Rate.one();
                    yRate = Rate.divide(x2_, y2_).opposNb();
                    defined = true;
                } else if (!x2_.isZero()){
                    yRate = Rate.one();
                    xRate = Rate.divide(y2_, x2_).opposNb();
                    defined = true;
                }
            }
            if (_one.eq(_two)) {
                defined = false;
            }
        }
    }

    public RatePoint incPoint(RatePoint _rate) {
        RatePoint r_ = new RatePoint(_rate);
        int i_;
        i_ = CustList.FIRST_INDEX;
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
            i_ ++;
        }
        i_ = CustList.FIRST_INDEX;
        while (i_ < dy_) {
            r_.getYcoords().addNb(Rate.one());
            i_ ++;
        }
        Rate x_ = getAbs(r_);
        Rate y_ = getOrd(r_);
        return RatePoint.newRefRatePoint(x_, y_);
    }

    public RatePoint incPoint(RatePoint _rate, int _dx, int _dy) {
        RatePoint r_ = new RatePoint(_rate);
        int i_;
        i_ = CustList.FIRST_INDEX;
        while (i_ < _dx) {
            r_.getXcoords().addNb(Rate.one());
            i_ ++;
        }
        i_ = CustList.FIRST_INDEX;
        while (i_ < _dy) {
            r_.getYcoords().addNb(Rate.one());
            i_ ++;
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
