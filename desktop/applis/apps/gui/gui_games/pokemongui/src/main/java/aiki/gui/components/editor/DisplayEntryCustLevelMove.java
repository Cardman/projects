package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.gui.*;
import code.util.*;
import code.util.core.*;

public final class DisplayEntryCustLevelMove implements DisplayEntryCust<Integer, LevelMove> {
    private final AbsMap<String, String> messages;

    public DisplayEntryCustLevelMove(AbsMap<String, String> _m) {
        this.messages = _m;
    }

    @Override
    public String display(Integer _k, LevelMove _v) {
        return _v.getLevel()+ConverterCommonMapUtil.K_V+ StringUtil.nullToEmpty(messages.getVal(_v.getMove()));
    }
}
