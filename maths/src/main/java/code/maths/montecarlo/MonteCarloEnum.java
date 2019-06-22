package code.maths.montecarlo;
import code.maths.LgInt;
import code.util.AbsMap;
import code.util.CollCapacity;
import code.util.EnumMap;


public final class MonteCarloEnum<E> extends AbMonteCarlo<E> {

    private AbsMap<E,LgInt> law;

    public MonteCarloEnum() {
        setLaw(new EnumMap<E,LgInt>());
    }

    public MonteCarloEnum(CollCapacity _capacity) {
        setLaw(new EnumMap<E,LgInt>(_capacity));
    }

    @Override
    public AbsMap<E,LgInt> getLaw() {
        return law;
    }
    public void setLaw(AbsMap<E, LgInt> _law) {
        law = _law;
    }
}
