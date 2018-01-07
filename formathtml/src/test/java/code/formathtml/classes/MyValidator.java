package code.formathtml.classes;
import code.bean.validator.Message;
import code.bean.validator.Validator;

public class MyValidator extends Validator {

    public MyValidator() {
        setClassName("code.formathtml.classes.MyValidator");
    }
    @Override
    public Message validate(Object _navigation, Object _node, Object _value) {
        try {
            Rate rate_ = new Rate((String)_value);
            if (rate_.isZero()) {
                Message message_ = new Message();
                message_.setArgs((String) _value);
                message_.setMessage("0 is unacceptable");
                message_.setFormatMessage(false);
                return message_;
            }
            return null;
        } catch (BadRateException _0) {
            Message message_ = new Message();
            message_.setArgs((String) _value);
            message_.setMessage("{0} is not a no zero rate");
            message_.setFormatMessage(true);
            return message_;
        } catch (ClassCastException _0) {
            //Long or Boolean
            Message message_ = new Message();
            if (_value instanceof Boolean) {
                message_.setArgs(String.valueOf(((Boolean)_value).booleanValue()));
            } else {
                message_.setArgs(String.valueOf(((Number)_value).longValue()));
            }
            message_.setMessage("{0} is not a no zero rate");
            message_.setFormatMessage(true);
            return message_;
        }
    }

}
