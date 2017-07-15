package code.formathtml.classes;
import org.w3c.dom.Node;

import code.bean.validator.Message;
import code.bean.validator.Validator;
import code.bean.validator.ValidatorException;

public class MyValidator implements Validator {

    @Override
    public void validate(Object _navigation, Node _node, Object _value) {
        try {
            Rate rate_ = new Rate((String)_value);
            if (rate_.isZero()) {
                Message message_ = new Message();
                message_.setArgs(_value);
                message_.setMessage("0 is unacceptable");
                message_.setFormatMessage(false);
                throw new ValidatorException(message_);
            }
//        } catch (NullPointerException e) {
//            Message message_ = new Message();
//            message_.setArgs(_value);
//            message_.setMessage("0 is unacceptable");
//            message_.setFormatMessage(false);
//            throw new ValidatorException(message_);
//        } catch (Exception e) {
        } catch (BadRateException _0) {
            Message message_ = new Message();
            message_.setArgs(_value);
            message_.setMessage("{0} is not a no zero rate");
            message_.setFormatMessage(true);
            throw new ValidatorException(message_);
        } catch (ClassCastException _0) {
            Message message_ = new Message();
            message_.setArgs(_value);
            message_.setMessage("{0} is not a no zero rate");
            message_.setFormatMessage(true);
            throw new ValidatorException(message_);
        }
    }

}
