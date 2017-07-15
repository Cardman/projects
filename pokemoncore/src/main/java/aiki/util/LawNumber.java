package aiki.util;
import code.maths.montecarlo.MonteCarloNumber;

public class LawNumber {

    private final MonteCarloNumber law;

    private final Short number;

    public LawNumber(MonteCarloNumber _law, Short _number) {
        law = _law;
        number = _number;
    }

    public MonteCarloNumber getLaw() {
        return law;
    }

    public Short getNumber() {
        return number;
    }

}
