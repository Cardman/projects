package aiki.beans;

import code.maths.*;

public final class BeanDisplayLgInt implements BeanDisplay<LgInt> {
    @Override
    public int display(CommonBean _rend, LgInt _info, int _index) {
        _rend.formatMessageDirCts(_info.toNumberString());
        return 1;
    }

}
