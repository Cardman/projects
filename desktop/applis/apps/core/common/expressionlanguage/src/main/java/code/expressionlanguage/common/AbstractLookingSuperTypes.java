package code.expressionlanguage.common;

import code.util.core.StringUtil;

public abstract class AbstractLookingSuperTypes {
    public String find(String _param) {
        String generic_ = "";
        int size_ = sizeType();
        for (int i = 0; i < size_; i++) {
            String g = getType(i);
            if (StringUtil.quickEq(StringExpUtil.getIdFromAllTypes(g), _param)) {
                generic_ = g;
                break;
            }
        }
        return generic_;
    }
    protected abstract int sizeType();
    protected abstract String getType(int _index);
}
