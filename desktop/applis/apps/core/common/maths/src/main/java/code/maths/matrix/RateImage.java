package code.maths.matrix;
import code.maths.Rate;

public final class RateImage {

    private Rate rate;

    private Rate value;

    public RateImage(Rate _rate, Rate _value) {
        rate = _rate;
        value = _value;
    }

    public Rate getRate() {
        return rate;
    }

    public Rate getValue() {
        return value;
    }

}
