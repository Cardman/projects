package code.serialize.exceptions;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public class NoAttributeForSerializable extends RuntimeException {

    private static final StringMap<StringMap<String>> MESSAGES = InitializerMessages.getMessages(NoAttributeForSerializable.class);

    private static final String EMPTY_STRING = "";
    private static final String NO_ATTRIBUTE = "noAttribute";
    private static final String NO_ATTRIBUTE_NAME = "noAttributeName";

    private boolean hasFieldName;

    private String fieldName;

    private String tagName;

    public NoAttributeForSerializable(String _tagName) {
        hasFieldName = false;
        tagName = _tagName;
    }


    public NoAttributeForSerializable(String _fieldName, String _tagName) {
        hasFieldName = true;
        fieldName = _fieldName;
        tagName = _tagName;
    }

    @Override
    public String getMessage() {
        try {
            StringMap<String> messages_ = MESSAGES.getVal(Constants.getLanguage());
            if (hasFieldName) {
                return StringList.simpleFormat(messages_.getVal(NO_ATTRIBUTE_NAME), fieldName, tagName);
            }
            return StringList.simpleFormat(messages_.getVal(NO_ATTRIBUTE), tagName);
        } catch (RuntimeException _0) {
            return EMPTY_STRING;
        }
    }
}
