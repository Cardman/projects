package code.maths.montecarlo;
import code.maths.LgInt;
import code.util.NumberMap;


public final class MonteCarloNb extends AbMonteCarlo<Long> {

    private NumberMap<Long,LgInt> law;

    public MonteCarloNb() {
        law = new NumberMap<Long,LgInt>();
    }

    @Override
    public NumberMap<Long,LgInt> getLaw() {
        return law;
    }
}
