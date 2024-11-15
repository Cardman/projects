package aiki.gui.components.editor;

import aiki.fight.enums.*;
import code.gui.*;
import code.maths.*;
import code.util.*;

public final class StatisticLgIntDisplayEntryCust implements DisplayEntryCust<Integer, EditedCrudPair<Statistic, LgInt>> {
    private final AbsMap<Statistic,String> messages;

    public StatisticLgIntDisplayEntryCust(AbsMap<Statistic, String> _m) {
        this.messages = _m;
    }

    @Override
    public String display(Integer _k, EditedCrudPair<Statistic,LgInt> _v) {
        return messages.getVal(_v.getKey())+":"+_v.getValue().toNumberString();
    }
}
