package aiki.game.fight;

import code.maths.Rate;

public final class BallNumberRatePk {

    private final String namePk;

    private final String name;

    private final Rate rate;

    private final String percent;
    public BallNumberRatePk(String _pk, Rate _rate, String _name) {
        rate = _rate;
        percent = _rate.percent().toNumberString();
        namePk = _pk;
        name = _name;
    }

    public String getNamePk() {
        return namePk;
    }

    public String getName() {
        return name;
    }

    public Rate getRate() {
        return rate;
    }

    public String getPercent() {
        return percent;
    }
}
