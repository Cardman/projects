package code.gui.document;

import code.maths.*;

public final class BeanDisplayLgInt implements BeanDisplay<LgInt> {
    @Override
    public int display(AbsBeanRender _rend, LgInt _info, int _index) {
        _rend.formatMessageDirCts(_info.toNumberString());
        return 1;
    }

}
