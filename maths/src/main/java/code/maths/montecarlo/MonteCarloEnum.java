package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.annot.RwXml;

@RwXml
public class MonteCarloEnum<E extends Enum<E>> extends AbMonteCarlo<E> {

    private EnumMap<E,LgInt> law = new EnumMap<E,LgInt>();

    public MonteCarloEnum() {
    }

    public MonteCarloEnum(E _event, Rate _rateEvent, E _otherEvent) {
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
    public EnumList<E> events() {
        return law.getKeys();
    }

    @Override
    protected EnumMap<E,LgInt> getLaw() {
        return law;
    }
}
