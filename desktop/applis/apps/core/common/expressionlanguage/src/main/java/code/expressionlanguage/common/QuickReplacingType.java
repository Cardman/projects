package code.expressionlanguage.common;

import code.util.StringMap;

public final class QuickReplacingType extends AbstractReplacingType {

    @Override
    public boolean replace(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i, int _diese) {
        quickReplaceType(_str, _type, _varTypes, _diese, _i);
        return false;
    }
    private static void quickReplaceType(StringBuilder _str, String _type, StringMap<String> _varTypes, int _diese, int _max) {
        String sub_ = _type.substring(_diese+1, _max);
        String value_ = _varTypes.getVal(sub_);
        if (value_ == null) {
            _str.append('#');
            _str.append(sub_);
            return;
        }
        _str.append(value_);
    }

}
