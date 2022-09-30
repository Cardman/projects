package code.formathtml.sample;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.validator.Validator;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;

public class MyValidator implements Validator {

    @Override
    public Message validate(Struct _value) {
        return BeanNatCommonLgNames.err(_value);
    }

}
