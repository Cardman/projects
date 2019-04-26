package code.maths.matrix;
import code.maths.Rate;

public final class RootPol {

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

}
