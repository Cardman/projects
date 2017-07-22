package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.CapacityInit;
import code.util.annot.RwXml;

@RwXml
public final class MonteCarloString extends AbMonteCarlo<String> {

//    private static final LgInt MAX_RANDOM = LgInt.getMaxLongPlusOne();

//    private static final int NB_RAND = 4;

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
        deleteZeroEvents();
    }

    @CapacityInit
    private MonteCarloString(int _capacity) {
    	law = new StringMap<LgInt>(_capacity);
    }

    @Override
    public StringList events() {
        return law.getKeys();
    }

    @Override
    protected StringMap<LgInt> getLaw() {
        return law;
    }

}
