package code.serialize.exceptions;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public class InexistingValueForEnum extends RuntimeException {

    private static final String INEXISTING = "inexisting";
    private static final String ACCESS = "stream.serialize.exceptions.InexistingValueForEnum";
    private static final String EMPTY_STRING = "";

    private static final StringMap<StringMap<String>> MESSAGES = InitializerMessages.getMessages(ACCESS);

    public InexistingValueForEnum(String _enumValueName, String _enumName) {
        super(getMessage(_enumValueName, _enumName));
    }

    private static String getMessage(String _enumValueName, String _enumName) {
        try {
            StringMap<String> messages_ = MESSAGES.getVal(Constants.getLanguage());
            return StringList.simpleStringsFormat(messages_.getVal(INEXISTING), _enumValueName, _enumName);
        } catch (Exception _0) {
            return EMPTY_STRING;
        }
    }
}
