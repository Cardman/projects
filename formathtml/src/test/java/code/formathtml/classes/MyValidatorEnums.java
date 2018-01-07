package code.formathtml.classes;
import code.bean.validator.Message;
import code.bean.validator.Validator;

public class MyValidatorEnums extends Validator {

    private static final String BAD_SELECTION = "Bad selection";

    public MyValidatorEnums() {
        setClassName("code.formathtml.classes.MyValidatorEnums");
    }

    @Override
    public Message validate(Object _navigation, Object _node, Object _value) {
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
