package code.maths.montecarlo;
import code.maths.LgInt;
import code.util.AbsMap;
import code.util.BooleanMap;
import code.util.CollCapacity;


public final class MonteCarloBoolean extends AbMonteCarloMap<Boolean> {

    private AbsMap<Boolean,LgInt> law;

    public MonteCarloBoolean() {
        setLaw(new BooleanMap<LgInt>());
    }
    
    public MonteCarloBoolean(CollCapacity _capacity) {
        setLaw(new BooleanMap<LgInt>(_capacity));
    }

    @Override
    public AbsMap<Boolean,LgInt> getLaw() {
        return law;
    }

    public void setLaw(AbsMap<Boolean,LgInt> _law) {
        law = _law;
    }
}
