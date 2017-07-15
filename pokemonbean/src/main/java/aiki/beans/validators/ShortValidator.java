package aiki.beans.validators;
import org.w3c.dom.Node;

import code.bean.validator.Message;
import code.bean.validator.Validator;
import code.bean.validator.ValidatorException;

public class ShortValidator implements Validator {

    @Override
    public void validate(Object _navigation, Node _node, Object _value) {
        try {
            short nb_ = Short.parseShort(_value.toString());
            if (nb_ >= 0) {
                return;
            }
        } catch (RuntimeException _0) {
        }
        Message message_;
        message_ = new Message();
        message_.setArgs(_value);
        throw new ValidatorException(message_);
    }

}
