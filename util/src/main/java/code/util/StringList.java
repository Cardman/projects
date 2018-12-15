package code.util;
import code.util.comparators.NaturalComparator;
import code.util.ints.Displayable;
import code.util.ints.Equallable;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;

public final class StringList extends AbEqList<String> implements Equallable<StringList>, Displayable {

    public static final char LEFT_BRACE = '{';

    public static final char RIGHT_BRACE = '}';

    public static final char LEFT_BRACKET = '[';

    public static final char RIGHT_BRACKET = ']';

    public static final char LEFT_PAR= '(';

    public static final char RIGHT_PAR= ')';

    public static final String POINT = ".";

    private static final char CHAR_WORD_OTHER = '_';

    private static final char BACK_SLASH_CHAR = '\\';

    private static final char SLASH = '/';

    private static final char DOT = '.';

    private static final char CHARACTER = '.';

    private static final char STRING = '*';

    private static final char POSSIBLE_CHAR = '?';

    private static final char ESCAPING_CHAR = '\\';

    private static final char QUOTE = '\'';

    private static final char LINE_RETURN_CHAR = '\n';

    private static final char SPACE_CHAR = ' ';

    private static final char TAB_CHAR = '\t';

    private static final char RETURN_CHAR = '\r';

    private static final char MINUS = '-';

    public StringList() {
    }

    public StringList(Listable<String> _list) {
        super(_list);
    }

    public StringList(String... _strings) {
        super(_strings);
    }

    
    public StringList(CollCapacity _capacity) {
        super(_capacity);
    }

