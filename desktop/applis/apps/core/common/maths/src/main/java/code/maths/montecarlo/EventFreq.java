package code.maths.montecarlo;

import code.maths.LgInt;

public final class EventFreq<E> {
    private final E event;
    private final LgInt freq;

    public EventFreq(E _event, LgInt _freq) {
        this.event = _event;
        this.freq = _freq;
    }

    public E getEvent() {
        return event;
    }

    public LgInt getFreq() {
        return freq;
    }
}
