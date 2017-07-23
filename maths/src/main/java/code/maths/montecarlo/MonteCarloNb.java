package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.annot.CapacityInit;
import code.util.annot.RwXml;

@RwXml
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
        checkEvents();
    }

    @CapacityInit
    private MonteCarloNb(int _capacity) {
        law = new NumberMap<E,LgInt>(_capacity);
    }

    @Override
    public Numbers<E> events() {
        return law.getKeys();
    }

    @Override
    protected NumberMap<E,LgInt> getLaw() {
        return law;
    }
}
