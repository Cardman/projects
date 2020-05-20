package code.formathtml.classes;
import code.formathtml.structs.Message;
import code.bean.validator.Validator;
import code.util.StringList;

public class MyValidator implements Validator {

    @Override
    public Message validate(Object _value) {
        if (!(_value instanceof String)) {
            //Long or Boolean
            Message message_ = new Message();
            if (_value instanceof Boolean) {
                message_.setArgs(String.valueOf(((Boolean)_value).booleanValue()));
            } else {
                message_.setArgs(String.valueOf(((Long)_value).longValue()));
            }
            message_.setMessage(StringList.simpleStringsFormat("{0} is not a no zero rate", message_.getArgs()));
            return message_;
        }
        if (!Rate.matchesRate((String)_value)) {
            Message message_ = new Message();
            message_.setArgs((String) _value);
            message_.setMessage(StringList.simpleStringsFormat("{0} is not a no zero rate", message_.getArgs()));
            return message_;
        }
        Rate rate_ = new Rate((String)_value);
        if (rate_.isZero()) {
            Message message_ = new Message();
            message_.setArgs((String) _value);
            message_.setMessage("0 is unacceptable");
            return message_;
        }
        return null;
    }

}
