package aiki.gui.components.editor;

import code.util.*;

public final class IntListConvertShort implements IntListConvert<Integer, Ints> {
    @Override
    public Ints fromList(CustList<Integer> _ls) {
        return new Ints(_ls);
    }

    @Override
    public CustList<Integer> toList(Ints _ls) {
        return _ls;
    }
}
