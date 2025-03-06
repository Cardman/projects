package aiki.beans.facade.simulation.dto;

import aiki.beans.BeanChgLong;
import aiki.beans.IntBeanChgLong;

public final class EvLine {
    private IntBeanChgLong ev = new BeanChgLong();

    public IntBeanChgLong getEv() {
        return ev;
    }

    public void setEv(IntBeanChgLong _ev) {
        ev = _ev;
    }
}