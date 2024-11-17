package aiki.gui.components.editor;

import code.gui.*;
import code.maths.*;
import code.util.*;

public final class StatisticLgIntDisplayEntryCust<E> implements DisplayEntryCust<Integer, EditedCrudPair<E, LgInt>> {
    private final AbsMap<E,String> messages;

    public StatisticLgIntDisplayEntryCust(AbsMap<E, String> _m) {
        this.messages = _m;
    }

    @Override
    public String display(Integer _k, EditedCrudPair<E,LgInt> _v) {
        return messages.getVal(_v.getKey())+":"+_v.getValue().toNumberString();
    }
}
