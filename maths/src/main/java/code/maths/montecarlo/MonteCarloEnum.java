package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.util.CollCapacity;
import code.util.EnumList;
import code.util.EnumMap;


public final class MonteCarloEnum<E extends Enum<E>> extends AbMonteCarlo<E> {

    private EnumMap<E,LgInt> law;

    public MonteCarloEnum() {
        setLaw(new EnumMap<E,LgInt>());
    }

    public MonteCarloEnum(CollCapacity _capacity) {
        setLaw(new EnumMap<E,LgInt>(_capacity));
    }

    @Override
    public EnumMap<E,LgInt> getLaw() {
        return law;
    }
    public void setLaw(EnumMap<E, LgInt> _law) {
        law = _law;
    }
}
