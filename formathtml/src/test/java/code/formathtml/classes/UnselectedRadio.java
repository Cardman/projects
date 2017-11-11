package code.formathtml.classes;
import org.w3c.dom.Node;

import code.bean.validator.Message;
import code.bean.validator.Validator;

public class UnselectedRadio implements Validator {

    private static final String NOT_SELECTED = "not selected";

    @Override
    public Message validate(Object _navigation, Node _node, Object _value) {
        if (_value == null) {
            return Message.newStandardMessage(NOT_SELECTED);
        }
        return null;
    }

}
