package code.formathtml.classes;
import code.bean.validator.Message;
import code.bean.validator.Validator;

public class MyValidatorEnum extends Validator {

    public MyValidatorEnum() {
        setClassName("code.formathtml.classes.MyValidatorEnum");
    }
    @Override
    public Message validate(Object _navigation, Object _node, Object _value) {
        return null;
    }
    @Override
    public Message validate(Object _value) {
        return null;
    }

}
