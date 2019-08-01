package aiki.beans.validators;
import code.bean.validator.Message;
import code.bean.validator.Validator;

public class UnselectedRadio implements Validator {

    @Override
    public Message validate(Object _value) {
        if (_value == null) {
            return Message.newStandardMessage();
        }
        return null;
    }
}