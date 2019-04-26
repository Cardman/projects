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
        law = new EnumMap<E,LgInt>();
    }

    public MonteCarloEnum(E _event, Rate _rateEvent, E _otherEvent) {
        law = new EnumMap<E,LgInt>();
        if (_rateEvent.greaterOrEqualsOne()) {
            addEvent(_event,LgInt.one());
        } else {
            NumDiffDenNum p_ = _rateEvent.getNumDiffDenNum();
            addEvent(_otherEvent, p_.getDiffDenNumerator());
            addEvent(_event, p_.getNumerator());
        }
    }

    
    public MonteCarloEnum(CollCapacity _capacity) {
        law = new EnumMap<E,LgInt>(_capacity);
    }

    @Override
    public EnumMap<E,LgInt> getLaw() {
        return law;
    }
    public void setLaw(EnumMap<E, LgInt> _law) {
        law = _law;
    }
}
