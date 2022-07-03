package code.mock;

import code.util.CustList;

public final class MockTyping<T> {
    private final CustList<T> events;
    private int index;

    public MockTyping(CustList<T> _evs) {
        this.events = _evs;
    }

    T incr(T _def) {
        if (index >= getEvents().size()) {
            return _def;
        }
        T ans_ = events.get(index);
        if (index +1< events.size()) {
            index++;
        } else {
            index = 0;
        }
        return ans_;
    }
    public CustList<T> getEvents() {
        return events;
    }
}
