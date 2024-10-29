package code.maths.montecarlo;
import code.maths.LgInt;
import code.util.AbsMap;
import code.util.CollCapacity;
import code.util.IdMap;


public final class MonteCarloEnum<E> extends AbMonteCarloMap<E> {

    private AbsMap<E,LgInt> law;

    public MonteCarloEnum() {
        setLaw(new IdMap<E,LgInt>());
    }

    public MonteCarloEnum(CollCapacity _capacity) {
        setLaw(new IdMap<E,LgInt>(_capacity));
    }

    public MonteCarloEnum(AbsMap<E,LgInt> _other) {
        setLaw(new IdMap<E,LgInt>());
        build(_other);
    }
    @Override
    public AbsMap<E,LgInt> getLaw() {
        return law;
    }
    public void setLaw(AbsMap<E, LgInt> _law) {
        law = _law;
    }
}
