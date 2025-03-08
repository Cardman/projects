package aiki.beans;

import aiki.beans.help.*;
import aiki.db.*;
import code.util.*;
import code.util.core.*;

public final class BeanDisplayTranslatedKeyList implements BeanDisplayElt<CustList<TranslatedKey>> {

    @Override
    public int displayElt(CommonBean _rend, CustList<TranslatedKey> _info) {
        _rend.formatMessageDirAnc(StringUtil.join(WithFilterBean.values(_info), CommonBean.CST_SEP_DASH),new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_COMBO_COMBOS_HTML+ FightHelpBean.ANCHOR+StringUtil.join(WithFilterBean.keys(_info), DataBase.SEPARATOR_MOVES)));
        return 1;
    }
}
