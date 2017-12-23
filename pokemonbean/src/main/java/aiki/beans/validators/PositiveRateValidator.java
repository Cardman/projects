package aiki.beans.validators;
import code.bean.validator.Message;
import code.bean.validator.Validator;
import code.maths.Rate;

public class PositiveRateValidator implements Validator {

    @Override
    public Message validate(Object _navigation, Object _node, Object _value) {
        if (Rate.isValid((String)_value)) {
            Rate rate_ = new Rate((String)_value);
            if (rate_.isZeroOrGt()) {
                return null;
            }
        }
        Message message_ = new Message();
        message_.setArgs((String)_value);
        return message_;
    }

}
