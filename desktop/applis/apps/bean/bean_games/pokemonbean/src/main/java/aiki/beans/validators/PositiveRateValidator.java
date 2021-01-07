package aiki.beans.validators;
import aiki.beans.RateStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;
import code.bean.validator.Validator;
import code.maths.Rate;

public class PositiveRateValidator implements Validator {

    @Override
    public Message validate(Struct _value) {
        if (_value instanceof RateStruct) {
            return procRate(((RateStruct) _value).getRate());
        }
        return null;
    }

    private static Message procRate(Rate _value) {
        if (_value.isZeroOrGt()) {
            return null;
        }
        Message message_ = new Message();
        message_.setArgs(_value.toNumberString());
        return message_;
    }

}