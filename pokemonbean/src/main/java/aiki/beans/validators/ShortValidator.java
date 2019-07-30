package aiki.beans.validators;
import code.bean.validator.Message;
import code.bean.validator.Validator;
import code.expressionlanguage.stds.NumParsers;

public class ShortValidator extends Validator {

    public ShortValidator() {
        setClassName("ShortValidator");
    }

    @Override
    public Message validate(Object _value) {
        Short nb_ = parseShort((String)_value);
        if (nb_ != null && nb_ >= 0) {
            return null;
        }
        Message message_;
        message_ = new Message();
        message_.setArgs((String)_value);
        return message_;
    }

    public static Short parseShort(String _string) {
        Long int_ = NumParsers.parseLong(_string,10);
        if (int_ == null) {
            return null;
        }
        if (int_ < Short.MIN_VALUE) {
            return null;
        }
        if (int_ > Short.MAX_VALUE) {
            return null;
        }
        return int_.shortValue();
    }
}