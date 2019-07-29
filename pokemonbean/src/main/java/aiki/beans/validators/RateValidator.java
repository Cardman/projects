package aiki.beans.validators;
import code.bean.validator.Message;
import code.bean.validator.Validator;
import code.maths.Rate;

public class RateValidator extends Validator {

    public RateValidator() {
        setClassName("RateValidator");
    }

    @Override
    public Message validate(Object _value) {
        if (Rate.isValid((String)_value)) {
            Rate rate_ = new Rate((String)_value);
            if (rate_.isZeroOrGt()) {
                if (!rate_.isZero()) {
                    return null;
                }
            }
        }
        Message message_ = new Message();
        message_.setArgs((String)_value);
        return message_;
    }

    @Override
    public Message validate(Object _navigation, Object _node, Object _value) {
        if (Rate.isValid((String)_value)) {
            Rate rate_ = new Rate((String)_value);
            if (rate_.isZeroOrGt()) {
                if (!rate_.isZero()) {
                    return null;
                }
            }
        }
        Message message_ = new Message();
        message_.setArgs((String)_value);
        return message_;
    }

}