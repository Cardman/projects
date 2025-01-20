package aiki.beans.facade.game.dto;
import code.maths.Rate;

public final class StatisticInfoPkPlayer {

    private String name;

    private Rate rate;

    private long ev;

    private long iv;

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate _rate) {
        rate = _rate;
    }

    public long getEv() {
        return ev;
    }

    public void setEv(long _ev) {
        ev = _ev;
    }

    public long getIv() {
        return iv;
    }

    public void setIv(long _iv) {
        iv = _iv;
    }
}