package code.util.opers;
import java.util.regex.Pattern;

import code.util.StringList;

public final class CommonRegExpUtil {

    private CommonRegExpUtil() {
    }

    public static StringList filter(StringList _list,String _regExp) {
        StringList list_ = new StringList();
        Pattern patt_ = Pattern.compile(_regExp);
        for (String s: _list) {
            if (!patt_.matcher(s).find()) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }
}
