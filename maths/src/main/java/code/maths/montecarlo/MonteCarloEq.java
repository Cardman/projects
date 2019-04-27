package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.util.CollCapacity;
import code.util.EqList;
import code.util.ObjectNotNullMap;
import code.util.ints.Equallable;


public final class MonteCarloEq<E extends Equallable<E>> extends AbMonteCarlo<E> {

    private ObjectNotNullMap<E,LgInt> law;

    public MonteCarloEq(CollCapacity _capacity) {
        law = new ObjectNotNullMap<E,LgInt>(_capacity);
    }

    @Override
    public ObjectNotNullMap<E,LgInt> getLaw() {
        return law;
    }
}
