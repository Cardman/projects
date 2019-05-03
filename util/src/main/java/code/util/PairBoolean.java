package code.util;


/** Not compared in tests, not use in res html, not instrospected*/

public final class PairBoolean {

    private boolean first;

    private boolean second;

    public PairBoolean(boolean _first,boolean _second) {
        first = _first;
        second = _second;
    }

    public boolean getFirst() {
        return first;
    }

    public void setFirst(boolean _first) {
        first = _first;
    }

    public boolean getSecond() {
        return second;
    }

    public void setSecond(boolean _second) {
        second = _second;
    }

}
