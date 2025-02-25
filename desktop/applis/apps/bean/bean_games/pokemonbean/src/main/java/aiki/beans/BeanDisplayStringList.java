package aiki.beans;

import aiki.beans.fight.*;
import code.util.*;
import code.util.core.*;

public final class BeanDisplayStringList implements BeanDisplay<StringList> {

    @Override
    public int display(CommonBean _rend, StringList _info, int _index) {
        _rend.formatMessageDirCts(StringUtil.join(_info, CommonFightBean.MOVES_SEPARATOR));
        return 1;
    }
}
