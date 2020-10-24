package code.util.core;

import code.util.*;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;

public final class StringUtil {
    public static final char LEFT_BRACE = '{';
    public static final char RIGHT_BRACE = '}';
    public static final String POINT = ".";
    public static final String EMPTY_STRING = "";
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

    public static int badDecode(byte[] _bytes, int _from, int _length) {
        int len_ = _from + _length;
        int i_ = _from;
        while (i_ < len_) {
            byte cur_ = _bytes[i_];
            if (cur_ >= 0) {
                i_++;
                continue;
            }
            if (i_ + 1 >= len_) {
                return i_;
            }
            if (cur_ < -62) {
                return i_;
            }
            if (cur_ > -17) {
                return i_;
            }
            byte next_ = _bytes[i_ + 1];
            if (next_ > -65) {
                return i_;
            }
            if (cur_ <= -33) {
                i_++;
                i_++;
                continue;
            }
            if (i_ + 2 >= len_) {
                return i_;
            }
            byte afterNext_ = _bytes[i_ + 2];
            if (afterNext_ > -65) {
                return i_;
            }
            i_++;
            i_++;
            i_++;
        }
        return -1;
    }

    public static String toLowerCase(String _string) {
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder(len_);
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            str_.append(toLowerCase(_string.charAt(i)));
        }
        return str_.toString();
    }

    public static String toUpperCase(String _string) {
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder(len_);
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            str_.append(toUpperCase(_string.charAt(i)));
        }
        return str_.toString();
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
            int length_ = _pattern.length();
            StringBuilder strBuilder_ = new StringBuilder();
            int i_ = IndexConstants.FIRST_INDEX;
            StringList keys_ = new StringList(_map.getKeys());
            boolean exit_ = false;
            while (i_ < length_) {
                int j_ = IndexConstants.FIRST_INDEX;
                StringList list_ = keys_;
                while (true) {
                    StringList nexList_ = new StringList();
                    for (String k: list_) {
                        if (okKey(_pattern, i_, j_, k)) {
                            nexList_.add(k);
                        }
                    }
                    list_ = nexList_;
                    if (_pattern.length() <= j_ + i_ + 1) {
                        exit_ = true;
                        String subString_ = _pattern.substring(i_);
                        if (contains(list_, subString_)) {
                            strBuilder_.append(_map.getVal(subString_));
                        } else {
                            strBuilder_.append(subString_);
                        }
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
        int length_ = _pattern.length();
        StringBuilder strBuilder_ = new StringBuilder();
        int i_ = IndexConstants.FIRST_INDEX;
        StringList keys_ = new StringList(_map.getKeys());
        boolean exit_ = false;
        while (i_ < length_) {
            int j_ = IndexConstants.FIRST_INDEX;
            StringList list_ = keys_;
            while (true) {
                StringList nexList_ = new StringList();
                for (String k: list_) {
                    if (okKey(_pattern, i_, j_, k)) {
                        nexList_.add(k);
                    }
                }
                list_ = nexList_;
                if (_pattern.length() <= j_ + i_ + 1) {
                    exit_ = true;
                    String subString_ = _pattern.substring(i_);
                    if (contains(list_, subString_)) {
                        strBuilder_.append(_map.getVal(subString_));
                    } else {
                        strBuilder_.append(subString_);
                    }
                    break;
                }
//                String subString_ = _pattern.substring(i_, j_ + i_ + 1);
                String subString_ = _pattern.substring(i_, Math.min(j_ + i_ + 1, _pattern.length()));
                boolean exist_ = false;
                for (String s: list_) {
                    if (s.contains(concat(subString_, String.valueOf(_pattern.charAt(j_ + i_ + 1))))) {
                        exist_ = true;
                        break;
                    }
                }
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
            int nbSeqQuotes_ = IndexConstants.SIZE_EMPTY;
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
            for (int i = IndexConstants.SIZE_EMPTY; i < nbAdds_; i++) {
                strBuilder_.append(_sep);
            }
            if (quoted_) {
                continue;
            }
            int j_ = IndexConstants.FIRST_INDEX;
            StringList list_ = keys_;
            while (true) {
                StringList nexList_ = new StringList();
                for (String k: list_) {
                    if (okKey(_pattern, i_, j_, k)) {
                        nexList_.add(k);
                    }
                }
                list_ = nexList_;
//                String subString_ = _pattern.substring(i_, j_ + i_ + 1);
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
            i_++;
        }
        return strBuilder_.toString();
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
                int argNb_ = NumberUtil.parseInt(arg_.toString());
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
                int argNb_ = NumberUtil.parseInt(arg_.toString());
                if (argNb_ >= 0 && argNb_ < argLength_) {
                    long a_ = _args[argNb_];
                    str_.append(Long.toString(a_));
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
            if (i_ < len_) {
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
        if (i_ < len_) {
            list_.append(_string.substring(i_));
        }
        return list_.toString();
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
        if (wordsAndSeparators_.isFirstSep()) {
            separators_.add(IndexConstants.FIRST_INDEX, EMPTY_STRING);
        }
        if (wordsAndSeparators_.isLastSep()) {
            separators_.add(EMPTY_STRING);
        }
        int i_= IndexConstants.FIRST_INDEX;
        int index_= IndexConstants.FIRST_INDEX;
        int indiceRDecalePt_;
        int indiceNext_;
        for(String e:words_){
            indiceRDecalePt_=index_;
            //indiceNext_=_string.indexOf(e,indiceRDecalePt_);
            //indiceNext_ = greatestIndex(_string, e, indiceRDecalePt_);
            if(separators_.get(i_).contains(String.valueOf(SPACE_CHAR))){
                if (words_.isValidIndex(i_+1)) {
                    indiceNext_=_string.indexOf(e,indiceRDecalePt_);
                    if(indiceNext_ == IndexConstants.INDEX_NOT_FOUND_ELT){
                        return false;
                    }
                } else {
                    indiceNext_=greatestIndex(_string, e,indiceRDecalePt_);
                    if(indiceNext_ == IndexConstants.INDEX_NOT_FOUND_ELT){
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
        return separators_.get(i_).contains(String.valueOf(SPACE_CHAR));
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
            String lastSep_ = separators_.last();
            int nbPts_ = 0;
            int nbZeroOne_ = 0;
            int index_ = IndexConstants.FIRST_INDEX;
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
                return _string.length() <= index_ + nbZeroOne_;
            }
            return false;
        }
        if (wordsAndSeparators_.isFirstSep()) {
            separators_.add(IndexConstants.FIRST_INDEX, EMPTY_STRING);
        }
        if (wordsAndSeparators_.isLastSep()) {
            separators_.add(EMPTY_STRING);
        }
        int i_= IndexConstants.FIRST_INDEX;
        int index_= IndexConstants.FIRST_INDEX;
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
            int indiceRDecalePt_ = index_ + nbPts_;
            int indiceNext_;
            if(separators_.get(i_).contains(String.valueOf(STRING))){
                //indiceNext_=_string.indexOf(e,indiceRDecalePt_);
                //indiceNext_ = greatestIndex(_string, e, indiceRDecalePt_);
                if (words_.isValidIndex(i_+1)) {
                    indiceNext_=_string.indexOf(e,indiceRDecalePt_);
                    if(indiceNext_ == IndexConstants.INDEX_NOT_FOUND_ELT){
                        return false;
                    }
                } else {
                    indiceNext_=greatestIndex(_string, e,indiceRDecalePt_);
                    if(indiceNext_ == IndexConstants.INDEX_NOT_FOUND_ELT){
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
            return _string.length() <= index_ + nbZeroOne_;
        }
        return false;
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
        int begin_ = IndexConstants.FIRST_INDEX;
        while (true) {
            int minIndex_ = lowestIndexOfMetaChar(_string, begin_, metas_);
            if (minIndex_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                if (begin_ < _string.length()) {
                    words_.add(_string.substring(begin_));
                }
                if (begin_ == IndexConstants.FIRST_INDEX) {
                    wordsSepBeginEnd_.setFirstSep(true);
                }
                wordsSepBeginEnd_.setLastSep(begin_ < _string.length());
                break;
            }
            if (minIndex_ > begin_) {
                words_.add(_string.substring(begin_, minIndex_));
                if (begin_ == IndexConstants.FIRST_INDEX) {
                    wordsSepBeginEnd_.setFirstSep(true);
                }
            }
            int ind_ = lowestIndexOfWordChar(_string, minIndex_, metas_);
            //ind_ < _string.length() ==> all character after or at minIndex_ are meta characters
            //so if ind_ is lower than the length of the string _string,
            //then this string does not end with a character of word
            separators_.add(_string.substring(minIndex_, ind_));
            begin_ = ind_;
        }
        int nbWords_=words_.size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbWords_; i++){
            String escapedString_= escapeSpace(words_.get(i));
            words_.set(i, escapedString_);
        }
        wordsSepBeginEnd_.setSeparators(separators_);
        wordsSepBeginEnd_.setWords(words_);
        return wordsSepBeginEnd_;
    }

    private static WordsSeparators wordsAndSeparators(String _string){
        WordsSeparators wordsSepBeginEnd_;
        wordsSepBeginEnd_ = new WordsSeparators();
        StringList words_ = new StringList();
        StringList separators_ = new StringList();
        CharList metas_ = getMetaCharacters();
        int begin_ = IndexConstants.FIRST_INDEX;
        while (true) {
            int minIndex_ = lowestIndexOfMetaChar(_string, begin_, metas_);
            if (minIndex_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                if (begin_ < _string.length()) {
                    words_.add(_string.substring(begin_));
                }
                if (begin_ == IndexConstants.FIRST_INDEX) {
                    wordsSepBeginEnd_.setFirstSep(true);
                }
                wordsSepBeginEnd_.setLastSep(begin_ < _string.length());
                break;
            }
            if (minIndex_ > begin_) {
                words_.add(_string.substring(begin_, minIndex_));
                if (begin_ == IndexConstants.FIRST_INDEX) {
                    wordsSepBeginEnd_.setFirstSep(true);
                }
            }
            int ind_ = lowestIndexOfWordChar(_string, minIndex_, metas_);
            //ind_ < _string.length() ==> all character after or at minIndex_ are meta characters
            //so if ind_ is lower than the length of the string _string,
            //then this string does not end with a character of word
            separators_.add(_string.substring(minIndex_, ind_));
            begin_ = ind_;
        }
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
        return new String(newArray_, IndexConstants.FIRST_INDEX,newLength_);
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
        return new String(newArray_, IndexConstants.FIRST_INDEX,newLength_);
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
                if (locLength_ == 0) {
                    continue;
                }
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
            l_.add(sub_.substring(IndexConstants.FIRST_INDEX, minIndex_));
            sub_ = sub_.substring(minIndex_ + maxLength_);
        }
        return l_;
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
                if (locLength_ == 0) {
                    continue;
                }
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
            l_.add(sub_.substring(IndexConstants.FIRST_INDEX, minIndex_));
            l_.add(candidate_);
            sub_ = sub_.substring(minIndex_ + maxLength_);
        }
        return l_;
    }

    public static String replaceWordsJoin(String _str, ListableEntries<String,String> _map) {
        return join(replaceWords(_str, _map), EMPTY_STRING);
    }

    private static StringList replaceWords(String _str, ListableEntries<String, String> _map) {
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

    public static StringList getWordsSeparatorsPrefix(String _str, String _prefixWord) {
        StringList list_ = getWordsSeparators(_str);
        StringList newList_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
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
        int i_ = IndexConstants.FIRST_INDEX;
        StringBuilder str_ = new StringBuilder();
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(_str);
                return ret_;
            }
            if (isKeyWordChar(_str.charAt(i_))) {
                str_.append(_str, IndexConstants.FIRST_INDEX, i_);
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
            if (isKeyWordChar(char_)) {
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

    public static StringList getDollarWordSeparators(String _str) {
        if (_str.length() == 0) {
            return new StringList();
        }
        StringList ret_ = new StringList();
        boolean wasWordChar_ = false;
        int i_ = IndexConstants.FIRST_INDEX;
        StringBuilder str_ = new StringBuilder();
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(_str);
                return ret_;
            }
            if (isDollarWordChar(_str.charAt(i_))) {
                str_.append(_str, IndexConstants.FIRST_INDEX, i_);
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
            if (isDollarWordChar(char_)) {
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
        String path_ = replaceBackSlash(_path);
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
        return concat(_file.substring(IndexConstants.FIRST_INDEX, _file.length() - _oldExt.length()), _newExt);
    }

    public static String replaceFinalFile(String _str) {
        int lastIndexDot_ = _str.lastIndexOf(DOT);
        if (lastIndexDot_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return _str;
        }
        int i_ = lastIndexDot_;
        i_--;
        while (i_ >= IndexConstants.FIRST_INDEX) {
            if (_str.charAt(i_) == SLASH) {
                if (i_ + 1 == lastIndexDot_) {
                    return _str;
                }
                return _str.substring(IndexConstants.FIRST_INDEX, i_);
            }
            if (!isWordChar(_str.charAt(i_))) {
                return _str;
            }
            i_--;
        }
        return _str;
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

    public static boolean isNumber(String _string) {
        if (_string.isEmpty()) {
            return false;
        }
        int i_ = IndexConstants.FIRST_INDEX;
        if (_string.charAt(i_) == MINUS) {
            if (_string.length() == IndexConstants.ONE_ELEMENT) {
                return false;
            }
            i_++;
        }
        int len_ = _string.length();
        while (i_ < len_) {
            if (!isDigit(_string.charAt(i_))) {
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

    public static boolean isPositiveNumber(String _string) {
        if (_string.isEmpty()) {
            return false;
        }
        for (char c : _string.toCharArray()) {
            if (!isDigit(c)) {
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
            if (!isKeyWordChar(c)) {
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

    public static boolean isKeyWordChar(char _ch) {
        if (_ch == CHAR_WORD_OTHER) {
            return true;
        }
        if (_ch < '0') {
            return false;
        }
        if (_ch <= '9') {
            return true;
        }
        if (_ch < 'A') {
            return false;
        }
        if (_ch <= 'Z') {
            return true;
        }
        if (_ch < 'a') {
            return false;
        }
        return _ch <= 'z';
    }

    public static boolean isWordChar(char _char) {
        if (_char == CHAR_WORD_OTHER) {
            return true;
        }
        return isLetterOrDigit(_char);
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

    public static boolean isWhitespace(char _ch) {
        return _ch <= ' ';
    }
    public static boolean isDigit(char _ch) {
        return _ch >= '0' && _ch <= '9';
    }
    public static int digit(char _ch,int _radix) {
        if (_radix < 2) {
            return -1;
        }
        if (_radix > 36) {
            return -1;
        }
        int digit_ = -1;
        if (isDigit(_ch)) {
            digit_ = _ch - '0';
        }
        if (_ch >= 'a' && _ch <= 'z') {
            digit_ = _ch - 'a' + 10;
        }
        if (_ch >= 'A' && _ch <= 'Z') {
            digit_ = _ch - 'A' + 10;
        }
        if (digit_ < _radix) {
            return digit_;
        }
        return -1;
    }

    public static char forDigit(int _digit,int _radix) {
        if (_radix < 2) {
            return 0;
        }
        if (_radix > 36) {
            return 0;
        }
        if (_digit < 0) {
            return 0;
        }
        if (_digit >= _radix) {
            return 0;
        }
        if (_digit < 10) {
            return (char) ('0'+_digit);
        }
        return (char)('a'+_digit-10);
    }
    public static boolean isLetterOrDigit(char _ch) {
        if (_ch < '0') {
            return false;
        }
        if (_ch <= '9') {
            return true;
        }
        if (_ch < 'A') {
            return false;
        }
        if (_ch <= 'Z') {
            return true;
        }
        if (_ch < 'a') {
            return false;
        }
        if (_ch <= 'z') {
            return true;
        }
        if (_ch < 170) {
            return false;
        }
        if (_ch < 192) {
            return _ch == 170 || _ch == 181 || _ch == 186;
        }
        if (_ch < 706) {
            return isSupplLetter(_ch);
        }
        if (isOtherLett(_ch)) {
            return true;
        }
        return isOtherDigit(_ch);
    }
    public static boolean isLetter(char _ch) {
        if (_ch < 'A') {
            return false;
        }
        if (_ch <= 'Z') {
            return true;
        }
        if (_ch < 'a') {
            return false;
        }
        if (_ch <= 'z') {
            return true;
        }
        if (_ch < 170) {
            return false;
        }
        if (_ch < 192) {
            return _ch == 170 || _ch == 181 || _ch == 186;
        }
        if (_ch < 706) {
            return isSupplLetter(_ch);
        }
        return isOtherLett(_ch);
    }

    private static boolean isOtherLett(char _ch) {
        if (_ch >= 19968 && _ch < 40909) {
            return true;
        }
        if (_ch >= 44032 && _ch < 55204) {
            return true;
        }
        if (_ch >= 13312 && _ch < 19894) {
            return true;
        }
        if (_ch >= 40960 && _ch < 42125) {
            return true;
        }
        if (_ch >= 5121 && _ch < 5741) {
            return true;
        }
        if (_ch >= 63744) {
            return isGreatLetter(_ch);
        }
        if (_ch >= 1015 && _ch < 1154) {
            return true;
        }
        if (_ch >= 1162 && _ch < 1320) {
            return true;
        }
        if (_ch >= 1649 && _ch < 1748) {
            return true;
        }
        if (_ch >= 4348 && _ch < 4681) {
            return true;
        }
        if (_ch >= 7680 && _ch < 7958) {
            return true;
        }
        if (_ch >= 7424 && _ch < 7616) {
            return true;
        }
        if (_ch >= 11360 && _ch < 11493) {
            return true;
        }
        if (_ch >= 12353 && _ch < 12439) {
            return true;
        }
        if (_ch >= 12449 && _ch < 12539) {
            return true;
        }
        if (_ch >= 12593 && _ch < 12687) {
            return true;
        }
        if (_ch >= 42240 && _ch < 42509) {
            return true;
        }
        if (_ch >= 42786 && _ch < 42889) {
            return true;
        }
        if (_ch >= 42656 && _ch < 42726) {
            return true;
        }
        if (_ch >= 43072 && _ch < 43124) {
            return true;
        }
        if (_ch >= 43138 && _ch < 43188) {
            return true;
        }
        if (_ch >= 11568 && _ch < 11624) {
            return true;
        }
        if (_ch >= 931 && _ch < 1014) {
            return true;
        }
        if (_ch >= 1869 && _ch < 1958) {
            return true;
        }
        if (_ch >= 2308 && _ch < 2362) {
            return true;
        }
        if (_ch >= 4824 && _ch < 4881) {
            return true;
        }
        if (_ch >= 4888 && _ch < 4955) {
            return true;
        }
        if (_ch >= 5024 && _ch < 5109) {
            return true;
        }
        if (_ch >= 5792 && _ch < 5867) {
            return true;
        }
        if (_ch >= 6176 && _ch < 6264) {
            return true;
        }
        if (_ch >= 6320 && _ch < 6390) {
            return true;
        }
        if (_ch >= 6016 && _ch < 6068) {
            return true;
        }
        if (_ch >= 6688 && _ch < 6741) {
            return true;
        }
        if (_ch >= 8064 && _ch < 8117) {
            return true;
        }
        if (_ch >= 55243 && _ch < 55292) {
            return true;
        }
        if (_ch >= 55216 && _ch < 55239) {
            return true;
        }
        if (_ch >= 42192 && _ch < 42238) {
            return true;
        }
        if (_ch >= 42560 && _ch < 42607) {
            return true;
        }
        if (_ch >= 42623 && _ch < 42648) {
            return true;
        }
        if (_ch >= 43020 && _ch < 43043) {
            return true;
        }
        if (_ch >= 43274 && _ch < 43302) {
            return true;
        }
        if (_ch >= 43312 && _ch < 43335) {
            return true;
        }
        if (_ch >= 43360 && _ch < 43389) {
            return true;
        }
        if (_ch >= 43396 && _ch < 43443) {
            return true;
        }
        if (_ch >= 43520 && _ch < 43561) {
            return true;
        }
        if (_ch >= 43616 && _ch < 43639) {
            return true;
        }
        if (_ch >= 43648 && _ch < 43696) {
            return true;
        }
        if (_ch >= 43968 && _ch < 44003) {
            return true;
        }
        if (_ch >= 710 && _ch < 722) {
            return true;
        }
        if (_ch >= 736 && _ch < 741) {
            return true;
        }
        if (_ch >= 880 && _ch < 885) {
            return true;
        }
        if (_ch >= 886 && _ch < 888) {
            return true;
        }
        if (_ch >= 890 && _ch < 894) {
            return true;
        }
        if (_ch >= 904 && _ch < 907) {
            return true;
        }
        if (_ch >= 1520 && _ch < 1523) {
            return true;
        }
        if (_ch >= 1646 && _ch < 1648) {
            return true;
        }
        if (_ch >= 1765 && _ch < 1767) {
            return true;
        }
        if (_ch >= 1774 && _ch < 1776) {
            return true;
        }
        if (_ch >= 1786 && _ch < 1789) {
            return true;
        }
        if (_ch >= 2036 && _ch < 2038) {
            return true;
        }
        if (_ch >= 2210 && _ch < 2221) {
            return true;
        }
        if (_ch >= 2392 && _ch < 2402) {
            return true;
        }
        if (_ch >= 2417 && _ch < 2424) {
            return true;
        }
        if (_ch >= 2425 && _ch < 2432) {
            return true;
        }
        if (_ch >= 2437 && _ch < 2445) {
            return true;
        }
        if (_ch >= 2447 && _ch < 2449) {
            return true;
        }
        if (_ch >= 2474 && _ch < 2481) {
            return true;
        }
        if (_ch >= 2486 && _ch < 2490) {
            return true;
        }
        if (_ch >= 2524 && _ch < 2526) {
            return true;
        }
        if (_ch >= 2527 && _ch < 2530) {
            return true;
        }
        if (_ch >= 2544 && _ch < 2546) {
            return true;
        }
        if (_ch >= 2565 && _ch < 2571) {
            return true;
        }
        if (_ch >= 2575 && _ch < 2577) {
            return true;
        }
        if (_ch >= 2602 && _ch < 2609) {
            return true;
        }
        if (_ch >= 2610 && _ch < 2612) {
            return true;
        }
        if (_ch >= 2613 && _ch < 2615) {
            return true;
        }
        if (_ch >= 2616 && _ch < 2618) {
            return true;
        }
        if (_ch >= 2649 && _ch < 2653) {
            return true;
        }
        if (_ch >= 2674 && _ch < 2677) {
            return true;
        }
        if (_ch >= 2693 && _ch < 2702) {
            return true;
        }
        if (_ch >= 2703 && _ch < 2706) {
            return true;
        }
        if (_ch >= 2730 && _ch < 2737) {
            return true;
        }
        if (_ch >= 2738 && _ch < 2740) {
            return true;
        }
        if (_ch >= 2741 && _ch < 2746) {
            return true;
        }
        if (_ch >= 2784 && _ch < 2786) {
            return true;
        }
        if (_ch >= 2821 && _ch < 2829) {
            return true;
        }
        if (_ch >= 2831 && _ch < 2833) {
            return true;
        }
        if (_ch >= 2858 && _ch < 2865) {
            return true;
        }
        if (_ch >= 2866 && _ch < 2868) {
            return true;
        }
        if (_ch >= 2869 && _ch < 2874) {
            return true;
        }
        if (_ch >= 2908 && _ch < 2910) {
            return true;
        }
        if (_ch >= 2911 && _ch < 2914) {
            return true;
        }
        if (_ch >= 2949 && _ch < 2955) {
            return true;
        }
        if (_ch >= 2958 && _ch < 2961) {
            return true;
        }
        if (_ch >= 2962 && _ch < 2966) {
            return true;
        }
        if (_ch >= 2969 && _ch < 2971) {
            return true;
        }
        if (_ch >= 2974 && _ch < 2976) {
            return true;
        }
        if (_ch >= 2979 && _ch < 2981) {
            return true;
        }
        if (_ch >= 2984 && _ch < 2987) {
            return true;
        }
        if (_ch >= 2990 && _ch < 3002) {
            return true;
        }
        if (_ch >= 3077 && _ch < 3085) {
            return true;
        }
        if (_ch >= 3086 && _ch < 3089) {
            return true;
        }
        if (_ch >= 3114 && _ch < 3124) {
            return true;
        }
        if (_ch >= 3125 && _ch < 3130) {
            return true;
        }
        if (_ch >= 3160 && _ch < 3162) {
            return true;
        }
        if (_ch >= 3168 && _ch < 3170) {
            return true;
        }
        if (_ch >= 3205 && _ch < 3213) {
            return true;
        }
        if (_ch >= 3214 && _ch < 3217) {
            return true;
        }
        if (_ch >= 3242 && _ch < 3252) {
            return true;
        }
        if (_ch >= 3253 && _ch < 3258) {
            return true;
        }
        if (_ch >= 3296 && _ch < 3298) {
            return true;
        }
        if (_ch >= 3313 && _ch < 3315) {
            return true;
        }
        if (_ch >= 3333 && _ch < 3341) {
            return true;
        }
        if (_ch >= 3342 && _ch < 3345) {
            return true;
        }
        if (_ch >= 3424 && _ch < 3426) {
            return true;
        }
        if (_ch >= 3450 && _ch < 3456) {
            return true;
        }
        if (_ch >= 3461 && _ch < 3479) {
            return true;
        }
        if (_ch >= 3507 && _ch < 3516) {
            return true;
        }
        if (_ch >= 3520 && _ch < 3527) {
            return true;
        }
        if (_ch >= 3634 && _ch < 3636) {
            return true;
        }
        if (_ch >= 3648 && _ch < 3655) {
            return true;
        }
        if (_ch >= 3713 && _ch < 3715) {
            return true;
        }
        if (_ch >= 3719 && _ch < 3721) {
            return true;
        }
        if (_ch >= 3732 && _ch < 3736) {
            return true;
        }
        if (_ch >= 3737 && _ch < 3744) {
            return true;
        }
        if (_ch >= 3745 && _ch < 3748) {
            return true;
        }
        if (_ch >= 3754 && _ch < 3756) {
            return true;
        }
        if (_ch >= 3757 && _ch < 3761) {
            return true;
        }
        if (_ch >= 3762 && _ch < 3764) {
            return true;
        }
        if (_ch >= 3776 && _ch < 3781) {
            return true;
        }
        if (_ch >= 3804 && _ch < 3808) {
            return true;
        }
        if (_ch >= 3904 && _ch < 3912) {
            return true;
        }
        if (_ch >= 3976 && _ch < 3981) {
            return true;
        }
        if (_ch >= 4176 && _ch < 4182) {
            return true;
        }
        if (_ch >= 4186 && _ch < 4190) {
            return true;
        }
        if (_ch >= 4197 && _ch < 4199) {
            return true;
        }
        if (_ch >= 4206 && _ch < 4209) {
            return true;
        }
        if (_ch >= 4213 && _ch < 4226) {
            return true;
        }
        if (_ch >= 4682 && _ch < 4686) {
            return true;
        }
        if (_ch >= 4688 && _ch < 4695) {
            return true;
        }
        if (_ch >= 4698 && _ch < 4702) {
            return true;
        }
        if (_ch >= 4746 && _ch < 4750) {
            return true;
        }
        if (_ch >= 4786 && _ch < 4790) {
            return true;
        }
        if (_ch >= 4792 && _ch < 4799) {
            return true;
        }
        if (_ch >= 4802 && _ch < 4806) {
            return true;
        }
        if (_ch >= 4808 && _ch < 4823) {
            return true;
        }
        if (_ch >= 4882 && _ch < 4886) {
            return true;
        }
        if (_ch >= 4992 && _ch < 5008) {
            return true;
        }
        if (_ch >= 5743 && _ch < 5760) {
            return true;
        }
        if (_ch >= 5888 && _ch < 5901) {
            return true;
        }
        if (_ch >= 5902 && _ch < 5906) {
            return true;
        }
        if (_ch >= 5920 && _ch < 5938) {
            return true;
        }
        if (_ch >= 5952 && _ch < 5970) {
            return true;
        }
        if (_ch >= 5984 && _ch < 5997) {
            return true;
        }
        if (_ch >= 5998 && _ch < 6001) {
            return true;
        }
        if (_ch >= 6512 && _ch < 6517) {
            return true;
        }
        if (_ch >= 6593 && _ch < 6600) {
            return true;
        }
        if (_ch >= 6981 && _ch < 6988) {
            return true;
        }
        if (_ch >= 7086 && _ch < 7088) {
            return true;
        }
        if (_ch >= 7245 && _ch < 7248) {
            return true;
        }
        if (_ch >= 7401 && _ch < 7405) {
            return true;
        }
        if (_ch >= 7406 && _ch < 7410) {
            return true;
        }
        if (_ch >= 7413 && _ch < 7415) {
            return true;
        }
        if (_ch >= 7960 && _ch < 7966) {
            return true;
        }
        if (_ch >= 8008 && _ch < 8014) {
            return true;
        }
        if (_ch >= 8016 && _ch < 8024) {
            return true;
        }
        if (_ch >= 8118 && _ch < 8125) {
            return true;
        }
        if (_ch >= 8130 && _ch < 8133) {
            return true;
        }
        if (_ch >= 8134 && _ch < 8141) {
            return true;
        }
        if (_ch >= 8144 && _ch < 8148) {
            return true;
        }
        if (_ch >= 8150 && _ch < 8156) {
            return true;
        }
        if (_ch >= 8160 && _ch < 8173) {
            return true;
        }
        if (_ch >= 8178 && _ch < 8181) {
            return true;
        }
        if (_ch >= 8182 && _ch < 8189) {
            return true;
        }
        if (_ch >= 8336 && _ch < 8349) {
            return true;
        }
        if (_ch >= 8458 && _ch < 8468) {
            return true;
        }
        if (_ch >= 8473 && _ch < 8478) {
            return true;
        }
        if (_ch >= 8490 && _ch < 8494) {
            return true;
        }
        if (_ch >= 8495 && _ch < 8506) {
            return true;
        }
        if (_ch >= 8508 && _ch < 8512) {
            return true;
        }
        if (_ch >= 8517 && _ch < 8522) {
            return true;
        }
        if (_ch >= 8579 && _ch < 8581) {
            return true;
        }
        if (_ch >= 11499 && _ch < 11503) {
            return true;
        }
        if (_ch >= 11506 && _ch < 11508) {
            return true;
        }
        if (_ch >= 11680 && _ch < 11687) {
            return true;
        }
        if (_ch >= 11688 && _ch < 11695) {
            return true;
        }
        if (_ch >= 11696 && _ch < 11703) {
            return true;
        }
        if (_ch >= 11704 && _ch < 11711) {
            return true;
        }
        if (_ch >= 11712 && _ch < 11719) {
            return true;
        }
        if (_ch >= 11720 && _ch < 11727) {
            return true;
        }
        if (_ch >= 11728 && _ch < 11735) {
            return true;
        }
        if (_ch >= 11736 && _ch < 11743) {
            return true;
        }
        if (_ch >= 12293 && _ch < 12295) {
            return true;
        }
        if (_ch >= 12337 && _ch < 12342) {
            return true;
        }
        if (_ch >= 12347 && _ch < 12349) {
            return true;
        }
        if (_ch >= 12445 && _ch < 12448) {
            return true;
        }
        if (_ch >= 12540 && _ch < 12544) {
            return true;
        }
        if (_ch >= 12784 && _ch < 12800) {
            return true;
        }
        if (_ch >= 910 && _ch < 930) {
            return true;
        }
        if (_ch >= 1329 && _ch < 1367) {
            return true;
        }
        if (_ch >= 1377 && _ch < 1416) {
            return true;
        }
        if (_ch >= 1488 && _ch < 1515) {
            return true;
        }
        if (_ch >= 1568 && _ch < 1611) {
            return true;
        }
        if (_ch >= 1810 && _ch < 1840) {
            return true;
        }
        if (_ch >= 1994 && _ch < 2027) {
            return true;
        }
        if (_ch >= 2048 && _ch < 2070) {
            return true;
        }
        if (_ch >= 2112 && _ch < 2137) {
            return true;
        }
        if (_ch >= 2451 && _ch < 2473) {
            return true;
        }
        if (_ch >= 2579 && _ch < 2601) {
            return true;
        }
        if (_ch >= 2707 && _ch < 2729) {
            return true;
        }
        if (_ch >= 2835 && _ch < 2857) {
            return true;
        }
        if (_ch >= 3090 && _ch < 3113) {
            return true;
        }
        if (_ch >= 3218 && _ch < 3241) {
            return true;
        }
        if (_ch >= 3346 && _ch < 3387) {
            return true;
        }
        if (_ch >= 3482 && _ch < 3506) {
            return true;
        }
        if (_ch >= 3585 && _ch < 3633) {
            return true;
        }
        if (_ch >= 3913 && _ch < 3949) {
            return true;
        }
        if (_ch >= 4096 && _ch < 4139) {
            return true;
        }
        if (_ch >= 4256 && _ch < 4294) {
            return true;
        }
        if (_ch >= 4304 && _ch < 4347) {
            return true;
        }
        if (_ch >= 4704 && _ch < 4745) {
            return true;
        }
        if (_ch >= 4752 && _ch < 4785) {
            return true;
        }
        if (_ch >= 5761 && _ch < 5787) {
            return true;
        }
        if (_ch >= 6272 && _ch < 6313) {
            return true;
        }
        if (_ch >= 6400 && _ch < 6429) {
            return true;
        }
        if (_ch >= 6480 && _ch < 6510) {
            return true;
        }
        if (_ch >= 6528 && _ch < 6572) {
            return true;
        }
        if (_ch >= 6656 && _ch < 6679) {
            return true;
        }
        if (_ch >= 6917 && _ch < 6964) {
            return true;
        }
        if (_ch >= 7043 && _ch < 7073) {
            return true;
        }
        if (_ch >= 7098 && _ch < 7142) {
            return true;
        }
        if (_ch >= 7168 && _ch < 7204) {
            return true;
        }
        if (_ch >= 7258 && _ch < 7294) {
            return true;
        }
        if (_ch >= 7968 && _ch < 8006) {
            return true;
        }
        if (_ch >= 8031 && _ch < 8062) {
            return true;
        }
        if (_ch >= 11264 && _ch < 11311) {
            return true;
        }
        if (_ch >= 11312 && _ch < 11359) {
            return true;
        }
        if (_ch >= 11520 && _ch < 11558) {
            return true;
        }
        if (_ch >= 11648 && _ch < 11671) {
            return true;
        }
        if (_ch >= 12549 && _ch < 12590) {
            return true;
        }
        if (_ch >= 12704 && _ch < 12731) {
            return true;
        }
        if (_ch >= 42512 && _ch < 42528) {
            return true;
        }
        if (_ch >= 42538 && _ch < 42540) {
            return true;
        }
        if (_ch >= 42775 && _ch < 42784) {
            return true;
        }
        if (_ch >= 42891 && _ch < 42895) {
            return true;
        }
        if (_ch >= 42896 && _ch < 42900) {
            return true;
        }
        if (_ch >= 42912 && _ch < 42923) {
            return true;
        }
        if (_ch >= 43000 && _ch < 43010) {
            return true;
        }
        if (_ch >= 43011 && _ch < 43014) {
            return true;
        }
        if (_ch >= 43015 && _ch < 43019) {
            return true;
        }
        if (_ch >= 43250 && _ch < 43256) {
            return true;
        }
        if (_ch >= 43584 && _ch < 43587) {
            return true;
        }
        if (_ch >= 43588 && _ch < 43596) {
            return true;
        }
        if (_ch >= 43701 && _ch < 43703) {
            return true;
        }
        if (_ch >= 43705 && _ch < 43710) {
            return true;
        }
        if (_ch >= 43739 && _ch < 43742) {
            return true;
        }
        if (_ch >= 43744 && _ch < 43755) {
            return true;
        }
        if (_ch >= 43762 && _ch < 43765) {
            return true;
        }
        if (_ch >= 43777 && _ch < 43783) {
            return true;
        }
        if (_ch >= 43785 && _ch < 43791) {
            return true;
        }
        if (_ch >= 43793 && _ch < 43799) {
            return true;
        }
        if (_ch >= 43808 && _ch < 43815) {
            return true;
        }
        if (_ch >= 43816 && _ch < 43823) {
            return true;
        }
        for (int v:NumberUtil.wrapIntArray(
                748,
                750,
                902,
                908,
                1369,
                1749,
                1791,
                1808,
                1969,
                2042,
                2074,
                2084,
                2088,
                2208,
                2365,
                2384,
                2482,
                2493,
                2510,
                2654,
                2749,
                2768,
                2877,
                2929,
                2947,
                2972,
                3024,
                3133,
                3261,
                3294,
                3389,
                3406,
                3517,
                3716,
                3722,
                3725,
                3749,
                3751,
                3773,
                3782,
                3840,
                4159,
                4193,
                4238,
                4295,
                4301,
                4696,
                4800,
                6103,
                6108,
                6314,
                6823,
                8025,
                8027,
                8029,
                8126,
                8305,
                8319,
                8450,
                8455,
                8469,
                8484,
                8486,
                8488,
                8526,
                11559,
                11565,
                11631,
                11823,
                43259,
                43471,
                43642,
                43697,
                43712,
                43714
        )) {
            if (v == _ch) {
                return true;
            }
        }
        return false;
    }

    private static boolean isGreatLetter(char _ch) {
        if (_ch < 64110) {
            return true;
        }
        if (_ch >= 64467 && _ch < 64830) {
            return true;
        }
        if (_ch >= 64112 && _ch < 64218) {
            return true;
        }
        if (_ch >= 64326 && _ch < 64434) {
            return true;
        }
        if (_ch >= 65142 && _ch < 65277) {
            return true;
        }
        if (_ch >= 65382 && _ch < 65471) {
            return true;
        }
        if (_ch >= 64848 && _ch < 64912) {
            return true;
        }
        if (_ch >= 64914 && _ch < 64968) {
            return true;
        }
        if (_ch >= 65313 && _ch < 65339) {
            return true;
        }
        if (_ch >= 65345 && _ch < 65371) {
            return true;
        }
        if (_ch >= 64287 && _ch < 64297) {
            return true;
        }
        if (_ch >= 64298 && _ch < 64311) {
            return true;
        }
        if (_ch >= 65008 && _ch < 65020) {
            return true;
        }
        if (_ch >= 64256 && _ch < 64263) {
            return true;
        }
        if (_ch >= 64275 && _ch < 64280) {
            return true;
        }
        if (_ch >= 64312 && _ch < 64317) {
            return true;
        }
        if (_ch >= 65136 && _ch < 65141) {
            return true;
        }
        if (_ch >= 65474 && _ch < 65480) {
            return true;
        }
        if (_ch >= 65482 && _ch < 65488) {
            return true;
        }
        if (_ch >= 65490 && _ch < 65496) {
            return true;
        }
        if (_ch >= 64320 && _ch < 64325) {
            return _ch != 64322;
        }
        if (_ch >= 65498 && _ch < 65501) {
            return true;
        }
        return _ch == 64285 || _ch == 64318;
    }

    private static boolean isSupplLetter(char _ch) {
        if (_ch == 215) {
            return false;
        }
        return _ch != 247;
    }

    public static boolean isLowerCase(char _string) {
        if (isUnassigned(_string)) {
            return false;
        }
        if (isOtherSpace(_string)) {
            return false;
        }
        if (isDigit(_string)) {
            return false;
        }
        if (isOtherDigit(_string)) {
            return false;
        }
        if (_string == 453 || _string == 456 || _string == 459 || _string == 498) {
            return false;
        }
        for (int i: NumberUtil.wrapIntArray(426,627,609,638,704,42800,8150,8166,
                8182,8462,8508,8134,8146,8118,7836)) {
            if (_string >= i && _string <= i + 1) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(620,8162,43000)) {
            if (_string >= i && _string <= i + 2) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(604,8518)) {
            if (_string >= i && _string <= i + 3) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(736,7830,64275)) {
            if (_string >= i && _string <= i + 4) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(42864)) {
            if (_string >= i && _string <= i + 8) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(564)) {
            if (_string >= i && _string <= i + 5) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(630,641,11383,64256)) {
            if (_string >= i && _string <= i + 6) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(8336)) {
            if (_string >= i && _string <= i + 12) {
                return true;
            }
        }
        if (_string == 42894 || _string == 11492) {
            return true;
        }
        if (_string == 11377 || _string == 11380) {
            return true;
        }
        if (_string == 170 || _string == 186 || _string == 8305) {
            return true;
        }
        if (_string == 8500 || _string == 8505 || _string == 8495) {
            return true;
        }
        if (_string == 8467 || _string == 8458 || _string == 8319) {
            return true;
        }
        if (_string == 8180 || _string == 8178 || _string == 8132) {
            return true;
        }
        if (_string == 8114 || _string == 8116 || _string == 8130) {
            return true;
        }
        if (_string == 8022 || _string == 8020 || _string == 8018) {
            return true;
        }
        if (_string == 8016 || _string == 7839) {
            return true;
        }
        if (_string == 223 || _string == 312) {
            return true;
        }
        if (_string == 329 || _string == 397) {
            return true;
        }
        if (_string >= 7424 && _string <= 7615) {
            return true;
        }
        if (_string == 411 || _string == 442) {
            return true;
        }
        if (_string == 446 || _string == 496) {
            return true;
        }
        if (_string == 545 || _string == 597) {
            return true;
        }
        if (_string == 600 || _string == 602) {
            return true;
        }
        if (_string >= 612 && _string <= 624) {
            return true;
        }
        if (_string == 660) {
            return false;
        }
        if (_string >= 653 && _string <= 696) {
            return true;
        }
        if (_string == 890 || _string == 912 || _string == 944) {
            return true;
        }
        if (_string == 1011 || _string == 1020 || _string == 1415) {
            return true;
        }
        if (toLowerCaseIntCheck(_string) == _string) {
            return toUpperCaseIntCheckLow(_string)!=_string;
        }
        return false;
    }

    public static boolean isUpperCase(char _string) {
        if (isUnassigned(_string)) {
            return false;
        }
        if (isOtherSpace(_string)) {
            return false;
        }
        if (isDigit(_string)) {
            return false;
        }
        if (isOtherDigit(_string)) {
            return false;
        }
        for (int i: NumberUtil.wrapIntArray(8072,8088,8104)) {
            if (_string >= i && _string <= i + 7) {
                return false;
            }
        }
        for (int i: NumberUtil.wrapIntArray(978,8459,8464)) {
            if (_string >= i && _string <= i + 2) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(8492,8496,8510)) {
            if (_string >= i && _string <= i + 1) {
                return true;
            }
        }
        if (_string == 8124) {
            return false;
        }
        if (_string == 8140) {
            return false;
        }
        if (_string == 8188) {
            return false;
        }
        if (_string == 8450 || _string == 8484) {
            return true;
        }
        if (_string == 8455 || _string == 8488) {
            return true;
        }
        if (_string == 8469 || _string == 8499 || _string == 8517) {
            return true;
        }
        if (_string >= 8473 && _string <= 8477) {
            return true;
        }
        return toUpperCaseIntCheck(_string)==_string&&toLowerCaseIntCheckUpp(_string)!=_string;
    }

    public static char toLowerCase(char _string) {
        return (char) toLowerCaseInt(_string);
    }

    public static char toUpperCase(char _string) {
        return (char) toUpperCaseInt(_string);
    }

    public static byte getDirectionality(char _ch) {
        int type_ = getCustomType(_ch);
        return (byte)generalDir(_ch, type_);
    }
    public static byte getType(char _ch) {
        int type_ = getCustomType(_ch);
        return (byte)procGene(_ch, type_);
    }

    public static int getCustomType(char _ch) {
        if (isLetter(_ch)) {
            if (isLowerCase(_ch)) {
                if (toUpperCase(_ch) != _ch) {
                    return 1;
                }
                return 3;
            }
            if (isUpperCase(_ch)) {
                if (toLowerCase(_ch) != _ch) {
                    return 2;
                }
                return 4;
            }
            if (_ch == 453 || _ch == 456 || _ch == 459 || _ch == 498) {
                return 5;
            }
            if (_ch == 6103) {
                return 6;
            }
            if (_ch == 43259) {
                return 6;
            }
            if (_ch >= 1488 && _ch<=2220) {
                return 8;
            }
            if (_ch >= 64285 && _ch<=65276) {
                return 8;
            }
            if (_ch >= 699 && _ch<=703) {
                return 6;
            }
            if (_ch >= 697 && _ch<=719) {
                return 7;
            }
            if (_ch == 11823 || _ch == 884|| _ch == 748) {
                return 7;
            }
            if (_ch >= 42656 && _ch<=42725) {
                return 6;
            }
            if (_ch >= 42623 && _ch<=42888) {
                return 7;
            }
            return 6;
        }
        if (isDigit(_ch)) {
            return 9;
        }
        if (isOtherDigit(_ch)) {
            return 10;
        }
        if (isUnderscoreOrCurrencyChar(_ch)) {
            if (_ch == '_') {
                return 11;
            }
            return 12;
        }
        if (isSensibleOtherLetter(_ch)) {
            return 13;
        }
        if (isRomanDigits(_ch)) {
            return 14;
        }
        if (_ch == '(' || _ch == '[' || _ch == '{') {
            return 15;
        }
        if (_ch == ')' || _ch == ']' || _ch == '}') {
            return 16;
        }
        if (_ch == '!' || _ch == '+' || _ch == '-' ||
                _ch == '*' || _ch == '%' || _ch == '/'||
                _ch == '#' || _ch == '&' || _ch == '|'||
                _ch == '^' || _ch == '?' || _ch == ':'||
                _ch == '<' || _ch == '=' || _ch == '>'||
                _ch == '~' || _ch == '@') {
            return 17;
        }
        if (_ch == ',' || _ch == '.' || _ch == ';') {
            return 18;
        }
        if (_ch == '\'' || _ch == '`' || _ch == '"') {
            return 19;
        }
        if (_ch == '\\') {
            return 20;
        }
        if (isOtherMathSymbol(_ch)) {
            return 21;
        }
        if (_ch == 166) {
            return 22;
        }
        for (int i: NumberUtil.wrapIntArray(168,184)) {
            if (_ch >= i && _ch <= i + 1) {
                return 22;
            }
        }
        for (int i: NumberUtil.wrapIntArray(173)) {
            if (_ch >= i && _ch <= i + 3) {
                return 22;
            }
        }
        for (int i: NumberUtil.wrapIntArray(178,188)) {
            if (_ch >= i && _ch <= i + 2) {
                return 22;
            }
        }
        if (isOtherSymbol(_ch)) {
            if (isModifierSymbol(_ch)) {
                return 23;
            }
            return 24;
        }
        if (isOtherPonctuation(_ch)) {
            if (isConnector(_ch)) {
                return 25;
            }
            if (isBoundPunct(_ch)) {
                return 26;
            }
            if (isQuotePunct(_ch)) {
                return 27;
            }
            return 28;
        }
        if (_ch <= ' ') {
            if (_ch == ' ') {
                return 29;
            }
            if (_ch == '\n') {
                return 29;
            }
            if (_ch == '\t') {
                return 29;
            }
            return 30;
        }
        if (isOtherSpace(_ch)) {
            return 30;
        }
        if (!isUnassigned(_ch)) {
            return 31;
        }
        return 32;
    }

    private static int generalDir(char _ch, int _type) {
        if (_type <= 6) {
            return 0;
        }
        if (_type == 7) {
            return 13;
        }
        if (_type == 8) {
            if (_ch <= 1514) {
                return 1;
            }
            if (_ch <= 1522) {
                return 1;
            }
            if (_ch >= 1994 && _ch <= 2136) {
                return 1;
            }
            if (_ch >= 64285 && _ch <= 64335) {
                return 1;
            }
            return 2;
        }
        if (_type == 9) {
            return 3;
        }
        if (_type == 10) {
            if (_ch <= 1641) {
                return 6;
            }
            if (_ch <= 1785) {
                return 3;
            }
            if (_ch <= 1993) {
                return 1;
            }
            if (_ch >= 65296) {
                return 3;
            }
            return 0;
        }
        if (_type == 11) {
            return 13;
        }
        if (_type == 12) {
            if (_ch == 1547) {
                return 2;
            }
            if (_ch == 65020) {
                return 2;
            }
            return 5;
        }
        if (_type == 13) {
            return 0;
        }
        if (_type == 14) {
            return 0;
        }
        if (_type == 15) {
            return 13;
        }
        return generalDirOther(_ch, _type);
    }

    private static int generalDirOther(char _ch, int _type) {
        if (_type == 16) {
            return 13;
        }
        if (_type == 17) {
            if (_ch == 33) {
                return 13;
            }
            if (_ch <= 37) {
                return 5;
            }
            if (_ch <= 42) {
                return 13;
            }
            if (_ch <= 45) {
                return 4;
            }
            if (_ch <= 58) {
                return 7;
            }
            return 13;
        }
        if (_type == 18) {
            if (_ch <= 46) {
                return 7;
            }
            return 13;
        }
        if (_type == 19) {
            return 13;
        }
        if (_type == 20) {
            return 13;
        }
        if (_type == 21) {
            return dirOtherPrintSix(_ch);
        }
        if (_type == 22) {
            return dirOtherPrintFive(_ch);
        }
        if (_type == 23) {
            return dirOtherPrintFour(_ch);
        }
        if (_type == 24) {
            return dirOtherPrintTwo(_ch);
        }
        if (_type == 25) {
            return dirOtherPrintThree(_ch);
        }
        if (_type == 26) {
            return 13;
        }
        if (_type == 27) {
            return 13;
        }
        if (_type == 28) {
            return dirOtherPrint(_ch);
        }
        if (_type == 29) {
            return dirSpace(_ch);
        }
        if (_type == 30) {
            return dirOtherSpace(_ch);
        }
        if (_type == 31) {
            return dirOther(_ch);
        }
        return -1;
    }

    private static int dirSpace(char _ch) {
        if (_ch == 32) {
            return 12;
        }
        if (_ch == 10) {
            return 10;
        }
        return 11;
    }

    private static int dirOtherSpace(char _ch) {
        if (_ch == 11) {
            return 11;
        }
        if (_ch == 12) {
            return 12;
        }
        if (_ch == 13) {
            return 10;
        }
        if (_ch == 31) {
            return 11;
        }
        if (_ch == 133) {
            return 10;
        }
        if (_ch == 160) {
            return 7;
        }
        if (_ch == 8239) {
            return 7;
        }
        if (_ch <= 27) {
            return 9;
        }
        if (_ch <= 30) {
            return 10;
        }
        if (_ch <= 159) {
            return 9;
        }
        return 12;
    }

    private static int dirOtherPrintSix(char _ch) {
        if (_ch == 172) {
            return 13;
        }
        if (_ch == 177) {
            return 5;
        }
        if (_ch == 1544) {
            return 2;
        }
        if (_ch == 8260) {
            return 7;
        }
        if (_ch == 8274) {
            return 13;
        }
        if (_ch == 8316) {
            return 13;
        }
        if (_ch == 8722) {
            return 4;
        }
        if (_ch == 8723) {
            return 5;
        }
        if (_ch == 65291) {
            return 4;
        }
        if (_ch <= 1543) {
            return 13;
        }
        if (_ch <= 8331) {
            return 4;
        }
        if (_ch <= 11084) {
            return 13;
        }
        if (_ch <= 65122) {
            return 4;
        }
        return 13;
    }

    private static int dirOtherPrintFive(char _ch) {
        if (_ch == 173) {
            return 9;
        }
        if (_ch == 176) {
            return 5;
        }
        if (_ch == 185) {
            return 3;
        }
        if (_ch <= 175) {
            return 13;
        }
        if (_ch <= 179) {
            return 3;
        }
        return 13;
    }

    private static int dirOtherPrintFour(char _ch) {
        if (_ch >= 42889 && _ch <= 42890) {
            return 0;
        }
        if (_ch >= 64434 && _ch <= 64449) {
            return 2;
        }
        return 13;
    }

    private static int dirOtherPrintThree(char _ch) {
        if (_ch == 1418) {
            return 13;
        }
        if (_ch == 1470) {
            return 1;
        }
        if (_ch <= 65112) {
            return 13;
        }
        if (_ch <= 65293) {
            return 4;
        }
        return 13;
    }

    private static int dirOtherPrintTwo(char _ch) {
        if (_ch == 1154) {
            return 0;
        }
        if (_ch == 8527) {
            return 0;
        }
        if (_ch == 9900) {
            return 0;
        }
        if (_ch == 9109) {
            return 0;
        }
        if (_ch == 12880) {
            return 13;
        }
        if (_ch == 8494) {
            return 5;
        }
        if (_ch == 43065) {
            return 5;
        }
        if (_ch <= 1769) {
            return 13;
        }
        if (_ch <= 1790) {
            return 2;
        }
        if (_ch >= 2554 && _ch <= 2928) {
            return 0;
        }
        if (_ch >= 3059 && _ch <= 3066) {
            return 13;
        }
        if (_ch >= 3199 && _ch <= 4255) {
            return 0;
        }
        if (_ch >= 5008 && _ch <= 6655) {
            return 13;
        }
        if (_ch >= 7009 && _ch <= 7036) {
            return 0;
        }
        if (_ch >= 8448 && _ch <= 9013) {
            return 13;
        }
        if (_ch >= 9014 && _ch <= 9082) {
            return 0;
        }
        if (_ch >= 9083 && _ch <= 9290) {
            return 13;
        }
        if (_ch >= 9372 && _ch <= 9397) {
            return 0;
        }
        if (_ch >= 9472 && _ch <= 10175) {
            return 13;
        }
        if (_ch >= 10240 && _ch <= 10495) {
            return 0;
        }
        if (_ch >= 11008 && _ch <= 12351) {
            return 13;
        }
        if (_ch >= 12688 && _ch <= 12703) {
            return 0;
        }
        if (_ch >= 12736 && _ch <= 12771) {
            return 13;
        }
        if (_ch >= 12800 && _ch <= 12828) {
            return 0;
        }
        if (_ch >= 12829 && _ch <= 12830) {
            return 13;
        }
        if (_ch >= 12842 && _ch <= 12923) {
            return 0;
        }
        if (_ch >= 12924 && _ch <= 12926) {
            return 13;
        }
        if (_ch >= 12927 && _ch <= 13003) {
            return 0;
        }
        if (_ch >= 13004 && _ch <= 13007) {
            return 13;
        }
        if (_ch >= 13008 && _ch <= 13174) {
            return 0;
        }
        if (_ch >= 13175 && _ch <= 13178) {
            return 13;
        }
        if (_ch >= 13179 && _ch <= 13277) {
            return 0;
        }
        if (_ch >= 13278 && _ch <= 13279) {
            return 13;
        }
        if (_ch >= 13280 && _ch <= 13310) {
            return 0;
        }
        if (_ch >= 13311 && _ch <= 43051) {
            return 13;
        }
        if (_ch >= 43062 && _ch <= 43641) {
            return 0;
        }
        return 13;
    }

    private static int dirOtherPrint(char _ch) {
        if (_ch == 1548) {
            return 7;
        }
        if (_ch == 1642) {
            return 5;
        }
        if (_ch == 1645) {
            return 2;
        }
        if (_ch == 1748) {
            return 2;
        }
        if (_ch == 11632) {
            return 0;
        }
        if (_ch == 65104) {
            return 7;
        }
        if (_ch == 65105) {
            return 13;
        }
        if (_ch == 65106) {
            return 7;
        }
        if (_ch == 65108) {
            return 13;
        }
        if (_ch == 65109) {
            return 7;
        }
        if (_ch == 65119) {
            return 5;
        }
        if (_ch == 65130) {
            return 5;
        }
        if (_ch <= 903) {
            return 13;
        }
        if (_ch <= 1417) {
            return 0;
        }
        if (_ch <= 1524) {
            return 1;
        }
        if (_ch <= 1546) {
            return 5;
        }
        if (_ch <= 1567) {
            return 2;
        }
        if (_ch <= 1644) {
            return 6;
        }
        if (_ch <= 1805) {
            return 2;
        }
        if (_ch <= 2041) {
            return 13;
        }
        if (_ch <= 2142) {
            return 1;
        }
        if (_ch <= 6106) {
            return 0;
        }
        if (_ch <= 6469) {
            return 13;
        }
        if (_ch <= 7379) {
            return 0;
        }
        if (_ch <= 8231) {
            return 13;
        }
        if (_ch <= 8244) {
            return 5;
        }
        if (_ch <= 12539) {
            return 13;
        }
        if (_ch <= 42239) {
            return 0;
        }
        if (_ch <= 42622) {
            return 13;
        }
        if (_ch <= 42743) {
            return 0;
        }
        if (_ch <= 43127) {
            return 13;
        }
        if (_ch <= 44011) {
            return 0;
        }
        if (_ch <= 65282) {
            return 13;
        }
        if (_ch <= 65285) {
            return 5;
        }
        if (_ch <= 65290) {
            return 13;
        }
        if (_ch <= 65306) {
            return 7;
        }
        return 13;
    }

    private static int dirOther(char _ch) {
        if (_ch == 11517) {
            return 13;
        }
        if (_ch == 2307){
            return 0;
        }
        if (_ch == 2363){
            return 0;
        }
        if (_ch == 2519){
            return 0;
        }
        if (_ch == 2563){
            return 0;
        }
        if (_ch == 2691){
            return 0;
        }
        if (_ch == 2878){
            return 0;
        }
        if (_ch == 2880){
            return 0;
        }
        if (_ch == 2903){
            return 0;
        }
        if (_ch == 3415){
            return 0;
        }
        if (_ch == 3967){
            return 0;
        }
        if (_ch == 4145){
            return 0;
        }
        if (_ch == 4152){
            return 0;
        }
        if (_ch == 6070){
            return 0;
        }
        if (_ch == 6743){
            return 0;
        }
        if (_ch == 6753){
            return 0;
        }
        if (_ch == 6916){
            return 0;
        }
        if (_ch == 6965){
            return 0;
        }
        if (_ch == 6971){
            return 0;
        }
        if (_ch == 7082){
            return 0;
        }
        if (_ch == 7143){
            return 0;
        }
        if (_ch == 7150){
            return 0;
        }
        if (_ch == 7393){
            return 0;
        }
        if (_ch == 8206){
            return 0;
        }
        if (_ch == 43395){
            return 0;
        }
        if (_ch == 43755){
            return 0;
        }
        if (_ch == 2362){
            return 8;
        }
        if (_ch == 2364){
            return 8;
        }
        if (_ch == 2381){
            return 8;
        }
        if (_ch == 2492){
            return 8;
        }
        if (_ch == 2509){
            return 8;
        }
        if (_ch == 2620){
            return 8;
        }
        if (_ch == 2748){
            return 8;
        }
        if (_ch == 2876){
            return 8;
        }
        if (_ch == 2879){
            return 8;
        }
        if (_ch == 2946){
            return 8;
        }
        if (_ch == 3008){
            return 8;
        }
        if (_ch == 3021){
            return 8;
        }
        if (_ch == 3260){
            return 8;
        }
        if (_ch == 3405){
            return 8;
        }
        if (_ch == 3530){
            return 8;
        }
        if (_ch == 4237){
            return 8;
        }
        if (_ch == 6086){
            return 8;
        }
        if (_ch == 6450){
            return 8;
        }
        if (_ch == 6742){
            return 8;
        }
        if (_ch == 6754){
            return 8;
        }
        if (_ch == 6964){
            return 8;
        }
        if (_ch == 6972){
            return 8;
        }
        if (_ch == 6978){
            return 8;
        }
        if (_ch == 7083){
            return 8;
        }
        if (_ch == 7142){
            return 8;
        }
        if (_ch == 7149){
            return 8;
        }
        if (_ch == 43443){
            return 8;
        }
        if (_ch == 43452){
            return 8;
        }
        if (_ch == 43766){
            return 8;
        }
        if (_ch == 44005){
            return 8;
        }
        if (_ch == 44008){
            return 8;
        }
        if (_ch == 44013){
            return 8;
        }
        if (_ch == 1757){
            return 6;
        }
        if (_ch == 1807){
            return 2;
        }
        if (_ch == 8207){
            return 1;
        }
        if (_ch == 8232){
            return 12;
        }
        if (_ch == 8233){
            return 10;
        }
        if (_ch == 8234){
            return 14;
        }
        if (_ch == 8235){
            return 16;
        }
        if (_ch == 8236){
            return 18;
        }
        if (_ch == 8237){
            return 15;
        }
        if (_ch == 8238){
            return 17;
        }
        if (_ch == 65279){
            return 9;
        }


        if (_ch <= 1479) {
            return 8;
        }
        if (_ch <= 1540) {
            return 6;
        }
        if (_ch <= 1756) {
            return 8;
        }
        if (_ch <= 1773) {
            return 8;
        }
        if (_ch <= 2306) {
            return 8;
        }
        if (_ch <= 2368) {
            return 0;
        }
        if (_ch <= 2376) {
            return 8;
        }
        if (_ch <= 2380) {
            return 0;
        }
        if (_ch <= 2383) {
            return 0;
        }
        if (_ch <= 2433) {
            return 8;
        }
        if (_ch <= 2435) {
            return 0;
        }
        if (_ch <= 2496) {
            return 0;
        }
        if (_ch <= 2500) {
            return 8;
        }
        if (_ch <= 2508) {
            return 0;
        }
        if (_ch <= 2531) {
            return 8;
        }
        if (_ch <= 2553) {
            return 0;
        }
        if (_ch <= 2562) {
            return 8;
        }
        if (_ch <= 2624) {
            return 0;
        }
        if (_ch <= 2690) {
            return 8;
        }
        if (_ch <= 2752) {
            return 0;
        }
        if (_ch <= 2760) {
            return 8;
        }
        if (_ch <= 2764) {
            return 0;
        }
        if (_ch <= 2817) {
            return 8;
        }
        if (_ch <= 2819) {
            return 0;
        }
        if (_ch <= 2884) {
            return 8;
        }
        if (_ch <= 2892) {
            return 0;
        }
        if (_ch <= 2902) {
            return 8;
        }
        if (_ch <= 2915) {
            return 8;
        }
        if (_ch <= 2935) {
            return 0;
        }
        if (_ch <= 3007) {
            return 0;
        }
        if (_ch <= 3020) {
            return 0;
        }
        if (_ch <= 3075) {
            return 0;
        }
        if (_ch <= 3136) {
            return 8;
        }
        if (_ch <= 3140) {
            return 0;
        }
        if (_ch <= 3171) {
            return 8;
        }
        if (_ch <= 3198) {
            return 13;
        }
        if (_ch <= 3203) {
            return 0;
        }
        if (_ch <= 3275) {
            return 0;
        }
        if (_ch <= 3277) {
            return 8;
        }
        if (_ch <= 3286) {
            return 0;
        }
        if (_ch <= 3299) {
            return 8;
        }
        if (_ch <= 3392) {
            return 0;
        }
        if (_ch <= 3396) {
            return 8;
        }
        if (_ch <= 3404) {
            return 0;
        }
        if (_ch <= 3427) {
            return 8;
        }
        if (_ch <= 3459) {
            return 0;
        }
        if (_ch <= 3537) {
            return 0;
        }
        if (_ch <= 3542) {
            return 8;
        }
        if (_ch <= 3571) {
            return 0;
        }
        if (_ch <= 3865) {
            return 8;
        }
        if (_ch <= 3891) {
            return 0;
        }
        if (_ch <= 3897) {
            return 8;
        }
        if (_ch <= 3903) {
            return 0;
        }
        if (_ch <= 3966) {
            return 8;
        }
        if (_ch <= 4038) {
            return 8;
        }
        if (_ch <= 4140) {
            return 0;
        }
        if (_ch <= 4144) {
            return 8;
        }
        if (_ch <= 4151) {
            return 8;
        }
        if (_ch <= 4154) {
            return 8;
        }
        if (_ch <= 4156) {
            return 0;
        }
        if (_ch <= 4158) {
            return 8;
        }
        if (_ch <= 4183) {
            return 0;
        }
        if (_ch <= 4192) {
            return 8;
        }
        if (_ch <= 4205) {
            return 0;
        }
        if (_ch <= 4226) {
            return 8;
        }
        if (_ch <= 4228) {
            return 0;
        }
        if (_ch <= 4230) {
            return 8;
        }
        if (_ch <= 4236) {
            return 0;
        }
        if (_ch <= 4252) {
            return 0;
        }
        if (_ch <= 4959) {
            return 8;
        }
        if (_ch <= 5872) {
            return 0;
        }
        if (_ch <= 6069) {
            return 8;
        }
        if (_ch <= 6077) {
            return 8;
        }
        if (_ch <= 6085) {
            return 0;
        }
        if (_ch <= 6088) {
            return 0;
        }
        if (_ch <= 6109) {
            return 8;
        }
        if (_ch <= 6137) {
            return 13;
        }
        if (_ch <= 6434) {
            return 8;
        }
        if (_ch <= 6438) {
            return 0;
        }
        if (_ch <= 6440) {
            return 8;
        }
        if (_ch <= 6449) {
            return 0;
        }
        if (_ch <= 6456) {
            return 0;
        }
        if (_ch <= 6459) {
            return 8;
        }
        if (_ch <= 6618) {
            return 0;
        }
        if (_ch <= 6680) {
            return 8;
        }
        if (_ch <= 6741) {
            return 0;
        }
        if (_ch <= 6752) {
            return 8;
        }
        if (_ch <= 6756) {
            return 0;
        }
        if (_ch <= 6764) {
            return 8;
        }
        if (_ch <= 6770) {
            return 0;
        }
        if (_ch <= 6915) {
            return 8;
        }
        if (_ch <= 6970) {
            return 8;
        }
        if (_ch <= 6977) {
            return 0;
        }
        if (_ch <= 6980) {
            return 0;
        }
        if (_ch <= 7041) {
            return 8;
        }
        if (_ch <= 7073) {
            return 0;
        }
        if (_ch <= 7077) {
            return 8;
        }
        if (_ch <= 7079) {
            return 0;
        }
        if (_ch <= 7081) {
            return 8;
        }
        if (_ch <= 7085) {
            return 0;
        }
        if (_ch <= 7145) {
            return 8;
        }
        if (_ch <= 7148) {
            return 0;
        }
        if (_ch <= 7153) {
            return 8;
        }
        if (_ch <= 7211) {
            return 0;
        }
        if (_ch <= 7219) {
            return 8;
        }
        if (_ch <= 7221) {
            return 0;
        }
        if (_ch <= 7392) {
            return 8;
        }
        if (_ch <= 7405) {
            return 8;
        }
        if (_ch <= 7411) {
            return 0;
        }
        if (_ch <= 7679) {
            return 8;
        }
        if (_ch <= 8205) {
            return 9;
        }
        if (_ch <= 8303) {
            return 9;
        }
        if (_ch <= 8329) {
            return 3;
        }
        if (_ch <= 8432) {
            return 8;
        }
        if (_ch <= 8543) {
            return 13;
        }
        if (_ch <= 8584) {
            return 0;
        }
        if (_ch <= 9351) {
            return 13;
        }
        if (_ch <= 9371) {
            return 3;
        }
        if (_ch <= 10131) {
            return 13;
        }
        if (_ch <= 11505) {
            return 8;
        }
        if (_ch <= 11775) {
            return 8;
        }
        if (_ch <= 12329) {
            return 0;
        }
        if (_ch <= 12333) {
            return 8;
        }
        if (_ch <= 12346) {
            return 0;
        }
        if (_ch <= 12442) {
            return 8;
        }
        if (_ch <= 12879) {
            return 0;
        }
        if (_ch <= 12895) {
            return 13;
        }
        if (_ch <= 12937) {
            return 0;
        }
        if (_ch <= 12991) {
            return 13;
        }
        if (_ch <= 42655) {
            return 8;
        }
        if (_ch <= 42735) {
            return 0;
        }
        if (_ch <= 43019) {
            return 8;
        }
        if (_ch <= 43044) {
            return 0;
        }
        if (_ch <= 43046) {
            return 8;
        }
        if (_ch <= 43203) {
            return 0;
        }
        if (_ch <= 43345) {
            return 8;
        }
        if (_ch <= 43347) {
            return 0;
        }
        if (_ch <= 43394) {
            return 8;
        }
        if (_ch <= 43445) {
            return 0;
        }
        if (_ch <= 43449) {
            return 8;
        }
        if (_ch <= 43451) {
            return 0;
        }
        if (_ch <= 43456) {
            return 0;
        }
        if (_ch <= 43566) {
            return 8;
        }
        if (_ch <= 43568) {
            return 0;
        }
        if (_ch <= 43570) {
            return 8;
        }
        if (_ch <= 43572) {
            return 0;
        }
        if (_ch <= 43596) {
            return 8;
        }
        if (_ch <= 43643) {
            return 0;
        }
        if (_ch <= 43713) {
            return 8;
        }
        if (_ch <= 43757) {
            return 8;
        }
        if (_ch <= 43765) {
            return 0;
        }
        if (_ch <= 44004) {
            return 0;
        }
        if (_ch <= 44007) {
            return 0;
        }
        if (_ch <= 44012) {
            return 0;
        }
        if (_ch <= 63743) {
            return 0;
        }
        if (_ch <= 65062) {
            return 8;
        }
        return 13;
    }

    private static boolean isOtherDigit(char _ch) {
        for (int i: NumberUtil.wrapIntArray(1632,1776,1984,2406,2534,2662,
                2790,2918,3046,3174,3302,3430,3664,3792,3872,4160,4240,6112,6160,
                6470,6608,6784,6800,6992,7088,7232,7248,42528,43216,43264,43472,
                43600,44016,65296)) {
            if (_ch >= i && _ch <= i + 9) {
                return true;
            }
        }
        return false;
    }
    private static boolean isOtherPonctuation(char _ch) {
        if (_ch == 161) {
            return true;
        }
        if (_ch == 167) {
            return true;
        }
        if (_ch == 171) {
            return true;
        }
        if (_ch == 187) {
            return true;
        }
        if (_ch == 191) {
            return true;
        }
        if (_ch == 894) {
            return true;
        }
        if (_ch == 903) {
            return true;
        }
        if (_ch >= 1370&& _ch <= 1375) {
            return true;
        }
        if (_ch >= 1642&& _ch <= 1645) {
            return true;
        }
        if (_ch >= 1792&& _ch <= 1805) {
            return true;
        }
        if (_ch >= 2039&& _ch <= 2041) {
            return true;
        }
        if (_ch >= 2096&& _ch <= 2110) {
            return true;
        }
        if (_ch >= 3844&& _ch <= 3858) {
            return true;
        }
        if (_ch >= 3898&& _ch <= 3901) {
            return true;
        }
        if (_ch >= 4048&& _ch <= 4052) {
            return true;
        }
        if (_ch >= 4170&& _ch <= 4175) {
            return true;
        }
        if (_ch >= 4960&& _ch <= 4968) {
            return true;
        }
        if (_ch >= 5867&& _ch <= 5869) {
            return true;
        }
        if (_ch >= 6100&& _ch <= 6106) {
            return true;
        }
        if (_ch >= 6144&& _ch <= 6154) {
            return true;
        }
        if (_ch >= 6818&& _ch <= 6822) {
            return true;
        }
        if (_ch >= 6824&& _ch <= 6829) {
            return true;
        }
        if (_ch >= 7002&& _ch <= 7008) {
            return true;
        }
        if (_ch >= 7164&& _ch <= 7167) {
            return true;
        }
        if (_ch >= 7227&& _ch <= 7231) {
            return true;
        }
        if (_ch >= 7360&& _ch <= 7367) {
            return true;
        }
        if (_ch >= 8208&& _ch <= 8231) {
            return true;
        }
        if (_ch >= 8240&& _ch <= 8259) {
            return true;
        }
        if (_ch >= 8261&& _ch <= 8273) {
            return true;
        }
        if (_ch >= 8275&& _ch <= 8286) {
            return true;
        }
        if (_ch >= 10088&& _ch <= 10101) {
            return true;
        }
        if (_ch >= 10214&& _ch <= 10223) {
            return true;
        }
        if (_ch >= 10627&& _ch <= 10648) {
            return true;
        }
        if (_ch >= 10712&& _ch <= 10715) {
            return true;
        }
        if (_ch >= 11513&& _ch <= 11516) {
            return true;
        }
        if (_ch >= 11776&& _ch <= 11822) {
            return true;
        }
        if (_ch >= 11824&& _ch <= 11835) {
            return true;
        }
        if (_ch >= 12289&& _ch <= 12291) {
            return true;
        }
        if (_ch >= 12296&& _ch <= 12305) {
            return true;
        }
        if (_ch >= 12308&& _ch <= 12319) {
            return true;
        }
        if (_ch >= 42738&& _ch <= 42743) {
            return true;
        }
        if (_ch >= 43124&& _ch <= 43127) {
            return true;
        }
        if (_ch >= 43457&& _ch <= 43469) {
            return true;
        }
        if (_ch >= 43612&& _ch <= 43615) {
            return true;
        }
        if (_ch >= 65040&& _ch <= 65049) {
            return true;
        }
        if (_ch >= 65072&& _ch <= 65106) {
            return true;
        }
        if (_ch >= 65108&& _ch <= 65123) {
            return true;
        }
        if (_ch >= 65281&& _ch <= 65283) {
            return true;
        }
        if (_ch >= 65285&& _ch <= 65290) {
            return true;
        }
        if (_ch >= 65292&& _ch <= 65295) {
            return true;
        }
        if (_ch >= 65339&& _ch <= 65341) {
            return true;
        }
        if (_ch >= 65375&& _ch <= 65381) {
            return true;
        }
        for (int i: NumberUtil.wrapIntArray(182,1417,1523,1545,
                1548,1566,2404,3674,4057,5741,5787,5941,6468,6686,6816,7294,8317,8333,9001,10181,
                10748,11518,42238,42510,43214,43256,43310,43486,43258,43742,43760,64830,65130,65306,65311)) {
            if (_ch >= i && _ch <= i + 1) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(1470,1472,1475,1478,1563,1748,
                2142,2416,2800,3572,3663,3860,3973,4347,
                5120,7379,11632,12336,12349,12448,12539,
                42509,42611,42622,43359,44011,65128,65343,65371,65373)) {
            if (_ch == i) {
                return true;
            }
        }
        return false;
    }
    private static boolean isOtherSymbol(char _ch) {
        if (_ch >= 706&& _ch <= 709) {
            return true;
        }
        if (_ch >= 722&& _ch <= 735) {
            return true;
        }
        if (_ch >= 741&& _ch <= 747) {
            return true;
        }
        if (_ch >= 751&& _ch <= 767) {
            return true;
        }
        if (_ch >= 3059&& _ch <= 3064) {
            return true;
        }
        if (_ch >= 3841&& _ch <= 3843) {
            return true;
        }
        if (_ch >= 3861&& _ch <= 3863) {
            return true;
        }
        if (_ch >= 3866&& _ch <= 3871) {
            return true;
        }
        if (_ch >= 4030&& _ch <= 4037) {
            return true;
        }
        if (_ch >= 4039&& _ch <= 4044) {
            return true;
        }
        if (_ch >= 4053&& _ch <= 4056) {
            return true;
        }
        if (_ch >= 5008&& _ch <= 5017) {
            return true;
        }
        if (_ch >= 6622&& _ch <= 6655) {
            return true;
        }
        if (_ch >= 7009&& _ch <= 7018) {
            return true;
        }
        if (_ch >= 7028&& _ch <= 7036) {
            return true;
        }
        if (_ch >= 8127&& _ch <= 8129) {
            return true;
        }
        if (_ch >= 8141&& _ch <= 8143) {
            return true;
        }
        if (_ch >= 8157&& _ch <= 8159) {
            return true;
        }
        if (_ch >= 8173&& _ch <= 8175) {
            return true;
        }
        if (_ch >= 8451&& _ch <= 8454) {
            return true;
        }
        if (_ch >= 8478&& _ch <= 8483) {
            return true;
        }
        if (_ch >= 8598&& _ch <= 8601) {
            return true;
        }
        if (_ch >= 9140&& _ch <= 9179) {
            return true;
        }
        if (_ch >= 9186&& _ch <= 9203) {
            return true;
        }
        if (_ch >= 9216&& _ch <= 9254) {
            return true;
        }
        if (_ch >= 9280&& _ch <= 9290) {
            return true;
        }
        if (_ch >= 8604&& _ch <= 8607) {
            return true;
        }
        if (_ch >= 8615&& _ch <= 8621) {
            return true;
        }
        if (_ch >= 8623&& _ch <= 8653) {
            return true;
        }
        if (_ch >= 8661&& _ch <= 8691) {
            return true;
        }
        if (_ch >= 8960&& _ch <= 8967) {
            return true;
        }
        if (_ch >= 8972&& _ch <= 8991) {
            return true;
        }
        if (_ch >= 8994&& _ch <= 9000) {
            return true;
        }
        if (_ch >= 9003&& _ch <= 9083) {
            return true;
        }
        if (_ch >= 9085&& _ch <= 9114) {
            return true;
        }
        if (_ch >= 9372&& _ch <= 9449) {
            return true;
        }
        for (int i: NumberUtil.wrapIntArray(9655,9665,9839,9984)) {
            if (_ch == i) {
                return false;
            }
        }
        if (_ch >= 12246&& _ch <= 12271) {
            return false;
        }
        if (_ch >= 9472&& _ch <= 10087) {
            return true;
        }
        if (_ch >= 10132&& _ch <= 10175) {
            return true;
        }
        if (_ch >= 10240&& _ch <= 10495) {
            return true;
        }
        if (_ch >= 11008&& _ch <= 11055) {
            return true;
        }
        if (_ch >= 11088&& _ch <= 11097) {
            return true;
        }
        if (_ch >= 11493&& _ch <= 11498) {
            return true;
        }
        if (_ch >= 11904&& _ch <= 11929) {
            return true;
        }
        if (_ch >= 11931&& _ch <= 12019) {
            return true;
        }
        if (_ch >= 12032&& _ch <= 12283) {
            return true;
        }
        if (_ch >= 12694&& _ch <= 12703) {
            return true;
        }
        if (_ch >= 12736&& _ch <= 12771) {
            return true;
        }
        if (_ch >= 12800&& _ch <= 12830) {
            return true;
        }
        if (_ch >= 12842&& _ch <= 12871) {
            return true;
        }
        if (_ch >= 12896&& _ch <= 12927) {
            return true;
        }
        if (_ch >= 12938&& _ch <= 12976) {
            return true;
        }
        if (_ch >= 12992&& _ch <= 13054) {
            return true;
        }
        if (_ch >= 13056&& _ch <= 13311) {
            return true;
        }
        if (_ch >= 19904&& _ch <= 19967) {
            return true;
        }
        if (_ch >= 42128&& _ch <= 42182) {
            return true;
        }
        if (_ch >= 42752&& _ch <= 42774) {
            return true;
        }
        if (_ch >= 43048&& _ch <= 43051) {
            return true;
        }
        if (_ch >= 43639&& _ch <= 43641) {
            return true;
        }
        if (_ch >= 64434&& _ch <= 64449) {
            return true;
        }
        for (int i: NumberUtil.wrapIntArray(900,1550,1789,
                4046,4254,8189,8448,8470,8456,8506,8597,8524,8609,8612,8656,
                11077,12306,12342,12350,12443,12688,42784,42889,43062,
                65507,65517,65532)) {
            if (_ch >= i && _ch <= i + 1) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(749,885,1154,1758,1769,
                2038,2554,2928,3066,3199,3449,3859,3892,
                3894,3896,6464,8125,8468,8485,8487,8489,
                8494,8522,8527,8659,12292,12320,12880,43065,65021,65342,
                65344,65512)) {
            if (_ch == i) {
                return true;
            }
        }
        return false;
    }
    private static boolean isUnderscoreOrCurrencyChar(char _ch) {
        if (_ch == '_') {
            return true;
        }
        return isCurrencyChar(_ch);
    }
    private static boolean isCurrencyChar(char _ch) {
        if (_ch >= 162&& _ch <= 165) {
            return true;
        }
        if (_ch >= 8352&& _ch <= 8378) {
            return true;
        }
        for (int i: NumberUtil.wrapIntArray(2546,65504,65509)) {
            if (_ch >= i && _ch <= i + 1) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(36,1423,1547,2555,2801,
                3065,3647,6107,
                43064,65020,65129,65284)) {
            if (_ch == i) {
                return true;
            }
        }
        return false;
    }
    private static boolean isOtherSpace(char _ch) {
        if (_ch == 160) {
            return true;
        }
        if (_ch == 5760) {
            return true;
        }
        if (_ch == 6158) {
            return true;
        }
        if (_ch == 12288) {
            return true;
        }
        if (_ch == 8239) {
            return true;
        }
        if (_ch == 8287) {
            return true;
        }
        if (_ch >= 127 && _ch <= 159) {
            return true;
        }
        return _ch >= 8192 && _ch <= 8202;
    }
    private static boolean isUnassigned(char _ch) {
        if (_ch >= 888 && _ch <= 889) {
            return true;
        }
        if (_ch >= 895 && _ch <= 899) {
            return true;
        }
        if (_ch == 907) {
            return true;
        }
        if (_ch == 909) {
            return true;
        }
        if (_ch == 930) {
            return true;
        }
        if (_ch >= 1320 && _ch <= 1328) {
            return true;
        }
        if (_ch >= 1367 && _ch <= 1368) {
            return true;
        }
        if (_ch == 1376) {
            return true;
        }
        if (_ch == 1416) {
            return true;
        }
        if (_ch >= 1419 && _ch <= 1422) {
            return true;
        }
        if (_ch == 1424) {
            return true;
        }
        if (_ch >= 1480 && _ch <= 1487) {
            return true;
        }
        if (_ch >= 1515 && _ch <= 1519) {
            return true;
        }
        if (_ch >= 1525 && _ch <= 1535) {
            return true;
        }
        if (_ch == 1541) {
            return true;
        }
        if (_ch >= 1564 && _ch <= 1565) {
            return true;
        }
        if (_ch == 1806) {
            return true;
        }
        if (_ch >= 1867 && _ch <= 1868) {
            return true;
        }
        if (_ch >= 1970 && _ch <= 1983) {
            return true;
        }
        if (_ch >= 2043 && _ch <= 2047) {
            return true;
        }
        if (_ch >= 2094 && _ch <= 2095) {
            return true;
        }
        if (_ch == 2111) {
            return true;
        }
        if (_ch >= 2140 && _ch <= 2141) {
            return true;
        }
        if (_ch >= 2143 && _ch <= 2207) {
            return true;
        }
        if (_ch == 2209) {
            return true;
        }
        if (_ch >= 2221 && _ch <= 2275) {
            return true;
        }
        if (_ch == 2303) {
            return true;
        }
        if (_ch == 2424) {
            return true;
        }
        if (_ch == 2432) {
            return true;
        }
        if (_ch == 2436) {
            return true;
        }
        if (_ch >= 2445 && _ch <= 2446) {
            return true;
        }
        if (_ch >= 2449 && _ch <= 2450) {
            return true;
        }
        if (_ch == 2473) {
            return true;
        }
        if (_ch == 2481) {
            return true;
        }
        if (_ch >= 2483 && _ch <= 2485) {
            return true;
        }
        if (_ch >= 2490 && _ch <= 2491) {
            return true;
        }
        if (_ch >= 2501 && _ch <= 2502) {
            return true;
        }
        if (_ch >= 2505 && _ch <= 2506) {
            return true;
        }
        if (_ch >= 2511 && _ch <= 2518) {
            return true;
        }
        if (_ch >= 2520 && _ch <= 2523) {
            return true;
        }
        if (_ch == 2526) {
            return true;
        }
        if (_ch >= 2532 && _ch <= 2533) {
            return true;
        }
        if (_ch >= 2556 && _ch <= 2560) {
            return true;
        }
        if (_ch == 2564) {
            return true;
        }
        if (_ch >= 2571 && _ch <= 2574) {
            return true;
        }
        if (_ch >= 2577 && _ch <= 2578) {
            return true;
        }
        if (_ch == 2601) {
            return true;
        }
        if (_ch == 2609) {
            return true;
        }
        if (_ch == 2612) {
            return true;
        }
        if (_ch == 2615) {
            return true;
        }
        if (_ch >= 2618 && _ch <= 2619) {
            return true;
        }
        if (_ch == 2621) {
            return true;
        }
        if (_ch >= 2627 && _ch <= 2630) {
            return true;
        }
        if (_ch >= 2633 && _ch <= 2634) {
            return true;
        }
        if (_ch >= 2638 && _ch <= 2640) {
            return true;
        }
        if (_ch >= 2642 && _ch <= 2648) {
            return true;
        }
        if (_ch == 2653) {
            return true;
        }
        if (_ch >= 2655 && _ch <= 2661) {
            return true;
        }
        if (_ch >= 2678 && _ch <= 2688) {
            return true;
        }
        if (_ch == 2692) {
            return true;
        }
        if (_ch == 2702) {
            return true;
        }
        if (_ch == 2706) {
            return true;
        }
        if (_ch == 2729) {
            return true;
        }
        if (_ch == 2737) {
            return true;
        }
        if (_ch == 2740) {
            return true;
        }
        if (_ch >= 2746 && _ch <= 2747) {
            return true;
        }
        if (_ch == 2758) {
            return true;
        }
        if (_ch == 2762) {
            return true;
        }
        if (_ch >= 2766 && _ch <= 2767) {
            return true;
        }
        if (_ch >= 2769 && _ch <= 2783) {
            return true;
        }
        if (_ch >= 2788 && _ch <= 2789) {
            return true;
        }
        if (_ch >= 2802 && _ch <= 2816) {
            return true;
        }
        if (_ch == 2820) {
            return true;
        }
        if (_ch >= 2829 && _ch <= 2830) {
            return true;
        }
        if (_ch >= 2833 && _ch <= 2834) {
            return true;
        }
        if (_ch == 2857) {
            return true;
        }
        if (_ch == 2865) {
            return true;
        }
        if (_ch == 2868) {
            return true;
        }
        if (_ch >= 2874 && _ch <= 2875) {
            return true;
        }
        if (_ch >= 2885 && _ch <= 2886) {
            return true;
        }
        if (_ch >= 2889 && _ch <= 2890) {
            return true;
        }
        if (_ch >= 2894 && _ch <= 2901) {
            return true;
        }
        if (_ch >= 2904 && _ch <= 2907) {
            return true;
        }
        if (_ch == 2910) {
            return true;
        }
        if (_ch >= 2916 && _ch <= 2917) {
            return true;
        }
        if (_ch >= 2936 && _ch <= 2945) {
            return true;
        }
        if (_ch == 2948) {
            return true;
        }
        if (_ch >= 2955 && _ch <= 2957) {
            return true;
        }
        if (_ch == 2961) {
            return true;
        }
        if (_ch >= 2966 && _ch <= 2968) {
            return true;
        }
        if (_ch == 2971) {
            return true;
        }
        if (_ch == 2973) {
            return true;
        }
        if (_ch >= 2976 && _ch <= 2978) {
            return true;
        }
        if (_ch >= 2981 && _ch <= 2983) {
            return true;
        }
        if (_ch >= 2987 && _ch <= 2989) {
            return true;
        }
        if (_ch >= 3002 && _ch <= 3005) {
            return true;
        }
        if (_ch >= 3011 && _ch <= 3013) {
            return true;
        }
        if (_ch == 3017) {
            return true;
        }
        if (_ch >= 3022 && _ch <= 3023) {
            return true;
        }
        if (_ch >= 3025 && _ch <= 3030) {
            return true;
        }
        if (_ch >= 3032 && _ch <= 3045) {
            return true;
        }
        if (_ch >= 3067 && _ch <= 3072) {
            return true;
        }
        if (_ch == 3076) {
            return true;
        }
        if (_ch == 3085) {
            return true;
        }
        if (_ch == 3089) {
            return true;
        }
        if (_ch == 3113) {
            return true;
        }
        if (_ch == 3124) {
            return true;
        }
        if (_ch >= 3130 && _ch <= 3132) {
            return true;
        }
        if (_ch == 3141) {
            return true;
        }
        if (_ch == 3145) {
            return true;
        }
        if (_ch >= 3150 && _ch <= 3156) {
            return true;
        }
        if (_ch == 3159) {
            return true;
        }
        if (_ch >= 3162 && _ch <= 3167) {
            return true;
        }
        if (_ch >= 3172 && _ch <= 3173) {
            return true;
        }
        if (_ch >= 3184 && _ch <= 3191) {
            return true;
        }
        if (_ch >= 3200 && _ch <= 3201) {
            return true;
        }
        if (_ch == 3204) {
            return true;
        }
        if (_ch == 3213) {
            return true;
        }
        if (_ch == 3217) {
            return true;
        }
        if (_ch == 3241) {
            return true;
        }
        if (_ch == 3252) {
            return true;
        }
        if (_ch >= 3258 && _ch <= 3259) {
            return true;
        }
        if (_ch == 3269) {
            return true;
        }
        if (_ch == 3273) {
            return true;
        }
        if (_ch >= 3278 && _ch <= 3284) {
            return true;
        }
        if (_ch >= 3287 && _ch <= 3293) {
            return true;
        }
        if (_ch == 3295) {
            return true;
        }
        if (_ch >= 3300 && _ch <= 3301) {
            return true;
        }
        if (_ch == 3312) {
            return true;
        }
        if (_ch >= 3315 && _ch <= 3329) {
            return true;
        }
        if (_ch == 3332) {
            return true;
        }
        if (_ch == 3341) {
            return true;
        }
        if (_ch == 3345) {
            return true;
        }
        if (_ch >= 3387 && _ch <= 3388) {
            return true;
        }
        if (_ch == 3397) {
            return true;
        }
        if (_ch == 3401) {
            return true;
        }
        if (_ch >= 3407 && _ch <= 3414) {
            return true;
        }
        if (_ch >= 3416 && _ch <= 3423) {
            return true;
        }
        if (_ch >= 3428 && _ch <= 3429) {
            return true;
        }
        if (_ch >= 3446 && _ch <= 3448) {
            return true;
        }
        if (_ch >= 3456 && _ch <= 3457) {
            return true;
        }
        if (_ch == 3460) {
            return true;
        }
        if (_ch >= 3479 && _ch <= 3481) {
            return true;
        }
        if (_ch == 3506) {
            return true;
        }
        if (_ch == 3516) {
            return true;
        }
        if (_ch >= 3518 && _ch <= 3519) {
            return true;
        }
        if (_ch >= 3527 && _ch <= 3529) {
            return true;
        }
        if (_ch >= 3531 && _ch <= 3534) {
            return true;
        }
        if (_ch == 3541) {
            return true;
        }
        if (_ch == 3543) {
            return true;
        }
        if (_ch >= 3552 && _ch <= 3569) {
            return true;
        }
        if (_ch >= 3573 && _ch <= 3584) {
            return true;
        }
        if (_ch >= 3643 && _ch <= 3646) {
            return true;
        }
        if (_ch >= 3676 && _ch <= 3712) {
            return true;
        }
        if (_ch == 3715) {
            return true;
        }
        if (_ch >= 3717 && _ch <= 3718) {
            return true;
        }
        if (_ch == 3721) {
            return true;
        }
        if (_ch >= 3723 && _ch <= 3724) {
            return true;
        }
        if (_ch >= 3726 && _ch <= 3731) {
            return true;
        }
        if (_ch == 3736) {
            return true;
        }
        if (_ch == 3744) {
            return true;
        }
        if (_ch == 3748) {
            return true;
        }
        if (_ch == 3750) {
            return true;
        }
        if (_ch >= 3752 && _ch <= 3753) {
            return true;
        }
        if (_ch == 3756) {
            return true;
        }
        if (_ch == 3770) {
            return true;
        }
        if (_ch >= 3774 && _ch <= 3775) {
            return true;
        }
        if (_ch == 3781) {
            return true;
        }
        if (_ch == 3783) {
            return true;
        }
        if (_ch >= 3790 && _ch <= 3791) {
            return true;
        }
        if (_ch >= 3802 && _ch <= 3803) {
            return true;
        }
        if (_ch >= 3808 && _ch <= 3839) {
            return true;
        }
        if (_ch == 3912) {
            return true;
        }
        if (_ch >= 3949 && _ch <= 3952) {
            return true;
        }
        if (_ch == 3992) {
            return true;
        }
        if (_ch == 4029) {
            return true;
        }
        if (_ch == 4045) {
            return true;
        }
        if (_ch >= 4059 && _ch <= 4095) {
            return true;
        }
        if (_ch == 4294) {
            return true;
        }
        if (_ch >= 4296 && _ch <= 4300) {
            return true;
        }
        if (_ch >= 4302 && _ch <= 4303) {
            return true;
        }
        if (_ch == 4681) {
            return true;
        }
        if (_ch >= 4686 && _ch <= 4687) {
            return true;
        }
        if (_ch == 4695) {
            return true;
        }
        if (_ch == 4697) {
            return true;
        }
        if (_ch >= 4702 && _ch <= 4703) {
            return true;
        }
        if (_ch == 4745) {
            return true;
        }
        if (_ch >= 4750 && _ch <= 4751) {
            return true;
        }
        if (_ch == 4785) {
            return true;
        }
        if (_ch >= 4790 && _ch <= 4791) {
            return true;
        }
        if (_ch == 4799) {
            return true;
        }
        if (_ch == 4801) {
            return true;
        }
        if (_ch >= 4806 && _ch <= 4807) {
            return true;
        }
        if (_ch == 4823) {
            return true;
        }
        if (_ch == 4881) {
            return true;
        }
        if (_ch >= 4886 && _ch <= 4887) {
            return true;
        }
        if (_ch >= 4955 && _ch <= 4956) {
            return true;
        }
        if (_ch >= 4989 && _ch <= 4991) {
            return true;
        }
        if (_ch >= 5018 && _ch <= 5023) {
            return true;
        }
        if (_ch >= 5109 && _ch <= 5119) {
            return true;
        }
        if (_ch >= 5789 && _ch <= 5791) {
            return true;
        }
        if (_ch >= 5873 && _ch <= 5887) {
            return true;
        }
        if (_ch == 5901) {
            return true;
        }
        if (_ch >= 5909 && _ch <= 5919) {
            return true;
        }
        if (_ch >= 5943 && _ch <= 5951) {
            return true;
        }
        if (_ch >= 5972 && _ch <= 5983) {
            return true;
        }
        if (_ch == 5997) {
            return true;
        }
        if (_ch == 6001) {
            return true;
        }
        if (_ch >= 6004 && _ch <= 6015) {
            return true;
        }
        if (_ch >= 6110 && _ch <= 6111) {
            return true;
        }
        if (_ch >= 6122 && _ch <= 6127) {
            return true;
        }
        if (_ch >= 6138 && _ch <= 6143) {
            return true;
        }
        if (_ch == 6159) {
            return true;
        }
        if (_ch >= 6170 && _ch <= 6175) {
            return true;
        }
        if (_ch >= 6264 && _ch <= 6271) {
            return true;
        }
        if (_ch >= 6315 && _ch <= 6319) {
            return true;
        }
        if (_ch >= 6390 && _ch <= 6399) {
            return true;
        }
        if (_ch >= 6429 && _ch <= 6431) {
            return true;
        }
        if (_ch >= 6444 && _ch <= 6447) {
            return true;
        }
        if (_ch >= 6460 && _ch <= 6463) {
            return true;
        }
        if (_ch >= 6465 && _ch <= 6467) {
            return true;
        }
        if (_ch >= 6510 && _ch <= 6511) {
            return true;
        }
        if (_ch >= 6517 && _ch <= 6527) {
            return true;
        }
        if (_ch >= 6572 && _ch <= 6575) {
            return true;
        }
        if (_ch >= 6602 && _ch <= 6607) {
            return true;
        }
        if (_ch >= 6619 && _ch <= 6621) {
            return true;
        }
        if (_ch >= 6684 && _ch <= 6685) {
            return true;
        }
        if (_ch == 6751) {
            return true;
        }
        if (_ch >= 6781 && _ch <= 6782) {
            return true;
        }
        if (_ch >= 6794 && _ch <= 6799) {
            return true;
        }
        if (_ch >= 6810 && _ch <= 6815) {
            return true;
        }
        if (_ch >= 6830 && _ch <= 6911) {
            return true;
        }
        if (_ch >= 6988 && _ch <= 6991) {
            return true;
        }
        if (_ch >= 7037 && _ch <= 7039) {
            return true;
        }
        if (_ch >= 7156 && _ch <= 7163) {
            return true;
        }
        if (_ch >= 7224 && _ch <= 7226) {
            return true;
        }
        if (_ch >= 7242 && _ch <= 7244) {
            return true;
        }
        if (_ch >= 7296 && _ch <= 7359) {
            return true;
        }
        if (_ch >= 7368 && _ch <= 7375) {
            return true;
        }
        if (_ch >= 7415 && _ch <= 7423) {
            return true;
        }
        if (_ch >= 7655 && _ch <= 7675) {
            return true;
        }
        if (_ch >= 7958 && _ch <= 7959) {
            return true;
        }
        if (_ch >= 7966 && _ch <= 7967) {
            return true;
        }
        if (_ch >= 8006 && _ch <= 8007) {
            return true;
        }
        if (_ch >= 8014 && _ch <= 8015) {
            return true;
        }
        if (_ch == 8024) {
            return true;
        }
        if (_ch == 8026) {
            return true;
        }
        if (_ch == 8028) {
            return true;
        }
        if (_ch == 8030) {
            return true;
        }
        if (_ch >= 8062 && _ch <= 8063) {
            return true;
        }
        if (_ch == 8117) {
            return true;
        }
        if (_ch == 8133) {
            return true;
        }
        if (_ch >= 8148 && _ch <= 8149) {
            return true;
        }
        if (_ch == 8156) {
            return true;
        }
        if (_ch >= 8176 && _ch <= 8177) {
            return true;
        }
        if (_ch == 8181) {
            return true;
        }
        if (_ch == 8191) {
            return true;
        }
        if (_ch >= 8293 && _ch <= 8297) {
            return true;
        }
        if (_ch >= 8306 && _ch <= 8307) {
            return true;
        }
        if (_ch == 8335) {
            return true;
        }
        if (_ch >= 8349 && _ch <= 8351) {
            return true;
        }
        if (_ch >= 8379 && _ch <= 8399) {
            return true;
        }
        if (_ch >= 8433 && _ch <= 8447) {
            return true;
        }
        if (_ch >= 8586 && _ch <= 8591) {
            return true;
        }
        if (_ch >= 9204 && _ch <= 9215) {
            return true;
        }
        if (_ch >= 9255 && _ch <= 9279) {
            return true;
        }
        if (_ch >= 9291 && _ch <= 9311) {
            return true;
        }
        if (_ch == 9984) {
            return true;
        }
        if (_ch >= 11085 && _ch <= 11087) {
            return true;
        }
        if (_ch >= 11098 && _ch <= 11263) {
            return true;
        }
        if (_ch == 11311) {
            return true;
        }
        if (_ch == 11359) {
            return true;
        }
        if (_ch >= 11508 && _ch <= 11512) {
            return true;
        }
        if (_ch == 11558) {
            return true;
        }
        if (_ch >= 11560 && _ch <= 11564) {
            return true;
        }
        if (_ch >= 11566 && _ch <= 11567) {
            return true;
        }
        if (_ch >= 11624 && _ch <= 11630) {
            return true;
        }
        if (_ch >= 11633 && _ch <= 11646) {
            return true;
        }
        if (_ch >= 11671 && _ch <= 11679) {
            return true;
        }
        if (_ch == 11687) {
            return true;
        }
        if (_ch == 11695) {
            return true;
        }
        if (_ch == 11703) {
            return true;
        }
        if (_ch == 11711) {
            return true;
        }
        if (_ch == 11719) {
            return true;
        }
        if (_ch == 11727) {
            return true;
        }
        if (_ch == 11735) {
            return true;
        }
        if (_ch == 11743) {
            return true;
        }
        if (_ch >= 11836 && _ch <= 11903) {
            return true;
        }
        if (_ch == 11930) {
            return true;
        }
        if (_ch >= 12020 && _ch <= 12031) {
            return true;
        }
        if (_ch >= 12246 && _ch <= 12271) {
            return true;
        }
        if (_ch >= 12284 && _ch <= 12287) {
            return true;
        }
        if (_ch == 12352) {
            return true;
        }
        if (_ch >= 12439 && _ch <= 12440) {
            return true;
        }
        if (_ch >= 12544 && _ch <= 12548) {
            return true;
        }
        if (_ch >= 12590 && _ch <= 12592) {
            return true;
        }
        if (_ch == 12687) {
            return true;
        }
        if (_ch >= 12731 && _ch <= 12735) {
            return true;
        }
        if (_ch >= 12772 && _ch <= 12783) {
            return true;
        }
        if (_ch == 12831) {
            return true;
        }
        if (_ch == 13055) {
            return true;
        }
        if (_ch >= 19894 && _ch <= 19903) {
            return true;
        }
        if (_ch >= 40909 && _ch <= 40959) {
            return true;
        }
        if (_ch >= 42125 && _ch <= 42127) {
            return true;
        }
        if (_ch >= 42183 && _ch <= 42191) {
            return true;
        }
        if (_ch >= 42540 && _ch <= 42559) {
            return true;
        }
        if (_ch >= 42648 && _ch <= 42654) {
            return true;
        }
        if (_ch >= 42744 && _ch <= 42751) {
            return true;
        }
        if (_ch == 42895) {
            return true;
        }
        if (_ch >= 42900 && _ch <= 42911) {
            return true;
        }
        if (_ch >= 42923 && _ch <= 42999) {
            return true;
        }
        if (_ch >= 43052 && _ch <= 43055) {
            return true;
        }
        if (_ch >= 43066 && _ch <= 43071) {
            return true;
        }
        if (_ch >= 43128 && _ch <= 43135) {
            return true;
        }
        if (_ch >= 43205 && _ch <= 43213) {
            return true;
        }
        if (_ch >= 43226 && _ch <= 43231) {
            return true;
        }
        if (_ch >= 43260 && _ch <= 43263) {
            return true;
        }
        if (_ch >= 43348 && _ch <= 43358) {
            return true;
        }
        if (_ch >= 43389 && _ch <= 43391) {
            return true;
        }
        if (_ch == 43470) {
            return true;
        }
        if (_ch >= 43482 && _ch <= 43485) {
            return true;
        }
        if (_ch >= 43488 && _ch <= 43519) {
            return true;
        }
        if (_ch >= 43575 && _ch <= 43583) {
            return true;
        }
        if (_ch >= 43598 && _ch <= 43599) {
            return true;
        }
        if (_ch >= 43610 && _ch <= 43611) {
            return true;
        }
        if (_ch >= 43644 && _ch <= 43647) {
            return true;
        }
        if (_ch >= 43715 && _ch <= 43738) {
            return true;
        }
        if (_ch >= 43767 && _ch <= 43776) {
            return true;
        }
        if (_ch >= 43783 && _ch <= 43784) {
            return true;
        }
        if (_ch >= 43791 && _ch <= 43792) {
            return true;
        }
        if (_ch >= 43799 && _ch <= 43807) {
            return true;
        }
        if (_ch == 43815) {
            return true;
        }
        if (_ch >= 43823 && _ch <= 43967) {
            return true;
        }
        if (_ch >= 44014 && _ch <= 44015) {
            return true;
        }
        if (_ch >= 44026 && _ch <= 44031) {
            return true;
        }
        if (_ch >= 55204 && _ch <= 55215) {
            return true;
        }
        if (_ch >= 55239 && _ch <= 55242) {
            return true;
        }
        if (_ch >= 55292 && _ch <= 55295) {
            return true;
        }
        if (_ch >= 64110 && _ch <= 64111) {
            return true;
        }
        if (_ch >= 64218 && _ch <= 64255) {
            return true;
        }
        if (_ch >= 64263 && _ch <= 64274) {
            return true;
        }
        if (_ch >= 64280 && _ch <= 64284) {
            return true;
        }
        if (_ch == 64311) {
            return true;
        }
        if (_ch == 64317) {
            return true;
        }
        if (_ch == 64319) {
            return true;
        }
        if (_ch == 64322) {
            return true;
        }
        if (_ch == 64325) {
            return true;
        }
        if (_ch >= 64450 && _ch <= 64466) {
            return true;
        }
        if (_ch >= 64832 && _ch <= 64847) {
            return true;
        }
        if (_ch >= 64912 && _ch <= 64913) {
            return true;
        }
        if (_ch >= 64968 && _ch <= 65007) {
            return true;
        }
        if (_ch >= 65022 && _ch <= 65023) {
            return true;
        }
        if (_ch >= 65050 && _ch <= 65055) {
            return true;
        }
        if (_ch >= 65063 && _ch <= 65071) {
            return true;
        }
        if (_ch == 65107) {
            return true;
        }
        if (_ch == 65127) {
            return true;
        }
        if (_ch >= 65132 && _ch <= 65135) {
            return true;
        }
        if (_ch == 65141) {
            return true;
        }
        if (_ch >= 65277 && _ch <= 65278) {
            return true;
        }
        if (_ch == 65280) {
            return true;
        }
        if (_ch >= 65471 && _ch <= 65473) {
            return true;
        }
        if (_ch >= 65480 && _ch <= 65481) {
            return true;
        }
        if (_ch >= 65488 && _ch <= 65489) {
            return true;
        }
        if (_ch >= 65496 && _ch <= 65497) {
            return true;
        }
        if (_ch >= 65501 && _ch <= 65503) {
            return true;
        }
        if (_ch == 65511) {
            return true;
        }
        if (_ch >= 65519 && _ch <= 65528) {
            return true;
        }
        return _ch >= 65534;
    }

    private static boolean isRomanDigits(char _ch) {
        return _ch >= 8544 && _ch <= 8575;
    }
    private static boolean isSensibleOtherLetter(char _ch) {
        return _ch >= 9398 && _ch <= 9449;
    }

    private static boolean isQuotePunct(char _ch) {
        if (_ch >= 8216 && _ch <= 8217) {
            return true;
        }
        if (_ch >= 8219 && _ch <= 8221) {
            return true;
        }
        if (_ch == 8223) {
            return true;
        }
        if (_ch == 171 || _ch == 187) {
            return true;
        }
        if (_ch >= 8249 && _ch <= 8250) {
            return true;
        }
        if (_ch >= 11778 && _ch <= 11781) {
            return true;
        }
        if (_ch >= 11785 && _ch <= 11786) {
            return true;
        }
        if (_ch >= 11788 && _ch <= 11789) {
            return true;
        }
        if (_ch >= 11804 && _ch <= 11805) {
            return true;
        }
        return _ch >= 11808 && _ch <= 11809;
    }
    private static boolean isBoundPunct(char _ch) {
        if (_ch >= 3898 && _ch <= 3901) {
            return true;
        }
        if (_ch >= 5787 && _ch <= 5788) {
            return true;
        }
        if (_ch == 8218 || _ch == 8222) {
            return true;
        }
        if (_ch >= 8261 && _ch <= 8262) {
            return true;
        }
        if (_ch >= 8287 && _ch <= 9002) {
            return true;
        }
        if (_ch >= 10088 && _ch <= 10749) {
            return true;
        }
        if (_ch >= 11810 && _ch <= 11817) {
            return true;
        }
        if (_ch >= 12296 && _ch <= 12319) {
            return true;
        }
        if (_ch >= 64830 && _ch <= 65039) {
            return true;
        }
        if (_ch >= 65047 && _ch <= 65048) {
            return true;
        }
        if (_ch >= 65073 && _ch <= 65092) {
            return true;
        }
        if (_ch >= 65095 && _ch <= 65096) {
            return true;
        }
        if (_ch >= 65113 && _ch <= 65118) {
            return true;
        }
        if (_ch >= 65288 && _ch <= 65289) {
            return true;
        }
        if (_ch >= 65313 && _ch <= 65339) {
            return true;
        }
        if (_ch >= 65341 && _ch <= 65376) {
            return true;
        }
        return _ch >= 65378 && _ch <= 65379;
    }
    private static boolean isConnector(char _ch) {
        if (_ch >= 1418 && _ch <= 1471) {
            return true;
        }
        if (_ch == 5120 || _ch == 6150 || _ch == 8276
                || _ch == 11802|| _ch == 12316|| _ch == 12448 || _ch == 12336
                || _ch == 65123 || _ch == 65112 ||_ch==65293 || _ch==65343) {
            return true;
        }
        if (_ch >= 8208 && _ch <= 8213) {
            return true;
        }
        if (_ch >= 8255 && _ch <= 8256) {
            return true;
        }
        if (_ch >= 11799 && _ch <= 11799) {
            return true;
        }
        if (_ch >= 11834 && _ch <= 11835) {
            return true;
        }
        if (_ch >= 65073 && _ch <= 65076) {
            return true;
        }
        return _ch >= 65101 && _ch <= 65103;
    }
    private static boolean isModifierSymbol(char _ch) {
        if (_ch <= 901) {
            return true;
        }
        if (_ch >= 8125 && _ch <= 8190) {
            return true;
        }
        if (_ch >= 12443 && _ch <= 12444) {
            return true;
        }
        if (_ch >= 42752 && _ch <= 42890) {
            return true;
        }
        if (_ch >= 64434 && _ch <= 65020) {
            return true;
        }
        return _ch >= 65022 && _ch <= 65507;
    }

    private static int procGene(char _ch, int _type) {
        if (_type == 1) {
            return 2;
        }
        if (_type == 2) {
            return 1;
        }
        if (_type == 3) {
            return processLetter(_ch);
        }
        if (_type == 4) {
            return 1;
        }
        if (_type == 5) {
            return 3;
        }
        if (_type == 6) {
            return processOtherLetter(_ch);
        }
        if (_type == 7) {
            return 4;
        }
        if (_type == 8) {
            return processOtherLetterTwo(_ch);
        }
        if (_type == 9) {
            return 9;
        }
        if (_type == 10) {
            return 9;
        }
        if (_type == 11) {
            return 23;
        }
        return processGeneralSymbol(_ch, _type);
    }

    private static int processGeneralSymbol(char _ch, int _type) {
        if (_type == 12) {
            return 26;
        }
        if (_type == 13) {
            return 28;
        }
        if (_type == 14) {
            return 10;
        }
        if (_type == 15) {
            return 21;
        }
        if (_type == 16) {
            return 22;
        }
        if (_type == 17) {
            return processOperators(_ch);
        }
        if (_type == 18) {
            return 24;
        }
        if (_type == 19) {
            return processQuote(_ch);
        }
        if (_type == 20) {
            return 24;
        }
        if (_type == 21) {
            return 25;
        }
        if (_type == 22) {
            return processModifier(_ch);
        }
        if (_type == 23) {
            return 27;
        }
        if (_type == 24) {
            return 28;
        }
        if (_type == 25) {
            return processPunct(_ch);
        }
        if (_type == 26) {
            return processPunctTwo(_ch);
        }
        if (_type == 27) {
            return processPunctThree(_ch);
        }
        return processDefault(_ch, _type);
    }

    private static int processDefault(char _ch, int _type) {
        if (_type == 28) {
            return 24;
        }
        if (_type == 29) {
            if (_ch <= 10) {
                return 15;
            }
            return 12;
        }
        if (_type == 30) {
            if (_ch <= 159) {
                return 15;
            }
            return 12;
        }
        if (_type == 31) {
            return processOther(_ch);
        }
        return 0;
    }

    private static int processPunctThree(char _ch) {
        if (_ch == 171) {
            return 29;
        }
        if (_ch == 8223) {
            return 29;
        }
        if (_ch == 187) {
            return 30;
        }
        if (_ch == 8221) {
            return 30;
        }
        if (_ch <= 8217) {
            if (_ch % 2 == 1) {
                return 30;
            }
            return 29;
        }
        if (_ch <= 8220) {
            return 29;
        }
        if (_ch <= 11777) {
            if (_ch % 2 == 1) {
                return 29;
            }
            return 30;
        }
        if (_ch <= 11781) {
            if (_ch % 2 == 1) {
                return 30;
            }
            return 29;
        }
        if (_ch <= 11786) {
            if (_ch % 2 == 1) {
                return 29;
            }
            return 30;
        }
        if (_ch % 2 == 1) {
            return 30;
        }
        return 29;
    }

    private static int processPunctTwo(char _ch) {
        if (_ch <= 4000) {
            if (_ch % 2 == 1) {
                return 22;
            }
            return 21;
        }
        if (_ch <= 6000) {
            if (_ch % 2 == 1) {
                return 21;
            }
            return 22;
        }
        if (_ch <= 8261) {
            return 21;
        }
        if (_ch <= 10000) {
            if (_ch % 2 == 1) {
                return 21;
            }
            return 22;
        }
        if (_ch <= 10180) {
            if (_ch % 2 == 1) {
                return 22;
            }
            return 21;
        }
        if (_ch <= 10182) {
            if (_ch % 2 == 1) {
                return 21;
            }
            return 22;
        }
        if (_ch <= 10223) {
            if (_ch % 2 == 1) {
                return 22;
            }
            return 21;
        }
        if (_ch <= 10711) {
            if (_ch % 2 == 1) {
                return 21;
            }
            return 22;
        }
        if (_ch <= 12315) {
            if (_ch % 2 == 1) {
                return 22;
            }
            return 21;
        }
        if (_ch <= 12318) {
            if (_ch % 2 == 1) {
                return 21;
            }
            return 22;
        }
        if (_ch <= 12319) {
            return 22;
        }
        if (_ch <= 64831) {
            if (_ch % 2 == 1) {
                return 22;
            }
            return 21;
        }
        if (_ch <= 65118) {
            if (_ch % 2 == 1) {
                return 21;
            }
            return 22;
        }
        if (_ch <= 65289) {
            if (_ch % 2 == 1) {
                return 22;
            }
            return 21;
        }
        if (_ch == 65339) {
            return 21;
        }
        if (_ch == 65341) {
            return 22;
        }
        if (_ch == 65371) {
            return 21;
        }
        if (_ch == 65373) {
            return 22;
        }
        if (_ch == 65375) {
            return 21;
        }
        if (_ch == 65376) {
            return 22;
        }
        if (_ch == 65378) {
            return 21;
        }
        return 22;
    }

    private static int processPunct(char _ch) {
        if (_ch <= 8213) {
            return 20;
        }
        if (_ch <= 8276) {
            return 23;
        }
        if (_ch <= 65074) {
            return 20;
        }
        if (_ch <= 65103) {
            return 23;
        }
        if (_ch <= 65293) {
            return 20;
        }
        return 23;
    }

    private static int processModifier(char _ch) {
        if (_ch == 173) {
            return 16;
        }
        if (_ch == 166) {
            return 28;
        }
        if (_ch == 168) {
            return 27;
        }
        if (_ch == 175) {
            return 27;
        }
        if (_ch == 180) {
            return 27;
        }
        if (_ch == 184) {
            return 27;
        }
        if (_ch <= 176) {
            return 28;
        }
        return 11;
    }

    private static int processQuote(char _ch) {
        if (_ch <= 64) {
            return 24;
        }
        return 27;
    }

    private static int processOperators(char _ch) {
        if (_ch == 45) {
            return 20;
        }
        if (_ch == 94) {
            return 27;
        }
        if (_ch == 43) {
            return 25;
        }
        if (_ch <= 58) {
            return 24;
        }
        if (_ch <= 62) {
            return 25;
        }
        if (_ch <= 64) {
            return 24;
        }
        return 25;
    }

    private static int processOtherLetterTwo(char _ch) {
        if (_ch == 1600) {
            return 4;
        }
        if (_ch == 1765) {
            return 4;
        }
        if (_ch == 1766) {
            return 4;
        }
        if (_ch == 2036) {
            return 4;
        }
        if (_ch == 2037) {
            return 4;
        }
        if (_ch == 2042) {
            return 4;
        }
        if (_ch == 2074) {
            return 4;
        }
        if (_ch == 2084) {
            return 4;
        }
        if (_ch == 2088) {
            return 4;
        }
        return 5;
    }

    private static int processOtherLetter(char _ch) {
        if (_ch == 1369) {
            return 4;
        }
        if (_ch == 2417) {
            return 4;
        }
        if (_ch == 3654) {
            return 4;
        }
        if (_ch == 3782) {
            return 4;
        }
        if (_ch == 4348) {
            return 4;
        }
        if (_ch == 6103) {
            return 4;
        }
        if (_ch == 6211) {
            return 4;
        }
        if (_ch == 6823) {
            return 4;
        }
        if (_ch == 11631) {
            return 4;
        }
        if (_ch == 12293) {
            return 4;
        }
        if (_ch == 40981) {
            return 4;
        }
        if (_ch == 42508) {
            return 4;
        }
        if (_ch == 43741) {
            return 4;
        }
        if (_ch == 43471) {
            return 4;
        }
        if (_ch == 43632) {
            return 4;
        }
        if (_ch == 43763) {
            return 4;
        }
        if (_ch == 43764) {
            return 4;
        }
        if (_ch == 65392) {
            return 4;
        }
        if (_ch == 65438) {
            return 4;
        }
        if (_ch == 65439) {
            return 4;
        }
        if (_ch>=7288&&_ch <= 7293) {
            return 4;
        }
        if (_ch>=12337&&_ch <= 12347) {
            return 4;
        }
        if (_ch>=12445&&_ch <= 12446) {
            return 4;
        }
        if (_ch>=12539&&_ch <= 12542) {
            return 4;
        }
        if (_ch>=42232&&_ch <= 42239) {
            return 4;
        }
        if (_ch>=699&&_ch <= 750) {
            return 4;
        }
        if (_ch>=8072&&_ch <= 8188) {
            return 3;
        }
        return 5;
    }

    private static int processLetter(char _ch) {
        if (_ch <= 186) {
            return 5;
        }
        if (_ch <= 687) {
            return 2;
        }
        if (_ch <= 890) {
            return 4;
        }
        if (_ch <= 7467) {
            return 2;
        }
        if (_ch <= 7530) {
            return 4;
        }
        if (_ch <= 7543) {
            return 2;
        }
        if (_ch <= 7544) {
            return 4;
        }
        if (_ch <= 7578) {
            return 2;
        }
        if (_ch <= 7615) {
            return 4;
        }
        if (_ch <= 8183) {
            return 2;
        }
        if (_ch <= 8348) {
            return 4;
        }
        if (_ch <= 11387) {
            return 2;
        }
        if (_ch <= 11389) {
            return 4;
        }
        if (_ch <= 42801) {
            return 2;
        }
        if (_ch <= 42864) {
            return 4;
        }
        if (_ch <= 42894) {
            return 2;
        }
        if (_ch <= 43001) {
            return 4;
        }
        return 2;
    }

    private static int processOther(char _ch) {
        if (_ch == 8232) {
            return 13;
        }
        if (_ch == 8233) {
            return 14;
        }
        if (_ch >= 57344 && _ch <= 63743) {
            return 18;
        }
        if (_ch >= 55296 && _ch <= 57343) {
            return 19;
        }
        if (_ch >= 8203 && _ch <= 8303) {
            return 16;
        }
        if (_ch >= 65279) {
            return 16;
        }
        if (_ch >= 44013) {
            return 6;
        }
        if (_ch >= 12690 && _ch <= 12991) {
            return 11;
        }
        if (_ch >= 43056 && _ch <= 43061) {
            return 11;
        }
        if (_ch >= 8585 && _ch <= 10131) {
            return 11;
        }
        if (_ch >= 2548 && _ch <= 2553) {
            return 11;
        }
        if (_ch >= 2930 && _ch <= 2935) {
            return 11;
        }
        if (_ch >= 3056 && _ch <= 3058) {
            return 11;
        }
        if (_ch >= 3192 && _ch <= 3198) {
            return 11;
        }
        if (_ch >= 3440 && _ch <= 3445) {
            return 11;
        }
        if (_ch >= 3882 && _ch <= 3891) {
            return 11;
        }
        if (_ch >= 4969 && _ch <= 4988) {
            return 11;
        }
        if (_ch >= 11517 && _ch <= 11517) {
            return 11;
        }
        if (_ch >= 8528 && _ch <= 8543) {
            return 11;
        }
        if (_ch >= 8304 && _ch <= 8329) {
            return 11;
        }
        if (_ch >= 6128 && _ch <= 6137) {
            return 11;
        }
        if (_ch >= 6618 && _ch <= 6618) {
            return 11;
        }
        if (_ch >= 8576 && _ch <= 8584) {
            return 10;
        }
        if (_ch >= 12295 && _ch <= 12329) {
            return 10;
        }
        if (_ch >= 42726 && _ch <= 42735) {
            return 10;
        }
        if (_ch >= 12344 && _ch <= 12346) {
            return 10;
        }
        if (_ch >= 5870 && _ch <= 5872) {
            return 10;
        }
        if (_ch >= 8413 && _ch <= 8416) {
            return 7;
        }
        if (_ch >= 8418 && _ch <= 8420) {
            return 7;
        }
        if (_ch >= 42608 && _ch <= 42610) {
            return 7;
        }
        if (_ch <= 1159) {
            return 6;
        }
        if (_ch <= 1161) {
            return 7;
        }
        if (_ch <= 1479) {
            return 6;
        }
        if (_ch <= 1540) {
            return 16;
        }
        if (_ch <= 1756) {
            return 6;
        }
        if (_ch <= 1757) {
            return 16;
        }
        if (_ch <= 1773) {
            return 6;
        }
        if (_ch <= 1807) {
            return 16;
        }
        if (_ch <= 2306) {
            return 6;
        }
        if (_ch >= 3633 && _ch <= 3897) {
            return 6;
        }
        if (_ch >= 3953 && _ch <= 3966) {
            return 6;
        }
        if (_ch >= 43204 && _ch <= 43345) {
            return 6;
        }
        if (_ch >= 7412 && _ch <= 12333) {
            return 6;
        }
        if (_ch >= 12441 && _ch <= 43019) {
            return 6;
        }
        if (_ch >= 7222 && _ch <= 7392) {
            return 6;
        }
        if (_ch >= 7394 && _ch <= 7405) {
            return 6;
        }
        if (_ch >= 3968 && _ch <= 4038) {
            return 6;
        }
        if (_ch >= 4253 && _ch <= 6069) {
            return 6;
        }
        if (_ch >= 6071 && _ch <= 6077) {
            return 6;
        }
        if (_ch >= 6089 && _ch <= 6434) {
            return 6;
        }
        if (_ch >= 2369 && _ch <= 2376) {
            return 6;
        }
        if (_ch >= 2385 && _ch <= 2433) {
            return 6;
        }
        if (_ch >= 2625 && _ch <= 2690) {
            return 6;
        }
        if (_ch >= 3142 && _ch <= 3171) {
            return 6;
        }
        if (_ch >= 6744 && _ch <= 6752) {
            return 6;
        }
        if (_ch >= 6771 && _ch <= 6915) {
            return 6;
        }
        if (_ch >= 43047 && _ch <= 43347) {
            return 8;
        }
        if (_ch >= 4239 && _ch <= 6085) {
            return 8;
        }
        if (_ch >= 2366 && _ch <= 2380) {
            return 8;
        }
        if (_ch >= 7220 && _ch <= 43044) {
            return 8;
        }
        if (_ch >= 7212 && _ch <= 43394) {
            return 6;
        }
        if (_ch >= 7154 && _ch <= 43395) {
            return 8;
        }
        if (_ch >= 7151 && _ch <= 43443) {
            return 6;
        }
        if (_ch >= 7150 && _ch <= 43445) {
            return 8;
        }
        if (_ch >= 7149 && _ch <= 43449) {
            return 6;
        }
        if (_ch >= 7146 && _ch <= 43451) {
            return 8;
        }
        if (_ch >= 7144 && _ch <= 43452) {
            return 6;
        }
        if (_ch >= 7143 && _ch <= 43456) {
            return 8;
        }
        if (_ch >= 7142 && _ch <= 43566) {
            return 6;
        }
        if (_ch >= 7084 && _ch <= 43568) {
            return 8;
        }
        if (_ch >= 7083 && _ch <= 43570) {
            return 6;
        }
        if (_ch >= 7082 && _ch <= 43572) {
            return 8;
        }
        if (_ch >= 7080 && _ch <= 43596) {
            return 6;
        }
        if (_ch >= 7078 && _ch <= 43643) {
            return 8;
        }
        if (_ch >= 7074 && _ch <= 43713) {
            return 6;
        }
        if (_ch >= 7042 && _ch <= 43755) {
            return 8;
        }
        if (_ch >= 7019 && _ch <= 43757) {
            return 6;
        }
        if (_ch >= 6979 && _ch <= 43765) {
            return 8;
        }
        if (_ch >= 6757 && _ch <= 6764) {
            return 6;
        }
        if (_ch >= 6966 && _ch <= 6970) {
            return 6;
        }
        if (_ch >= 4141 && _ch <= 4144) {
            return 6;
        }
        if (_ch >= 4146 && _ch <= 4151) {
            return 6;
        }
        if (_ch >= 4153 && _ch <= 4154) {
            return 6;
        }
        if (_ch >= 4157 && _ch <= 4158) {
            return 6;
        }
        if (_ch >= 4184 && _ch <= 4192) {
            return 6;
        }
        if (_ch >= 4209 && _ch <= 4226) {
            return 6;
        }
        if (_ch >= 4229 && _ch <= 4230) {
            return 6;
        }
        if (_ch >= 4237 && _ch <= 6086) {
            return 6;
        }
        if (_ch >= 6439 && _ch <= 6440) {
            return 6;
        }
        if (_ch >= 6450 && _ch <= 6450) {
            return 6;
        }
        if (_ch >= 6457 && _ch <= 6459) {
            return 6;
        }
        if (_ch >= 3544 && _ch <= 6601) {
            return 8;
        }
        if (_ch >= 3538 && _ch <= 6680) {
            return 6;
        }
        if (_ch >= 3535 && _ch <= 6741) {
            return 8;
        }
        if (_ch >= 3393 && _ch <= 3396) {
            return 6;
        }
        if (_ch >= 3330 && _ch <= 3404) {
            return 8;
        }
        if (_ch >= 2753 && _ch <= 2760) {
            return 6;
        }
        if (_ch >= 2750 && _ch <= 2764) {
            return 8;
        }
        if (_ch >= 2748 && _ch <= 2817) {
            return 6;
        }
        if (_ch >= 2622 && _ch <= 2819) {
            return 8;
        }
        if (_ch >= 2530 && _ch <= 2562) {
            return 6;
        }
        if (_ch >= 2519 && _ch <= 2563) {
            return 8;
        }
        if (_ch >= 2509 && _ch <= 2876) {
            return 6;
        }
        if (_ch >= 2503 && _ch <= 2878) {
            return 8;
        }
        if (_ch >= 3298 && _ch <= 3405) {
            return 6;
        }
        if (_ch >= 3285 && _ch <= 3415) {
            return 8;
        }
        if (_ch >= 3276 && _ch <= 3427) {
            return 6;
        }
        if (_ch >= 3271 && _ch <= 3459) {
            return 8;
        }
        if (_ch >= 2887 && _ch <= 2892) {
            return 8;
        }
        if (_ch >= 2881 && _ch <= 2902) {
            return 6;
        }
        if (_ch >= 2880 && _ch <= 2903) {
            return 8;
        }
        if (_ch >= 2497 && _ch <= 2946) {
            return 6;
        }
        if (_ch >= 6755 && _ch <= 6916) {
            return 8;
        }
        if (_ch >= 6754 && _ch <= 6964) {
            return 6;
        }
        if (_ch == 2362) {
            return 6;
        }
        if (_ch == 2492) {
            return 6;
        }
        if (_ch == 3008) {
            return 6;
        }
        if (_ch == 3021) {
            return 6;
        }
        if (_ch >= 2382 && _ch <= 3075) {
            return 8;
        }
        if (_ch >= 2364 && _ch <= 3136) {
            return 6;
        }
        if (_ch >= 3270 && _ch <= 6742) {
            return 6;
        }
        if (_ch >= 3264 && _ch <= 6971) {
            return 8;
        }
        if (_ch == 3260) {
            return 6;
        }
        if (_ch == 44008) {
            return 6;
        }
        if (_ch == 44005) {
            return 6;
        }
        if (_ch >= 6973 && _ch <= 6977) {
            return 8;
        }
        if (_ch >= 3263 && _ch <= 43766) {
            return 6;
        }
        return 8;
    }

    private static boolean isOtherMathSymbol(char _string) {
        if (_string < 128) {
            return false;
        }
        for (int i: NumberUtil.wrapIntArray(8602,8654)) {
            if (_string >= i && _string <= i + 1) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(1542,8314,8330)) {
            if (_string >= i && _string <= i + 2) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(8512,8592)) {
            if (_string >= i && _string <= i + 4) {
                return true;
            }
        }
        if (_string == 172) {
            return true;
        }
        if (_string == 177) {
            return true;
        }
        if (_string == 215) {
            return true;
        }
        if (_string == 247) {
            return true;
        }
        if (_string == 1014 || _string == 8611) {
            return true;
        }
        if (_string == 8260 || _string == 8614) {
            return true;
        }
        if (_string == 8274 || _string == 8622) {
            return true;
        }
        if (_string == 8472 || _string == 8660 || _string == 8658) {
            return true;
        }
        if (_string == 8523 ||_string ==9655 ||_string ==9665) {
            return true;
        }
        if (_string == 8608 || _string == 9839 || _string == 9084) {
            return true;
        }
        if (_string >= 9115&&_string<=9139) {
            return true;
        }
        if (_string >= 9180&&_string<=9185) {
            return true;
        }
        if (_string >= 9720&&_string<=9727) {
            return true;
        }
        if (_string >= 8968&&_string<=8971) {
            return true;
        }
        if (_string >= 8960&&_string<=8991) {
            return false;
        }
        if (_string >= 8692&&_string<=8993) {
            return true;
        }
        if (_string >= 10176&&_string<=10180) {
            return true;
        }
        if (_string >= 10183&&_string<=10213) {
            return true;
        }
        if (_string >= 10224&&_string<=10239) {
            return true;
        }
        if (_string >= 10627&&_string<=10648) {
            return false;
        }
        if (_string >= 10716&&_string<=10747) {
            return true;
        }
        if (_string >= 10712&&_string<=10749) {
            return false;
        }
        if (_string >= 11056&&_string<=11076) {
            return true;
        }
        if (_string >= 11008&&_string<=11078) {
            return false;
        }
        if (_string >= 10496&&_string<=11084) {
            return true;
        }
        if (_string >= 65513&&_string<=65516) {
            return true;
        }
        if (_string >= 65308&&_string<=65310) {
            return true;
        }
        if (_string >= 65124&&_string<=65126) {
            return true;
        }
        if (_string == 64297) {
            return true;
        }
        if (_string == 65122) {
            return true;
        }
        if (_string == 65291) {
            return true;
        }
        if (_string == 65372) {
            return true;
        }
        if (_string == 65374) {
            return true;
        }
        return _string == 65506;
    }


    private static int toLowerCaseInt(char _ch) {
        if (_ch == 453) {
            return _ch + 1;
        }
        if (_ch == 456) {
            return _ch + 1;
        }
        if (isUnassigned(_ch)) {
            return _ch;
        }
        if (isOtherSpace(_ch)) {
            return _ch;
        }
        if (isDigit(_ch)) {
            return _ch;
        }
        if (isOtherDigit(_ch)) {
            return _ch;
        }
        return toLowerCheckDefFive(_ch);
    }

    private static int toLowerCheckDefFive(char _ch) {
        if (isRomanDigits(_ch)) {
            if (_ch >= 8544 + 16) {
                return _ch;
            }
            return _ch+16;
        }
        if (isSensibleOtherLetter(_ch)) {
            if (_ch >= 9398 + 26) {
                return _ch;
            }
            return _ch+26;
        }
        if (StringUtil.isOtherMathSymbol(_ch)) {
            return _ch;
        }
        return toLowerCheckDefFour(_ch);
    }

    private static int toLowerCheckDefFour(char _ch) {
        if (_ch == 391) {
            return _ch + 1;
        }
        if (_ch == 395) {
            return _ch + 1;
        }
        if (_ch == 401) {
            return _ch + 1;
        }
        if (_ch == 408) {
            return _ch + 1;
        }
        if (_ch == 423) {
            return _ch + 1;
        }
        if (_ch == 428) {
            return _ch + 1;
        }
        if (_ch == 431) {
            return _ch + 1;
        }
        if (_ch == 440) {
            return _ch + 1;
        }
        if (_ch == 444) {
            return _ch + 1;
        }
        return toLowerCheckDefThree(_ch);
    }

    private static int toLowerCheckDefTwo(char _ch) {
        if (_ch % 2 == 0) {
            if (_ch >= 256 && _ch <= 302) {
                return _ch + 1;
            }
            if (_ch >= 306 && _ch <= 310) {
                return _ch + 1;
            }
            if (_ch >= 330 && _ch <= 374) {
                return _ch + 1;
            }
            if (_ch >= 386 && _ch <= 388) {
                return _ch + 1;
            }
            if (_ch >= 416 && _ch <= 420) {
                return _ch + 1;
            }
            if (_ch >= 478 && _ch <= 494) {
                return _ch + 1;
            }
            if (_ch >= 498 && _ch <= 500) {
                return _ch + 1;
            }
            if (_ch >= 504 && _ch <= 542) {
                return _ch + 1;
            }
            if (_ch >= 546 && _ch <= 562) {
                return _ch + 1;
            }
            if (_ch >= 582 && _ch <= 590) {
                return _ch + 1;
            }
            if (_ch >= 880 && _ch <= 882) {
                return _ch + 1;
            }
            if (_ch >= 984 && _ch <= 1006) {
                return _ch + 1;
            }
            if (_ch >= 1120 && _ch <= 1152) {
                return _ch + 1;
            }
            if (_ch >= 1162 && _ch <= 1214) {
                return _ch + 1;
            }
            if (_ch >= 1232 && _ch <= 1318) {
                return _ch + 1;
            }
            if (_ch >= 7680 && _ch <= 7828) {
                return _ch + 1;
            }
            if (_ch >= 7840 && _ch <= 7934) {
                return _ch + 1;
            }
            if (_ch >= 11392 && _ch <= 11490) {
                return _ch + 1;
            }
            if (_ch >= 42560 && _ch <= 42604) {
                return _ch + 1;
            }
            if (_ch >= 42624 && _ch <= 42646) {
                return _ch + 1;
            }
            if (_ch >= 42786 && _ch <= 42798) {
                return _ch + 1;
            }
            if (_ch >= 42802 && _ch <= 42862) {
                return _ch + 1;
            }
            if (_ch >= 42878 && _ch <= 42886) {
                return _ch + 1;
            }
            if (_ch >= 42896 && _ch <= 42898) {
                return _ch + 1;
            }
            if (_ch >= 42912 && _ch <= 42920) {
                return _ch + 1;
            }
        } else {
            if (_ch >= 313 && _ch <= 327) {
                return _ch + 1;
            }
            if (_ch >= 377 && _ch <= 381) {
                return _ch + 1;
            }
            if (_ch >= 435 && _ch <= 437) {
                return _ch + 1;
            }
            if (_ch >= 459 && _ch <= 475) {
                return _ch + 1;
            }
            if (_ch >= 1217 && _ch <= 1229) {
                return _ch + 1;
            }
            if (_ch >= 11367 && _ch <= 11371) {
                return _ch + 1;
            }
            if (_ch >= 11499 && _ch <= 11501) {
                return _ch + 1;
            }
            if (_ch >= 42873 && _ch <= 42875) {
                return _ch + 1;
            }
        }

        return toLowerCheckDef(_ch);
    }


    private static int toLowerCaseIntCheckUpp(char _ch) {
        if (isRomanDigits(_ch)) {
            return _ch+16;
        }
        if (isSensibleOtherLetter(_ch)) {
            return _ch+26;
        }
        if (StringUtil.isOtherMathSymbol(_ch)) {
            return _ch;
        }
        if (_ch == 391) {
            return _ch + 1;
        }
        if (_ch == 395) {
            return _ch + 1;
        }
        if (_ch == 401) {
            return _ch + 1;
        }
        if (_ch == 408) {
            return _ch + 1;
        }
        if (_ch == 423) {
            return _ch + 1;
        }
        if (_ch == 428) {
            return _ch + 1;
        }
        if (_ch == 431) {
            return _ch + 1;
        }
        if (_ch == 440) {
            return _ch + 1;
        }
        if (_ch == 444) {
            return _ch + 1;
        }
        if (_ch == 571) {
            return _ch + 1;
        }
        if (_ch == 577) {
            return _ch + 1;
        }
        if (_ch == 886) {
            return _ch + 1;
        }
        if (_ch == 1015) {
            return _ch + 1;
        }
        if (_ch == 1018) {
            return _ch + 1;
        }
        if (_ch == 8579) {
            return _ch + 1;
        }
        if (_ch == 11360) {
            return _ch + 1;
        }
        if (_ch == 11378) {
            return _ch + 1;
        }
        if (_ch == 11381) {
            return _ch + 1;
        }
        if (_ch == 11506) {
            return _ch + 1;
        }
        if (_ch == 42891) {
            return _ch + 1;
        }
        if (_ch == 304) {
            return _ch -199;
        }
        if (_ch == 376) {
            return _ch -121;
        }
        if (_ch == 385) {
            return _ch + 210;
        }
        if (_ch == 390) {
            return _ch + 206;
        }
        if (_ch == 398) {
            return _ch + 79;
        }
        if (_ch == 399) {
            return _ch + 202;
        }
        if (_ch == 400) {
            return _ch + 203;
        }
        if (_ch == 403) {
            return _ch + 205;
        }
        if (_ch == 404) {
            return _ch + 207;
        }
        if (_ch == 406) {
            return _ch + 211;
        }
        if (_ch == 407) {
            return _ch + 209;
        }
        if (_ch == 412) {
            return _ch + 211;
        }
        if (_ch == 413) {
            return _ch + 213;
        }
        if (_ch == 415) {
            return _ch + 214;
        }
        if (_ch == 422) {
            return _ch + 218;
        }
        if (_ch == 425) {
            return _ch + 218;
        }
        if (_ch == 430) {
            return _ch + 218;
        }
        if (_ch == 439) {
            return _ch + 219;
        }
        if (_ch == 452) {
            return _ch + 2;
        }
        if (_ch == 455) {
            return _ch + 2;
        }
        if (_ch == 458) {
            return _ch + 2;
        }
        if (_ch == 497) {
            return _ch + 2;
        }
        if (_ch == 502) {
            return _ch -97;
        }
        if (_ch == 503) {
            return _ch -56;
        }
        if (_ch == 544) {
            return _ch -130;
        }
        if (_ch == 570) {
            return _ch + 10795;
        }
        if (_ch == 573) {
            return _ch -163;
        }
        if (_ch == 574) {
            return _ch + 10792;
        }
        if (_ch == 579) {
            return _ch -195;
        }
        if (_ch == 580) {
            return _ch + 69;
        }
        if (_ch == 581) {
            return _ch + 71;
        }
        if (_ch == 902) {
            return _ch + 38;
        }
        if (_ch == 908) {
            return _ch + 64;
        }
        if (_ch == 975) {
            return _ch + 8;
        }
        if (_ch == 1012) {
            return _ch -60;
        }
        if (_ch == 1017) {
            return _ch -7;
        }
        if (_ch == 1216) {
            return _ch + 15;
        }
        if (_ch == 4295) {
            return _ch + 7264;
        }
        if (_ch == 4301) {
            return _ch + 7264;
        }
        if (_ch == 7838) {
            return _ch -7615;
        }
        if (_ch == 8025) {
            return _ch -8;
        }
        if (_ch == 8027) {
            return _ch -8;
        }
        if (_ch == 8029) {
            return _ch -8;
        }
        if (_ch == 8031) {
            return _ch -8;
        }
        if (_ch == 8172) {
            return _ch -7;
        }
        if (_ch == 8486) {
            return _ch -7517;
        }
        if (_ch == 8490) {
            return _ch -8383;
        }
        if (_ch == 8491) {
            return _ch -8262;
        }
        if (_ch == 8498) {
            return _ch + 28;
        }
        if (_ch == 11362) {
            return _ch -10743;
        }
        if (_ch == 11363) {
            return _ch -3814;
        }
        if (_ch == 11364) {
            return _ch -10727;
        }
        if (_ch == 11373) {
            return _ch -10780;
        }
        if (_ch == 11374) {
            return _ch -10749;
        }
        if (_ch == 11375) {
            return _ch -10783;
        }
        if (_ch == 11376) {
            return _ch -10782;
        }
        if (_ch == 42877) {
            return _ch -35332;
        }
        if (_ch == 42893) {
            return _ch -42280;
        }
        if (_ch == 42922) {
            return _ch -42308;
        }
        return toLowerCaseDefaultSeven(_ch);
    }

    private static int toLowerCaseDefaultSeven(char _ch) {
        if (_ch % 2 == 0) {
            if (_ch >= 256 && _ch <= 302) {
                return _ch + 1;
            }
            if (_ch >= 306 && _ch <= 310) {
                return _ch + 1;
            }
            if (_ch >= 330 && _ch <= 374) {
                return _ch + 1;
            }
            if (_ch >= 386 && _ch <= 388) {
                return _ch + 1;
            }
            if (_ch >= 416 && _ch <= 420) {
                return _ch + 1;
            }
            if (_ch >= 478 && _ch <= 494) {
                return _ch + 1;
            }
            if (_ch >= 498 && _ch <= 500) {
                return _ch + 1;
            }
            if (_ch >= 504 && _ch <= 542) {
                return _ch + 1;
            }
            if (_ch >= 546 && _ch <= 562) {
                return _ch + 1;
            }
            if (_ch >= 582 && _ch <= 590) {
                return _ch + 1;
            }
            if (_ch >= 880 && _ch <= 882) {
                return _ch + 1;
            }
            if (_ch >= 984 && _ch <= 1006) {
                return _ch + 1;
            }
            if (_ch >= 1120 && _ch <= 1152) {
                return _ch + 1;
            }
            if (_ch >= 1162 && _ch <= 1214) {
                return _ch + 1;
            }
            if (_ch >= 1232 && _ch <= 1318) {
                return _ch + 1;
            }
            if (_ch >= 7680 && _ch <= 7828) {
                return _ch + 1;
            }
            if (_ch >= 7840 && _ch <= 7934) {
                return _ch + 1;
            }
            if (_ch >= 11392 && _ch <= 11490) {
                return _ch + 1;
            }
            if (_ch >= 42560 && _ch <= 42604) {
                return _ch + 1;
            }
            if (_ch >= 42624 && _ch <= 42646) {
                return _ch + 1;
            }
            if (_ch >= 42786 && _ch <= 42798) {
                return _ch + 1;
            }
            if (_ch >= 42802 && _ch <= 42862) {
                return _ch + 1;
            }
            if (_ch >= 42878 && _ch <= 42886) {
                return _ch + 1;
            }
            if (_ch >= 42896 && _ch <= 42898) {
                return _ch + 1;
            }
            if (_ch >= 42912 && _ch <= 42920) {
                return _ch + 1;
            }
        } else {
            if (_ch >= 313 && _ch <= 327) {
                return _ch + 1;
            }
            if (_ch >= 377 && _ch <= 381) {
                return _ch + 1;
            }
            if (_ch >= 435 && _ch <= 437) {
                return _ch + 1;
            }
            if (_ch >= 459 && _ch <= 475) {
                return _ch + 1;
            }
            if (_ch >= 1217 && _ch <= 1229) {
                return _ch + 1;
            }
            if (_ch >= 11367 && _ch <= 11371) {
                return _ch + 1;
            }
            if (_ch >= 11499 && _ch <= 11501) {
                return _ch + 1;
            }
            if (_ch >= 42873 && _ch <= 42875) {
                return _ch + 1;
            }
        }

        return toLowerCaseDefaultSix(_ch);
    }

    private static int toLowerCaseDefaultSix(char _ch) {
        if (_ch >= 65 && _ch <= 90) {
            return _ch + 32;
        }
        if (_ch >= 192 && _ch <= 214) {
            return _ch + 32;
        }
        if (_ch >= 216 && _ch <= 222) {
            return _ch + 32;
        }
        if (_ch >= 393 && _ch <= 394) {
            return _ch + 205;
        }
        if (_ch >= 433 && _ch <= 434) {
            return _ch + 217;
        }
        if (_ch >= 904 && _ch <= 906) {
            return _ch + 37;
        }
        if (_ch >= 910 && _ch <= 911) {
            return _ch + 63;
        }
        if (_ch >= 913 && _ch <= 929) {
            return _ch + 32;
        }
        if (_ch >= 931 && _ch <= 939) {
            return _ch + 32;
        }
        if (_ch >= 1021 && _ch <= 1023) {
            return _ch -130;
        }
        if (_ch >= 1024 && _ch <= 1039) {
            return _ch + 80;
        }
        if (_ch >= 1040 && _ch <= 1071) {
            return _ch + 32;
        }
        if (_ch >= 1329 && _ch <= 1366) {
            return _ch + 48;
        }
        if (_ch >= 4256 && _ch <= 4293) {
            return _ch + 7264;
        }
        if (_ch >= 7944 && _ch <= 7951) {
            return _ch -8;
        }
        if (_ch >= 7960 && _ch <= 7965) {
            return _ch -8;
        }
        if (_ch >= 7976 && _ch <= 7983) {
            return _ch -8;
        }
        if (_ch >= 7992 && _ch <= 7999) {
            return _ch -8;
        }
        if (_ch >= 8008 && _ch <= 8013) {
            return _ch -8;
        }
        if (_ch >= 8040 && _ch <= 8047) {
            return _ch -8;
        }
        if (_ch >= 8120 && _ch <= 8121) {
            return _ch -8;
        }
        if (_ch >= 8122 && _ch <= 8123) {
            return _ch -74;
        }
        if (_ch >= 8136 && _ch <= 8139) {
            return _ch -86;
        }
        if (_ch >= 8152 && _ch <= 8153) {
            return _ch -8;
        }
        if (_ch >= 8154 && _ch <= 8155) {
            return _ch -100;
        }
        if (_ch >= 8168 && _ch <= 8169) {
            return _ch -8;
        }
        if (_ch >= 8170 && _ch <= 8171) {
            return _ch -112;
        }
        if (_ch >= 8184 && _ch <= 8185) {
            return _ch -128;
        }
        if (_ch >= 8186 && _ch <= 8187) {
            return _ch -126;
        }
        if (_ch >= 11264 && _ch <= 11310) {
            return _ch + 48;
        }
        if (_ch >= 11390 && _ch <= 11391) {
            return _ch -10815;
        }
        if (_ch >= 65313 && _ch <= 65338) {
            return _ch + 32;
        }
        return _ch;
    }


    private static int toLowerCaseIntCheck(char _ch) {
        return toLowerCheckDefFive(_ch);
    }

    private static int toLowerCheckDefThree(char _ch) {
        if (_ch == 571) {
            return _ch + 1;
        }
        if (_ch == 577) {
            return _ch + 1;
        }
        if (_ch == 886) {
            return _ch + 1;
        }
        if (_ch == 1015) {
            return _ch + 1;
        }
        if (_ch == 1018) {
            return _ch + 1;
        }
        if (_ch == 8579) {
            return _ch + 1;
        }
        if (_ch == 11360) {
            return _ch + 1;
        }
        if (_ch == 11378) {
            return _ch + 1;
        }
        if (_ch == 11381) {
            return _ch + 1;
        }
        if (_ch == 11506) {
            return _ch + 1;
        }
        if (_ch == 42891) {
            return _ch + 1;
        }
        if (_ch == 304) {
            return _ch -199;
        }
        if (_ch == 376) {
            return _ch -121;
        }
        if (_ch == 385) {
            return _ch + 210;
        }
        if (_ch == 390) {
            return _ch + 206;
        }
        if (_ch == 398) {
            return _ch + 79;
        }
        if (_ch == 399) {
            return _ch + 202;
        }
        if (_ch == 400) {
            return _ch + 203;
        }
        if (_ch == 403) {
            return _ch + 205;
        }
        if (_ch == 404) {
            return _ch + 207;
        }
        if (_ch == 406) {
            return _ch + 211;
        }
        if (_ch == 407) {
            return _ch + 209;
        }
        if (_ch == 412) {
            return _ch + 211;
        }
        if (_ch == 413) {
            return _ch + 213;
        }
        if (_ch == 415) {
            return _ch + 214;
        }
        if (_ch == 422) {
            return _ch + 218;
        }
        if (_ch == 425) {
            return _ch + 218;
        }
        if (_ch == 430) {
            return _ch + 218;
        }
        if (_ch == 439) {
            return _ch + 219;
        }
        if (_ch == 452) {
            return _ch + 2;
        }
        if (_ch == 455) {
            return _ch + 2;
        }
        if (_ch == 458) {
            return _ch + 2;
        }
        if (_ch == 497) {
            return _ch + 2;
        }
        if (_ch == 502) {
            return _ch -97;
        }
        if (_ch == 503) {
            return _ch -56;
        }
        if (_ch == 544) {
            return _ch -130;
        }
        if (_ch == 570) {
            return _ch + 10795;
        }
        if (_ch == 573) {
            return _ch -163;
        }
        if (_ch == 574) {
            return _ch + 10792;
        }
        if (_ch == 579) {
            return _ch -195;
        }
        if (_ch == 580) {
            return _ch + 69;
        }
        if (_ch == 581) {
            return _ch + 71;
        }
        if (_ch == 902) {
            return _ch + 38;
        }
        if (_ch == 908) {
            return _ch + 64;
        }
        if (_ch == 975) {
            return _ch + 8;
        }
        if (_ch == 1012) {
            return _ch -60;
        }
        if (_ch == 1017) {
            return _ch -7;
        }
        if (_ch == 1216) {
            return _ch + 15;
        }
        if (_ch == 4295) {
            return _ch + 7264;
        }
        if (_ch == 4301) {
            return _ch + 7264;
        }
        if (_ch == 7838) {
            return _ch -7615;
        }
        if (_ch == 8025) {
            return _ch -8;
        }
        if (_ch == 8027) {
            return _ch -8;
        }
        if (_ch == 8029) {
            return _ch -8;
        }
        if (_ch == 8031) {
            return _ch -8;
        }
        if (_ch == 8124) {
            return _ch -9;
        }
        if (_ch == 8140) {
            return _ch -9;
        }
        if (_ch == 8172) {
            return _ch -7;
        }
        if (_ch == 8188) {
            return _ch -9;
        }
        if (_ch == 8486) {
            return _ch -7517;
        }
        if (_ch == 8490) {
            return _ch -8383;
        }
        if (_ch == 8491) {
            return _ch -8262;
        }
        if (_ch == 8498) {
            return _ch + 28;
        }
        if (_ch == 11362) {
            return _ch -10743;
        }
        if (_ch == 11363) {
            return _ch -3814;
        }
        if (_ch == 11364) {
            return _ch -10727;
        }
        if (_ch == 11373) {
            return _ch -10780;
        }
        if (_ch == 11374) {
            return _ch -10749;
        }
        if (_ch == 11375) {
            return _ch -10783;
        }
        if (_ch == 11376) {
            return _ch -10782;
        }
        if (_ch == 42877) {
            return _ch -35332;
        }
        if (_ch == 42893) {
            return _ch -42280;
        }
        if (_ch == 42922) {
            return _ch -42308;
        }
        return toLowerCheckDefTwo(_ch);
    }

    private static int toLowerCheckDef(char _ch) {
        if (_ch >= 65 && _ch <= 90) {
            return _ch + 32;
        }
        if (_ch >= 192 && _ch <= 214) {
            return _ch + 32;
        }
        if (_ch >= 216 && _ch <= 222) {
            return _ch + 32;
        }
        if (_ch >= 393 && _ch <= 394) {
            return _ch + 205;
        }
        if (_ch >= 433 && _ch <= 434) {
            return _ch + 217;
        }
        if (_ch >= 904 && _ch <= 906) {
            return _ch + 37;
        }
        if (_ch >= 910 && _ch <= 911) {
            return _ch + 63;
        }
        if (_ch >= 913 && _ch <= 929) {
            return _ch + 32;
        }
        if (_ch >= 931 && _ch <= 939) {
            return _ch + 32;
        }
        if (_ch >= 1021 && _ch <= 1023) {
            return _ch -130;
        }
        if (_ch >= 1024 && _ch <= 1039) {
            return _ch + 80;
        }
        if (_ch >= 1040 && _ch <= 1071) {
            return _ch + 32;
        }
        if (_ch >= 1329 && _ch <= 1366) {
            return _ch + 48;
        }
        if (_ch >= 4256 && _ch <= 4293) {
            return _ch + 7264;
        }
        if (_ch >= 7944 && _ch <= 7951) {
            return _ch -8;
        }
        if (_ch >= 7960 && _ch <= 7965) {
            return _ch -8;
        }
        if (_ch >= 7976 && _ch <= 7983) {
            return _ch -8;
        }
        if (_ch >= 7992 && _ch <= 7999) {
            return _ch -8;
        }
        if (_ch >= 8008 && _ch <= 8013) {
            return _ch -8;
        }
        if (_ch >= 8040 && _ch <= 8047) {
            return _ch -8;
        }
        if (_ch >= 8072 && _ch <= 8079) {
            return _ch -8;
        }
        if (_ch >= 8088 && _ch <= 8095) {
            return _ch -8;
        }
        if (_ch >= 8104 && _ch <= 8111) {
            return _ch -8;
        }
        if (_ch >= 8120 && _ch <= 8121) {
            return _ch -8;
        }
        if (_ch >= 8122 && _ch <= 8123) {
            return _ch -74;
        }
        if (_ch >= 8136 && _ch <= 8139) {
            return _ch -86;
        }
        if (_ch >= 8152 && _ch <= 8153) {
            return _ch -8;
        }
        if (_ch >= 8154 && _ch <= 8155) {
            return _ch -100;
        }
        if (_ch >= 8168 && _ch <= 8169) {
            return _ch -8;
        }
        if (_ch >= 8170 && _ch <= 8171) {
            return _ch -112;
        }
        if (_ch >= 8184 && _ch <= 8185) {
            return _ch -128;
        }
        if (_ch >= 8186 && _ch <= 8187) {
            return _ch -126;
        }
        if (_ch >= 11264 && _ch <= 11310) {
            return _ch + 48;
        }
        if (_ch >= 11390 && _ch <= 11391) {
            return _ch -10815;
        }
        if (_ch >= 65313 && _ch <= 65338) {
            return _ch + 32;
        }
        return _ch;
    }

    private static int toUpperCaseInt(char _ch) {
        if (isUnassigned(_ch)) {
            return _ch;
        }
        if (isOtherSpace(_ch)) {
            return _ch;
        }
        if (isDigit(_ch)) {
            return _ch;
        }
        if (isOtherDigit(_ch)) {
            return _ch;
        }
        if (isRomanDigits(_ch)) {
            if (_ch >= 8544 + 16) {
                return _ch-16;
            }
            return _ch;
        }
        if (isSensibleOtherLetter(_ch)) {
            if (_ch >= 9398 + 26) {
                return _ch-26;
            }
            return _ch;
        }
        if (StringUtil.isOtherMathSymbol(_ch)) {
            return _ch;
        }
        if (_ch == 383) {
            return 83;
        }
        if (_ch == 410) {
            return 573;
        }
        if (_ch == 457) {
            return 455;
        }
        if (_ch == 454) {
            return 452;
        }
        if (_ch == 460) {
            return 458;
        }
        if (_ch == 477) {
            return 398;
        }
        if (_ch == 499) {
            return 497;
        }
        if (_ch == 592) {
            return 11375;
        }
        if (_ch == 1008) {
            return 922;
        }
        if (_ch == 1231) {
            return 1216;
        }
        if (_ch == 7936) {
            return 7944;
        }
        if (_ch == 392) {
            return _ch -1;
        }
        if (_ch == 396) {
            return _ch -1;
        }
        if (_ch == 402) {
            return _ch -1;
        }
        if (_ch == 409) {
            return _ch -1;
        }
        if (_ch == 424) {
            return _ch -1;
        }
        if (_ch == 429) {
            return _ch -1;
        }
        if (_ch == 432) {
            return _ch -1;
        }
        if (_ch == 441) {
            return _ch -1;
        }
        if (_ch == 445) {
            return _ch -1;
        }
        if (_ch == 453) {
            return _ch -1;
        }
        if (_ch == 456) {
            return _ch -1;
        }
        return toUpperCaseDefThree(_ch);
    }

    private static int toUpperCaseDefThree(char _ch) {
        if (_ch == 613) {
            return _ch + 42280;
        }
        if (_ch == 614) {
            return _ch + 42308;
        }
        if (_ch == 616) {
            return _ch -209;
        }
        if (_ch == 617) {
            return _ch -211;
        }
        if (_ch == 619) {
            return _ch + 10743;
        }
        if (_ch == 623) {
            return _ch -211;
        }
        if (_ch == 643) {
            return _ch -218;
        }
        if (_ch == 658) {
            return _ch -219;
        }
        if (_ch == 7545) {
            return _ch + 35332;
        }
        if (_ch == 7549) {
            return _ch + 3814;
        }
        if (_ch == 459) {
            return _ch -1;
        }
        if (_ch == 498) {
            return _ch -1;
        }
        return toUpperCaseDefFour(_ch);
    }


    private static int toUpperCaseIntCheck(char _ch) {
        if (isRomanDigits(_ch)) {
            if (_ch >= 8544 + 16) {
                return _ch-16;
            }
            return _ch;
        }
        if (isSensibleOtherLetter(_ch)) {
            if (_ch >= 9398 + 26) {
                return _ch-26;
            }
            return _ch;
        }
        if (StringUtil.isOtherMathSymbol(_ch)) {
            return _ch;
        }
        if (_ch == 383) {
            return 83;
        }
        if (_ch == 410) {
            return 573;
        }
        if (_ch == 457) {
            return 455;
        }
        if (_ch == 454) {
            return 452;
        }
        if (_ch == 460) {
            return 458;
        }
        if (_ch == 477) {
            return 398;
        }
        if (_ch == 499) {
            return 497;
        }
        if (_ch == 592) {
            return 11375;
        }
        if (_ch == 1008) {
            return 922;
        }
        if (_ch == 1231) {
            return 1216;
        }
        if (_ch == 7936) {
            return 7944;
        }
        if (_ch == 392) {
            return _ch -1;
        }
        if (_ch == 396) {
            return _ch -1;
        }
        if (_ch == 402) {
            return _ch -1;
        }
        if (_ch == 409) {
            return _ch -1;
        }
        if (_ch == 424) {
            return _ch -1;
        }
        if (_ch == 429) {
            return _ch -1;
        }
        if (_ch == 432) {
            return _ch -1;
        }
        if (_ch == 441) {
            return _ch -1;
        }
        if (_ch == 445) {
            return _ch -1;
        }
        if (_ch == 453) {
            return _ch -1;
        }
        if (_ch == 456) {
            return _ch -1;
        }
        return toUpperCaseDefThree(_ch);
    }

    private static int toUpperCaseDefTwo(char _ch) {
        if (_ch % 2 == 0) {
            if (_ch >= 314 && _ch <= 328) {
                return _ch -1;
            }
            if (_ch >= 378 && _ch <= 382) {
                return _ch -1;
            }
            if (_ch >= 436 && _ch <= 438) {
                return _ch -1;
            }
            if (_ch >= 462 && _ch <= 476) {
                return _ch -1;
            }
            if (_ch >= 1218 && _ch <= 1230) {
                return _ch -1;
            }
            if (_ch >= 11368 && _ch <= 11372) {
                return _ch -1;
            }
            if (_ch >= 11500 && _ch <= 11502) {
                return _ch -1;
            }
            if (_ch >= 42874 && _ch <= 42876) {
                return _ch -1;
            }
        } else {
            if (_ch >= 257 && _ch <= 303) {
                return _ch -1;
            }
            if (_ch >= 307 && _ch <= 311) {
                return _ch -1;
            }
            if (_ch >= 331 && _ch <= 375) {
                return _ch -1;
            }
            if (_ch >= 387 && _ch <= 389) {
                return _ch -1;
            }
            if (_ch >= 417 && _ch <= 421) {
                return _ch -1;
            }
            if (_ch >= 479 && _ch <= 495) {
                return _ch -1;
            }
            if (_ch >= 505 && _ch <= 543) {
                return _ch -1;
            }
            if (_ch >= 547 && _ch <= 563) {
                return _ch -1;
            }
            if (_ch >= 583 && _ch <= 591) {
                return _ch -1;
            }
            if (_ch >= 881 && _ch <= 883) {
                return _ch -1;
            }
            if (_ch >= 985 && _ch <= 1007) {
                return _ch -1;
            }
            if (_ch >= 1121 && _ch <= 1153) {
                return _ch -1;
            }
            if (_ch >= 1163 && _ch <= 1215) {
                return _ch -1;
            }
            if (_ch >= 1233 && _ch <= 1319) {
                return _ch -1;
            }
            if (_ch >= 7681 && _ch <= 7829) {
                return _ch -1;
            }
            if (_ch >= 7841 && _ch <= 7935) {
                return _ch -1;
            }
            if (_ch >= 11393 && _ch <= 11491) {
                return _ch -1;
            }
            if (_ch >= 42561 && _ch <= 42605) {
                return _ch -1;
            }
            if (_ch >= 42625 && _ch <= 42647) {
                return _ch -1;
            }
            if (_ch >= 42787 && _ch <= 42799) {
                return _ch -1;
            }
            if (_ch >= 42803 && _ch <= 42863) {
                return _ch -1;
            }
            if (_ch >= 42879 && _ch <= 42887) {
                return _ch -1;
            }
            if (_ch >= 42897 && _ch <= 42899) {
                return _ch -1;
            }
            if (_ch >= 42913 && _ch <= 42921) {
                return _ch -1;
            }
        }
        return toUpperCaseDef(_ch);
    }

    private static int toUpperCaseIntCheckLow(char _ch) {
        if (isRomanDigits(_ch)) {
            return _ch-16;
        }
        if (isSensibleOtherLetter(_ch)) {
            return _ch-26;
        }
        if (StringUtil.isOtherMathSymbol(_ch)) {
            return _ch;
        }
        if (_ch == 383) {
            return 83;
        }
        if (_ch == 410) {
            return 573;
        }
        if (_ch == 457) {
            return 455;
        }
        if (_ch == 454) {
            return 452;
        }
        if (_ch == 460) {
            return 458;
        }
        if (_ch == 477) {
            return 398;
        }
        if (_ch == 499) {
            return 497;
        }
        if (_ch == 592) {
            return 11375;
        }
        if (_ch == 1008) {
            return 922;
        }
        if (_ch == 1231) {
            return 1216;
        }
        if (_ch == 7936) {
            return 7944;
        }
        if (_ch == 392) {
            return _ch -1;
        }
        if (_ch == 396) {
            return _ch -1;
        }
        if (_ch == 402) {
            return _ch -1;
        }
        if (_ch == 409) {
            return _ch -1;
        }
        if (_ch == 424) {
            return _ch -1;
        }
        if (_ch == 429) {
            return _ch -1;
        }
        if (_ch == 432) {
            return _ch -1;
        }
        if (_ch == 441) {
            return _ch -1;
        }
        if (_ch == 445) {
            return _ch -1;
        }
        return toUpperCaseDefFour(_ch);
    }

    private static int toUpperCaseDefFour(char _ch) {
        if (_ch == 501) {
            return _ch -1;
        }
        if (_ch == 572) {
            return _ch -1;
        }
        if (_ch == 578) {
            return _ch -1;
        }
        if (_ch == 887) {
            return _ch -1;
        }
        if (_ch == 1016) {
            return _ch -1;
        }
        if (_ch == 1019) {
            return _ch -1;
        }
        if (_ch == 8580) {
            return _ch -1;
        }
        if (_ch == 11361) {
            return _ch -1;
        }
        if (_ch == 11379) {
            return _ch -1;
        }
        if (_ch == 11382) {
            return _ch -1;
        }
        if (_ch == 11507) {
            return _ch -1;
        }
        if (_ch == 42892) {
            return _ch -1;
        }
        if (_ch == 181) {
            return _ch + 743;
        }
        if (_ch == 255) {
            return _ch + 121;
        }
        if (_ch == 305) {
            return _ch -232;
        }
        if (_ch == 384) {
            return _ch + 195;
        }
        if (_ch == 405) {
            return _ch + 97;
        }
        if (_ch == 414) {
            return _ch + 130;
        }
        if (_ch == 447) {
            return _ch + 56;
        }
        if (_ch == 593) {
            return _ch + 10780;
        }
        if (_ch == 594) {
            return _ch + 10782;
        }
        if (_ch == 595) {
            return _ch -210;
        }
        if (_ch == 596) {
            return _ch -206;
        }
        if (_ch == 601) {
            return _ch -202;
        }
        if (_ch == 603) {
            return _ch -203;
        }
        if (_ch == 608) {
            return _ch -205;
        }
        if (_ch == 611) {
            return _ch -207;
        }
        if (_ch == 625) {
            return _ch + 10749;
        }
        if (_ch == 626) {
            return _ch -213;
        }
        if (_ch == 629) {
            return _ch -214;
        }
        if (_ch == 637) {
            return _ch + 10727;
        }
        if (_ch == 640) {
            return _ch -218;
        }
        if (_ch == 648) {
            return _ch -218;
        }
        if (_ch == 649) {
            return _ch -69;
        }
        if (_ch == 652) {
            return _ch -71;
        }
        if (_ch == 837) {
            return _ch + 84;
        }
        if (_ch == 940) {
            return _ch -38;
        }
        if (_ch == 962) {
            return _ch -31;
        }
        if (_ch == 972) {
            return _ch -64;
        }
        if (_ch == 976) {
            return _ch -62;
        }
        if (_ch == 977) {
            return _ch -57;
        }
        if (_ch == 981) {
            return _ch -47;
        }
        if (_ch == 982) {
            return _ch -54;
        }
        if (_ch == 983) {
            return _ch -8;
        }
        if (_ch == 1009) {
            return _ch -80;
        }
        if (_ch == 1010) {
            return _ch + 7;
        }
        if (_ch == 1013) {
            return _ch -96;
        }
        if (_ch == 7835) {
            return _ch -59;
        }
        if (_ch == 8017) {
            return _ch + 8;
        }
        if (_ch == 8019) {
            return _ch + 8;
        }
        if (_ch == 8021) {
            return _ch + 8;
        }
        if (_ch == 8023) {
            return _ch + 8;
        }
        if (_ch == 8115) {
            return _ch + 9;
        }
        if (_ch == 8126) {
            return _ch -7205;
        }
        if (_ch == 8131) {
            return _ch + 9;
        }
        if (_ch == 8165) {
            return _ch + 7;
        }
        if (_ch == 8179) {
            return _ch + 9;
        }
        if (_ch == 8526) {
            return _ch -28;
        }
        if (_ch == 11365) {
            return _ch -10795;
        }
        if (_ch == 11366) {
            return _ch -10792;
        }
        if (_ch == 11559) {
            return _ch -7264;
        }
        if (_ch == 11565) {
            return _ch -7264;
        }
        return toUpperCaseDefTwo(_ch);
    }

    private static int toUpperCaseDef(char _ch) {
        if (_ch >= 97 && _ch <= 122) {
            return _ch -32;
        }
        if (_ch >= 224 && _ch <= 246) {
            return _ch -32;
        }
        if (_ch >= 248 && _ch <= 254) {
            return _ch -32;
        }
        if (_ch >= 575 && _ch <= 576) {
            return _ch + 10815;
        }
        if (_ch >= 598 && _ch <= 599) {
            return _ch -205;
        }
        if (_ch >= 650 && _ch <= 651) {
            return _ch -217;
        }
        if (_ch >= 891 && _ch <= 893) {
            return _ch + 130;
        }
        if (_ch >= 941 && _ch <= 943) {
            return _ch -37;
        }
        if (_ch >= 945 && _ch <= 961) {
            return _ch -32;
        }
        if (_ch >= 963 && _ch <= 971) {
            return _ch -32;
        }
        if (_ch >= 973 && _ch <= 974) {
            return _ch -63;
        }
        if (_ch >= 1072 && _ch <= 1103) {
            return _ch -32;
        }
        if (_ch >= 1104 && _ch <= 1119) {
            return _ch -80;
        }
        if (_ch >= 1377 && _ch <= 1414) {
            return _ch -48;
        }
        if (_ch >= 7937 && _ch <= 7943) {
            return _ch + 8;
        }
        if (_ch >= 7952 && _ch <= 7957) {
            return _ch + 8;
        }
        if (_ch >= 7968 && _ch <= 7975) {
            return _ch + 8;
        }
        if (_ch >= 7984 && _ch <= 7991) {
            return _ch + 8;
        }
        if (_ch >= 8000 && _ch <= 8005) {
            return _ch + 8;
        }
        if (_ch >= 8032 && _ch <= 8039) {
            return _ch + 8;
        }
        if (_ch >= 8048 && _ch <= 8049) {
            return _ch + 74;
        }
        if (_ch >= 8050 && _ch <= 8053) {
            return _ch + 86;
        }
        if (_ch >= 8054 && _ch <= 8055) {
            return _ch + 100;
        }
        if (_ch >= 8056 && _ch <= 8057) {
            return _ch + 128;
        }
        if (_ch >= 8058 && _ch <= 8059) {
            return _ch + 112;
        }
        if (_ch >= 8060 && _ch <= 8061) {
            return _ch + 126;
        }
        if (_ch >= 8064 && _ch <= 8071) {
            return _ch + 8;
        }
        if (_ch >= 8080 && _ch <= 8087) {
            return _ch + 8;
        }
        if (_ch >= 8096 && _ch <= 8103) {
            return _ch + 8;
        }
        if (_ch >= 8112 && _ch <= 8113) {
            return _ch + 8;
        }
        if (_ch >= 8144 && _ch <= 8145) {
            return _ch + 8;
        }
        if (_ch >= 8160 && _ch <= 8161) {
            return _ch + 8;
        }
        if (_ch >= 11312 && _ch <= 11358) {
            return _ch -48;
        }
        if (_ch >= 11520 && _ch <= 11557) {
            return _ch -7264;
        }
        if (_ch >= 65345 && _ch <= 65370) {
            return _ch -32;
        }
        return _ch;
    }
}
