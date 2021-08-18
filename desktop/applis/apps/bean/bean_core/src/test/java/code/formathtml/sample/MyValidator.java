package code.formathtml.sample;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;
import code.bean.validator.Validator;
import code.util.core.StringUtil;

public class MyValidator implements Validator {

    @Override
    public Message validate(Struct _value) {
        if (!(_value instanceof StringStruct)) {
            //Long or Boolean
            Message message_ = new Message();
            if (_value instanceof BooleanStruct) {
                message_.setArgs("");
            } else {
                message_.setArgs(Long.toString(NumParsers.convertToNumber(_value).longStruct()));
            }
            message_.setContent(StringUtil.simpleStringsFormat("{0} is not a no zero rate", message_.getArgs()));
            return message_;
        }
        if (!RateSample.matchesRate(((StringStruct) _value).getInstance())) {
            Message message_ = new Message();
            message_.setArgs(((StringStruct) _value).getInstance());
            message_.setContent(StringUtil.simpleStringsFormat("{0} is not a no zero rate", message_.getArgs()));
            return message_;
        }
        RateSample rate_ = new RateSample(((StringStruct)_value).getInstance());
        if (rate_.isZero()) {
            Message message_ = new Message();
            message_.setArgs(((StringStruct) _value).getInstance());
            message_.setContent("0 is unacceptable");
            return message_;
        }
        return null;
    }

}
