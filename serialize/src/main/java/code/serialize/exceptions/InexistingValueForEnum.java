package code.serialize.exceptions;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public class InexistingValueForEnum extends RuntimeException {

    private static final String EMPTY_STRING = "";
    private static final String INEXISTING = "inexisting";

    private static final StringMap<StringMap<String>> MESSAGES = InitializerMessages.getMessages(InexistingValueForEnum.class);

    private String enumValueName;

    private String enumName;

    public InexistingValueForEnum(String _enumValueName, String _enumName) {
        enumValueName = _enumValueName;
        enumName = _enumName;
    }

    @Override
    public String getMessage() {
        try {
            StringMap<String> messages_ = MESSAGES.getVal(Constants.getLanguage());
            return StringList.simpleFormat(messages_.getVal(INEXISTING), enumValueName, enumName);
        } catch (RuntimeException _0) {
            return EMPTY_STRING;
        }
    }
}
