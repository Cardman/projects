package cards.belote.beans;

import code.expressionlanguage.structs.AbNullStruct;

public final class DeclaringPlayerValueStruct extends AbNullStruct {
    private final DeclaringPlayerValue declaringPlayerValue;

    public DeclaringPlayerValueStruct(DeclaringPlayerValue _declaringPlayerValue) {
        this.declaringPlayerValue = _declaringPlayerValue;
    }

    public DeclaringPlayerValue getDeclaringPlayerValue() {
        return declaringPlayerValue;
    }
}
