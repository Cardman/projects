package code.formathtml.classes;
import org.w3c.dom.Node;

import code.bean.validator.Message;
import code.bean.validator.Validator;

public class MyValidatorEnums implements Validator {

    private static final String BAD_SELECTION = "Bad selection";

    @Override
    public Message validate(Object _navigation, Node _node, Object _value) {
        if (_value instanceof EnumNumbers) {
            for (Object o: (EnumNumbers) _value) {
                if (o == EnumNumber.FOUR) {
                    return Message.newStandardMessage(BAD_SELECTION);
                }
            }
        }
        return null;
    }

}
