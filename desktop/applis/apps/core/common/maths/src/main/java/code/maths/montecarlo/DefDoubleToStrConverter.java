package code.maths.montecarlo;

public final class DefDoubleToStrConverter implements AbsDoubleToStrConverter {
    @Override
    public String convert(double _d) {
        return Double.toString(_d);
    }
}
