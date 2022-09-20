package cards.gui.dialogs.help;

import code.util.AbsBasicMap;

public final class HelpIndexesTree extends AbsBasicMap<HelpIndexes,ElementHelp> {
    @Override
    protected boolean matchKeys(HelpIndexes _one, HelpIndexes _two) {
        return _one.eq(_two);
    }
}
