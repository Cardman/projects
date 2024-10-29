package code.maths.montecarlo;

import code.maths.LgInt;
import code.util.AbsMap;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.IntIndexOfEntryUtil;

public abstract class MonteCarloList<E> extends AbMonteCarlo<E> {
    private final CustList<EventFreq<E>> events;

    protected MonteCarloList() {
        events = new CustList<EventFreq<E>>();
    }
    protected MonteCarloList(CollCapacity _capacity) {
        events = new CustList<EventFreq<E>>(_capacity);
    }

    protected MonteCarloList(AbsMap<E,LgInt> _other) {
        events = new CustList<EventFreq<E>>();
        int len_ = _other.size();
        for (int i = 0; i < len_; i++) {
            addQuickEvent(_other.getKey(i), _other.getValue(i));
        }
    }
    @Override
    public E getEvent(int _index) {
        return events.get(_index).getEvent();
    }

    @Override
    public LgInt getFreq(int _index) {
        return events.get(_index).getFreq();
    }

    @Override
    public CustList<E> events() {
        CustList<E> evs_ = new CustList<E>(new CollCapacity(events.size()));
        for (EventFreq<E> e: events) {
            evs_.add(e.getEvent());
        }
        return evs_;
    }
    @Override
    public boolean containsEvent(E _event) {
        for (EventFreq<E> e: getEvents()) {
            if (matchesEvent(_event,e.getEvent())) {
                return true;
            }
        }
        return false;
    }
    @Override
    public CustList<E> eventsDiff() {
        return new IntIndexOfEntryUtil<E>(this).differentKeys();
    }

    @Override
    public LgInt sum() {
        LgInt somme_= LgInt.zero();
        for (EventFreq<E> e: events) {
            somme_.addNb(e.getFreq());
        }
        return somme_;
    }

    @Override
    public LgInt rate(E _e) {
        LgInt somme_= LgInt.zero();
        for (EventFreq<E> e: events) {
            if (matchesEvent(_e,e.getEvent())) {
                somme_.addNb(e.getFreq());
            }
        }
        return somme_;
    }

    @Override
    public int indexOfEntry(E _key, int _from) {
        int len_ = nbEvents();
        for (int i = _from; i < len_; i++) {
            if (matchesEvent(getEvent(i),_key)) {
                return i;
            }
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    protected abstract boolean matchesEvent(E _one, E _two);

    public CustList<EventFreq<E>> getEvents() {
        return events;
    }

    @Override
    public int nbEvents() {
        return size();
    }

    @Override
    public int size() {
        return getEvents().size();
    }

    @Override
    public void addEvent(E _event, LgInt _probaRelative){
        addQuickEvent(_event, _probaRelative);
    }

    @Override
    public void addQuickEvent(E _event, LgInt _probaRelative){
        events.add(new EventFreq<E>(_event, _probaRelative));
    }

}