    public static byte[] encode(CharSequence _input) {
        int len_ = _input.length();
        char[] chs_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            chs_[i] = _input.charAt(i);
        }
        return encode(chs_);
    }
    public static byte[] encode(char[] _input) {
        Numbers<Byte> expBytes_ = encodeList(_input);
        int length_ = expBytes_.size();
        byte[] bytes_ = new byte[length_];
        for (int i = 0; i < length_; i++) {
            byte b_ = expBytes_.get(i);
            bytes_[i] = b_;
        }
        return bytes_;
    }
    public static Numbers<Byte> encodeList(char[] _input) {
        Numbers<Byte> expBytes_ = new Numbers<Byte>();
        for (char c: _input) {
            if (c < 128) {
                expBytes_.add((byte)c);
                continue;
            }
            if (c < 2048) {
                byte f_ = (byte) (c/64+192);
                expBytes_.add(f_);
                byte s_ = (byte) (c%64+128);
                expBytes_.add(s_);
                continue;
            }
            if (c < 4096) {
                byte f_ = -32;
                expBytes_.add(f_);
                byte s_ = (byte) (c/64+128);
                expBytes_.add(s_);
                byte t_ = (byte) (c%64+128);
                expBytes_.add(t_);
                continue;
            }
            byte f_ = (byte)(c/(64*64)+224);
            expBytes_.add(f_);
            byte s_ = (byte) ((c/64)%64+128);
            expBytes_.add(s_);
            byte t_ = (byte) (c%64+128);
            expBytes_.add(t_);
        }
        return expBytes_;
    }
    public static String decode(byte[] _bytes) {
        return decode(_bytes, 0, _bytes.length);
    }
    public static String decode(byte[] _bytes, int _from, int _length) {
        int len_ = _from + _length;
        int i_ = _from;
        StringBuilder out_ = new StringBuilder();
        while (i_ < len_) {
            byte cur_ = _bytes[i_];
            if (cur_ >= 0) {
                out_.append((char)cur_);
                i_++;
                continue;
            }
            if (i_ + 1 >= len_) {
                return null;
            }
            if (cur_ < -62) {
                return null;
            }
            if (cur_ > -17) {
                return null;
            }
            byte next_ = _bytes[i_ + 1];
            if (next_ > -65) {
                return null;
            }
            if (cur_ <= -33) {
                short f_ = (short)(cur_ + 64);
                short s_ = (short)(next_ + 128);
                short t_ = (short) (64 * f_ + s_);
                out_.append((char)t_);
                i_++;
                i_++;
                continue;
            }
            if (i_ + 2 >= len_) {
                return null;
            }
            byte afterNext_ = _bytes[i_ + 2];
            if (afterNext_ > -65) {
                return null;
            }
            if (cur_ == -32) {
                short f_ = (short)(next_ + 128);
                short s_ = (short)(afterNext_ + 128);
                short t_ = (short) (64 * f_ + s_);
                out_.append((char)t_);
                i_++;
                i_++;
                i_++;
                continue;
            }
            short f_ = (short)(cur_ + 32);
            short s_ = (short)(next_ + 128);
            short t_ = (short) (afterNext_ + 128);
            short full_ = (short) (64 * 64 * f_ + 64 * s_ + t_);
            out_.append((char)full_);
            i_++;
            i_++;
            i_++;
        }
        return out_.toString();
    }
    public static String toLowerCase(String _string) {
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder(len_);
        for (int i = FIRST_INDEX; i < len_; i++) {
            str_.append(Character.toLowerCase(_string.charAt(i)));
        }
        return str_.toString();
    }

    public static String toUpperCase(String _string) {
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder(len_);
        for (int i = FIRST_INDEX; i < len_; i++) {
            str_.append(Character.toUpperCase(_string.charAt(i)));
        }
        return str_.toString();
    }

    public static boolean equalsSet(StringList _list1,StringList _list2) {
        for (String c: _list2) {
            boolean contains_ = false;
            for (String d: _list1) {
                if (c == null) {
                    if (d == null) {
                        contains_ = true;
                        break;
                    }
                    continue;
                }
                if (d != null && quickEq(c, d)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (String c: _list1) {
            boolean contains_ = false;
            for (String d: _list2) {
                if (c == null) {
                    if (d == null) {
                        contains_ = true;
                        break;
                    }
                    continue;
                }
                if (d != null && quickEq(c, d)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return true;
    }
    public static boolean equalsStringListSet(Listable<StringList> _list1,Listable<StringList> _list2) {
        for (StringList c: _list2) {
            boolean contains_ = false;
            for (StringList d: _list1) {
                if (eqStrings(c, d)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (StringList c: _list1) {
            boolean contains_ = false;
            for (StringList d: _list2) {
                if (eqStrings(c, d)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return true;
    }

    public static boolean eqStrings(StringList _list1,StringList _list2) {
        if (_list1 == null) {
            return _list2 == null;
        }
        if (_list1.size() != _list2.size()) {
            return false;
        }
        int size_ = _list1.size();
        for (int i=FIRST_INDEX;i<size_;i++) {
            if (_list1.get(i) == null) {
                if (_list2.get(i) != null) {
                    return false;
                }
                continue;
            }
            if (_list2.get(i) == null) {
                return false;
            }
            if (!quickEq(_list1.get(i),_list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static String formatQuote(String _pattern, StringMap<String> _map) {
        return format(_pattern, QUOTE, _map);
    }

    public static String formatBasic(String _pattern, StringMap<String> _map, boolean _substringFirst) {
        if (_substringFirst) {
            int length_ = _pattern.length();
            StringBuilder strBuilder_ = new StringBuilder();
            int i_ = CustList.FIRST_INDEX;
            StringList keys_ = new StringList(_map.getKeys());
            boolean exit_ = false;
            while (i_ < length_) {
                int j_ = CustList.FIRST_INDEX;
                StringList list_ = keys_;
                while (true) {
                    StringList nexList_ = new StringList();
                    for (String k: list_) {
                        if (k.length() <= j_) {
                            continue;
                        }
                        if (k.charAt(j_) == _pattern.charAt(j_ + i_)) {
                            nexList_.add(k);
                        }
                    }
                    list_ = nexList_;
                    if (_pattern.length() <= j_ + i_ + 1) {
                        exit_ = true;
                        String subString_ = _pattern.substring(i_);
                        if (list_.containsStr(subString_)) {
                            strBuilder_.append(_map.getVal(subString_));
                        } else {
                            strBuilder_.append(subString_);
                        }
                        break;
                    }
//                    String subString_ = _pattern.substring(i_, j_ + i_ + 1);
                    String subString_ = _pattern.substring(i_, Math.min(j_ + i_ + 1, _pattern.length()));
                    if (list_.containsStr(subString_)) {
                        strBuilder_.append(_map.getVal(subString_));
                        i_ += j_;
                        break;
                    }
                    if (list_.isEmpty()) {
                        strBuilder_.append(subString_);
                        i_ += j_;
                        break;
                    }
                    j_++;
                }
                if (exit_) {
                    break;
                }
                i_++;
            }
            return strBuilder_.toString();
        }
        int length_ = _pattern.length();
        StringBuilder strBuilder_ = new StringBuilder();
        int i_ = CustList.FIRST_INDEX;
        StringList keys_ = new StringList(_map.getKeys());
        boolean exit_ = false;
        while (i_ < length_) {
            int j_ = CustList.FIRST_INDEX;
            StringList list_ = keys_;
            while (true) {
                StringList nexList_ = new StringList();
                for (String k: list_) {
                    if (k.length() <= j_) {
                        continue;
                    }
                    if (k.charAt(j_) == _pattern.charAt(j_ + i_)) {
                        nexList_.add(k);
                    }
                }
                list_ = nexList_;
                if (_pattern.length() <= j_ + i_ + 1) {
                    exit_ = true;
                    String subString_ = _pattern.substring(i_);
                    if (list_.containsStr(subString_)) {
                        strBuilder_.append(_map.getVal(subString_));
                    } else {
                        strBuilder_.append(subString_);
                    }
                    break;
                }
//                String subString_ = _pattern.substring(i_, j_ + i_ + 1);
                String subString_ = _pattern.substring(i_, Math.min(j_ + i_ + 1, _pattern.length()));
                StringList filterList_ = new StringList(list_);
                boolean exist_ = false;
                for (String s: filterList_) {
                    if (s.contains(concat(subString_, String.valueOf(_pattern.charAt(j_ + i_ + 1))))) {
                        exist_ = true;
                        break;
                    }
                }
                if (list_.containsStr(subString_) && !exist_) {
                    strBuilder_.append(_map.getVal(subString_));
                    i_ += j_;
                    break;
                }
                if (list_.isEmpty()) {
                    strBuilder_.append(subString_);
                    i_ += j_;
                    break;
                }
                j_++;
            }
            if (exit_) {
                break;
            }
            i_++;
        }
        return strBuilder_.toString();
    }

    public static String format(String _pattern, char _sep, StringMap<String> _map) {
        int length_ = _pattern.length();
        StringBuilder strBuilder_ = new StringBuilder();
        int i_ = CustList.FIRST_INDEX;
        StringList keys_ = new StringList(_map.getKeys());
        boolean quoted_ = false;
        while (i_ < length_) {
            if (quoted_) {
                if (_pattern.charAt(i_) != _sep) {
                    strBuilder_.append(_pattern.charAt(i_));
                    i_++;
                    continue;
                }
                quoted_ = false;
                i_++;
                continue;
            }
            int nbSeqQuotes_ = SIZE_EMPTY;
            while (true) {
                if (i_ >= length_) {
                    break;
                }
                if (_pattern.charAt(i_) != _sep) {
                    break;
                }
                nbSeqQuotes_++;
                i_++;
            }
            quoted_ = nbSeqQuotes_ % 2 ==1;
            int nbAdds_ = nbSeqQuotes_/2;
            for (int i = SIZE_EMPTY; i < nbAdds_; i++) {
                strBuilder_.append(_sep);
            }
            if (quoted_) {
                continue;
            }
            int j_ = CustList.FIRST_INDEX;
            StringList list_ = keys_;
            while (true) {
                StringList nexList_ = new StringList();
                for (String k: list_) {
                    if (k.length() <= j_) {
                        continue;
                    }
                    if (k.charAt(j_) == _pattern.charAt(j_ + i_)) {
                        nexList_.add(k);
                    }
                }
                list_ = nexList_;
//                String subString_ = _pattern.substring(i_, j_ + i_ + 1);
                String subString_ = _pattern.substring(i_, Math.min(j_ + i_ + 1, _pattern.length()));
                if (list_.containsStr(subString_)) {
                    strBuilder_.append(_map.getVal(subString_));
                    i_ += j_;
                    break;
                }
                if (list_.isEmpty()) {
                    strBuilder_.append(subString_);
                    i_ += j_;
                    break;
                }
                j_++;
            }
            i_++;
        }
        return strBuilder_.toString();
    }

    public static boolean quickEq(String _string1,String _string2) {
        int lenOne_ = _string1.length();
        int lenTwo_ = _string2.length();
        if (lenOne_ != lenTwo_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < lenOne_; i++) {
            char cOne_ = _string1.charAt(i);
            char cTwo_ = _string2.charAt(i);
            if (cOne_ != cTwo_) {
                return false;
            }
        }
        return true;
    }

    public static String skipStringUntil(String _string, String _search) {
        return _string.substring(_string.indexOf(_search)+CustList.ONE_ELEMENT);
    }

    public void sort() {
        sortElts(new NaturalComparator());
    }

    public void replace(String _old, String _new) {
        if (_old == null) {
            int size_ = size();
            for (int i = FIRST_INDEX; i < size_; i++) {
                if (get(i) == null) {
                    set(i, _new);
                }
            }
            return;
        }
        int size_ = size();
        for (int i = FIRST_INDEX; i < size_; i++) {
            if (get(i) == null) {
                continue;
            }
            if (quickEq(_old, get(i))) {
                set(i, _new);
            }
        }
    }

    public void replaceInStrings(String _replaced, String _replacing) {
        //setModified();
        int size_ = size();
        for (int i=FIRST_INDEX;i<size_;i++) {
            set(i, StringList.replace(get(i), _replaced, _replacing));
        }
    }

    public void replaceBackSlashesInStrings() {
        //setModified();
        int size_ = size();
        for (int i=FIRST_INDEX;i<size_;i++) {
            set(i, StringList.replaceBackSlash(get(i)));
        }
    }

    public static String simpleStringsFormat(String _format, String... _args) {
        if (_args == null) {
            return _format;
        }
        StringBuilder str_ = new StringBuilder();
        StringBuilder arg_ = new StringBuilder();
        int length_ = _format.length();
        boolean escaped_ = false;
        boolean inside_ = false;
        int i_ = FIRST_INDEX;
        int argLength_ = _args.length;
        while (i_ < length_) {
            char cur_ = _format.charAt(i_);
            if (cur_ == QUOTE) {
                escaped_ = !escaped_;
                if (i_ < length_ - 1) {
                    if (_format.charAt(i_ + 1) == QUOTE) {
                        str_.append(QUOTE);
                        i_++;
                        i_++;
                        escaped_ = false;
                        continue;
                    }
                }
                i_++;
                continue;
            }
            if (escaped_) {
                str_.append(cur_);
                i_++;
                continue;
            }
            if (cur_ == LEFT_BRACE) {
                arg_ = new StringBuilder();
                inside_ = true;
            } else if (cur_ == RIGHT_BRACE) {
                inside_ = false;
                int argNb_ = Numbers.parseInt(arg_.toString());
                if (argNb_ >= 0 && argNb_ < argLength_) {
                    str_.append(_args[argNb_]);
                } else {
                    str_.append(LEFT_BRACE);
                    str_.append(arg_);
                    str_.append(RIGHT_BRACE);
                }
            } else if (inside_) {
                arg_.append(cur_);
            } else {
                str_.append(cur_);
            }
            i_++;
        }
        return str_.toString();
    }

    public static String simpleNumberFormat(String _format, Number... _args) {
        if (_args == null) {
            return _format;
        }
        StringBuilder str_ = new StringBuilder();
        StringBuilder arg_ = new StringBuilder();
        int length_ = _format.length();
        boolean escaped_ = false;
        boolean inside_ = false;
        int i_ = FIRST_INDEX;
        int argLength_ = _args.length;
        while (i_ < length_) {
            char cur_ = _format.charAt(i_);
            if (cur_ == QUOTE) {
                escaped_ = !escaped_;
                if (i_ < length_ - 1) {
                    if (_format.charAt(i_ + 1) == QUOTE) {
                        str_.append(QUOTE);
                        i_++;
                        i_++;
                        escaped_ = false;
                        continue;
                    }
                }
                i_++;
                continue;
            }
            if (escaped_) {
                str_.append(cur_);
                i_++;
                continue;
            }
            if (cur_ == LEFT_BRACE) {
                arg_ = new StringBuilder();
                inside_ = true;
            } else if (cur_ == RIGHT_BRACE) {
                inside_ = false;
                int argNb_ = Numbers.parseInt(arg_.toString());
                if (argNb_ >= 0 && argNb_ < argLength_) {
                    Number a_ = _args[argNb_];
                    if (a_ != null) {
                        str_.append(Numbers.toString(a_));
                    } else {
                        str_.append(LEFT_BRACE);
                        str_.append(arg_);
                        str_.append(RIGHT_BRACE);
                    }
                } else {
                    str_.append(LEFT_BRACE);
                    str_.append(arg_);
                    str_.append(RIGHT_BRACE);
                }
            } else if (inside_) {
                arg_.append(cur_);
            } else {
                str_.append(cur_);
            }
            i_++;
        }
        return str_.toString();
    }

    public static StringList getFields(String _pattern) {
        StringList tokens_;
        tokens_ = new StringList();
        int i_ = CustList.FIRST_INDEX;
        while (i_ < _pattern.length()) {
            int indexOne_ = _pattern.indexOf(QUOTE, i_);
            if (indexOne_ == CustList.INDEX_NOT_FOUND_ELT) {
                //add tokens from _pattern (min:i_+1)
//                StringList tokensSep_ = StringList.getTokensSeparators(_pattern.substring(i_), REG_EXP_EL);
//                int nbTokensSep_ = tokensSep_.size();
//                for (int i = CustList.FIRST_INDEX; i < nbTokensSep_; i++) {
//                    if (i % 2 == 0) {
//                        continue;
//                    }
////                    String token_ = tokensSep_.get(i).replaceAll(BOUNDS_EL, EMPTY_STRING);
//                    String token_ = removeElBounds(tokensSep_.get(i));
//                    tokens_.add(token_);
//                }
                tokens_.addAllElts(getEl(_pattern.substring(i_)));
                break;
            }
//            StringList tokensSep_ = StringList.getTokensSeparators(_pattern.substring(i_, indexOne_), REG_EXP_EL);
//            int nbTokensSep_ = tokensSep_.size();
//            for (int i = CustList.FIRST_INDEX; i < nbTokensSep_; i++) {
//                if (i % 2 == 0) {
//                    continue;
//                }
////                String token_ = tokensSep_.get(i).replaceAll(BOUNDS_EL, EMPTY_STRING);
//                String token_ = removeElBounds(tokensSep_.get(i));
//                tokens_.add(token_);
//            }
            tokens_.addAllElts(getEl(_pattern.substring(i_, indexOne_)));
            int indexTwo_ = _pattern.indexOf(QUOTE, indexOne_ + 1);
            if (indexTwo_ == CustList.INDEX_NOT_FOUND_ELT) {
                break;
            }
            i_ = indexTwo_ + 1;
        }
        return tokens_;
    }

    private static StringList getEl(String _string) {
        StringList tokens_ = StringList.getTokensSets(_string);
        StringList newList_ = new StringList();
        for (String t: tokens_) {
            if (t.lastIndexOf(StringList.LEFT_BRACE) != CustList.FIRST_INDEX) {
                continue;
            }
            newList_.add(removeElBounds(t));
        }
        return newList_;
    }

    private static String removeElBounds(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            if (c == StringList.LEFT_BRACE) {
                continue;
            }
            if (c == StringList.RIGHT_BRACE) {
                continue;
            }
            str_.append(c);
        }
        return str_.toString();
    }

    public static int getFirstPrintableCharIndex(String _string) {
        int i_ = CustList.FIRST_INDEX;
        int len_ = _string.length();
        while (i_ < len_) {
            if (Character.isWhitespace(_string.charAt(i_))) {
                i_++;
                continue;
            }
            return i_;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

    public static int getLastPrintableCharIndex(String _string) {
        int i_ = _string.length() - 1;
        int min_ = CustList.FIRST_INDEX;
        while (i_ >= min_) {
            if (Character.isWhitespace(_string.charAt(i_))) {
                i_--;
                continue;
            }
            return i_;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }
    public static String removeAllSpaces(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            if (Character.isWhitespace(c)) {
                continue;
            }
            str_.append(c);
        }
        return str_.toString();
    }

    /**Not empty _old string*/
    public static String removeStrings(String _string, String... _strings) {
        return splitStrings(_string, _strings).join(EMPTY_STRING);
    }

    public static String removeChars(String _string, Character... _chars) {
        return splitChars(_string, _chars).join(EMPTY_STRING);
    }

    public static String replaceMult(String _string, Replacement... _strings) {
        StringMap<String> rep_ = new StringMap<String>();
        for (Replacement r: _strings) {
            rep_.put(r.getOldString(), r.getNewString());
        }
        return replaceMultiple(_string, rep_);
    }
    /**Not empty _old string*/
    public static String replaceMultiple(String _string, ListableEntries<String,String> _strings) {
        Listable<String> keys_ = _strings.getKeys();
        String[] args_ = new String[keys_.size()];
        int i_ = FIRST_INDEX;
        for (String k: keys_) {
            args_[i_] = k;
            i_++;
        }
        StringList firstParts_ = splitStringsSep(_string, args_);
        StringList parts_ = new StringList();
        for (String t: firstParts_) {
            if (_strings.contains(t)) {
                parts_.add(_strings.getVal(t));
            } else {
                parts_.add(t);
            }
        }
        return parts_.join(CustList.EMPTY_STRING);
    }

    /**Not empty _old string*/
    public static String replace(String _string, String _old, String _new) {
        if (_old == null) {
            return _string;
        }
        if (_new == null) {
            if (_old.isEmpty()) {
                StringList list_ = new StringList();
                for (char c: _string.toCharArray()) {
                    list_.add(String.valueOf(c));
                }
                return list_.join(EMPTY_STRING);
            }
            StringBuilder list_ = new StringBuilder();
            int i_ = FIRST_INDEX;
            int len_ = _string.length();
            int index_ = INDEX_NOT_FOUND_ELT;
            while (i_ < len_) {
                index_ = _string.indexOf(_old, i_);
                if (index_ < 0) {
                    break;
                }
                list_.append(_string.substring(i_, index_));
                i_ = index_ + _old.length();
            }
            if (i_ <= len_) {
                list_.append(_string.substring(i_));
            }
            return list_.toString();
        }
        if (_old.isEmpty()) {
            StringList list_ = new StringList();
            list_.add(_new);
            for (char c: _string.toCharArray()) {
                list_.add(String.valueOf(c));
                list_.add(_new);
            }
            return list_.join(EMPTY_STRING);
        }
        StringBuilder list_ = new StringBuilder();
        int i_ = FIRST_INDEX;
        int len_ = _string.length();
        int index_ = INDEX_NOT_FOUND_ELT;
        while (i_ < len_) {
            index_ = _string.indexOf(_old, i_);
            if (index_ < 0) {
                break;
            }
            list_.append(_string.substring(i_, index_));
            list_.append(_new);
            i_ = index_ + _old.length();
        }
        if (i_ <= len_) {
            list_.append(_string.substring(i_));
        }
        return list_.toString();
    }

    public static StringList tokensOfSubString(String _string, String _subString) {
        if (_subString.isEmpty()) {
            StringList list_ = new StringList();
            list_.add(EMPTY_STRING);
            for (char c: _string.toCharArray()) {
                list_.add(String.valueOf(c));
                list_.add(EMPTY_STRING);
            }
            return list_;
        }
        StringList list_ = new StringList();
        int i_ = FIRST_INDEX;
        int len_ = _string.length();
        int index_ = INDEX_NOT_FOUND_ELT;
        while (i_ < len_) {
            index_ = _string.indexOf(_subString, i_);
            if (index_ < 0) {
                break;
            }
            list_.add(_string.substring(i_, index_));
            list_.add(_string.substring(index_, index_ + _subString.length()));
            i_ = index_ + _subString.length();
        }
        if (i_ <= len_) {
            list_.add(_string.substring(i_));
        }
        return list_;
    }

    public static Numbers<Integer> indexesOfSubString(String _string, String _subString) {
        Numbers<Integer> list_ = new Numbers<Integer>();
        if (_subString.isEmpty()) {
            list_.add((int) FIRST_INDEX);
            return list_;
        }
        int i_ = FIRST_INDEX;
        int len_ = _string.length();
        while (i_ < len_) {
            int index_ = _string.indexOf(_subString, i_);
            if (index_ < 0) {
                break;
            }
            list_.add(index_);
            i_ = index_ + _subString.length();
        }
        return list_;
    }

    public static Numbers<Integer> indexesOfChar(String _string, char _subString) {
        Numbers<Integer> list_ = new Numbers<Integer>();
        int i_ = FIRST_INDEX;
        int len_ = _string.length();
        while (i_ < len_) {
            int index_ = _string.indexOf(_subString, i_);
            if (index_ < 0) {
                break;
            }
            list_.add(index_);
            i_ = index_ + 1;
        }
        return list_;
    }

    public Numbers<Integer> indexesOfString(String _string) {
        Numbers<Integer> list_ = new Numbers<Integer>();
        int size_ = size();
        for (int i=FIRST_INDEX;i<size_;i++) {
            if (quickEq(get(i),_string)) {
                list_.add(i);
            }
        }
        return list_;
    }

    public StringList intersect(StringList _list) {
        StringList list_ = new StringList();
        for (String s: _list) {
            if (containsObj(s)) {
                //_list.containsObj(s)
                list_.add(s);
            }
        }
        return list_;
    }

//    @Override
//    public boolean containsObj(String _element) {
//        return indexOfObj(_element) != INDEX_NOT_FOUND_ELT;
//    }

    @Override
    public int indexOfObj(String _element, int _from) {
        if (_element == null) {
            return indexOfNull(_from);
        }
        int s_ = size();
        for (int i = _from; i < s_; i++) {
            String e_ = get(i);
            if (e_ == null) {
                continue;
            }
            if (quickEq(_element, e_)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND_ELT;
    }

    public Numbers<Integer> indexesOfSubstring(String _subSubstring) {
        Numbers<Integer> list_ = new Numbers<Integer>();
        int size_ = size();
        for (int i=FIRST_INDEX;i<size_;i++) {
            if (get(i).contains(_subSubstring)) {
                list_.add(i);
            }
        }
        return list_;
    }
    public Numbers<Integer> indexesOfMultiWords(String _exp) {
        Numbers<Integer> list_ = new Numbers<Integer>();
        int size_ = size();
        for (int i=FIRST_INDEX;i<size_;i++) {
            if (match(get(i), _exp)) {
                list_.add(i);
            }
        }
        return list_;
    }
    public void removePrefixInStrings(String _prefix) {
        //setModified();
        int size_ = size();
        for (int i=FIRST_INDEX;i<size_;i++) {
            if (!get(i).startsWith(_prefix)) {
                continue;
            }
            set(i, get(i).substring(_prefix.length()));
        }
    }

    public StringList filterByMultiWords(String _exp) {
        StringList list_ = new StringList();
        for (String s: this) {
            if (!match(s, _exp)) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }
    public StringList filterBeginsWith(String _regExp) {
        StringList list_ = new StringList();
        for (String s: this) {
            if (!s.startsWith(_regExp)) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }
    public StringList filterEndsWith(String _regExp) {
        StringList list_ = new StringList();
        for (String s: this) {
            if (!s.endsWith(_regExp)) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }

    public StringList intersectStr(StringList _list) {
        StringList list_ = new StringList();
        for (String s: _list) {
            if (containsObj(s)) {
                //_list.containsObj(s)
                list_.add(s);
            }
        }
        return list_;
    }

    public void removeAllString(String _obj) {
        //setModified();
        while (containsStr(_obj)) {
            removeObj(_obj);
        }
    }

    public void removeString(String _string) {
        removeObj(_string);
    }

//    public StringList mid(int _beginIndex, int _nbElements) {
//        if (_beginIndex+_nbElements > size()) {
//            return mid(_beginIndex);
//        }
//        return new StringList(sub(_beginIndex, _beginIndex+_nbElements));
//    }

    @Override
    public StringList mid(int _beginIndex) {
        return new StringList(sub(_beginIndex,size()));
    }

    public static boolean startsWith(String _string,String _filter) {
        return _string.startsWith(_filter.trim());
    }

    public static boolean endsWith(String _string,String _filter) {
        return _string.endsWith(_filter.trim());
    }

    public static boolean matchSpace(String _string,String _filter){
        if(_filter.trim().isEmpty()){
            return true;
        }
        PairEq<PairEq<StringList,StringList>,PairBoolean> wordsAndSeparators_
        =wordsAndSeparatorsSpace(_filter);
        StringList words_=wordsAndSeparators_.getFirst().getSecond();
        StringList separators_=wordsAndSeparators_.getFirst().getFirst();
        if (words_.isEmpty()) {
            String lastSep_ = separators_.last();
            int index_ = FIRST_INDEX;
            if(index_==_string.length()){
                return true;
            }
            if(index_<_string.length()){
                if(lastSep_.contains(String.valueOf(SPACE_CHAR))){
                    return true;
                }
            }
            return false;
        }
        if (wordsAndSeparators_.getSecond().getFirst()) {
            separators_.add(FIRST_INDEX, EMPTY_STRING);
        }
        if (wordsAndSeparators_.getSecond().getSecond()) {
            separators_.add(EMPTY_STRING);
        }
        int i_=FIRST_INDEX;
        int index_=FIRST_INDEX;
        int indiceRDecalePt_=0;
        int indiceNext_=0;
        for(String e:words_){
            indiceRDecalePt_=index_;
          //indiceNext_=_string.indexOf(e,indiceRDecalePt_);
            //indiceNext_ = greatestIndex(_string, e, indiceRDecalePt_);
            if(separators_.get(i_).contains(String.valueOf(SPACE_CHAR))){
                if (words_.isValidIndex(i_+1)) {
                    indiceNext_=_string.indexOf(e,indiceRDecalePt_);
                    if(indiceNext_ == INDEX_NOT_FOUND_ELT){
                        return false;
                    }
                } else {
                    indiceNext_=greatestIndex(_string, e,indiceRDecalePt_);
                    if(indiceNext_ == INDEX_NOT_FOUND_ELT){
                        return false;
                    }
                }
            } else{
                indiceNext_=_string.indexOf(e,indiceRDecalePt_);
                if(indiceRDecalePt_ != indiceNext_){
                    return false;
                }
            }
            
            index_=indiceNext_+e.length();
            i_++;
        }
        if(index_==_string.length()){
            return true;
        }
        if(index_<_string.length()){
            if(separators_.get(i_).contains(String.valueOf(SPACE_CHAR))){
                return true;
            }
        }
        return false;
    }
    public static boolean match(String _string,String _filter){
        if(_filter.isEmpty()){
            return true;
        }
        PairEq<PairEq<StringList,StringList>,PairBoolean> wordsAndSeparators_
            =wordsAndSeparators(_filter);
        StringList words_=wordsAndSeparators_.getFirst().getSecond();
        StringList separators_=wordsAndSeparators_.getFirst().getFirst();
        if (words_.isEmpty()) {
            String lastSep_ = separators_.last();
            int nbPts_ = 0;
            int nbZeroOne_ = 0;
            int index_ = FIRST_INDEX;
            for (char c: lastSep_.toCharArray()) {
                if (c == CHARACTER) {
                    nbPts_++;
                }
                if (c == POSSIBLE_CHAR) {
                    nbZeroOne_++;
                }
            }
            index_+=nbPts_;
            if(index_==_string.length()){
                return true;
            }
            if(index_<_string.length()){
                if(lastSep_.contains(String.valueOf(STRING))){
                    return true;
                }
                if(_string.length()<=index_+nbZeroOne_){
                    return true;
                }
            }
            return false;
        }
        if (wordsAndSeparators_.getSecond().getFirst()) {
            separators_.add(FIRST_INDEX, EMPTY_STRING);
        }
        if (wordsAndSeparators_.getSecond().getSecond()) {
            separators_.add(EMPTY_STRING);
        }
        int i_=FIRST_INDEX;
        int index_=FIRST_INDEX;
        int indiceRDecalePt_=0;
        int indiceNext_=0;
        for(String e:words_){
            String sep_ = separators_.get(i_);
            int nbPts_ = 0;
            int nbZeroOne_ = 0;
            for (char c: sep_.toCharArray()) {
                if (c == CHARACTER) {
                    nbPts_++;
                }
                if (c == POSSIBLE_CHAR) {
                    nbZeroOne_++;
                }
            }
            indiceRDecalePt_=index_+nbPts_;
            if(separators_.get(i_).contains(String.valueOf(STRING))){
                //indiceNext_=_string.indexOf(e,indiceRDecalePt_);
                //indiceNext_ = greatestIndex(_string, e, indiceRDecalePt_);
                if (words_.isValidIndex(i_+1)) {
                    indiceNext_=_string.indexOf(e,indiceRDecalePt_);
                    if(indiceNext_ == INDEX_NOT_FOUND_ELT){
                        return false;
                    }
                } else {
                    indiceNext_=greatestIndex(_string, e,indiceRDecalePt_);
                    if(indiceNext_ == INDEX_NOT_FOUND_ELT){
                        return false;
                    }
                }
//                indiceNext_=_string.indexOf(e,indiceRDecalePt_);
//                if(indiceNext_ == INDEX_NOT_FOUND_ELT){
//                    return false;
//                }
            }else{
                indiceNext_=_string.indexOf(e,indiceRDecalePt_);
                if(indiceRDecalePt_>indiceNext_||indiceRDecalePt_<indiceNext_-nbZeroOne_){
                    return false;
                }
            }
            index_=indiceNext_+e.length();
            i_++;
        }
        String lastSep_ = separators_.last();
        int nbPts_ = 0;
        int nbZeroOne_ = 0;
        for (char c: lastSep_.toCharArray()) {
            if (c == CHARACTER) {
                nbPts_++;
            }
            if (c == POSSIBLE_CHAR) {
                nbZeroOne_++;
            }
        }
        index_+=nbPts_;
        if(index_==_string.length()){
            return true;
        }
        if(index_<_string.length()){
            if(lastSep_.contains(String.valueOf(STRING))){
                return true;
            }
            if(_string.length()<=index_+nbZeroOne_){
                return true;
            }
        }
        return false;
    }

    static int greatestIndex(String _string, String _substring, int _offset) {
        String start_ = _string.substring(_offset);
        if (!start_.contains(_substring)) {
            return CustList.INDEX_NOT_FOUND_ELT;
        }
        int index_ = CustList.FIRST_INDEX;
        int return_ = CustList.FIRST_INDEX;
        while (true) {
            return_ = index_;
            return_--;
            int ind_ = start_.indexOf(_substring, index_);
            if (ind_ != CustList.INDEX_NOT_FOUND_ELT) {
                index_ = ind_ + 1;
            } else {
                break;
            }
        }
        return return_ + _offset;
    }

    private static PairEq<PairEq<StringList,StringList>,PairBoolean> wordsAndSeparatorsSpace(String _string){
        PairEq<PairEq<StringList,StringList>,PairBoolean> wordsSepBeginEnd_;
        wordsSepBeginEnd_ = new PairEq<PairEq<StringList,StringList>,PairBoolean>();
        wordsSepBeginEnd_.setSecond(new PairBoolean(false,false));
        PairEq<StringList,StringList> wordsAndSeparators_ = new PairEq<StringList,StringList>();
        StringList words_ = new StringList();
        StringList separators_ = new StringList();
        CharList metas_ = getMetaCharactersSpace();
        int begin_ = FIRST_INDEX;
        while (true) {
            int minIndex_ = lowestIndexOfMetaChar(_string, begin_, metas_);
            if (minIndex_ == INDEX_NOT_FOUND_ELT) {
                if (begin_ < _string.length()) {
                    words_.add(_string.substring(begin_));
                }
                if (begin_ == FIRST_INDEX) {
                    wordsSepBeginEnd_.getSecond().setFirst(true);
                }
                wordsSepBeginEnd_.getSecond().setSecond(begin_ < _string.length());
                break;
            }
            if (minIndex_ > begin_) {
                words_.add(_string.substring(begin_, minIndex_));
                if (begin_ == FIRST_INDEX) {
                    wordsSepBeginEnd_.getSecond().setFirst(true);
                }
            }
            int ind_ = lowestIndexOfWordChar(_string, minIndex_, metas_);
            //ind_ < _string.length() ==> all character after or at minIndex_ are meta characters
            //so if ind_ is lower than the length of the string _string,
            //then this string does not end with a character of word
            if (ind_ > minIndex_) {
                separators_.add(_string.substring(minIndex_, ind_));
            }
            begin_ = ind_;
        }
        wordsAndSeparators_.setFirst(separators_);
        wordsAndSeparators_.setSecond(words_);
        int nbWords_=wordsAndSeparators_.getSecond().size();
        for(int i=FIRST_INDEX;i<nbWords_;i++){
            String escapedString_= escapeSpace(wordsAndSeparators_.getSecond().get(i));
            wordsAndSeparators_.getSecond().set(i, escapedString_);
        }
        wordsSepBeginEnd_.setFirst(wordsAndSeparators_);
        return wordsSepBeginEnd_;
    }

    private static PairEq<PairEq<StringList,StringList>,PairBoolean> wordsAndSeparators(String _string){
        PairEq<PairEq<StringList,StringList>,PairBoolean> wordsSepBeginEnd_;
        wordsSepBeginEnd_ = new PairEq<PairEq<StringList,StringList>,PairBoolean>();
        wordsSepBeginEnd_.setSecond(new PairBoolean(false,false));
        PairEq<StringList,StringList> wordsAndSeparators_ = new PairEq<StringList,StringList>();
        StringList words_ = new StringList();
        StringList separators_ = new StringList();
        CharList metas_ = getMetaCharacters();
        int begin_ = FIRST_INDEX;
        while (true) {
            int minIndex_ = lowestIndexOfMetaChar(_string, begin_, metas_);
            if (minIndex_ == INDEX_NOT_FOUND_ELT) {
                if (begin_ < _string.length()) {
                    words_.add(_string.substring(begin_));
                }
                if (begin_ == FIRST_INDEX) {
                    wordsSepBeginEnd_.getSecond().setFirst(true);
                }
                wordsSepBeginEnd_.getSecond().setSecond(begin_ < _string.length());
                break;
            }
            if (minIndex_ > begin_) {
                words_.add(_string.substring(begin_, minIndex_));
                if (begin_ == FIRST_INDEX) {
                    wordsSepBeginEnd_.getSecond().setFirst(true);
                }
            }
            int ind_ = lowestIndexOfWordChar(_string, minIndex_, metas_);
            //ind_ < _string.length() ==> all character after or at minIndex_ are meta characters
            //so if ind_ is lower than the length of the string _string,
            //then this string does not end with a character of word
            if (ind_ > minIndex_) {
                separators_.add(_string.substring(minIndex_, ind_));
            }
            begin_ = ind_;
        }
        wordsAndSeparators_.setFirst(separators_);
        wordsAndSeparators_.setSecond(words_);
        int nbWords_=wordsAndSeparators_.getSecond().size();
        for(int i=FIRST_INDEX;i<nbWords_;i++){
            String escapedString_= escape(wordsAndSeparators_.getSecond().get(i));
            wordsAndSeparators_.getSecond().set(i, escapedString_);
        }
        wordsSepBeginEnd_.setFirst(wordsAndSeparators_);
        return wordsSepBeginEnd_;
    }

    static int lowestIndexOfMetaChar(String _string,
            int _begin,
            CharList _metas) {
        int minIndex_ = INDEX_NOT_FOUND_ELT;
        for (char s: _metas) {
            int index_ = indexOf(_string, _begin, s);
            if (index_ == INDEX_NOT_FOUND_ELT) {
                continue;
            }
            if (minIndex_ == INDEX_NOT_FOUND_ELT) {
                minIndex_ = index_;
            } else {
                minIndex_ = Math.min(minIndex_, index_);
            }
        }
        return minIndex_;
    }

    static int lowestIndexOfWordChar(String _string,
            int _minIndex,
            CharList _metas) {
        int ind_ = _minIndex;
        while (ind_ < _string.length()) {
            if (!_metas.containsChar(_string.charAt(ind_))) {
                break;
            }
            ind_++;
        }
        return ind_;
    }

    private static int indexOf(String _input, int _begin, char _meta) {
        int index_ = _input.indexOf(_meta, _begin);
        if (index_ == FIRST_INDEX || index_ == INDEX_NOT_FOUND_ELT) {
            return index_;
        }
        while(true) {
            int i_ = index_ - 1;
            int nbSl_ = 0;
            while (i_ >= _begin) {
                if (_input.charAt(i_) != ESCAPING_CHAR) {
                    break;
                }
                nbSl_++;
                i_--;
            }
            if (nbSl_%2 == 0) {
                break;
            }
            int ind_ = _input.indexOf(_meta, index_+1);
            if (ind_ == INDEX_NOT_FOUND_ELT) {
                index_ = INDEX_NOT_FOUND_ELT;
                break;
            }
            index_ = ind_;
        }
        return index_;
    }

    public static CharList getMetaCharacters() {
        return new CharList(CHARACTER, STRING, POSSIBLE_CHAR);
    }

    private static CharList getMetaCharactersSpace() {
        return new CharList(SPACE_CHAR);
    }

    @Override
    public StringList mid(int _beginIndex, int _nbElements) {
        if (_beginIndex+_nbElements > size()) {
            return new StringList(sub(_beginIndex,size()));
        }
        return new StringList(sub(_beginIndex, _beginIndex+_nbElements));
    }

    @Override
    public StringList subAbEq(int _from, int _to) {
        return sub(_from, _to);
    }

    @Override
    public StringList sub(int _from, int _to) {
        if (_from > _to) {
            return new StringList();
        }
        return new StringList(super.sub(_from, _to));
    }

    public boolean containsStr(String _string) {
        return containsObj(_string);
    }
    @Override
    public StringList getReverse() {
        StringList list_ = new StringList(this);
        int i_ = FIRST_INDEX;
        int j_ = list_.size();
        j_--;
        while (i_ < j_) {
            list_.swapIndexes(i_, j_);
            i_++;
            j_--;
        }
        return list_;
    }

    @Override
    public String[] toArray() {
        int size_ = size();
        String[] array_ = new String[size_];
        for (int i = FIRST_INDEX; i < size_; i++) {
            array_[i] = get(i);
        }
        return array_;
    }

    public String join(String _join) {
        if (isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder return_ = new StringBuilder(first());
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            return_.append(_join);
            return_.append(get(i));
        }
        return return_.toString();
    }

    public String join(char _join) {
        if (isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder return_ = new StringBuilder(first());
        int size_ = size();
        for (int i=SECOND_INDEX;i<size_;i++) {
            return_.append(_join);
            return_.append(get(i));
        }
        return return_.toString();
    }

    public static boolean disjoints(EqList<StringList> _list) {
        int size_ = _list.size();
        for (int i = CustList.FIRST_INDEX; i < size_; i++) {
            for (int j = CustList.FIRST_INDEX; j < size_; j++) {
                if (i == j) {
                    continue;
                }
                if (!_list.get(i).disjoint(_list.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean disjoint(StringList _list) {
        for (String s: _list) {
            if (containsStr(s)) {
                return false;
            }
        }
        return true;
    }

    public static String[] wrapStringArray(String... _strings) {
        return _strings;
    }

    public static String concat(CharSequence... _strings) {
        StringBuilder str_ = new StringBuilder();
        for (CharSequence s: _strings) {
            str_.append(s);
        }
        return str_.toString();
    }

    public static String concatNbs(CharSequence _string,long _nbs) {
        StringBuilder str_ = new StringBuilder(_string);
        str_.append(_nbs);
        return str_.toString();
    }

    public static String concatNb(long _nb,CharSequence _string) {
        StringBuilder str_ = new StringBuilder(_string);
        str_.append(_nb);
        str_.append(_string);
        return str_.toString();
    }

    public static String escape(String _input) {
        int length_ = _input.length();
        char[] newArray_ = new char[length_];
        int i_ = FIRST_INDEX;
        int j_ = FIRST_INDEX;
        int newLength_ = length_;
        while (i_ < length_) {
            char c_ = _input.charAt(i_);
            if (c_ == ESCAPING_CHAR) {
                int next_ = i_;
                next_++;
                if (next_ < length_) {
                    if (_input.charAt(next_) == CHARACTER) {
                        i_++;
                        c_ = _input.charAt(i_);
                        newLength_--;
                    } else if (_input.charAt(next_) == STRING) {
                        i_++;
                        c_ = _input.charAt(i_);
                        newLength_--;
                    } else if (_input.charAt(next_) == POSSIBLE_CHAR) {
                        i_++;
                        c_ = _input.charAt(i_);
                        newLength_--;
                    } else if (_input.charAt(next_) == ESCAPING_CHAR) {
                        i_++;
                        c_ = _input.charAt(i_);
                        newLength_--;
                    }
                }
            }
            newArray_[j_] = c_;
            j_++;
            i_++;
        }
        return new String(newArray_,FIRST_INDEX,newLength_);
    }

    public static String escapeSpace(String _input) {
        int length_ = _input.length();
        char[] newArray_ = new char[length_];
        int i_ = FIRST_INDEX;
        int j_ = FIRST_INDEX;
        int newLength_ = length_;
        while (i_ < length_) {
            char c_ = _input.charAt(i_);
            if (c_ == ESCAPING_CHAR) {
                int next_ = i_;
                next_++;
                if (next_ < length_) {
                    if (_input.charAt(next_) == SPACE_CHAR) {
                        i_++;
                        c_ = _input.charAt(i_);
                        newLength_--;
                    } else if (_input.charAt(next_) == ESCAPING_CHAR) {
                        i_++;
                        c_ = _input.charAt(i_);
                        newLength_--;
                    }
                }
            }
            newArray_[j_] = c_;
            j_++;
            i_++;
        }
        return new String(newArray_,FIRST_INDEX,newLength_);
    }

    public static String getFirstToken(String _string, char _separator) {
        int index_ = _string.indexOf(_separator);
        if (index_ == INDEX_NOT_FOUND_ELT) {
            return _string;
        }
        return _string.substring(FIRST_INDEX, index_);
    }

    public static String getFirstToken(String _string, String _separator) {
        int index_ = _string.indexOf(_separator);
        if (index_ == INDEX_NOT_FOUND_ELT) {
            return _string;
        }
        return _string.substring(FIRST_INDEX, index_);
    }

    public static String wrapContent(String _text, int _maxCols, boolean _splitBreakLine) {
        StringList parts_;
        if (_splitBreakLine) {
            parts_ = splitChars(_text, RETURN_CHAR, LINE_RETURN_CHAR, SPACE_CHAR, TAB_CHAR);
            parts_.removeAllString(EMPTY_STRING);
            return wrapLine(parts_, _maxCols);
        }
        StringList lines_ = splitChars(_text, RETURN_CHAR, LINE_RETURN_CHAR);
        StringBuilder str_ = new StringBuilder();
        for (String l :lines_) {
            parts_ = splitChars(l, SPACE_CHAR, TAB_CHAR);
            parts_.removeAllString(EMPTY_STRING);
            str_.append(wrapLine(parts_, _maxCols));
            str_.append(LINE_RETURN_CHAR);
            str_.append(LINE_RETURN_CHAR);
        }
        str_.deleteCharAt(str_.length() - 1);
        return str_.toString();
    }

    private static String wrapLine(StringList _lineParts, int _maxCols) {
        StringBuilder str_ = new StringBuilder();
        boolean entered_ = false;
        int pos_ = SIZE_EMPTY;
        for (String p: _lineParts) {
            if (!entered_) {
                pos_ = p.length();
                str_.append(p);
                entered_ = true;
                continue;
            }
//            if (pos_ == SIZE_EMPTY) {
//                pos_ = p.length();
//                str_.append(LINE_RETURN_CHAR);
//                str_.append(p);
//                continue;
//            }
            if (p.length() + pos_ + 1 > _maxCols) {
                pos_ = p.length();
                str_.append(LINE_RETURN_CHAR);
                str_.append(p);
            } else {
                pos_ += p.length() + 1;
                str_.append(SPACE_CHAR);
                str_.append(p);
            }
            entered_ = true;
        }
        return str_.toString();
    }

    public static StringList splitChars(String _string, Character... _separators) {
        StringList l_ = new StringList();
        CharList cs_ = new CharList(_separators);
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder();
        for (int i = FIRST_INDEX; i < len_; i++) {
            if (cs_.containsChar(_string.charAt(i))) {
                l_.add(str_.toString());
                str_ = new StringBuilder();
            } else {
                str_.append(_string.charAt(i));
            }
        }
        l_.add(str_.toString());
        return l_;
    }

    public static StringList splitCharsArr(String _string, char... _separators) {
        StringList l_ = new StringList();
        CharList cs_ = new CharList();
        for (char c: _separators) {
            cs_.add(c);
        }
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder();
        for (int i = FIRST_INDEX; i < len_; i++) {
            if (cs_.containsChar(_string.charAt(i))) {
                l_.add(str_.toString());
                str_ = new StringBuilder();
            } else {
                str_.append(_string.charAt(i));
            }
        }
        l_.add(str_.toString());
        return l_;
    }

    public static StringList splitStrings(String _string, String... _separators) {
        StringList l_ = new StringList();
        int len_ = _string.length();
        String sub_ = _string;
        while (true) {
            String candidate_ = null;
            int minIndex_ = len_;
            int maxLength_ = 0;
            for (String s: _separators) {
                int locIndex_ = sub_.indexOf(s);
                if (locIndex_ < 0) {
                    continue;
                }
                if (locIndex_ > minIndex_) {
                    continue;
                }
                int locLength_ = s.length();
                if (locIndex_ < minIndex_) {
                    minIndex_ = locIndex_;
                    maxLength_ = locLength_;
                    candidate_ = s;
                    continue;
                }
                if (locLength_ > maxLength_) {
                    maxLength_ = locLength_;
                    candidate_ = s;
                }
            }
            if (candidate_ == null) {
                l_.add(sub_);
                break;
            }
            l_.add(sub_.substring(FIRST_INDEX, minIndex_));
            sub_ = sub_.substring(minIndex_ + maxLength_);
        }
        return l_;
    }

    public static StringList splitCharsSep(String _string, Character... _separators) {
        StringList l_ = new StringList();
        CharList cs_ = new CharList(_separators);
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder();
        for (int i = FIRST_INDEX; i < len_; i++) {
            if (cs_.containsChar(_string.charAt(i))) {
                l_.add(str_.toString());
                l_.add(Character.toString(_string.charAt(i)));
                str_ = new StringBuilder();
            } else {
                str_.append(_string.charAt(i));
            }
        }
        l_.add(str_.toString());
        return l_;
    }

    public static StringList splitStringsSep(String _string, String... _separators) {
        StringList l_ = new StringList();
        int len_ = _string.length();
        String sub_ = _string;
        while (true) {
            String candidate_ = null;
            int minIndex_ = len_;
            int maxLength_ = 0;
            for (String s: _separators) {
                int locIndex_ = sub_.indexOf(s);
                if (locIndex_ < 0) {
                    continue;
                }
                if (locIndex_ > minIndex_) {
                    continue;
                }
                int locLength_ = s.length();
                if (locIndex_ < minIndex_) {
                    minIndex_ = locIndex_;
                    maxLength_ = locLength_;
                    candidate_ = s;
                    continue;
                }
                if (locLength_ > maxLength_) {
                    maxLength_ = locLength_;
                    candidate_ = s;
                }
            }
            if (candidate_ == null) {
                l_.add(sub_);
                break;
            }
            l_.add(sub_.substring(FIRST_INDEX, minIndex_));
            l_.add(candidate_);
            sub_ = sub_.substring(minIndex_ + maxLength_);
        }
        return l_;
    }

    public static String replaceWordsJoin(String _str, ListableEntries<String,String> _map) {
        return replaceWords(_str, _map).join(EMPTY_STRING);
    }

    public static StringList replaceWords(String _str, ListableEntries<String,String> _map) {
        StringList list_ = getWordsSeparators(_str);
        StringList newList_ = new StringList();
        for (String t : list_) {
            if (_map.contains(t)) {
                newList_.add(_map.getVal(t));
            } else {
                newList_.add(t);
            }
        }
        return newList_;
    }

    public static StringList getTokensSets(String _str) {
        int i_ = CustList.FIRST_INDEX;
        StringList tokens_ = new StringList();
        while (true) {
            int index_ = _str.indexOf(StringList.LEFT_BRACE, i_);
            if (index_ < 0) {
                tokens_.add(_str.substring(i_));
                break;
            }
            int indexTwo_ = _str.indexOf(StringList.RIGHT_BRACE, index_ + 2);
            if (indexTwo_ < 0) {
                tokens_.add(_str.substring(i_));
                break;
            }
            tokens_.add(_str.substring(i_, index_));
            tokens_.add(_str.substring(index_, indexTwo_ + 1));
            i_ = indexTwo_ + 1;
        }
        return tokens_;
    }

    public static StringList getWordsSeparatorsPrefix(String _str, String _prefixWord) {
        StringList list_ = getWordsSeparators(_str);
        StringList newList_ = new StringList();
        int i_ = CustList.FIRST_INDEX;
        for (String t : list_) {
            if (i_ % 2 == 0) {
                if (newList_.isEmpty()) {
                    newList_.add(t);
                } else if (!newList_.last().startsWith(_prefixWord)) {
                    newList_.setLast(concat(newList_.last(),t));
                } else {
                    newList_.add(t);
                }
                i_++;
                continue;
            }
            if (t.startsWith(_prefixWord)) {
                newList_.add(t);
                i_++;
                continue;
            }
//            if (newList_.last().startsWith(_prefixWord)) {
//                newList_.add(t);
//                i_++;
//                continue;
//            }
            newList_.setLast(concat(newList_.last(),t));
            i_++;
        }
        return newList_;
    }

    public static StringList getWordsSeparators(String _str) {
        if (_str.isEmpty()) {
            return new StringList();
        }
        StringList ret_ = new StringList();
        boolean wasWordChar_ = false;
        int i_ = CustList.FIRST_INDEX;
        StringBuilder str_ = new StringBuilder();
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(_str);
                return ret_;
            }
            if (isWordChar(_str.charAt(i_))) {
                str_.append(_str.substring(CustList.FIRST_INDEX, i_));
                break;
            }
            i_++;
        }
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(str_.toString());
//                if (wasWordChar_) {
//                    ret_.add(EMPTY_STRING);
//                }
                break;
            }
            char char_ = _str.charAt(i_);
            if (isWordChar(char_)) {
                if (!wasWordChar_) {
                    ret_.add(str_.toString());
                    str_ = new StringBuilder();
                    wasWordChar_ = true;
                }
            } else {
                if (wasWordChar_) {
                    ret_.add(str_.toString());
                    str_ = new StringBuilder();
                    wasWordChar_ = false;
                }
            }
            str_.append(char_);
            i_++;
        }
        return ret_;
    }

    public static StringList getWhitespaceSeparators(String _str) {
        if (_str.isEmpty()) {
            return new StringList();
        }
        StringList ret_ = new StringList();
        boolean wasWordChar_ = false;
        int i_ = CustList.FIRST_INDEX;
        StringBuilder str_ = new StringBuilder();
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(_str);
                return ret_;
            }
            if (!Character.isWhitespace(_str.charAt(i_))) {
                str_.append(_str.substring(CustList.FIRST_INDEX, i_));
                break;
            }
            i_++;
        }
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(str_.toString());
                break;
            }
            char char_ = _str.charAt(i_);
            if (!Character.isWhitespace(char_)) {
                if (!wasWordChar_) {
                    ret_.add(str_.toString());
                    str_ = new StringBuilder();
                    wasWordChar_ = true;
                }
            } else {
                if (wasWordChar_) {
                    ret_.add(str_.toString());
                    str_ = new StringBuilder();
                    wasWordChar_ = false;
                }
            }
            str_.append(char_);
            i_++;
        }
        return ret_;
    }
    public static StringList getWhitespaceSeparators(StringBuilder _str) {
        if (_str.length() == 0) {
            return new StringList();
        }
        StringList ret_ = new StringList();
        boolean wasWordChar_ = false;
        int i_ = CustList.FIRST_INDEX;
        StringBuilder str_ = new StringBuilder();
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(_str.toString());
                return ret_;
            }
            if (!Character.isWhitespace(_str.charAt(i_))) {
                str_.append(_str.substring(CustList.FIRST_INDEX, i_));
                break;
            }
            i_++;
        }
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(str_.toString());
                break;
            }
            char char_ = _str.charAt(i_);
            if (!Character.isWhitespace(char_)) {
                if (!wasWordChar_) {
                    ret_.add(str_.toString());
                    str_ = new StringBuilder();
                    wasWordChar_ = true;
                }
            } else {
                if (wasWordChar_) {
                    ret_.add(str_.toString());
                    str_ = new StringBuilder();
                    wasWordChar_ = false;
                }
            }
            str_.append(char_);
            i_++;
        }
        return ret_;
    }
    public static StringList getDollarWordSeparators(CharSequence _str) {
        if (_str.length() == 0) {
            return new StringList();
        }
        StringList ret_ = new StringList();
        boolean wasWordChar_ = false;
        int i_ = CustList.FIRST_INDEX;
        StringBuilder str_ = new StringBuilder();
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(_str.toString());
                return ret_;
            }
            if (isWordChar(_str.charAt(i_)) || _str.charAt(i_) == '$') {
                str_.append(_str.toString().substring(CustList.FIRST_INDEX, i_));
                break;
            }
            i_++;
        }
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(str_.toString());
                break;
            }
            char char_ = _str.charAt(i_);
            if (isWordChar(char_) || char_ == '$') {
                if (!wasWordChar_) {
                    ret_.add(str_.toString());
                    str_ = new StringBuilder();
                    wasWordChar_ = true;
                }
            } else {
                if (wasWordChar_) {
                    ret_.add(str_.toString());
                    str_ = new StringBuilder();
                    wasWordChar_ = false;
                }
            }
            str_.append(char_);
            i_++;
        }
        return ret_;
    }

    /**The returned String ends with a slash.*/
    public static String replaceBackSlashDot(String _path) {
        String path_ = StringList.replaceBackSlash(_path);
        if (path_.endsWith(concat(String.valueOf(SLASH),String.valueOf(DOT)))) {
            path_ = path_.substring(0, path_.length() - 1);
        } else if (!path_.endsWith(String.valueOf(SLASH))) {
            path_ = concat(path_, String.valueOf(SLASH));
        }
        return path_;
    }

    public static String replaceBackSlash(String _fileName) {
        return _fileName.replace(BACK_SLASH_CHAR, SLASH);
    }

    public static String replaceExtension(String _file, String _oldExt, String _newExt) {
        if (!_file.endsWith(_oldExt)) {
            return _file;
        }
        return concat(_file.substring(CustList.FIRST_INDEX, _file.length() - _oldExt.length()), _newExt);
    }

    public static String replaceFinalFile(String _str) {
        int lastIndexDot_ = _str.lastIndexOf(DOT);
        if (lastIndexDot_ == CustList.INDEX_NOT_FOUND_ELT) {
            return _str;
        }
        int i_ = lastIndexDot_;
        i_--;
        while (i_ >= CustList.FIRST_INDEX) {
            if (_str.charAt(i_) == SLASH) {
                if (i_ + 1 == lastIndexDot_) {
                    return _str;
                }
                return _str.substring(CustList.FIRST_INDEX, i_);
            }
            if (!isWordChar(_str.charAt(i_))) {
                return _str;
            }
            i_--;
        }
        return _str;
//        int lastIndexSlash_ = _str.lastIndexOf(SLASH);
//        if (lastIndexSlash_ == CustList.INDEX_NOT_FOUND_ELT) {
//            return _str;
//        }
//        if (lastIndexSlash_ > lastIndexDot_) {
//            return _str;
//        }
//        Character.is
    }

    public static String replaceExtension(String _str) {
        int lastIndexDot_ = _str.lastIndexOf(DOT);
        if (lastIndexDot_ == CustList.INDEX_NOT_FOUND_ELT) {
            return _str;
        }
        return _str.substring(CustList.FIRST_INDEX, lastIndexDot_);
    }
    public static String replaceBegin(String _str, String _prefix) {
        String ret_ = _str;
        if (_str.startsWith(_prefix)) {
            ret_ = _str.substring(_prefix.length());
        }
        return ret_;
    }

    public static String replaceEnd(String _str, String _suffix) {
        String ret_ = _str;
        if (_str.endsWith(_suffix)) {
            ret_ = _str.substring(CustList.FIRST_INDEX, _str.length() - _suffix.length());
        }
        return ret_;
    }

    public static boolean isNumber(String _string) {
        if (_string.isEmpty()) {
            return false;
        }
        int i_ = FIRST_INDEX;
        if (_string.charAt(i_) == MINUS) {
            if (_string.length() == ONE_ELEMENT) {
                return false;
            }
            i_++;
        }
        int len_ = _string.length();
        while (i_ < len_) {
            if (!Character.isDigit(_string.charAt(i_))) {
                return false;
            }
            i_++;
        }
        return true;
    }

    public static StringList splitInTwo(String _string, int _limit) {
        StringList l_ = new StringList();
        StringBuilder str_ = new StringBuilder();
        int len_ = _string.length();
        for (int i = FIRST_INDEX; i < len_; i++) {
            if (i == _limit) {
                l_.add(str_.toString());
                str_ = new StringBuilder();
            }
            str_.append(_string.charAt(i));
        }
        l_.add(str_.toString());
        return l_;
    }
    public static boolean isDoubleNumber(String _string) {
        if (_string.isEmpty()) {
            return false;
        }
        int i_ = FIRST_INDEX;
        if (_string.charAt(i_) == MINUS) {
            if (_string.length() == ONE_ELEMENT) {
                return false;
            }
            i_++;
        }
        int len_ = _string.length();
        int nbDots_ = 0;
        while (i_ < len_) {
            if (!Character.isDigit(_string.charAt(i_))) {
                if (_string.charAt(i_) != DOT || nbDots_ > 0) {
                    return false;
                }
                nbDots_++;
                i_++;
                break;
            }
            i_++;
        }
        while (i_ < len_) {
            if (!Character.isDigit(_string.charAt(i_))) {
                return false;
            }
            i_++;
        }
        return true;
    }

    public static boolean isPositiveNumber(String _string) {
        if (_string.isEmpty()) {
            return false;
        }
        for (char c : _string.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isWord(String _string) {
        if (_string.isEmpty()) {
            return false;
        }
        for (char c : _string.toCharArray()) {
            if (!isWordChar(c)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isDollarWord(String _string) {
        if (_string.isEmpty()) {
            return false;
        }
        for (char c : _string.toCharArray()) {
            if (!isWordChar(c) && c != '$') {
                return false;
            }
        }
        return true;
    }

    public static boolean isDollarWordChar(char _char) {
        if (_char == '$') {
            return true;
        }
        return isWordChar(_char);
    }
    public static boolean isWordChar(char _char) {
        if (_char == CHAR_WORD_OTHER) {
            return true;
        }
        return Character.isLetterOrDigit(_char);
    }

    public static char getCharacter() {
        return CHARACTER;
    }

    public static char getString() {
        return STRING;
    }

    public static char getPossibleChar() {
        return POSSIBLE_CHAR;
    }

    public static char getEscapingChar() {
        return ESCAPING_CHAR;
    }

    @Override
    public boolean eq(StringList _g) {
        if (_g == null) {
            return false;
        }
        int len_ = size();
        if (_g.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            String e_ = get(i);
            String f_ = _g.get(i);
            if (e_ == null) {
                if (f_ != null) {
                    return false;
                }
                continue;
            }
            if (f_ == null) {
                continue;
            }
            if (!quickEq(e_, f_)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append("[");
        str_.append(join(","));
        str_.append("]");
        return str_.toString();
    }
}
