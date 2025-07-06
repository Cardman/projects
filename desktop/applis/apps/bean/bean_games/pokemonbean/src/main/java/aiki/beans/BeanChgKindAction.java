package aiki.beans;

import aiki.game.fight.actions.*;

public class BeanChgKindAction implements IntBeanChgKindAction {
    private KindAction selected = KindAction.NO;

    @Override
    public KindAction valueKa() {
        return selected;
    }

    @Override
    public void valueKa(KindAction _v) {
        selected = _v;
    }
}
