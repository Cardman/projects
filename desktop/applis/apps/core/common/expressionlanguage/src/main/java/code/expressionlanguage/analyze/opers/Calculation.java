package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;

public final class Calculation {

    private static final String NO_OP = "";

    private final MethodAccessKind staticBlock;
    private final String fieldName;
    private final boolean hasFieldName;



    public Calculation(String _fieldName) {
        this(MethodAccessKind.STATIC,_fieldName,true);
    }
    private Calculation(MethodAccessKind _staticBlock,String _fieldName, boolean _hasFieldName) {
        staticBlock = _staticBlock;
        fieldName = _fieldName;
        hasFieldName = _hasFieldName;
    }

    public static Calculation staticCalculation(boolean _staticBlock) {
        return new Calculation(MethodId.getKind(_staticBlock), NO_OP,false);
    }

    public static Calculation staticCalculation(MethodAccessKind _staticBlock) {
        return new Calculation(_staticBlock, NO_OP,false);
    }

    public MethodAccessKind getStaticBlock() {
        return staticBlock;
    }

    public String getFieldName() {
        return fieldName;
    }

    public boolean isHasFieldName() {
        return hasFieldName;
    }
}
