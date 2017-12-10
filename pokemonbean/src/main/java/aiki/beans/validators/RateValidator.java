package aiki.beans.validators;
import code.bean.validator.Message;
import code.bean.validator.Validator;
import code.maths.Rate;

public class RateValidator implements Validator {

    @Override
    public Message validate(Object _navigation, Object _node, Object _value) {
        if (Rate.isValid(_value.toString())) {
            Rate rate_ = new Rate(_value.toString());
            if (rate_.isZeroOrGt()) {
                if (!rate_.isZero()) {
                    return null;
                }
            }
        }
        Message message_ = new Message();
        message_.setArgs(_value);
        return message_;
    }

}
