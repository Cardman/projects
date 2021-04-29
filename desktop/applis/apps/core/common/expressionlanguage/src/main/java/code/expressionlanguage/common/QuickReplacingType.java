package code.expressionlanguage.common;

import code.util.StringMap;

public final class QuickReplacingType extends AbstractReplacingType {

    @Override
    protected boolean replace(String _type, StringMap<String> _varTypes, StringBuilder _str, int _i) {
        quickReplaceType(_str, _type, _varTypes, _i);
        return false;
    }
    private void quickReplaceType(StringBuilder _str, String _type, StringMap<String> _varTypes, int _max) {
        int diese_ = getDiese();
        String sub_ = _type.substring(diese_+1, _max);
        String value_ = _varTypes.getVal(sub_);
        if (value_ == null) {
            _str.append('#');
            _str.append(sub_);
            return;
        }
        _str.append(value_);
    }

}
