package aiki.beans.validators;

import code.bean.validator.Validator;
import code.formathtml.structs.Message;
import code.util.StringList;

public class UnselectedRadio implements Validator {

    @Override
    public Message validate(StringList _values) {
        if (_values.isEmpty()) {
            return Message.newStandardMessage();
        }
        return null;
    }
}