package code.expressionlanguage.methods.util;

import code.util.StringList;

public final class BadElError extends FoundErrorInterpret {

    private int offsetInEl;

    private String el;

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,Integer.toString(offsetInEl),SEP_INFO,el,SEP_INFO);
    }

    public int getOffsetInEl() {
        return offsetInEl;
    }

    public void setOffsetInEl(int _offsetInEl) {
        offsetInEl = _offsetInEl;
    }

    public String getEl() {
        return el;
    }

    public void setEl(String _el) {
        el = _el;
    }
}
