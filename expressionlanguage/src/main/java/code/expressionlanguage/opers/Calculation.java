package code.expressionlanguage.opers;

public final class Calculation {

    private static final String NO_OP = "";

    private final boolean staticAcces;
    private final String fieldName;
    private final boolean leftStep;

    private final String oper;
    public Calculation() {
        this(NO_OP);
    }
    public Calculation(String _fieldName) {
        this(true, _fieldName);
    }
    public Calculation(boolean _staticAcces,String _fieldName) {
        this(_staticAcces, _fieldName, false);
    }
    public Calculation(boolean _staticAcces,String _fieldName,boolean _leftStep) {
        this(_staticAcces, _fieldName, _leftStep, NO_OP);
    }

    public Calculation(boolean _staticAcces,String _fieldName,boolean _leftStep, String _oper) {
        staticAcces = _staticAcces;
        fieldName = _fieldName;
        leftStep = _leftStep;
        oper = _oper;
    }
    public static Calculation instanceCalculation() {
        return new Calculation(false, NO_OP);
    }

    public static Calculation staticCalculation(boolean _static) {
        return new Calculation(_static, NO_OP);
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

    public String getFieldName() {
        return fieldName;
    }
}
