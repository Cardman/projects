package aiki.gui.components.editor;

import aiki.fight.util.LevelMove;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparingLevelMove implements Comparing<LevelMove> {
    private final StringMap<String> messages;

    public ComparingLevelMove(StringMap<String> _m) {
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
