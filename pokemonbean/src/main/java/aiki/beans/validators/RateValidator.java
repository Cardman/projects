package aiki.beans.validators;
import org.w3c.dom.Node;

import code.bean.validator.Message;
import code.bean.validator.Validator;
import code.bean.validator.ValidatorException;
import code.maths.Rate;

public class RateValidator implements Validator {

    @Override
    public void validate(Object _navigation, Node _node, Object _value) {
        if (Rate.isValid(_value.toString())) {
            Rate rate_ = new Rate(_value.toString());
            if (rate_.isZeroOrGt()) {
                if (!rate_.isZero()) {
                    return;
                }
            }
        }
        Message message_ = new Message();
        message_.setArgs(_value);
        throw new ValidatorException(message_);
    }

}
