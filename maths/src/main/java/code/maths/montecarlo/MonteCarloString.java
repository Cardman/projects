package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.util.CollCapacity;
import code.util.StringList;
import code.util.StringMap;


public final class MonteCarloString extends AbMonteCarlo<String> {

    private StringMap<LgInt> law;

    public MonteCarloString() {
        law = new StringMap<LgInt>();
    }

    public MonteCarloString(String _event, Rate _rateEvent, String _otherEvent) {
        law = new StringMap<LgInt>();
        if (_rateEvent.greaterOrEqualsOne()) {
            addEvent(_event,LgInt.one());
        } else {
            NumDiffDenNum p_ = _rateEvent.getNumDiffDenNum();
            addEvent(_otherEvent, p_.getDiffDenNumerator());
            addEvent(_event, p_.getNumerator());
        }
    }

    
    public MonteCarloString(CollCapacity _capacity) {
        law = new StringMap<LgInt>(_capacity);
    }

    @Override
    public StringList events() {
        return law.getKeys();
    }

    @Override
    public StringMap<LgInt> getLaw() {
        return law;
    }

    public void setLaw(StringMap<LgInt> _law) {
        law = _law;
    }
}
