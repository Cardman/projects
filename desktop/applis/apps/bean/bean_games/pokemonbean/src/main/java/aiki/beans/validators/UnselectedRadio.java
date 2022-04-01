package aiki.beans.validators;

import code.bean.validator.Validator;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;

public class UnselectedRadio implements Validator {

    @Override
    public Message validate(Struct _value) {
        if (_value == NullStruct.NULL_VALUE) {
            return Message.newStandardMessage();
        }
        return null;
    }
}