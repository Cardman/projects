package aiki.beans.validators;
import code.formathtml.structs.Message;
import code.bean.validator.Validator;
import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;

public class ShortValidator implements Validator {

    @Override
    public Message validate(Object _value) {
        LongInfo nb_ = parseShort((String)_value);
        if (nb_.isValid() && nb_.getValue() >= 0) {
            return null;
        }
        Message message_;
        message_ = new Message();
        message_.setArgs((String)_value);
        return message_;
    }

    private static LongInfo parseShort(String _string) {
        LongInfo int_ = NumParsers.parseLong(_string,10);
        if (int_.outOfRange(Short.MIN_VALUE,Short.MAX_VALUE)) {
            return new LongInfo();
        }
        return int_;
    }
}