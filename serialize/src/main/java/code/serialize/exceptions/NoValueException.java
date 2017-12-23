package code.serialize.exceptions;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public class NoValueException extends RuntimeException {

    private static final String NO_VALUE = "noValue";
    private static final String ACCESS = "stream.serialize.exceptions.NoValueException";
    private static final String EMPTY_STRING = "";

    private static final StringMap<StringMap<String>> MESSAGES = InitializerMessages.getMessages(ACCESS);

    public NoValueException(String _tagName) {
        super(getMessage(_tagName));
    }

    private static String getMessage(String _tagName) {
        try {
            StringMap<String> messages_ = MESSAGES.getVal(Constants.getLanguage());
            return StringList.simpleStringsFormat(messages_.getVal(NO_VALUE), _tagName);
        } catch (Exception _0) {
            return EMPTY_STRING;
        }
    }
}
