package aiki.game.fight;
import code.maths.LgInt;
import code.maths.Rate;

public final class BallNumberRate {

    private LgInt number;

    private Rate rate;

    private String name;

    private String percent;

    public BallNumberRate(LgInt _number, Rate _rate, String _name) {
        number = _number;
        rate = _rate;
        name = _name;
        percent = rate.percent().toNumberString();
    }

    public LgInt getNumber() {
        return number;
    }

    public Rate getRate() {
        return rate;
    }

    public String getName() {
        return name;
    }

    public String getPercent() {
        return percent;
    }
}
