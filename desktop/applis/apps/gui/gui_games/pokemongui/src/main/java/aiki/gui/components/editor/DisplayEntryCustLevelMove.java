package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.gui.*;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DisplayEntryCustLevelMove implements DisplayEntryCust<Integer, LevelMove> {
    private final StringMap<String> messages;

    public DisplayEntryCustLevelMove(StringMap<String> _m) {
        this.messages = _m;
    }

    @Override
    public String display(Integer _k, LevelMove _v) {
        return _v.getLevel()+" "+ StringUtil.nullToEmpty(messages.getVal(_v.getMove()));
    }
}
