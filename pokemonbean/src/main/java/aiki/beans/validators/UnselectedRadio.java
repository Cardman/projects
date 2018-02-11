package aiki.beans.validators;
import code.bean.validator.Message;
import code.bean.validator.Validator;

public class UnselectedRadio extends Validator {

    public UnselectedRadio() {
        setClassName("UnselectedRadio");
    }
    @Override
    public Message validate(Object _navigation, Object _node, Object _value) {
        if (_value == null) {
            return Message.newStandardMessage();
        }
        return null;
    }

}