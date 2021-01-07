package aiki.beans.validators;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;
import code.bean.validator.Validator;
import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;

public class ShortValidator implements Validator {

    @Override
    public Message validate(Struct _value) {
        NumberStruct n_ = NumParsers.convertToNumber(_value);
        long val_ = n_.longStruct();
        LongInfo nb_ = parseShort(new LongInfo(val_));
        return procInfo(val_, nb_);
    }

    private static Message procInfo(long _value, LongInfo _nb) {
        if (_nb.isValid() && _nb.getValue() >= 0) {
            return null;
        }
        Message message_;
        message_ = new Message();
        message_.setArgs(Long.toString(_value));
        return message_;
    }

    private static LongInfo parseShort(LongInfo _int) {
        if (_int.outOfRange(Short.MIN_VALUE,Short.MAX_VALUE)) {
            return new LongInfo();
        }
        return _int;
    }
}