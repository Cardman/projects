package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.NumDiffDenNum;
import code.maths.Rate;
import code.util.BooleanList;
import code.util.BooleanMap;
import code.util.CollCapacity;


public final class MonteCarloBoolean extends AbMonteCarlo<Boolean> {

    private BooleanMap<LgInt> law;

    public MonteCarloBoolean() {
        setLaw(new BooleanMap<LgInt>());
    }
    
    public MonteCarloBoolean(CollCapacity _capacity) {
        setLaw(new BooleanMap<LgInt>(_capacity));
    }

    @Override
    public BooleanMap<LgInt> getLaw() {
        return law;
    }

    public void setLaw(BooleanMap<LgInt> _law) {
        law = _law;
    }
}
