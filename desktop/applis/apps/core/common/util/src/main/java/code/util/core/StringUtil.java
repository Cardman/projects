package code.util.core;

import code.util.*;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;

public final class StringUtil {
    public static final char LEFT_BRACE = '{';
    public static final char RIGHT_BRACE = '}';
    public static final String POINT = ".";
    public static final String EMPTY_STRING = "";
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

    private StringUtil() {
    }
    public static byte[] encode(String _input) {
        int len_ = _input.length();
        char[] chs_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            chs_[i] = _input.charAt(i);
        }
        return encode(chs_);
    }

    public static byte[] encode(char[] _input) {
        Bytes expBytes_ = encodeList(_input);
        int length_ = expBytes_.size();
        byte[] bytes_ = new byte[length_];
        for (int i = 0; i < length_; i++) {
            byte b_ = expBytes_.get(i);
            bytes_[i] = b_;
        }
        return bytes_;
    }

    private static Bytes encodeList(char[] _input) {
        Bytes expBytes_ = new Bytes();
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
            int n_ = processChar(_bytes, i_, len_, out_);
            if (n_ < 0) {
                return null;
            }
            i_ = n_;
        }
        return out_.toString();
    }

    private static int processChar(byte[] _bytes, int _index, int _len,StringBuilder _dest) {
        int i_ = _index;
        byte cur_ = _bytes[i_];
        if (cur_ >= 0) {
            _dest.append((char)cur_);
            i_++;
            return i_;
        }
        if (koNextChar(_len, i_, cur_)) {
            return -1;
        }
        byte next_ = _bytes[i_ + 1];
        if (next_ > -65) {
            return -1;
        }
        if (cur_ <= -33) {
            short f_ = (short)(cur_ + 64);
            short s_ = (short)(next_ + 128);
            short t_ = (short) (64 * f_ + s_);
            _dest.append((char)t_);
            i_++;
            i_++;
            return i_;
        }
        if (i_ + 2 >= _len) {
            return -1;
        }
        byte afterNext_ = _bytes[i_ + 2];
        if (afterNext_ > -65) {
            return -1;
        }
        if (cur_ == -32) {
            short f_ = (short)(next_ + 128);
            short s_ = (short)(afterNext_ + 128);
            short t_ = (short) (64 * f_ + s_);
            _dest.append((char)t_);
            i_++;
            i_++;
            i_++;
            return i_;
        }
        short f_ = (short)(cur_ + 32);
        short s_ = (short)(next_ + 128);
        short t_ = (short) (afterNext_ + 128);
        short full_ = (short) (64 * 64 * f_ + 64 * s_ + t_);
        _dest.append((char)full_);
        i_++;
        i_++;
        i_++;
        return i_;
    }
    public static int badDecode(byte[] _bytes, int _from, int _length) {
        int len_ = _from + _length;
        int i_ = _from;
        while (i_ < len_) {
            int n_ = processBadChar(_bytes, i_, len_);
            if (n_ == i_) {
                return i_;
            }
            i_ = n_;
        }
        return -1;
    }

    private static int processBadChar(byte[] _bytes, int _index, int _len) {
        int i_ = _index;
        byte cur_ = _bytes[i_];
        if (cur_ >= 0) {
            i_++;
            return i_;
        }
        if (koNextChar(_len, i_, cur_)) {
            return i_;
        }
        byte next_ = _bytes[i_ + 1];
        if (next_ > -65) {
            return i_;
        }
        if (cur_ <= -33) {
            i_++;
            i_++;
            return i_;
        }
        if (i_ + 2 >= _len) {
            return i_;
        }
        byte afterNext_ = _bytes[i_ + 2];
        if (afterNext_ > -65) {
            return i_;
        }
        i_++;
        i_++;
        i_++;
        return i_;
    }
    private static boolean koNextChar(int _len, int _i, byte _cur) {
        return _i + 1 >= _len || _cur < -62 || _cur > -17;
    }

    public static boolean equalsSet(CustList<String> _list1, CustList<String> _list2) {
        for (String c: _list2) {
            boolean contains_ = containsString(_list1, c);
            if (!contains_) {
                return false;
            }
        }
        for (String c: _list1) {
            boolean contains_ = containsString(_list2, c);
            if (!contains_) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsString(CustList<String> _list1, String _c) {
        boolean contains_ = false;
        for (String d: _list1) {
            if (quickEq(_c, d)) {
                contains_ = true;
                break;
            }
        }
        return contains_;
    }

    public static boolean equalsStringListSet(CustList<StringList> _list1, CustList<StringList> _list2) {
        for (StringList c: _list2) {
            boolean contains_ = containsListString(_list1, c);
            if (!contains_) {
                return false;
            }
        }
        for (StringList c: _list1) {
            boolean contains_ = containsListString(_list2, c);
            if (!contains_) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsListString(CustList<StringList> _list1, StringList _c) {
        boolean contains_ = false;
        for (StringList d: _list1) {
            if (eqStrings(_c, d)) {
                contains_ = true;
                break;
            }
        }
        return contains_;
    }

    public static boolean eqStrings(StringList _list1, StringList _list2) {
        return _list1.eq(_list2);
    }

    public static String formatQuote(String _pattern, StringMap<String> _map) {
        return format(_pattern, QUOTE, _map);
    }

    public static String formatBasic(String _pattern, StringMap<String> _map, boolean _substringFirst) {
        if (_substringFirst) {
            return formatBasicSub(_pattern, _map);
        }
        return formatBasicDef(_pattern, _map);
    }

    private static String formatBasicDef(String _pattern, StringMap<String> _map) {
        int length_ = _pattern.length();
        StringBuilder strBuilder_ = new StringBuilder();
        int i_ = IndexConstants.FIRST_INDEX;
        StringList keys_ = new StringList(_map.getKeys());
        boolean exit_ = false;
        while (i_ < length_) {
            int j_ = IndexConstants.FIRST_INDEX;
            StringList list_ = keys_;
            while (true) {
                list_ = buildNextList(_pattern, i_, j_, list_);
                if (_pattern.length() <= j_ + i_ + 1) {
                    exit_ = true;
                    appendSub(_pattern, _map, strBuilder_, i_, list_);
                    break;
                }
//                String subString_ = _pattern.substring(i_, j_ + i_ + 1);
                String subString_ = _pattern.substring(i_, Math.min(j_ + i_ + 1, _pattern.length()));
                boolean exist_ = existSub(_pattern, i_, j_, list_, subString_);
                if (contains(list_, subString_) && !exist_) {
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

    private static boolean existSub(String _pattern, int _i, int _j, StringList _list, String _subString) {
        boolean exist_ = false;
        for (String s: _list) {
            if (s.contains(concat(_subString, Character.toString(_pattern.charAt(_j + _i + 1))))) {
                exist_ = true;
                break;
            }
        }
        return exist_;
    }

    private static StringList buildNextList(String _pattern, int _i, int _j, StringList _list) {
        StringList nexList_ = new StringList();
        for (String k : _list) {
            if (okKey(_pattern, _i, _j, k)) {
                nexList_.add(k);
            }
        }
        return nexList_;
    }

    private static void appendSub(String _pattern, StringMap<String> _map, StringBuilder _strBuilder, int _i, StringList _list) {
        String subString_ = _pattern.substring(_i);
        if (contains(_list, subString_)) {
            _strBuilder.append(_map.getVal(subString_));
        } else {
            _strBuilder.append(subString_);
        }
    }

    private static String formatBasicSub(String _pattern, StringMap<String> _map) {
        int length_ = _pattern.length();
        StringBuilder strBuilder_ = new StringBuilder();
        int i_ = IndexConstants.FIRST_INDEX;
        StringList keys_ = new StringList(_map.getKeys());
        boolean exit_ = false;
        while (i_ < length_) {
            int j_ = IndexConstants.FIRST_INDEX;
            StringList list_ = keys_;
            while (true) {
                list_ = buildNextList(_pattern, i_, j_, list_);
                if (_pattern.length() <= j_ + i_ + 1) {
                    exit_ = true;
                    appendSub(_pattern, _map, strBuilder_, i_, list_);
                    break;
                }
//                    String subString_ = _pattern.substring(i_, j_ + i_ + 1);
                String subString_ = _pattern.substring(i_, Math.min(j_ + i_ + 1, _pattern.length()));
                if (contains(list_, subString_)) {
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
        int i_ = IndexConstants.FIRST_INDEX;
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
            int after_ = nbQuotes(_pattern, length_, i_, _sep);
            int nbSeqQuotes_ = after_ - i_;
            i_ = after_;
            quoted_ = nbSeqQuotes_ % 2 ==1;
            appendSeps(_sep, strBuilder_, nbSeqQuotes_);
            if (quoted_) {
                continue;
            }
            i_ = incr(strBuilder_, keys_, i_, _pattern, _map);
        }
        return strBuilder_.toString();
    }

    private static int incr(StringBuilder _strBuilder, StringList _keys, int _i, String _pattern, StringMap<String> _map) {
        int i_ = _i;
        int j_ = IndexConstants.FIRST_INDEX;
        StringList list_ = _keys;
        while (true) {
            list_ = buildNextList(_pattern, i_, j_, list_);
//                String subString_ = _pattern.substring(i_, j_ + i_ + 1);
            String subString_ = _pattern.substring(i_, Math.min(j_ + i_ + 1, _pattern.length()));
            if (contains(list_, subString_)) {
                _strBuilder.append(_map.getVal(subString_));
                i_ += j_;
                break;
            }
            if (list_.isEmpty()) {
                _strBuilder.append(subString_);
                i_ += j_;
                break;
            }
            j_++;
        }
        i_++;
        return i_;
    }
    private static int nbQuotes(String _pattern, int _length, int _from, char _sep) {
        int i_ = _from;
        while (i_ < _length) {
            if (_pattern.charAt(i_) != _sep) {
                break;
            }
            i_++;
        }
        return i_;
    }
    private static void appendSeps(char _sep, StringBuilder _strBuilder, int _nbSeqQuotes) {
        int nbAdds_ = _nbSeqQuotes /2;
        for (int i = IndexConstants.SIZE_EMPTY; i < nbAdds_; i++) {
            _strBuilder.append(_sep);
        }
    }

    private static boolean okKey(String _pattern, int _i, int _j, String _k) {
        return !checkLengths(_pattern, _i, _j, _k) && _k.charAt(_j) == _pattern.charAt(_j + _i);
    }

    private static boolean checkLengths(String _pattern, int _i, int _j, String _k) {
        return _k.length() <= _j || _pattern.length() <= _j + _i;
    }

    public static boolean eq(String _string1, String _string2) {
        if (_string1 == null) {
            return _string2 == null;
        }
        if (_string2 == null) {
            return false;
        }
        return quickEq(_string1,_string2);
    }

    public static boolean quickEq(String _string1, String _string2) {
        int lenOne_ = _string1.length();
        int lenTwo_ = _string2.length();
        if (lenOne_ != lenTwo_) {
            return false;
        }
        for (int i = IndexConstants.FIRST_INDEX; i < lenOne_; i++) {
            char cOne_ = _string1.charAt(i);
            char cTwo_ = _string2.charAt(i);
            if (cOne_ != cTwo_) {
                return false;
            }
        }
        return true;
    }

    public static String skipStringUntil(String _string, String _search) {
        return _string.substring(_string.indexOf(_search)+ IndexConstants.ONE_ELEMENT);
    }

    public static String simpleStringsFormat(String _format, CustList<String> _args) {
        int len_ = _args.size();
        String[] arr_ = new String[len_];
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            arr_[i] = _args.get(i);
        }
        return simpleStringsFormat(_format,arr_);
    }

    public static String simpleStringsFormat(String _format, String... _args) {
        StringBuilder str_ = new StringBuilder();
        StringBuilder arg_ = new StringBuilder();
        int length_ = _format.length();
        boolean escaped_ = false;
        boolean inside_ = false;
        int i_ = IndexConstants.FIRST_INDEX;
        int argLength_ = _args.length;
        while (i_ < length_) {
            char cur_ = _format.charAt(i_);
            if (cur_ == QUOTE) {
                escaped_ = !escaped_;
                if (endQuote(_format, length_, i_)) {
                    str_.append(QUOTE);
                    i_++;
                    i_++;
                    escaped_ = false;
                    continue;
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
                int argNb_ = NumberUtil.parseInt(arg_.toString());
                tryAppArg(str_, arg_, argLength_, argNb_, _args);
            } else {
                append(str_, arg_, inside_, cur_);
            }
            i_++;
        }
        return str_.toString();
    }

    private static void append(StringBuilder _str, StringBuilder _arg, boolean _inside, char _cur) {
        if (_inside) {
            _arg.append(_cur);
        } else {
            _str.append(_cur);
        }
    }

    private static void tryAppArg(StringBuilder _str, StringBuilder _arg, int _argLength, int _argNb, String[] _args) {
        if (inRange(_argLength, _argNb)) {
            _str.append(_args[_argNb]);
        } else {
            appFull(_str, _arg);
        }
    }

    private static void appFull(StringBuilder _str, StringBuilder _arg) {
        _str.append(LEFT_BRACE);
        _str.append(_arg);
        _str.append(RIGHT_BRACE);
    }

    private static boolean inRange(int _argLength, int _argNb) {
        return _argNb >= 0 && _argNb < _argLength;
    }

    public static String simpleNumberFormat(String _format, long... _args) {
        StringBuilder str_ = new StringBuilder();
        StringBuilder arg_ = new StringBuilder();
        int length_ = _format.length();
        boolean escaped_ = false;
        boolean inside_ = false;
        int i_ = IndexConstants.FIRST_INDEX;
        int argLength_ = _args.length;
        while (i_ < length_) {
            char cur_ = _format.charAt(i_);
            if (cur_ == QUOTE) {
                escaped_ = !escaped_;
                if (endQuote(_format, length_, i_)) {
                    str_.append(QUOTE);
                    i_++;
                    i_++;
                    escaped_ = false;
                    continue;
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
                int argNb_ = NumberUtil.parseInt(arg_.toString());
                tryAppArg(str_, arg_, argLength_, argNb_, _args);
            } else {
                append(str_, arg_, inside_, cur_);
            }
            i_++;
        }
        return str_.toString();
    }

    private static void tryAppArg(StringBuilder _str, StringBuilder _arg, int _argLength, int _argNb, long[] _args) {
        if (inRange(_argLength, _argNb)) {
            _str.append(_args[_argNb]);
        } else {
            appFull(_str, _arg);
        }
    }

    private static boolean endQuote(String _format, int _length, int _i) {
        return _i < _length - 1 && _format.charAt(_i + 1) == QUOTE;
    }

    public static StringList getFields(String _pattern) {
        StringList tokens_;
        tokens_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < _pattern.length()) {
            int indexOne_ = _pattern.indexOf(QUOTE, i_);
            if (indexOne_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                //add tokens from _pattern (min:i_+1)
                tokens_.addAllElts(getEl(_pattern.substring(i_)));
                break;
            }
            tokens_.addAllElts(getEl(_pattern.substring(i_, indexOne_)));
            int indexTwo_ = _pattern.indexOf(QUOTE, indexOne_ + 1);
            if (indexTwo_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                break;
            }
            i_ = indexTwo_ + 1;
        }
        return tokens_;
    }

    private static StringList getEl(String _string) {
        StringList tokens_ = getTokensSets(_string);
        StringList newList_ = new StringList();
        for (String t: tokens_) {
            if (t.lastIndexOf(LEFT_BRACE) != IndexConstants.FIRST_INDEX) {
                continue;
            }
            newList_.add(removeElBounds(t));
        }
        return newList_;
    }

    private static String removeElBounds(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            if (c == LEFT_BRACE) {
                continue;
            }
            if (c == RIGHT_BRACE) {
                continue;
            }
            str_.append(c);
        }
        return str_.toString();
    }

    public static int getFirstPrintableCharIndex(String _string) {
        int i_ = IndexConstants.FIRST_INDEX;
        int len_ = _string.length();
        while (i_ < len_) {
            if (isWhitespace(_string.charAt(i_))) {
                i_++;
                continue;
            }
            return i_;
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public static int getLastPrintableCharIndex(String _string) {
        int i_ = _string.length() - 1;
        while (i_ >= IndexConstants.FIRST_INDEX) {
            if (isWhitespace(_string.charAt(i_))) {
                i_--;
                continue;
            }
            return i_;
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public static String removeAllSpaces(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            if (isWhitespace(c)) {
                continue;
            }
            str_.append(c);
        }
        return str_.toString();
    }

    /**Not empty _old string*/
    public static String removeStrings(String _string, String... _strings) {
        return join(splitStrings(_string, _strings), EMPTY_STRING);
    }

    public static String removeChars(String _string, char... _chars) {
        return join(splitChars(_string, _chars), EMPTY_STRING);
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
        int i_ = IndexConstants.FIRST_INDEX;
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
        return join(parts_, EMPTY_STRING);
    }

    /**Not empty _old string*/
    public static String replace(String _string, String _old, String _new) {
        if (_old == null) {
            return _string;
        }
        if (_new == null) {
            return replaceWhenNull(_string, _old);
        }
        if (_old.isEmpty()) {
            StringList list_ = new StringList();
            list_.add(_new);
            for (char c: _string.toCharArray()) {
                list_.add(Character.toString(c));
                list_.add(_new);
            }
            return join(list_, EMPTY_STRING);
        }
        StringBuilder list_ = new StringBuilder();
        int i_ = IndexConstants.FIRST_INDEX;
        int len_ = _string.length();
        int index_;
        while (i_ < len_) {
            index_ = _string.indexOf(_old, i_);
            if (index_ < 0) {
                break;
            }
            list_.append(_string, i_, index_);
            list_.append(_new);
            i_ = index_ + _old.length();
        }
        return appendLastPart(_string, list_, i_, len_);
    }

    private static String replaceWhenNull(String _string, String _old) {
        if (_old.isEmpty()) {
            return _string;
        }
        StringBuilder list_ = new StringBuilder();
        int i_ = IndexConstants.FIRST_INDEX;
        int len_ = _string.length();
        int index_;
        while (i_ < len_) {
            index_ = _string.indexOf(_old, i_);
            if (index_ < 0) {
                break;
            }
            list_.append(_string, i_, index_);
            i_ = index_ + _old.length();
        }
        return appendLastPart(_string, list_, i_, len_);
    }

    private static String appendLastPart(String _string, StringBuilder _list, int _i, int _len) {
        if (_i < _len) {
            _list.append(_string.substring(_i));
        }
        return _list.toString();
    }

    public static Ints indexesOfSubString(String _string, String _subString) {
        Ints list_ = new Ints();
        if (_subString.isEmpty()) {
            list_.add((int) IndexConstants.FIRST_INDEX);
            return list_;
        }
        int i_ = IndexConstants.FIRST_INDEX;
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

    public static Ints indexesOfChar(String _string, char _subString) {
        Ints list_ = new Ints();
        int i_ = IndexConstants.FIRST_INDEX;
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

    public static StringList intersect(CustList<String> _first, CustList<String> _list) {
        StringList list_ = new StringList();
        for (String s: _list) {
            if (contains(_first,s)) {
                list_.add(s);
            }
        }
        return list_;
    }

    public static void removePrefixInStrings(CustList<String> _list, String _prefix) {
        //setModified();
        int size_ = _list.size();
        for (int i = IndexConstants.FIRST_INDEX; i<size_; i++) {
            if (!_list.get(i).startsWith(_prefix)) {
                continue;
            }
            _list.set(i, _list.get(i).substring(_prefix.length()));
        }
    }

    public static void removeAllElements(CustList<String> _strings, CustList<String> _c) {
        for (String s: _c) {
            removeAllObj(_strings, s);
        }
    }

    public static void removeAllObj(CustList<String> _strings, String _obj) {
        int index_ = _strings.size() - 1;
        while (index_ >= IndexConstants.FIRST_INDEX) {
            String current_ = _strings.get(index_);
            if (quickEq(_obj,current_)) {
                _strings.remove(index_);
            }
            index_--;
        }
    }

    public static void removeObj(CustList<String> _strings, String _obj) {
        int index_ = indexOf(_strings,_obj);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        _strings.remove(index_);
    }

    public static boolean startsWith(String _string, String _filter) {
        return _string.startsWith(_filter.trim());
    }

    public static boolean endsWith(String _string, String _filter) {
        return _string.endsWith(_filter.trim());
    }

    public static boolean matchSpace(String _string, String _filter){
        if(_filter.trim().isEmpty()){
            return true;
        }
        WordsSeparators wordsAndSeparators_
                =wordsAndSeparatorsSpace(_filter);
        StringList words_=wordsAndSeparators_.getWords();
        StringList separators_=wordsAndSeparators_.getSeparators();
        tryAddSeps(wordsAndSeparators_, separators_);
        IndexSeparators ind_ = new IndexSeparators();
        for(String e:words_){
            if (!hasNextSepSpace(_string,e,ind_,wordsAndSeparators_)){
                return false;
            }
        }
        int i_= ind_.getIndexSep();
        int index_= ind_.getIndex();
        if(index_==_string.length()){
            return true;
        }
        return separators_.get(i_).contains(Character.toString(SPACE_CHAR));
    }

    private static boolean hasNextSepSpace(String _string,String _w,IndexSeparators _curr, WordsSeparators _ws) {
        int index_ = _curr.getIndex();
        int i_ = _curr.getIndexSep();
        StringList words_=_ws.getWords();
        StringList separators_=_ws.getSeparators();
        if(separators_.get(i_).contains(Character.toString(SPACE_CHAR))){
            return processAny(_string, _w, _curr, i_, index_, words_);
        }
        int indiceNext_ = _string.indexOf(_w, index_);
        if(index_ != indiceNext_){
            return false;
        }
        return processKeep(_w, _curr, i_, indiceNext_);
    }

    private static boolean processAny(String _string, String _w, IndexSeparators _curr, int _i, int _indiceRDecalePt, StringList _words) {
        int indiceNext_;
        if (_words.isValidIndex(_i + 1)) {
            indiceNext_ = _string.indexOf(_w, _indiceRDecalePt);
        } else {
            indiceNext_ = greatestIndex(_string, _w, _indiceRDecalePt);
        }
        if (indiceNext_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return false;
        }
        return processKeep(_w, _curr, _i, indiceNext_);
    }

    private static void tryAddSeps(WordsSeparators _wordsAndSeparators, StringList _separators) {
        if (_wordsAndSeparators.isFirstSep()) {
            _separators.add(IndexConstants.FIRST_INDEX, EMPTY_STRING);
        }
        if (_wordsAndSeparators.isLastSep()) {
            _separators.add(EMPTY_STRING);
        }
    }

    public static boolean match(String _string, String _filter){
        if(_filter.isEmpty()){
            return true;
        }
        WordsSeparators wordsAndSeparators_
                =wordsAndSeparators(_filter);
        StringList words_=wordsAndSeparators_.getWords();
        StringList separators_=wordsAndSeparators_.getSeparators();
        if (words_.isEmpty()) {
            return procNoWords(_string, separators_);
        }
        tryAddSeps(wordsAndSeparators_, separators_);
        IndexSeparators ind_ = new IndexSeparators();
        for(String e:words_){
            if (!hasNextSep(_string,e,ind_,wordsAndSeparators_)){
                return false;
            }
        }
        int index_= ind_.getIndex();
        return procDefFilter(_string, separators_, index_);
    }

    private static boolean hasNextSep(String _string,String _w,IndexSeparators _curr, WordsSeparators _ws) {
        int i_ = _curr.getIndexSep();
        StringList separators_=_ws.getSeparators();
        String sep_ = separators_.get(i_);
        NbPtsZeroOne nb_ = calculateSeparators(sep_);
        return hasNextSep(_string, _w, _curr, _ws, nb_.getNbPts(), nb_.getNbZeroOne());
    }

    private static boolean hasNextSep(String _string, String _w, IndexSeparators _curr, WordsSeparators _ws, int _nbPts, int _nbZeroOne) {
        StringList words_=_ws.getWords();
        StringList separators_=_ws.getSeparators();
        int index_ = _curr.getIndex();
        int i_ = _curr.getIndexSep();
        int indiceRDecalePt_ = index_ + _nbPts;
        if(separators_.get(i_).contains(Character.toString(STRING))){
            return processAny(_string, _w, _curr, i_, indiceRDecalePt_, words_);
        }
        int indiceNext_ = _string.indexOf(_w, indiceRDecalePt_);
        if(indiceRDecalePt_>indiceNext_||indiceRDecalePt_<indiceNext_- _nbZeroOne){
            return false;
        }
        return processKeep(_w, _curr, i_, indiceNext_);
    }

    private static boolean processKeep(String _w, IndexSeparators _curr, int _i, int _indiceNext) {
        int i_ = _i;
        int index_ = _indiceNext + _w.length();
        i_++;
        _curr.setIndex(index_);
        _curr.setIndexSep(i_);
        return true;
    }

    private static boolean procNoWords(String _string, StringList _separators) {
        int index_ = IndexConstants.FIRST_INDEX;
        return procDefFilter(_string, _separators, index_);
    }

    private static boolean procDefFilter(String _string, StringList _separators, int _i) {
        String lastSep_ = _separators.last();
        NbPtsZeroOne nb_ = calculateSeparators(lastSep_);
        int nbPts_ = nb_.getNbPts();
        int nbZeroOne_ = nb_.getNbZeroOne();
        int index_ = _i + nbPts_;
        if(index_==_string.length()){
            return true;
        }
        if(index_<_string.length()){
            if(lastSep_.contains(Character.toString(STRING))){
                return true;
            }
            return _string.length() <= index_ + nbZeroOne_;
        }
        return false;
    }

    private static NbPtsZeroOne calculateSeparators(String _sep) {
        NbPtsZeroOne nb_ = new NbPtsZeroOne();
        for (char c : _sep.toCharArray()) {
            if (c == CHARACTER) {
                nb_.setNbPts(nb_.getNbPts() + 1);
            }
            if (c == POSSIBLE_CHAR) {
                nb_.setNbZeroOne(nb_.getNbZeroOne() + 1);
            }
        }
        return nb_;
    }

    public static int greatestIndex(String _string, String _substring, int _offset) {
        String start_ = _string.substring(_offset);
        if (!start_.contains(_substring)) {
            return IndexConstants.INDEX_NOT_FOUND_ELT;
        }
        int index_ = IndexConstants.FIRST_INDEX;
        int return_;
        while (true) {
            return_ = index_;
            return_--;
            int ind_ = start_.indexOf(_substring, index_);
            if (ind_ != IndexConstants.INDEX_NOT_FOUND_ELT) {
                index_ = ind_ + 1;
            } else {
                break;
            }
        }
        return return_ + _offset;
    }

    private static WordsSeparators wordsAndSeparatorsSpace(String _string){
        WordsSeparators wordsSepBeginEnd_;
        wordsSepBeginEnd_ = new WordsSeparators();
        StringList words_ = new StringList();
        StringList separators_ = new StringList();
        CharList metas_ = getMetaCharactersSpace();
        feedSeparators(_string, wordsSepBeginEnd_, words_, separators_, metas_);
        int nbWords_=words_.size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbWords_; i++){
            String escapedString_= escapeSpace(words_.get(i));
            words_.set(i, escapedString_);
        }
        wordsSepBeginEnd_.setSeparators(separators_);
        wordsSepBeginEnd_.setWords(words_);
        return wordsSepBeginEnd_;
    }

    private static void feedSeparators(String _string, WordsSeparators _wordsSeps, StringList _words, StringList _separators, CharList _metas) {
        int begin_ = IndexConstants.FIRST_INDEX;
        while (true) {
            int minIndex_ = lowestIndexOfMetaChar(_string, begin_, _metas);
            if (minIndex_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                procLastSepSpace(_string, _wordsSeps, _words, begin_);
                break;
            }
            if (minIndex_ > begin_) {
                _words.add(_string.substring(begin_, minIndex_));
                if (begin_ == IndexConstants.FIRST_INDEX) {
                    _wordsSeps.setFirstSep(true);
                }
            }
            int ind_ = lowestIndexOfWordChar(_string, minIndex_, _metas);
            //ind_ < _string.length() ==> all character after or at minIndex_ are meta characters
            //so if ind_ is lower than the length of the string _string,
            //then this string does not end with a character of word
            _separators.add(_string.substring(minIndex_, ind_));
            begin_ = ind_;
        }
    }

    private static void procLastSepSpace(String _string, WordsSeparators _wordsSepBeginEnd, StringList _words, int _begin) {
        if (_begin < _string.length()) {
            _words.add(_string.substring(_begin));
        }
        if (_begin == IndexConstants.FIRST_INDEX) {
            _wordsSepBeginEnd.setFirstSep(true);
        }
        _wordsSepBeginEnd.setLastSep(_begin < _string.length());
    }

    private static WordsSeparators wordsAndSeparators(String _string){
        WordsSeparators wordsSepBeginEnd_;
        wordsSepBeginEnd_ = new WordsSeparators();
        StringList words_ = new StringList();
        StringList separators_ = new StringList();
        CharList metas_ = getMetaCharacters();
        feedSeparators(_string, wordsSepBeginEnd_, words_, separators_, metas_);
        int nbWords_=words_.size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbWords_; i++){
            String escapedString_= escape(words_.get(i));
            words_.set(i, escapedString_);
        }
        wordsSepBeginEnd_.setSeparators(separators_);
        wordsSepBeginEnd_.setWords(words_);
        return wordsSepBeginEnd_;
    }

    private static int lowestIndexOfMetaChar(String _string,
                                             int _begin,
                                             CharList _metas) {
        int minIndex_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        for (char s: _metas) {
            int index_ = indexOf(_string, _begin, s);
            if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                continue;
            }
            if (minIndex_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                minIndex_ = index_;
            } else {
                minIndex_ = Math.min(minIndex_, index_);
            }
        }
        return minIndex_;
    }

    private static int lowestIndexOfWordChar(String _string,
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
        if (index_ == IndexConstants.FIRST_INDEX || index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
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
            if (ind_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                index_ = IndexConstants.INDEX_NOT_FOUND_ELT;
                break;
            }
            index_ = ind_;
        }
        return index_;
    }

    private static CharList getMetaCharacters() {
        return new CharList(CHARACTER, STRING, POSSIBLE_CHAR);
    }

    private static CharList getMetaCharactersSpace() {
        return new CharList(SPACE_CHAR);
    }

    public static void retainAllElements(CustList<String> _strings, CustList<String> _c) {
        int i_ = _strings.size() - 1;
        while (i_ >= IndexConstants.FIRST_INDEX) {
            String e_ = _strings.get(i_);
            if (!contains(_c, e_)) {
                _strings.remove(i_);
            }
            i_--;
        }
    }

    public static String join(CustList<String> _strings, String _join) {
        if (_strings.isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder return_ = new StringBuilder(_strings.first());
        int size_ = _strings.size();
        for (int i = IndexConstants.SECOND_INDEX; i<size_; i++) {
            return_.append(_join);
            return_.append(_strings.get(i));
        }
        return return_.toString();
    }

    public static String join(CustList<String> _strings, char _join) {
        if (_strings.isEmpty()) {
            return EMPTY_STRING;
        }
        StringBuilder return_ = new StringBuilder(_strings.first());
        int size_ = _strings.size();
        for (int i = IndexConstants.SECOND_INDEX; i<size_; i++) {
            return_.append(_join);
            return_.append(_strings.get(i));
        }
        return return_.toString();
    }

    public static boolean disjoints(CustList<StringList> _list) {
        int size_ = _list.size();
        for (int i = IndexConstants.FIRST_INDEX; i < size_; i++) {
            for (int j = IndexConstants.FIRST_INDEX; j < size_; j++) {
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

    public static String spliceIfFirst(char _ch, String _str) {
        if (_str.isEmpty()) {
            return _str;
        }
        if (_str.charAt(0) == _ch) {
            return _str.substring(1);
        }
        return _str;
    }

    public static String[] wrapStringArray(String... _strings) {
        return _strings;
    }

    public static String concat(String... _strings) {
        StringBuilder str_ = new StringBuilder();
        for (String s: _strings) {
            str_.append(s);
        }
        return str_.toString();
    }

    public static String concatNbs(String _string, long _nbs) {
        StringBuilder str_ = new StringBuilder(_string);
        str_.append(_nbs);
        return str_.toString();
    }

    public static String concatNb(long _nb, String _string) {
        StringBuilder str_ = new StringBuilder();
        str_.append(_nb);
        str_.append(_string);
        return str_.toString();
    }

    public static String escape(String _input) {
        int length_ = _input.length();
        char[] newArray_ = new char[length_];
        int i_ = IndexConstants.FIRST_INDEX;
        int j_ = IndexConstants.FIRST_INDEX;
        int newLength_ = length_;
        while (i_ < length_) {
            char c_ = _input.charAt(i_);
            if (c_ == ESCAPING_CHAR) {
                int next_ = i_;
                next_++;
                EscapeState esc_ = new EscapeState(i_,c_,newLength_);
                if (next_ < length_ && isEscChar(_input, next_)) {
                    esc_.apply(_input);
                }
                i_ = esc_.getIndex();
                c_ = esc_.getCurChar();
                newLength_ = esc_.getNewLength();
            }
            newArray_[j_] = c_;
            j_++;
            i_++;
        }
        return extract(newArray_, newLength_);
    }

    private static boolean isEscChar(String _input, int _next) {
        return _input.charAt(_next) == CHARACTER || _input.charAt(_next) == STRING || _input.charAt(_next) == POSSIBLE_CHAR || _input.charAt(_next) == ESCAPING_CHAR;
    }

    public static String escapeSpace(String _input) {
        int length_ = _input.length();
        char[] newArray_ = new char[length_];
        int i_ = IndexConstants.FIRST_INDEX;
        int j_ = IndexConstants.FIRST_INDEX;
        int newLength_ = length_;
        while (i_ < length_) {
            char c_ = _input.charAt(i_);
            if (c_ == ESCAPING_CHAR) {
                int next_ = i_;
                next_++;
                EscapeState esc_ = new EscapeState(i_,c_,newLength_);
                if (next_ < length_ && isEscSpaceChar(_input, next_)) {
                    esc_.apply(_input);
                }
                i_ = esc_.getIndex();
                c_ = esc_.getCurChar();
                newLength_ = esc_.getNewLength();
            }
            newArray_[j_] = c_;
            j_++;
            i_++;
        }
        return extract(newArray_, newLength_);
    }

    private static boolean isEscSpaceChar(String _input, int _next) {
        return _input.charAt(_next) == SPACE_CHAR || _input.charAt(_next) == ESCAPING_CHAR;
    }

    private static String extract(char[] _arr, int _len) {
        int min_ = Math.min(_arr.length,_len);
        char[] ext_ = new char[min_];
        for (int i = 0; i < min_; i++) {
            set(_arr, ext_, i);
        }
        return String.valueOf(ext_);
    }

    private static void set(char[] _arr, char[] _ext, int _i) {
        _ext[_i] = _arr[_i];
    }

    public static String getFirstToken(String _string, char _separator) {
        int index_ = _string.indexOf(_separator);
        return getFirstTokenIndex(_string, index_);
    }

    public static String getFirstToken(String _string, String _separator) {
        int index_ = _string.indexOf(_separator);
        return getFirstTokenIndex(_string, index_);
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

    private static String wrapLine(CustList<String> _lineParts, int _maxCols) {
        StringBuilder str_ = new StringBuilder();
        boolean entered_ = false;
        int pos_ = IndexConstants.SIZE_EMPTY;
        for (String p: _lineParts) {
            if (!entered_) {
                pos_ = p.length();
                str_.append(p);
                entered_ = true;
                continue;
            }
            if (p.length() + pos_ + 1 > _maxCols) {
                pos_ = p.length();
                str_.append(LINE_RETURN_CHAR);
            } else {
                pos_ += p.length() + 1;
                str_.append(SPACE_CHAR);
            }
            str_.append(p);
            entered_ = true;
        }
        return str_.toString();
    }

    public static StringList splitChars(String _string, char... _separators) {
        StringList l_ = new StringList();
        CharList cs_ = new CharList(_separators);
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            char ch_ = _string.charAt(i);
            if (cs_.containsChar(ch_)) {
                l_.add(str_.toString());
                str_ = new StringBuilder();
            } else {
                str_.append(ch_);
            }
        }
        l_.add(str_.toString());
        return l_;
    }

    public static StringList splitChar(String _string, char _separator) {
        StringList l_ = new StringList();
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            char ch_ = _string.charAt(i);
            if (_separator == ch_) {
                l_.add(str_.toString());
                str_ = new StringBuilder();
            } else {
                str_.append(ch_);
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
            String next_ = nextStr(len_, sub_, l_, _separators);
            if (next_ == null) {
                break;
            }
            sub_ = next_;
        }
        return l_;
    }

    private static String nextStr(int _len,String _sub, StringList _list,String... _separators) {
        SubstringResults sub_ = new SubstringResults(_len);
        sub_.calculate(_sub, _separators);
        String candidate_ = sub_.getCandidate();
        int minIndex_ = sub_.getMinIndex();
        int maxLength_ = sub_.getMaxLength();
        if (candidate_ == null) {
            _list.add(_sub);
            return null;
        }
        _list.add(_sub.substring(IndexConstants.FIRST_INDEX, minIndex_));
        return _sub.substring(minIndex_ + maxLength_);
    }

    public static StringList splitCharSep(String _string, char _separators) {
        StringList l_ = new StringList();
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            char ch_ = _string.charAt(i);
            if (_separators == ch_) {
                l_.add(str_.toString());
                l_.add(Character.toString(ch_));
                str_ = new StringBuilder();
            } else {
                str_.append(ch_);
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
            String next_ = nextStrSep(len_, sub_, l_, _separators);
            if (next_ == null) {
                break;
            }
            sub_ = next_;
        }
        return l_;
    }

    private static String nextStrSep(int _len,String _sub, StringList _list,String... _separators) {
        SubstringResults sub_ = new SubstringResults(_len);
        sub_.calculate(_sub, _separators);
        String candidate_ = sub_.getCandidate();
        int minIndex_ = sub_.getMinIndex();
        int maxLength_ = sub_.getMaxLength();
        if (candidate_ == null) {
            _list.add(_sub);
            return null;
        }
        _list.add(_sub.substring(IndexConstants.FIRST_INDEX, minIndex_));
        _list.add(candidate_);
        return _sub.substring(minIndex_ + maxLength_);
    }
    public static StringList getTokensSets(String _str) {
        int i_ = IndexConstants.FIRST_INDEX;
        StringList tokens_ = new StringList();
        while (true) {
            int index_ = _str.indexOf(LEFT_BRACE, i_);
            if (index_ < 0) {
                tokens_.add(_str.substring(i_));
                break;
            }
            int indexTwo_ = _str.indexOf(RIGHT_BRACE, index_ + 2);
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

    /**The returned String ends with a slash.*/
    public static String replaceBackSlashDot(String _path) {
        String path_ = replaceBackSlash(_path);
        if (path_.endsWith(concat(Character.toString(SLASH),Character.toString(DOT)))) {
            path_ = path_.substring(0, path_.length() - 1);
        } else {
            if (!path_.endsWith(Character.toString(SLASH))) {
                path_ = concat(path_, Character.toString(SLASH));
            }
        }
        return path_;
    }

    public static String replaceBackSlash(String _fileName) {
        return nullToEmpty(_fileName).replace(BACK_SLASH_CHAR, SLASH);
    }

    public static String replaceExtension(String _file, String _oldExt, String _newExt) {
        if (!_file.endsWith(_oldExt)) {
            return _file;
        }
        return concat(_file.substring(IndexConstants.FIRST_INDEX, _file.length() - _oldExt.length()), _newExt);
    }

    public static String replaceExtension(String _str) {
        int lastIndexDot_ = _str.lastIndexOf(DOT);
        return getFirstTokenIndex(_str, lastIndexDot_);
    }

    private static String getFirstTokenIndex(String _string, int _index) {
        if (_index == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return _string;
        }
        return _string.substring(IndexConstants.FIRST_INDEX, _index);
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
            ret_ = _str.substring(IndexConstants.FIRST_INDEX, _str.length() - _suffix.length());
        }
        return ret_;
    }

    public static StringList splitInTwo(String _string, int _limit) {
        StringList l_ = new StringList();
        StringBuilder str_ = new StringBuilder();
        int len_ = _string.length();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (i == _limit) {
                l_.add(str_.toString());
                str_ = new StringBuilder();
            }
            str_.append(_string.charAt(i));
        }
        l_.add(str_.toString());
        return l_;
    }
    public static boolean containsAllObj(CustList<String> _this,CustList<String> _list) {
        for (String e: _list) {
            if (!StringUtil.contains(_this, e)) {
                return false;
            }
        }
        return true;
    }
    public static boolean disjoint(CustList<String> _this,CustList<String> _list) {
        for (String s: _list) {
            if (StringUtil.contains(_this,s)) {
                return false;
            }
        }
        return true;
    }
    public static boolean contains(CustList<String> _list, String _value) {
        return indexOf(_list,_value) > IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public static int indexOf(CustList<String> _list, String _value) {
        return indexOf(_list,_value, IndexConstants.FIRST_INDEX);
    }

    public static int indexOf(CustList<String> _list, String _value, int _from) {
        int s_ = _list.size();
        for (int i = _from; i < s_; i++) {
            String e_ = _list.get(i);
            if (quickEq(_value, e_)) {
                return i;
            }
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public static String nullToEmpty(String _value) {
        if (_value == null) {
            return "";
        }
        return _value;
    }
    public static boolean isWhitespace(char _ch) {
        return _ch <= ' ';
    }


    public static int compareStrings(String _first, String _second) {
        int lenFirst_ = _first.length();
        int lenSecond_ = _second.length();
        int lim_ = Math.min(lenFirst_, lenSecond_);

        int k = 0;
        while (k < lim_) {
            int diff_ = NumberUtil.compareLg(_first.charAt(k), _second.charAt(k));
            if (diff_ != 0) {
                return diff_;
            }
            k++;
        }
        return NumberUtil.compareLg(lenFirst_, lenSecond_);
    }
}
