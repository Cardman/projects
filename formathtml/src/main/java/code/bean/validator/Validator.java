package code.bean.validator;

import code.formathtml.structs.Message;

public interface Validator {

    Message validate(Object _value);

}
