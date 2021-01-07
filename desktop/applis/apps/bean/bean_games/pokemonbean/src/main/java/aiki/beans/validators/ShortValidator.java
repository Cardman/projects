package aiki.beans.validators;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;
import code.bean.validator.Validator;
import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;

public class ShortValidator implements Validator {

    @Override
    public Message validate(Object _value) {
        if (_value instanceof Struct) {
            NumberStruct n_ = NumParsers.convertToNumber((Struct) _value);
            LongInfo nb_ = parseShort(new LongInfo(n_.longStruct()));
            return procInfo(n_.longStruct(), nb_);
        }
        if (_value instanceof Byte) {
            LongInfo nb_ = parseShort(new LongInfo((Byte)_value));
            return procInfo(_value, nb_);
        }
        if (_value instanceof Short) {
            LongInfo nb_ = parseShort(new LongInfo((Short)_value));
            return procInfo(_value, nb_);
        }
        if (_value instanceof Integer) {
            LongInfo nb_ = parseShort(new LongInfo((Integer)_value));
            return procInfo(_value, nb_);
        }
        if (_value instanceof Long) {
            LongInfo nb_ = parseShort(new LongInfo((Long)_value));
            return procInfo(_value, nb_);
        }
        return null;
    }

    private static Message procInfo(Object _value, LongInfo _nb) {
        if (_nb.isValid() && _nb.getValue() >= 0) {
            return null;
        }
        Message message_;
        message_ = new Message();
        message_.setArgs("" + _value);
        return message_;
    }

    private static LongInfo parseShort(LongInfo _int) {
        if (_int.outOfRange(Short.MIN_VALUE,Short.MAX_VALUE)) {
            return new LongInfo();
        }
        return _int;
    }
}