package code.expressionlanguage.opers;

import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.MethodId;

public final class Calculation {

    private static final String NO_OP = "";

    private final MethodAccessKind staticBlock;
    private final String fieldName;



    public Calculation(String _fieldName) {
        this(MethodAccessKind.STATIC,_fieldName);
    }
    Calculation(MethodAccessKind _staticBlock,String _fieldName) {
        staticBlock = _staticBlock;
        fieldName = _fieldName;
    }

    public static Calculation staticCalculation(boolean _staticBlock) {
        return new Calculation(MethodId.getKind(_staticBlock), NO_OP);
    }

    public static Calculation staticCalculation(MethodAccessKind _staticBlock) {
        return new Calculation(_staticBlock, NO_OP);
    }

    public MethodAccessKind getStaticBlock() {
        return staticBlock;
    }

    public String getFieldName() {
        return fieldName;
    }
}
