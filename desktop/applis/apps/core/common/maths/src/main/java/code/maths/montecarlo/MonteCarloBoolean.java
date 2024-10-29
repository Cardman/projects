package code.maths.montecarlo;
import code.maths.LgInt;
import code.util.AbsMap;
import code.util.CollCapacity;
import code.util.core.BoolVal;


public final class MonteCarloBoolean extends MonteCarloList<BoolVal> {

    public MonteCarloBoolean() {
    }
    
    public MonteCarloBoolean(CollCapacity _capacity) {
        super(_capacity);
    }

    public MonteCarloBoolean(AbsMap<BoolVal, LgInt> _other) {
        super(_other);
    }
    @Override
    protected boolean matchesEvent(BoolVal _one, BoolVal _two) {
        return _one == _two;
    }


}
