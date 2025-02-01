package code.gui.document;

import code.maths.*;

public final class BeanDisplayRate implements BeanDisplay<Rate> {
    @Override
    public int display(AbsBeanRender _rend, Rate _info, int _index) {
        _rend.formatMessageDirCts(_info.toNumberString());
        return 1;
    }

}
