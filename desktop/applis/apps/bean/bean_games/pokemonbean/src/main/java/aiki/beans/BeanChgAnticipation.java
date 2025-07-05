package aiki.beans;

import aiki.game.fight.*;

public class BeanChgAnticipation implements IntBeanChgAnticipation {

    private Anticipation value = new Anticipation();

    @Override
    public Anticipation genericValue() {
        return valueAnt();
    }

    @Override
    public Anticipation valueAnt() {
        return value;
    }

    @Override
    public void valueAnt(Anticipation _v) {
        value = _v;
    }
}
