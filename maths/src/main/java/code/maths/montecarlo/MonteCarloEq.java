package code.maths.montecarlo;
import code.maths.LgInt;
import code.util.AbsMap;
import code.util.CollCapacity;
import code.util.ObjectMap;
import code.util.ints.Equallable;


public final class MonteCarloEq<E extends Equallable<E>> extends AbMonteCarlo<E> {

    private AbsMap<E,LgInt> law;

    public MonteCarloEq(CollCapacity _capacity) {
        law = new ObjectMap<E,LgInt>(_capacity);
    }

    @Override
    public AbsMap<E,LgInt> getLaw() {
        return law;
    }
}
