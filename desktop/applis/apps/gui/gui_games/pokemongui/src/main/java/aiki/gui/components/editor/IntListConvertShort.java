package aiki.gui.components.editor;

import code.util.*;

public final class IntListConvertShort implements IntListConvert<Short, Shorts> {
    @Override
    public Shorts fromList(CustList<Short> _ls) {
        return new Shorts(_ls);
    }

    @Override
    public CustList<Short> toList(Shorts _ls) {
        return _ls;
    }
}
