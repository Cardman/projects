package code.expressionlanguage.opers;

public final class Calculation {

    private static final String NO_OP = "";

    private final boolean staticBlock;
    private final String fieldName;
    private final boolean leftStep;

    private final String oper;

    public Calculation(String _fieldName) {
        this(true,_fieldName);
    }
    Calculation(boolean _staticBlock,String _fieldName) {
        this(_staticBlock, _fieldName, false);
    }
    public Calculation(boolean _staticBlock,String _fieldName,boolean _leftStep) {
        this(_staticBlock, _fieldName, _leftStep, NO_OP);
    }

    private Calculation(boolean _staticBlock,String _fieldName,boolean _leftStep, String _oper) {
        staticBlock = _staticBlock;
        fieldName = _fieldName;
        leftStep = _leftStep;
        oper = _oper;
    }

    public static Calculation staticCalculation(boolean _staticBlock) {
        return new Calculation(_staticBlock, NO_OP);
    }


    public boolean isLeftStep() {
        return leftStep;
    }

    public String getOper() {
        return oper;
    }

    public boolean isStaticBlock() {
        return staticBlock;
    }

    public String getFieldName() {
        return fieldName;
    }
}
