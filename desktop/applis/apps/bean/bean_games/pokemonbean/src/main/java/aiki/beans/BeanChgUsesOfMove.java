package aiki.beans;

import aiki.game.*;

public class BeanChgUsesOfMove implements IntBeanChgUsesOfMove {

    private UsesOfMove value = new UsesOfMove(0);

    @Override
    public UsesOfMove genericValue() {
        return valueUse();
    }

    @Override
    public UsesOfMove valueUse() {
        return value;
    }

    @Override
    public void valueUse(UsesOfMove _v) {
        value = _v;
    }
}
