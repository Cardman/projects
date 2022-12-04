package cards.belote.beans;

import code.bean.nat.*;

public final class DeclaringPlayerValueStruct extends NaNuSt {
    private final DeclaringPlayerValue declaringPlayerValue;

    public DeclaringPlayerValueStruct(DeclaringPlayerValue _declaringPlayerValue) {
        this.declaringPlayerValue = _declaringPlayerValue;
    }

    public DeclaringPlayerValue getDeclaringPlayerValue() {
        return declaringPlayerValue;
    }
}
