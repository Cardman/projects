package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.blocks.InnerTypeOrElement;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.util.StringList;

public final class Calculation {

    private static final String NO_OP = "";

    private final MethodAccessKind staticBlock;
    private final InnerTypeOrElement innerElt;
    private final String fieldName;
    private final StringList fieldErrors;


    public Calculation(InnerTypeOrElement _innerElt, StringList _fieldErrors) {
        this(MethodAccessKind.STATIC, _innerElt, _fieldErrors);
    }
    private Calculation(MethodAccessKind _staticBlock, InnerTypeOrElement _innerElt, StringList _fieldErrors) {
        staticBlock = _staticBlock;
        if (_innerElt != null) {
            fieldName = _innerElt.getUniqueFieldName();
        } else {
            fieldName = NO_OP;
        }
        innerElt = _innerElt;
        fieldErrors = _fieldErrors;
    }

    public static Calculation staticCalculation(boolean _staticBlock) {
        return new Calculation(MethodId.getKind(_staticBlock), null, new StringList());
    }

    public static Calculation staticCalculation(MethodAccessKind _staticBlock) {
        return new Calculation(_staticBlock, null, new StringList());
    }

    public MethodAccessKind getStaticBlock() {
        return staticBlock;
    }

    public String getFieldName() {
        return fieldName;
    }

    public boolean hasFieldName() {
        return innerElt != null;
    }

    public InnerTypeOrElement getInnerElt() {
        return innerElt;
    }

    public StringList getFieldErrors() {
        return fieldErrors;
    }
}
