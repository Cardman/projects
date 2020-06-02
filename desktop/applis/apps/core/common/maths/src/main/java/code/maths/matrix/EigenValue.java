package code.maths.matrix;

import code.maths.Rate;

public final class EigenValue {
    private Rate value;
    private int degree;
    private int space;

    public EigenValue(Rate _value, int _degree, int _space) {
        value = _value;
        degree = _degree;
        space = _space;
    }

    public Rate getValue() {
        return value;
    }

    public int getDegree() {
        return degree;
    }

    public int getSpace() {
        return space;
    }
}
