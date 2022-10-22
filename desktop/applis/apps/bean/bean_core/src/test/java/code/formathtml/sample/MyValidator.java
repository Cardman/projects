package code.formathtml.sample;

import code.bean.validator.Validator;
import code.formathtml.structs.Message;
import code.util.StringList;
import code.util.core.StringUtil;

public class MyValidator implements Validator {

    public static Message err(StringList _values) {
//        if (!(_value instanceof StringStruct)) {
//            Long or Boolean
//            Message message_ = new Message();
//            if (_value instanceof BooleanStruct) {
//                message_.setArgs("");
//            } else {
//                message_.setArgs(Long.toString(NumParsers.convertToNumber(_value).longStruct()));
//            }
//            message_.setArgs(Long.toString(NumParsers.convertToNumber(_value).longStruct()));
//            message_.setContent(StringUtil.simpleStringsFormat("{0} is not a no zero rate", message_.getArgs()));
//            return message_;
//        }
        Message message_ = new Message();
        message_.setArgs(_values.first());
//        message_.setArgs(((StringStruct) _value).getInstance());
        message_.setContent(StringUtil.simpleStringsFormat("{0} is not a no zero rate", message_.getArgs()));
        return message_;
//        if (!Rate.isValid(((StringStruct) _value).getInstance())) {
//            Message message_ = new Message();
//            message_.setArgs(((StringStruct) _value).getInstance());
//            message_.setContent(StringUtil.simpleStringsFormat("{0} is not a no zero rate", message_.getArgs()));
//            return message_;
//        }
//        Message message_ = new Message();
//        message_.setArgs(((StringStruct) _value).getInstance());
//        message_.setContent(StringUtil.simpleStringsFormat("{0} is unacceptable", message_.getArgs()));
//        return message_;
    }

    @Override
    public Message validate(StringList _values) {
        return err(_values);
    }

}
