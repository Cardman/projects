package aiki.beans.validators;

import code.bean.validator.Validator;
import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;
import code.formathtml.structs.Message;
import code.formathtml.util.BeanLgNames;
import code.util.StringList;
import code.util.core.StringUtil;

public class ShortValidator implements Validator {

    @Override
    public Message validate(StringList _values) {
        String value_ = StringUtil.nullToEmpty(BeanLgNames.oneElt(_values));
        LongInfo nb_ = parseShort(NumParsers.parseLong(value_,10));
        return procInfo(nb_,value_);
    }

    private static Message procInfo(LongInfo _nb,String _v) {
        if (_nb.isValid() && _nb.getValue() >= 0) {
            return null;
        }
        Message message_;
        message_ = new Message();
        message_.setContent("");
        message_.setArgs(_v);
        return message_;
    }

    private static LongInfo parseShort(LongInfo _int) {
        if (_int.outOfRange(Short.MIN_VALUE,Short.MAX_VALUE)) {
            return new LongInfo();
        }
        return _int;
    }
}