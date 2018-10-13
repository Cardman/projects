package code.expressionlanguage.opers;

public final class Calculation {

    private static final String NO_OP = "";

    private final boolean staticBlock;
    private final String fieldName;



    public Calculation(String _fieldName) {
        this(true,_fieldName);
    }
    Calculation(boolean _staticBlock,String _fieldName) {
        staticBlock = _staticBlock;
        fieldName = _fieldName;
    }

    public static Calculation staticCalculation(boolean _staticBlock) {
        return new Calculation(_staticBlock, NO_OP);
    }

    public boolean isStaticBlock() {
        return staticBlock;
    }

    public String getFieldName() {
        return fieldName;
    }
}
