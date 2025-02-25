package aiki.beans;

import code.util.*;

public final class BeanDisplayMember implements BeanDisplayElt<EntryCust<Integer,String>> {
    @Override
    public int displayElt(CommonBean _rend, EntryCust<Integer,String> _info) {
        _rend.formatMessageDirAnc(_info.getValue(),new BeanAnchorToFighterEvent(_info.getKey(),_rend, _rend.getForms()));
        return 1;
    }
}
