package aiki.beans.validators;

import code.bean.nat.RateStruct;
import code.bean.validator.Validator;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;
import code.maths.Rate;

public class RateValidator implements Validator {

    @Override
    public Message validate(Struct _value) {
        if (_value instanceof RateStruct) {
            return procRate(((RateStruct) _value).getInstance());
        }
        return null;
    }

    private static Message procRate(Rate _value) {
        if (_value.isZeroOrGt()) {
            if (!_value.isZero()) {
                return null;
            }
        }
        Message message_ = new Message();
        message_.setArgs(_value.toNumberString());
        return message_;
    }

}