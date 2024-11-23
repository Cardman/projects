package aiki.gui.components.editor;

import code.gui.*;
import code.util.*;
import code.util.core.*;

public final class DisplayEntryCustElt<E> implements DisplayEntryCust<Integer, E> {
    private final AbsMap<E, String> messages;

    public DisplayEntryCustElt(AbsMap<E, String> _m) {
        this.messages = _m;
    }

    @Override
    public String display(Integer _k, E _v) {
        return StringUtil.nullToEmpty(messages.getVal(_v));
    }
}
