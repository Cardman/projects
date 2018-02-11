package aiki.beans.facade.fight;
import code.bean.Accessible;
import code.maths.Rate;
import aiki.fight.enums.Statistic;

public final class StatisticInfo {

    /***/
    private Statistic statistic;

    @Accessible
    private String displayStatistic;

    @Accessible
    private short ev;

    @Accessible
    private short iv;

    @Accessible
    private Rate statisBase;

    @Accessible
    private byte statisBoost;

    public Statistic getStatistic() {
        return statistic;
    }

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
