package code.maths.montecarlo;

import code.maths.LgInt;
import code.util.CollCapacity;
import code.util.CustList;

public final class MonteCarloList<E> extends AbMonteCarlo<E> {
    private final CustList<EventFreq<E>> events = new CustList<EventFreq<E>>();

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
    public LgInt sum() {
        LgInt somme_= LgInt.zero();
        for (EventFreq<E> e: events) {
            somme_.addNb(e.getFreq());
        }
        return somme_;
    }

    public CustList<EventFreq<E>> getEvents() {
        return events;
    }

    @Override
    public int nbEvents() {
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
