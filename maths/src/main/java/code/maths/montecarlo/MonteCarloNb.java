package code.maths.montecarlo;
import code.maths.LgInt;
import code.util.*;


public final class MonteCarloNb extends AbMonteCarlo<Long> {

    private LongMap<LgInt> law;

    public MonteCarloNb() {
        law = new LongMap<LgInt>();
    }

    @Override
    public LongMap<LgInt> getLaw() {
        return law;
    }
}
