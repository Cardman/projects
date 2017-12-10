package code.bean.validator;

public interface Validator {

    Message validate(Object _navigation, Object _node, Object _value);
}
