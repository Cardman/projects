package aiki.beans;

import code.util.*;

public final class BeanDisplayVars implements BeanDisplayElt<EntryCust<String,String>> {
    @Override
    public int displayElt(CommonBean _rend, EntryCust<String,String> _info) {
        _rend.formatMessageDir(_info.getKey()+" : "+_info.getValue());
        return 1;
    }
}
