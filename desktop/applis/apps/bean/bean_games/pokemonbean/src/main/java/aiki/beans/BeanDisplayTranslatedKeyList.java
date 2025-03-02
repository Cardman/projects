package aiki.beans;

import code.util.*;
import code.util.core.*;

public final class BeanDisplayTranslatedKeyList implements BeanDisplayElt<CustList<TranslatedKey>> {

    @Override
    public int displayElt(CommonBean _rend, CustList<TranslatedKey> _info) {
        _rend.formatMessageDir(StringUtil.join(WithFilterBean.values(_info), CommonBean.CST_SEP_DASH));
        return 1;
    }
}
