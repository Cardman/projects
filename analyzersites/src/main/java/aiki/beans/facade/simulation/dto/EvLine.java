package aiki.beans.facade.simulation.dto;
import code.bean.Accessible;

public final class EvLine {

    @Accessible
    private short ev;

    public short getEv() {
        return ev;
    }

    public void setEv(short _ev) {
        ev = _ev;
    }
}
