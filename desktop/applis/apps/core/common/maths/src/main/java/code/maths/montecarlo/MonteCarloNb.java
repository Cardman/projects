package code.maths.montecarlo;
import code.maths.LgInt;
import code.util.*;


public final class MonteCarloNb extends AbMonteCarloMap<Long> {

    private final AbsMap<Long,LgInt> law;

    public MonteCarloNb() {
        law = new LongMap<LgInt>();
    }

    @Override
    public AbsMap<Long,LgInt> getLaw() {
        return law;
    }
}
