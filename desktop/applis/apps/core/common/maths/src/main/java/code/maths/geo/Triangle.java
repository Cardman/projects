package code.maths.geo;
import code.maths.Rate;
import code.util.CustList;
import code.util.ints.Displayable;

public final class Triangle implements HasEdges, Displayable {

    public static final int NB_POINTS = 3;

    private static final String SEPARATOR = ";";

    private final RatePoint firstPoint;

    private final RatePoint secondPoint;

    private final RatePoint thirdPoint;

    public Triangle(RatePoint _firstPoint, RatePoint _secondPoint, RatePoint _thirdPoint) {
        firstPoint = _firstPoint;
        secondPoint = _secondPoint;
        thirdPoint = _thirdPoint;
    }

    @Override
    public CustList<Edge> getEdges() {
        CustList<Edge> l_ = new CustList<Edge>();
        l_.add(new Edge(firstPoint, secondPoint));
        l_.add(new Edge(secondPoint, thirdPoint));
        l_.add(new Edge(thirdPoint, firstPoint));
        return l_;
    }

    @Override
    public CustList<RatePoint> getPoints() {
        CustList<RatePoint> l_ = new CustList<RatePoint>();
        l_.add(firstPoint);
        l_.add(secondPoint);
        l_.add(thirdPoint);
        return l_;
    }

    public boolean isInCircum(RatePoint _point) {
        RatePoint omega_;
        omega_ = getCircumCenter();
        Rate distInput_ = omega_.sqDist(_point);
        Rate distTr_ = omega_.sqDist(firstPoint);
        return Rate.strLower(distInput_,distTr_);
    }

    public boolean isInCircumBorder(RatePoint _point) {
        RatePoint omega_;
        omega_ = getCircumCenter();
        Rate distInput_ = omega_.sqDist(_point);
        Rate distTr_ = omega_.sqDist(firstPoint);
        return distInput_.eq(distTr_);
    }

    public CustLine euler() {
        RatePoint g_ = getGravityCenter();
        Rate gx_ = g_.getXcoords();
        Rate gy_ = g_.getYcoords();
        Rate cd_ = com();
        Rate cx_;
        Rate cy_;
        if (cd_.isZero()) {
            return new CustLine(Rate.zero(),Rate.zero(),Rate.zero());
        }
        RatePoint c_ = getCircumCenter();
        cx_ = c_.getXcoords();
        cy_ = c_.getYcoords();
        return new CustLine(new RatePoint(gx_, gy_), new RatePoint(cx_, cy_));
    }

    public RatePoint getGravityCenter() {
        Rate xg_ = Rate.zero();
        xg_.addNb(firstPoint.getXcoords());
        xg_.addNb(secondPoint.getXcoords());
        xg_.addNb(thirdPoint.getXcoords());
        xg_.divideBy(new Rate(3));
        Rate yg_ = Rate.zero();
        yg_.addNb(firstPoint.getYcoords());
        yg_.addNb(secondPoint.getYcoords());
        yg_.addNb(thirdPoint.getYcoords());
        yg_.divideBy(new Rate(3));
        return new RatePoint(xg_,yg_);
    }

    public RatePoint getCircumCenter() {
        Rate bpx_ = Rate.minus(secondPoint.getXcoords(), firstPoint.getXcoords());
        Rate cpx_ = Rate.minus(thirdPoint.getXcoords(), firstPoint.getXcoords());
        Rate bpy_ = Rate.minus(secondPoint.getYcoords(), firstPoint.getYcoords());
        Rate cpy_ = Rate.minus(thirdPoint.getYcoords(), firstPoint.getYcoords());
        Rate dp_ = Rate.multiply(new Rate(2),Rate.minus(Rate.multiply(bpx_, cpy_), Rate.multiply(bpy_, cpx_)));
        Rate x_ = Rate.multiply(cpy_,Rate.plus(Rate.multiply(bpx_, bpx_),Rate.multiply(bpy_, bpy_)));
        x_.removeNb(Rate.multiply(bpy_,Rate.plus(Rate.multiply(cpx_,cpx_),Rate.multiply(cpy_,cpy_))));
        Rate y_ = Rate.multiply(bpx_,Rate.plus(Rate.multiply(cpx_, cpx_),Rate.multiply(cpy_, cpy_)));
        y_.removeNb(Rate.multiply(cpx_,Rate.plus(Rate.multiply(bpx_,bpx_),Rate.multiply(bpy_,bpy_))));
        x_.addNb(Rate.multiply(firstPoint.getXcoords(), dp_));
        y_.addNb(Rate.multiply(firstPoint.getYcoords(), dp_));
        Rate xc_ = Rate.divide(x_,dp_);
        Rate yc_ = Rate.divide(y_,dp_);
        return new RatePoint(xc_,yc_);
    }
    public Rate com() {
        Rate bpx_ = Rate.minus(secondPoint.getXcoords(), firstPoint.getXcoords());
        Rate cpx_ = Rate.minus(thirdPoint.getXcoords(), firstPoint.getXcoords());
        Rate bpy_ = Rate.minus(secondPoint.getYcoords(), firstPoint.getYcoords());
        Rate cpy_ = Rate.minus(thirdPoint.getYcoords(), firstPoint.getYcoords());
        return Rate.multiply(new Rate(2),Rate.minus(Rate.multiply(bpx_, cpy_), Rate.multiply(bpy_, cpx_)));
    }

    public RatePoint getFirstPoint() {
        return firstPoint;
    }

    public RatePoint getSecondPoint() {
        return secondPoint;
    }

    public RatePoint getThirdPoint() {
        return thirdPoint;
    }

    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(firstPoint.display());
        str_.append(SEPARATOR);
        str_.append(secondPoint.display());
        str_.append(SEPARATOR);
        str_.append(thirdPoint.display());
        return str_.toString();
    }
}
