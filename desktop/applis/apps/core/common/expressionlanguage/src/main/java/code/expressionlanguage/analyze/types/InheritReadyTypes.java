package code.expressionlanguage.analyze.types;

import code.util.StringList;

public final class InheritReadyTypes implements ReadyTypes {
    private StringList list;

    public InheritReadyTypes(StringList _list) {
        list = _list;
    }

    @Override
    public boolean isReady(String _type) {
        return StringList.contains(list,_type);
    }

}
