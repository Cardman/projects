package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CollCapacity;
import code.util.CustList;


public final class MonteCarloBoolean extends AbMonteCarlo<Boolean> {

    private final MonteCarloList<Boolean> law;
    private final CustList<EventFreq<Boolean>> events;

    public MonteCarloBoolean() {
        MonteCarloList<Boolean> lawBool_ = new MonteCarloList<Boolean>();
        law = lawBool_;
        events = lawBool_.getEvents();
    }
    
    public MonteCarloBoolean(CollCapacity _capacity) {
        MonteCarloList<Boolean> lawBool_ = new MonteCarloList<Boolean>(_capacity);
        law = lawBool_;
        events = lawBool_.getEvents();
    }

    public Rate normalizedRate(boolean _event) {
        LgInt sum_ = sum();
        return new Rate(rate(_event), sum_);
    }
    public LgInt rate(boolean _event) {
        LgInt sum_ = LgInt.zero();
        for (EventFreq<Boolean> e: getEvents()) {
            if (e.match(_event)) {
                sum_.addNb(e.getFreq());
            }
        }
        return sum_;
    }
    public boolean containsEvent(boolean _event) {
        for (EventFreq<Boolean> e: getEvents()) {
            if (e.match(_event)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean getEvent(int _index) {
        return law.getEvent(_index);
    }

    @Override
    public LgInt getFreq(int _index) {
        return law.getFreq(_index);
    }

    @Override
    public CustList<Boolean> events() {
        return law.events();
    }

    @Override
    public LgInt sum() {
        return law.sum();
    }

    public void addEvent(boolean _event, LgInt _probaRelative) {
        law.addEvent(_event, _probaRelative);
    }

    public void addQuickEvent(boolean _event, LgInt _probaRelative) {
        law.addQuickEvent(_event, _probaRelative);
    }

    @Override
    public int nbEvents() {
        return law.nbEvents();
    }

    public CustList<EventFreq<Boolean>> getEvents() {
        return events;
    }
}
