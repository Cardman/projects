package code.expressionlanguage.analyze.types;

import code.util.StringList;
import code.util.core.StringUtil;

public final class InheritReadyTypes implements ReadyTypes {
    private StringList list;

    public InheritReadyTypes(StringList _list) {
        list = _list;
    }

    @Override
    public boolean isReady(String _type) {
        return StringUtil.contains(list,_type);
    }

}
