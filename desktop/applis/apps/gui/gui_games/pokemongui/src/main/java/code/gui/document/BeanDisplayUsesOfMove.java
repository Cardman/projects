package code.gui.document;

import aiki.game.*;

public final class BeanDisplayUsesOfMove implements BeanDisplay<UsesOfMove> {
    @Override
    public int display(AbsBeanRender _rend, UsesOfMove _info, int _index) {
        _rend.formatMessageDirCts(Long.toString(_info.getCurrent()));
        _rend.formatMessageDirCts(Long.toString(_info.getMax()));
        return 2;
    }

}
