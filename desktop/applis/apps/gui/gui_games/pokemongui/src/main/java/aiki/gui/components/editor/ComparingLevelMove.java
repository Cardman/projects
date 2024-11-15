package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.util.*;
import code.util.core.*;
import code.util.ints.*;

public final class ComparingLevelMove implements Comparing<LevelMove> {
    private final AbsMap<String, String> messages;

    public ComparingLevelMove(AbsMap<String, String> _m) {
        this.messages = _m;
    }
    @Override
    public int compare(LevelMove _one, LevelMove _two) {
        int res_ = NumberUtil.compareLg(_one.getLevel(), _two.getLevel());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return StringUtil.compareStrings(StringUtil.nullToEmpty(messages.getVal(_one.getMove())),StringUtil.nullToEmpty(messages.getVal(_two.getMove())));
    }
}
