package code.gui.document;

import aiki.game.fight.util.*;

public final class BeanDisplayCopiedMove implements BeanDisplay<CopiedMove> {
    @Override
    public int display(AbsBeanRender _rend, CopiedMove _info, int _index) {
        _rend.formatMessageDirCts(_info.getMove());
        _rend.formatMessageDirCts(Long.toString(_info.getPp()));
        return 2;
    }

}
