package aiki.beans.validators;
import code.bean.validator.Message;
import code.bean.validator.Validator;

public class ShortValidator implements Validator {

    @Override
    public Message validate(Object _navigation, Object _node, Object _value) {
        try {
            short nb_ = Short.parseShort(_value.toString());
            if (nb_ >= 0) {
                return null;
            }
        } catch (RuntimeException _0) {
        }
        Message message_;
        message_ = new Message();
        message_.setArgs(_value);
        return message_;
    }

}
