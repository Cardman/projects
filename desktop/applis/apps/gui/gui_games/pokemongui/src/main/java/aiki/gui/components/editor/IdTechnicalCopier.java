package aiki.gui.components.editor;

import code.util.*;

public final class IdTechnicalCopier<T> implements AbsTechnicalCopier<T> {
    @Override
    public T copy(T _e) {
        return _e;
    }


    public CustList<T> copy(AbsTechnicalCopier<T> _cp, CustList<T> _e) {
        CustList<T> cp_ = new CustList<T>();
        for (T l: _e) {
            cp_.add(_cp.copy(l));
        }
        return cp_;
    }
}
