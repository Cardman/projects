package code.serialize.exceptions;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public class NoValueException extends RuntimeException {

    private static final String EMPTY_STRING = "";
    private static final String NO_VALUE = "noValue";

    private static final StringMap<StringMap<String>> MESSAGES = InitializerMessages.getMessages(NoValueException.class);

    private final String tagName;

    public NoValueException(String _tagName) {
        tagName = _tagName;
    }

    @Override
    public String getMessage() {
        try {
            StringMap<String> messages_ = MESSAGES.getVal(Constants.getLanguage());
            return StringList.simpleFormat(messages_.getVal(NO_VALUE), tagName);
        } catch (RuntimeException _0) {
            return EMPTY_STRING;
        }
    }
}
