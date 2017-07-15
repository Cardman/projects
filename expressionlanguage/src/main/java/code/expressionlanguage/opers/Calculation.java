package code.expressionlanguage.opers;

public final class Calculation {

    private static final String NO_OP = "";

    private final boolean staticAcces;
    private final boolean enumAcces;
    private final boolean leftStep;

    private final String oper;
    
    public Calculation() {
        this(false);
    }
    
    public Calculation(boolean _enumAcces) {
        this(true, _enumAcces);
    }
    
    public Calculation(boolean _staticAcces,boolean _enumAcces) {
        this(_staticAcces, _enumAcces, false);
    }
  
    public Calculation(boolean _staticAcces,boolean _enumAcces,boolean _leftStep) {
        this(_staticAcces, _enumAcces, _leftStep, NO_OP);
    }

    public Calculation(boolean _staticAcces,boolean _enumAcces,boolean _leftStep, String _oper) {
        staticAcces = _staticAcces;
        enumAcces = _enumAcces;
        leftStep = _leftStep;
        oper = _oper;
    }
    
    public static Calculation instanceCalculation() {
        return new Calculation(false, false);
    }

    public static Calculation staticCalculation(boolean _static) {
        return new Calculation(_static, false);
    }

    public boolean isLeftStep() {
        return leftStep;
    }

    public String getOper() {
        return oper;
    }

    public boolean isStaticAcces() {
        return staticAcces;
    }

    public boolean isEnumAcces() {
        return enumAcces;
    }
}
