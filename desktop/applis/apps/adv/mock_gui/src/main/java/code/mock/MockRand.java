package code.mock;

import code.maths.LgInt;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloBoolean;
import code.util.core.BoolVal;

public final class MockRand {
    private final MonteCarloBoolean monteCarloBoolean = new MonteCarloBoolean();
    private final AbstractGenerator generator;

    public MockRand(AbstractGenerator _gen) {
        this.generator = _gen;
        monteCarloBoolean.addEvent(BoolVal.FALSE, LgInt.one());
        monteCarloBoolean.addEvent(BoolVal.TRUE, LgInt.one());
    }

    public boolean edit() {
        return monteCarloBoolean.editNumber(new LgInt(2),generator) == BoolVal.TRUE;
    }
}
