package code.util.opers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import code.util.CustList;
import code.util.StringList;

public final class RegExpUtil {

    public static final char BACK_SLASH = '\\';

    public static final String BOUNDS = "\\b";

    public static final String CHAR_WORD = "\\w";

    public static final char CHAR_WORD_OTHER = '_';

    public static final String LEFT_PAR_REG_EXP = "(";

    public static final String RIGHT_PAR_REG_EXP = ")";

    public static final String BEGIN_REG_EXP = "^";

    public static final String OR_REG_EXP = "|";

    public static final String END_REG_EXP = "$";

    public static final String LEFT_CLASS_REG_EXP = "[";

    public static final String RIGHT_CLASS_REG_EXP = "]";

    public static final char LEFT_PAR= '(';

    public static final char RIGHT_PAR= ')';

    public static final char LEFT_BRACKET = '[';

    public static final char RIGHT_BRACKET = ']';

    public static final char LEFT_BRACE = '{';

    public static final char RIGHT_BRACE = '}';

    public static final String PIPE = "|";

    public static final String POINT = ".";

    public static final String AT_LEAST ="+";

    public static final String POSSIBLE ="*";

    public static final String NO_MORE_ONE = "?";

//    private static final String ESCAPED_META = escapedMeta();
    private static final String ESCAPED_META = "(\\*|\\+|\\[|\\]|\\(|\\)|\\{|\\}|\\.|\\^|\\?|\\$|\\|)";

    private static final String VAR_REG_EXP = "$1";

    private static final String EMPTY_STRING = "";

    private RegExpUtil() {
    }

    public static String pars(String _exp) {
        return StringList.concat(LEFT_PAR_REG_EXP, _exp, RIGHT_PAR_REG_EXP);
    }

    public static String wholeString(String _string) {
        return StringList.concat(BEGIN_REG_EXP, _string, END_REG_EXP);
    }

    public static String beginString(String _string) {
        return StringList.concat(BEGIN_REG_EXP, _string);
    }

    public static String endString(String _string) {
        return StringList.concat(_string, END_REG_EXP);
    }

    public static String quote(String _string) {
        String bk_ = String.valueOf(BACK_SLASH);
        bk_ = StringList.concat(bk_, bk_);
        return _string.replaceAll(ESCAPED_META,StringList.concat(bk_,VAR_REG_EXP));
    }

    public static String classOfCharacters(String... _args) {
        StringList escaped_ = new StringList();
        for (String s: _args) {
            escaped_.add(quote(s));
        }
        return StringList.concat(LEFT_CLASS_REG_EXP,escaped_.join(EMPTY_STRING),RIGHT_CLASS_REG_EXP);
    }

    public static String outOfClassOfCharacters(String... _args) {
        StringList escaped_ = new StringList();
        for (String s: _args) {
            escaped_.add(quote(s));
        }
        return StringList.concat(LEFT_CLASS_REG_EXP,BEGIN_REG_EXP,escaped_.join(EMPTY_STRING),RIGHT_CLASS_REG_EXP);
    }

    public static CustList<Integer> indexesOfRegExp(CustList<String> _list,String _regExp) {
        CustList<Integer> list_ = new CustList<Integer>();
        int size_ = _list.size();
        for (int i=CustList.FIRST_INDEX;i<size_;i++) {
            if (!matchingRegExp(_list.get(i),_regExp).isEmpty()) {
                list_.add(i);
            }
        }
        return list_;
    }

    public static void replaceRegExpInStrings(StringList _list,String _replacedRegExp, String _replacing) {
        //setModified();
        int size_ = _list.size();
        for (int i=CustList.FIRST_INDEX;i<size_;i++) {
            _list.set(i, _list.get(i).replaceAll(_replacedRegExp, _replacing));
        }
    }

    public static boolean matchRegExp(String _string,String _regExp) {
        return Pattern.compile(_regExp).matcher(_string).find();
    }

    public static StringList filterIgnoreCase(StringList _list,String _regExp) {
        StringList list_ = new StringList();
        Pattern patt_ = Pattern.compile(StringList.toUpperCase(_regExp));
        for (String s: _list) {
            if (!patt_.matcher(StringList.toUpperCase(s)).find()) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }

    public static StringList getTokensSeparators(String _string, String _regExpToken) {
        int index_ = CustList.FIRST_INDEX;
        Pattern patt_ = Pattern.compile(_regExpToken);
        StringList list_ = new StringList();
        while (index_ < _string.length()) {
            String sub_ = _string.substring(index_);
            Matcher m_ = patt_.matcher(sub_);
            if (m_.find()) {
                String token_ = m_.group();
                int next_ = sub_.indexOf(token_);
                list_.add(sub_.substring(CustList.FIRST_INDEX, next_));
                list_.add(token_);
                index_ += next_ + token_.length();
            } else {
                list_.add(sub_);
                break;
            }
        }
        return list_;
    }

    public CustList<StringList> catchRegExp(CustList<String> _list,String _regExp) {
        CustList<StringList> list_ = new CustList<StringList>();
        for (String s: _list) {
            list_.add(matchingRegExp(s,_regExp));
        }
        return list_;
    }

    public static StringList matchingRegExp(String _string, String _regExp) {
        Matcher m_ = Pattern.compile(_regExp).matcher(_string);
        StringList list_ = new StringList();
        while (m_.find()) {
            list_.add(m_.group());
        }
        return list_;
    }
}
