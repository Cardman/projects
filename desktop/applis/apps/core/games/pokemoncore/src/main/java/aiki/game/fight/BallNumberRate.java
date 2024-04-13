package aiki.game.fight;
import code.maths.LgInt;
import code.maths.montecarlo.MonteCarloNumber;

public final class BallNumberRate {

    private final LgInt number;

    private final MonteCarloNumber law;
    private final String name;

    public BallNumberRate(LgInt _number, MonteCarloNumber _rate, String _name) {
        number = _number;
        law = _rate;
        name = _name;
    }

    public LgInt getNumber() {
        return number;
    }

    public MonteCarloNumber getLaw() {
        return law;
    }

    public String getName() {
        return name;
    }
}
