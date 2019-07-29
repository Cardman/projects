package code.formathtml.classes;
import code.bean.validator.Message;
import code.bean.validator.Validator;

public class UnselectedRadio extends Validator {

    private static final String NOT_SELECTED = "not selected";

    public UnselectedRadio() {
        setClassName("code.formathtml.classes.UnselectedRadio");
    }
    @Override
    public Message validate(Object _value) {
        if (_value == null) {
            return Message.newStandardMessage(NOT_SELECTED);
        }
        return null;
    }
    @Override
    public Message validate(Object _navigation, Object _node, Object _value) {
        if (_value == null) {
            return Message.newStandardMessage(NOT_SELECTED);
        }
        return null;
    }

}
