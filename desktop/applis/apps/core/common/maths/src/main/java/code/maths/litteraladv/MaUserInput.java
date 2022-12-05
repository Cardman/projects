package code.maths.litteraladv;

import code.util.CustList;
import code.util.Replacement;

public final class MaUserInput {
    private final String el;
    private final CustList<Replacement> repl;
    private final boolean ok;

    public MaUserInput(String _e, CustList<Replacement> _r, boolean _o) {
        this.el = _e;
        this.repl = _r;
        this.ok = _o;
    }

    public CustList<Replacement> getRepl() {
        return repl;
    }

    public String getEl() {
        return el;
    }

    public boolean isOk() {
        return ok;
    }
}
