package code.bean.validator;

import code.formathtml.structs.Message;
import code.util.StringList;

public interface Validator {

    Message validate(StringList _values);

}
