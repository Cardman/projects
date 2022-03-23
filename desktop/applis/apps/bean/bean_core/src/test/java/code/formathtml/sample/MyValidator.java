package code.formathtml.sample;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;
import code.bean.validator.Validator;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class MyValidator implements Validator {

    private static final char ZERO_CHAR = '0';

    private static final char SEPARATOR = '/';

    private static final String ZERO = "0";

    private static final char MINUS = '-';
    private static final char DOT = '.';
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
        if (!matchesRate(((StringStruct) _value).getInstance())) {
            Message message_ = new Message();
            message_.setArgs(((StringStruct) _value).getInstance());
            message_.setContent(StringUtil.simpleStringsFormat("{0} is not a no zero rate", message_.getArgs()));
            return message_;
        }
        if (StringUtil.quickEq(((StringStruct)_value).getInstance(),ZERO)) {
            Message message_ = new Message();
            message_.setArgs(((StringStruct) _value).getInstance());
            message_.setContent("0 is unacceptable");
            return message_;
        }
        return null;
    }
    public static boolean matchesRate(String _input) {
        if (_input.isEmpty()) {
            return false;
        }
        int i_ = IndexConstants.FIRST_INDEX;
        if (_input.charAt(i_) == MINUS) {
            i_++;
        }
        if (i_ >= _input.length()) {
            return false;
        }
        if (_input.charAt(i_) == DOT) {
            i_++;
            while (true) {
                if (i_ >= _input.length()) {
                    break;
                }
                if (!Character.isDigit(_input.charAt(i_))) {
                    return false;
                }
                i_++;
            }
            return true;
        }
        if (!Character.isDigit(_input.charAt(i_))) {
            return false;
        }
        while (true) {
            if (i_ >= _input.length()) {
                return true;
            }
            if (!Character.isDigit(_input.charAt(i_))) {
                if (_input.charAt(i_) == SEPARATOR) {
                    break;
                }
                if (_input.charAt(i_) == DOT) {
                    break;
                }
                return false;
            }
            i_++;
        }
        if (_input.charAt(i_) == DOT) {
            i_++;
            while (true) {
                if (i_ >= _input.length()) {
                    break;
                }
                if (!Character.isDigit(_input.charAt(i_))) {
                    return false;
                }
                i_++;
            }
            return true;
        }
        if (i_ + 1 >= _input.length()) {
            return false;
        }
        i_++;
        if (_input.charAt(i_) == MINUS) {
            i_++;
        }
        while (true) {
            if (i_ >= _input.length()) {
                return false;
            }
            if (_input.charAt(i_) != ZERO_CHAR) {
                if (!Character.isDigit(_input.charAt(i_))) {
                    return false;
                }
                break;
            }
            i_++;
        }
        while (true) {
            if (i_ >= _input.length()) {
                break;
            }
            if (!Character.isDigit(_input.charAt(i_))) {
                return false;
            }
            i_++;
        }
        return true;
    }
}
