package cards.belote.beans;

import code.bean.nat.CommNatStruct;

public final class DeclaringPlayerValueStruct extends CommNatStruct {
    private final DeclaringPlayerValue declaringPlayerValue;

    public DeclaringPlayerValueStruct(DeclaringPlayerValue _declaringPlayerValue, String _className) {
        super(_className);
        this.declaringPlayerValue = _declaringPlayerValue;
    }

    public DeclaringPlayerValue getDeclaringPlayerValue() {
        return declaringPlayerValue;
    }
}
