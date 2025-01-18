package aiki.beans.facade.fight;
import aiki.fight.enums.Statistic;
import code.maths.Rate;

public final class StatisticInfo {

    /***/
    private Statistic statistic;
    private String displayStatistic;
    private int ev;
    private int iv;
    private Rate statisBase;
    private int statisBoost;

    public void setStatistic(Statistic _statistic) {
        statistic = _statistic;
    }

    public boolean isBoost() {
        return isBoost(statistic);
    }

    public boolean isBase() {
        return isBase(statistic);
    }

    public static boolean isBoost(Statistic _st) {
        return _st == Statistic.ATTACK || _st == Statistic.DEFENSE||
                _st == Statistic.SPECIAL_ATTACK || _st == Statistic.SPECIAL_DEFENSE||
                _st == Statistic.SPEED || _st == Statistic.ACCURACY || _st == Statistic.EVASINESS || _st == Statistic.CRITICAL_HIT;
    }

    public static boolean isBase(Statistic _st) {
        return _st == Statistic.ATTACK || _st == Statistic.DEFENSE||
                _st == Statistic.SPECIAL_ATTACK || _st == Statistic.SPECIAL_DEFENSE||
                _st == Statistic.SPEED || _st == Statistic.HP;
    }
    public String getDisplayStatistic() {
        return displayStatistic;
    }

    public void setDisplayStatistic(String _displayStatistic) {
        displayStatistic = _displayStatistic;
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

    public Rate getStatisBase() {
        return statisBase;
    }

    public void setStatisBase(Rate _statisBase) {
        statisBase = _statisBase;
    }

    public int getStatisBoost() {
        return statisBoost;
    }

    public void setStatisBoost(int _statisBoost) {
        statisBoost = _statisBoost;
    }
}