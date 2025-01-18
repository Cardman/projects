package aiki.beans.facade.game.dto;
import code.maths.Rate;

public final class StatisticInfoPkPlayer {

    private String name;

    private Rate rate;

    private int ev;

    private int iv;

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

    public int getEv() {
        return ev;
    }

    public void setEv(int _ev) {
        ev = _ev;
    }

    public int getIv() {
        return iv;
    }

    public void setIv(int _iv) {
        iv = _iv;
    }
}