package aiki.beans.validators;
import code.bean.validator.Message;
import code.bean.validator.Validator;
import code.expressionlanguage.stds.LgNames;

public class ShortValidator extends Validator {

    public ShortValidator() {
        setClassName("ShortValidator");
    }

    @Override
    public Message validate(Object _navigation, Object _node, Object _value) {
        Short nb_ = LgNames.parseShort((String)_value);
        if (nb_ != null && nb_ >= 0) {
            return null;
        }
        Message message_;
        message_ = new Message();
        message_.setArgs((String)_value);
        return message_;
    }

}