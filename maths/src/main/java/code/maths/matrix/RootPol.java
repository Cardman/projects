package code.maths.matrix;
import code.maths.Rate;
import code.util.ints.Equallable;

public final class RootPol implements Equallable<RootPol> {

    private Rate value;

    private int degree;

    public RootPol(Rate _value, int _degree) {
        value = _value;
        degree = _degree;
    }

    public Rate getValue() {
        return value;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int _degree) {
        degree = _degree;
    }

    @Override
    public boolean eq(RootPol _g) {
        if (degree != _g.degree) {
            return false;
        }
        if (!value.eq(_g.value)) {
            return false;
        }
        return true;
    }
}
