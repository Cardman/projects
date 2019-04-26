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

    public MonteCarloEq() {
        law = new ObjectNotNullMap<E,LgInt>();
    }

    public MonteCarloEq(E _event, Rate _rateEvent, E _otherEvent) {
        law = new ObjectNotNullMap<E,LgInt>();
        if (_rateEvent.greaterOrEqualsOne()) {
            addEvent(_event,LgInt.one());
        } else {
            NumDiffDenNum p_ = _rateEvent.getNumDiffDenNum();
            addEvent(_otherEvent, p_.getDiffDenNumerator());
            addEvent(_event, p_.getNumerator());
        }
    }

    
    public MonteCarloEq(CollCapacity _capacity) {
        law = new ObjectNotNullMap<E,LgInt>(_capacity);
    }

    @Override
    public ObjectNotNullMap<E,LgInt> getLaw() {
        return law;
    }
    public void setLaw(ObjectNotNullMap<E, LgInt> _law) {
        law = _law;
    }
}
