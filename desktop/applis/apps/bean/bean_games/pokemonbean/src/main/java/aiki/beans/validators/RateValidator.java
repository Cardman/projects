package aiki.beans.validators;

import code.bean.validator.Validator;
import code.formathtml.structs.Message;
import code.formathtml.util.BeanLgNames;
import code.maths.Rate;
import code.util.StringList;
import code.util.core.StringUtil;

public class RateValidator implements Validator {

    @Override
    public Message validate(StringList _values) {
        String value_ = StringUtil.nullToEmpty(BeanLgNames.oneElt(_values));
        return procRate(value_);
    }

    private static Message procRate(String _v) {
        if (!Rate.isValid(_v))  {
            Message message_ = new Message();
            message_.setContent("");
            message_.setArgs(_v);
            return message_;
        }
        Rate r_ = Rate.newRate(_v);
        if (r_.isZeroOrGt() && !r_.isZero()) {
            return null;
        }
        Message message_ = new Message();
        message_.setContent("");
        message_.setArgs(r_.toNumberString());
        return message_;
    }

}