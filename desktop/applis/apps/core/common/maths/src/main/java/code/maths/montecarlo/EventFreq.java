package code.maths.montecarlo;

import code.maths.LgInt;

public final class EventFreq<E> {
    private final E event;
    private final LgInt freq;

    public EventFreq(E _event, LgInt _freq) {
        this.event = _event;
        this.freq = _freq;
    }

    public boolean match(E _in) {
        return event == _in;
    }
    public E getEvent() {
        return event;
    }

    public LgInt getFreq() {
        return freq;
    }
}
