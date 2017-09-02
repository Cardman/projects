package code.serialize.exceptions;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public class ClassFoundException extends RuntimeException {

    private static final String ACCESS = "stream.serialize.exceptions.ClassFoundException";
    private static final StringMap<StringMap<String>> MESSAGES = InitializerMessages.getMessages(ACCESS);
    private static final String EMPTY_STRING = "";

    private static final String INHERIT = "unexpectedInherit";
    private static final String NOT_INHERIT = "unexpected";
    private static final String ENUM_CLASS = "an enumeration";

    public ClassFoundException(String _found) {
        this(_found, ENUM_CLASS);
    }

    public ClassFoundException(String _found, String _expected) {
        this(false, _found, _expected);
    }

    public ClassFoundException(boolean _inherit, String _found, String _expected) {
        super(getMessage(_inherit, _found, _expected));
    }

    private static String getMessage(boolean _inherit, String _found, String _expected) {
        try {
            StringMap<String> messages_ = MESSAGES.getVal(Constants.getLanguage());
            if (_inherit) {
                return StringList.simpleFormat(messages_.getVal(INHERIT), _found, _expected);
            }
            return StringList.simpleFormat(messages_.getVal(NOT_INHERIT), _found, _expected);
        } catch (Exception _0) {
            return EMPTY_STRING;
        }
    }
}
