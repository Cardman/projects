package aiki.beans.facade.fight;
import code.maths.Rate;

public final class SufferedDamageCategory {

    private Rate round;

    private Rate using;

    public Rate getRound() {
        return round;
    }

    public void setRound(Rate _round) {
        round = _round;
    }

    public Rate getUsing() {
        return using;
    }

    public void setUsing(Rate _using) {
        using = _using;
    }
}