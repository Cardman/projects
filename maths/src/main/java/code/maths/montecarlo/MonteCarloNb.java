package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.util.CollCapacity;
import code.util.NumberMap;
import code.util.Numbers;


public final class MonteCarloNb<E extends Number> extends AbMonteCarlo<E> {

    private NumberMap<E,LgInt> law;

    public MonteCarloNb() {
        law = new NumberMap<E,LgInt>();
    }

    @Override
    public NumberMap<E,LgInt> getLaw() {
        return law;
    }
}
