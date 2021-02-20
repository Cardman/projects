package code.maths.montecarlo;
import code.maths.LgInt;
import code.util.AbsMap;
import code.util.CollCapacity;
import code.util.StringMap;


public final class MonteCarloString extends AbMonteCarloMap<String> {

    private AbsMap<String,LgInt> law;

    public MonteCarloString() {
        setLaw(new StringMap<LgInt>());
    }

    public MonteCarloString(CollCapacity _capacity) {
        setLaw(new StringMap<LgInt>(_capacity));
    }
    @Override
    public AbsMap<String,LgInt> getLaw() {
        return law;
    }

    public void setLaw(AbsMap<String,LgInt> _law) {
        law = _law;
    }
}
