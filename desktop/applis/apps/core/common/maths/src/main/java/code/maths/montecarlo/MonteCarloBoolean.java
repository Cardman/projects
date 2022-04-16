package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.BoolVal;


public final class MonteCarloBoolean extends AbMonteCarlo<BoolVal> {

    private final MonteCarloList<BoolVal> law;
    private final CustList<EventFreq<BoolVal>> events;

    public MonteCarloBoolean() {
        MonteCarloList<BoolVal> lawBool_ = new MonteCarloList<BoolVal>();
        law = lawBool_;
        events = lawBool_.getEvents();
    }
    
    public MonteCarloBoolean(CollCapacity _capacity) {
        MonteCarloList<BoolVal> lawBool_ = new MonteCarloList<BoolVal>(_capacity);
        law = lawBool_;
        events = lawBool_.getEvents();
    }

    public Rate normalizedRate(BoolVal _event) {
        LgInt sum_ = sum();
        return new Rate(rate(_event), sum_);
    }
    public LgInt rate(BoolVal _event) {
        LgInt sum_ = LgInt.zero();
        for (EventFreq<BoolVal> e: getEvents()) {
            if (e.getEvent() == _event) {
                sum_.addNb(e.getFreq());
            }
        }
        return sum_;
    }
    public boolean containsEvent(BoolVal _event) {
        for (EventFreq<BoolVal> e: getEvents()) {
            if (e.getEvent() == _event) {
                return true;
            }
        }
        return false;
    }

    @Override
    public BoolVal getEvent(int _index) {
        return law.getEvent(_index);
    }

    @Override
    public LgInt getFreq(int _index) {
        return law.getFreq(_index);
    }

    @Override
    public CustList<BoolVal> events() {
        return law.events();
    }

    @Override
    public LgInt sum() {
        return law.sum();
    }

    public void addEvent(BoolVal _event, LgInt _probaRelative) {
        law.addEvent(_event, _probaRelative);
    }

    public void addQuickEvent(BoolVal _event, LgInt _probaRelative) {
        law.addQuickEvent(_event, _probaRelative);
    }

    @Override
    public int nbEvents() {
        return law.nbEvents();
    }

    public CustList<EventFreq<BoolVal>> getEvents() {
        return events;
    }
}
