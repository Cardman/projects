package code.expressionlanguage.options;

import code.maths.montecarlo.AbsDoubleToStrConverter;

public final class AdvDoubleToStrConverter implements AbsDoubleToStrConverter {
    @Override
    public String convert(double _d) {
        return Double.toString(_d).replace('E','#');
    }
}
