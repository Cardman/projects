package code.expressionlanguage.common;

import code.util.StringMap;

public final class TryReplacingType extends AbstractReplacingType {
    @Override
    public boolean replace(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i, int _diese) {
        tryReplaceType(_type, _varTypes, _str, _i, _diese);
        return false;
    }
}
