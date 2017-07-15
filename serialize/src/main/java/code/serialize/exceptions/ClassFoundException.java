package code.serialize.exceptions;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public class ClassFoundException extends RuntimeException {

    private static final StringMap<StringMap<String>> MESSAGES = InitializerMessages.getMessages(ClassFoundException.class);

    private static final String EMPTY_STRING = "";
    private static final String INHERIT = "unexpectedInherit";
    private static final String NOT_INHERIT = "unexpected";

    private final boolean inherit;

    private final String found;

    private final String expected;

    public ClassFoundException(String _found, String _expected) {
        this(false, _found, _expected);
    }

    public ClassFoundException(boolean _inherit, String _found, String _expected) {
        inherit = _inherit;
        found = _found;
        expected = _expected;
    }

    @Override
    public String getMessage() {
        try {
            StringMap<String> messages_ = MESSAGES.getVal(Constants.getLanguage());
            if (inherit) {
                return StringList.simpleFormat(messages_.getVal(INHERIT), found, expected);
            }
            return StringList.simpleFormat(messages_.getVal(NOT_INHERIT), found, expected);
        } catch (RuntimeException _0) {
            return EMPTY_STRING;
        }
    }
}
