package aiki.beans.facade.game.dto;
import code.maths.Rate;

public class StatisticInfoPkPlayer {

    private String name;

    private Rate rate;

    private short ev;

    private short iv;

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

    public short getEv() {
        return ev;
    }

    public void setEv(short _ev) {
        ev = _ev;
    }

    public short getIv() {
        return iv;
    }

    public void setIv(short _iv) {
        iv = _iv;
    }
}
