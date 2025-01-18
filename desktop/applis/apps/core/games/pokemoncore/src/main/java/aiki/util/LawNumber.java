package aiki.util;
import code.maths.montecarlo.MonteCarloNumber;

public class LawNumber {

    private final MonteCarloNumber law;

    private final Integer number;

    public LawNumber(MonteCarloNumber _law, Integer _number) {
        law = _law;
        number = _number;
    }

    public MonteCarloNumber getLaw() {
        return law;
    }

    public Integer getNumber() {
        return number;
    }

}
