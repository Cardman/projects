package aiki.util;
import code.maths.Rate;
import code.util.StringList;

public final class SortingHealingItem extends SortingItem {

    private int nbHealedStatus;

    private final StringList status = new StringList();

    private boolean relativeRateHp;

    private Rate hp;

    private Rate hpRate;

    private boolean relativeRatePp;

    private Rate pp;

    private boolean healOneMove;

    private int nbStatistics;

    private final StringList statistics = new StringList();

    private boolean ko;

    public int getNbHealedStatus() {
        return nbHealedStatus;
    }

    public void setNbHealedStatus(int _nbHealedStatus) {
        nbHealedStatus = _nbHealedStatus;
    }

    public boolean isRelativeRateHp() {
        return relativeRateHp;
    }

    public void setRelativeRateHp(boolean _relativeRateHp) {
        relativeRateHp = _relativeRateHp;
    }

    public Rate getHp() {
        return hp;
    }

    public void setHp(Rate _hp) {
        hp = _hp;
    }

    public Rate getHpRate() {
        return hpRate;
    }

    public void setHpRate(Rate _hpRate) {
        hpRate = _hpRate;
    }

    public boolean isRelativeRatePp() {
        return relativeRatePp;
    }

    public void setRelativeRatePp(boolean _relativeRatePp) {
        relativeRatePp = _relativeRatePp;
    }

    public Rate getPp() {
        return pp;
    }

    public void setPp(Rate _pp) {
        pp = _pp;
    }

    public boolean isHealOneMove() {
        return healOneMove;
    }

    public void setHealOneMove(boolean _healOneMove) {
        healOneMove = _healOneMove;
    }

    public int getNbStatistics() {
        return nbStatistics;
    }

    public void setNbStatistics(int _nbStatistics) {
        nbStatistics = _nbStatistics;
    }

    public boolean isKo() {
        return ko;
    }

    public void setKo(boolean _ko) {
        ko = _ko;
    }

    public StringList getStatistics() {
        return statistics;
    }

    public StringList getStatus() {
        return status;
    }
}
