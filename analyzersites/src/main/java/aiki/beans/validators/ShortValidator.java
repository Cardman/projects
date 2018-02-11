package aiki.beans.validators;
import code.bean.validator.Message;
import code.bean.validator.Validator;

public class ShortValidator extends Validator {

    public ShortValidator() {
        setClassName("ShortValidator");
    }

    @Override
    public Message validate(Object _navigation, Object _node, Object _value) {
        try {
            short nb_ = Short.parseShort((String)_value);
            if (nb_ >= 0) {
                return null;
            }
        } catch (RuntimeException _0) {
        }
        Message message_;
        message_ = new Message();
        message_.setArgs((String)_value);
        return message_;
    }

}
