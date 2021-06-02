package code.expressionlanguage.common;


import code.util.StringList;

public final class CommonLookingSuperTypes extends AbstractLookingSuperTypes {
    private final StringList list;
    public CommonLookingSuperTypes(StringList _list) {
        list = _list;
    }
    @Override
    protected int sizeType() {
        return list.size();
    }

    @Override
    protected String getType(int _index) {
        return list.get(_index);
    }
}
