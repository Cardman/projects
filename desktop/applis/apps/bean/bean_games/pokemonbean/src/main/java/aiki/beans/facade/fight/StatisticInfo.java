package aiki.beans.facade.fight;
import aiki.fight.enums.Statistic;
import code.maths.Rate;

public final class StatisticInfo {

    /***/
    private Statistic statistic;
    private String displayStatistic;
    private short ev;
    private short iv;
    private Rate statisBase;
    private byte statisBoost;

    public void setStatistic(Statistic _statistic) {
        statistic = _statistic;
    }

    public boolean isBoost() {
        return statistic.isBoost();
    }

    public boolean isBase() {
        return statistic.isWithBaseStatistic();
    }

    public String getDisplayStatistic() {
        return displayStatistic;
    }

    public void setDisplayStatistic(String _displayStatistic) {
        displayStatistic = _displayStatistic;
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

    public Rate getStatisBase() {
        return statisBase;
    }

    public void setStatisBase(Rate _statisBase) {
        statisBase = _statisBase;
    }

    public byte getStatisBoost() {
        return statisBoost;
    }

    public void setStatisBoost(byte _statisBoost) {
        statisBoost = _statisBoost;
    }
}