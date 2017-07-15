package code.util.opers;

import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.exceptions.BadFilePropertiesContentException;

public final class MessagesUtil {

    private static final String LINE_RETURN = "\n";
    private static final String EMPTY_STRING = Constants.EMPTY_STRING;
    private static final String TAB = "\t";
    private static final String EQUALS = "=";
    private static final String BEFORE_LINE_RETURN = "\r\n";

    private MessagesUtil() {
    }
    public static StringMap<String> getMessages(String _content) {
        String lastKey_ = EMPTY_STRING;
        StringMap<String> messages_ = new StringMap<String>();
        int line_ = CustList.FIRST_INDEX;
        for (String l: StringList.splitStrings(_content, BEFORE_LINE_RETURN, LINE_RETURN)) {
            line_++;
            if (l.isEmpty()) {
                continue;
            }
            if (l.startsWith(TAB)) {
                String text_ = messages_.getVal(lastKey_);
                if (text_ != null) {
                    text_ += l.substring(1);
                    messages_.put(lastKey_, text_);
                }
            } else {
                try {
                    int indexSep_ = l.indexOf(EQUALS);
                    lastKey_ = l.substring(0,indexSep_);
                    messages_.put(lastKey_, l.substring(indexSep_+1));
                } catch (RuntimeException _0) {
                    throw new BadFilePropertiesContentException(_content, line_);
                }
            }
        }
        return messages_;
    }

}
