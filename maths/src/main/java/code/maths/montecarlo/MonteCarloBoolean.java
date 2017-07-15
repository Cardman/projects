package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.util.BooleanList;
import code.util.BooleanMap;
import code.util.annot.RwXml;

@RwXml
public final class MonteCarloBoolean extends AbMonteCarlo<Boolean> {

//    private static final LgInt MAX_RANDOM = LgInt.getMaxLongPlusOne();

//    private static final int NB_RAND = 4;

    private BooleanMap<LgInt> law = new BooleanMap<LgInt>();

    public MonteCarloBoolean() {
    }

    public MonteCarloBoolean(Boolean _event, Rate _rateEvent, Boolean _otherEvent) {
        if (_rateEvent.greaterOrEqualsOne()) {
            addEvent(_event,LgInt.one());
        } else {
            NumDiffDenNum p_ = _rateEvent.getNumDiffDenNum();
            addEvent(_otherEvent, p_.getDiffDenNumerator());
            addEvent(_event, p_.getNumerator());
        }
        deleteZeroEvents();
    }

    @Override
    public BooleanList events() {
        return law.getKeys();
    }

    @Override
    protected BooleanMap<LgInt> getLaw() {
        return law;
    }

}
