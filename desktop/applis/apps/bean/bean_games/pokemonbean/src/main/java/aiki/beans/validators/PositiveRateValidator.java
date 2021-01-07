package aiki.beans.validators;
import aiki.beans.DefaultStruct;
import aiki.beans.RateStruct;
import code.formathtml.structs.Message;
import code.bean.validator.Validator;
import code.maths.Rate;

public class PositiveRateValidator implements Validator {

    @Override
    public Message validate(Object _value) {
        if (_value instanceof RateStruct) {
            return procRate(((RateStruct) _value).getRate());
        }
        if (_value instanceof Rate) {
            return procRate((Rate) _value);
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