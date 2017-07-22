package code.serialize.exceptions;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public class NoAttributeForSerializable extends RuntimeException {
    private static final String ACCESS = "stream.serialize.exceptions.NoAttributeForSerializable";

    private static final StringMap<StringMap<String>> MESSAGES = InitializerMessages.getMessages(ACCESS);

    private static final String EMPTY_STRING = "";
    private static final String NO_ATTRIBUTE = "noAttribute";
    private static final String NO_ATTRIBUTE_NAME = "noAttributeName";

    public NoAttributeForSerializable(String _tagName) {
        super(getMessage(false, EMPTY_STRING, _tagName));
    }


    public NoAttributeForSerializable(String _fieldName, String _tagName) {
        super(getMessage(true, _fieldName, _tagName));
    }

    private static String getMessage(boolean _hasFieldName, String _fieldName, String _tagName) {
        try {
            StringMap<String> messages_ = MESSAGES.getVal(Constants.getLanguage());
            if (_hasFieldName) {
                return StringList.simpleFormat(messages_.getVal(NO_ATTRIBUTE_NAME), _fieldName, _tagName);
            }
            return StringList.simpleFormat(messages_.getVal(NO_ATTRIBUTE), _tagName);
        } catch (Exception _0) {
            return EMPTY_STRING;
        }
    }
}
