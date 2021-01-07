package code.bean.validator;

import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;

public interface Validator {

    Message validate(Struct _value);

}
