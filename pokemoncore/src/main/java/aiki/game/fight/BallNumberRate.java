package aiki.game.fight;
import code.maths.LgInt;
import code.maths.Rate;

public final class BallNumberRate {

//    private static final Rate CENT = new Rate(100);

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

    public void setNumber(LgInt _number) {
        number = _number;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate _rate) {
        rate = _rate;
        percent = rate.percent().toNumberString();
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getPercent() {
        return percent;
    }
}
