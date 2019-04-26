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

    public MonteCarloNb(E _event, Rate _rateEvent, E _otherEvent) {
        law = new NumberMap<E,LgInt>();
        if (_rateEvent.greaterOrEqualsOne()) {
            addEvent(_event,LgInt.one());
        } else {
            NumDiffDenNum p_ = _rateEvent.getNumDiffDenNum();
            addEvent(_otherEvent, p_.getDiffDenNumerator());
            addEvent(_event, p_.getNumerator());
        }
    }

    
    public MonteCarloNb(CollCapacity _capacity) {
        law = new NumberMap<E,LgInt>(_capacity);
    }

    @Override
    public NumberMap<E,LgInt> getLaw() {
        return law;
    }
    public void setLaw(NumberMap<E, LgInt> _law) {
        law = _law;
    }
}
