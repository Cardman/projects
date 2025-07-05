package aiki.beans;

import aiki.game.fight.*;

public class BeanChgStackOfUses implements IntBeanChgStackOfUses {

    private StacksOfUses value = new StacksOfUses();

    @Override
    public StacksOfUses genericValue() {
        return valueStack();
    }

    @Override
    public StacksOfUses valueStack() {
        return value;
    }

    @Override
    public void valueStack(StacksOfUses _v) {
        value = _v;
    }
}
