package aiki.gui.components.editor;

import code.util.*;

public final class IntListConvertString implements IntListConvert<String, StringList> {
    @Override
    public StringList fromList(CustList<String> _ls) {
        return new StringList(_ls);
    }

    @Override
    public CustList<String> toList(StringList _ls) {
        return _ls;
    }
}
