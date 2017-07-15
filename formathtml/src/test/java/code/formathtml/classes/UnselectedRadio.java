package code.formathtml.classes;
import org.w3c.dom.Node;

import code.bean.validator.Validator;
import code.bean.validator.ValidatorException;

public class UnselectedRadio implements Validator {

    private static final String NOT_SELECTED = "not selected";

    @Override
    public void validate(Object _navigation, Node _node, Object _value) {
        if (_value == null) {
            throw new ValidatorException(NOT_SELECTED);
        }
    }

}
