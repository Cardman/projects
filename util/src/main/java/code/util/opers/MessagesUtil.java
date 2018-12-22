package code.util.opers;

import code.util.StringList;
import code.util.StringMap;

public final class MessagesUtil {

    private static final String LINE_RETURN = "\n";
    private static final String EMPTY_STRING = "";
    private static final String TAB = "\t";
    private static final String EQUALS = "=";
    private static final String BEFORE_LINE_RETURN = "\r\n";

    private MessagesUtil() {
    }
    public static StringMap<String> getMessages(String _content) {
        String lastKey_ = EMPTY_STRING;
        StringMap<String> messages_ = new StringMap<String>();
        for (String l: StringList.splitStrings(_content, BEFORE_LINE_RETURN, LINE_RETURN)) {
            if (l.isEmpty()) {
                continue;
            }
            if (l.startsWith(TAB)) {
                String text_ = messages_.getVal(lastKey_);
                if (text_ != null) {
                    text_ = StringList.concat(text_,l.substring(1));
                    messages_.put(lastKey_, text_);
                }
            } else {
                int indexSep_ = l.indexOf(EQUALS);
                lastKey_ = l.substring(0,indexSep_);
                messages_.put(lastKey_, l.substring(indexSep_+1));
            }
        }
        return messages_;
    }

}
