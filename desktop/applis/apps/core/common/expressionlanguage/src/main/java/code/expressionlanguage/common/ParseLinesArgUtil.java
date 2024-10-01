package code.expressionlanguage.common;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.instr.*;
import code.util.CharList;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
//import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ParseLinesArgUtil {
    private boolean escaped;
    private final String unicode;
    private final String newLine;
    private final String space;
    private final String tab;
    private final String character;
    private final String digitsSupp;
    private ParseLinesArgUtil(StringMap<String> _messages) {
        unicode = _messages.getVal(MessagesCdmBase.UNICODE);
        newLine = _messages.getVal(MessagesCdmBase.NEW_LINE);
        space = _messages.getVal(MessagesCdmBase.SPACE);
        tab = _messages.getVal(MessagesCdmBase.TAB);
        character = _messages.getVal(MessagesCdmBase.CHARACTER);
        digitsSupp = _messages.getVal(MessagesCdmBase.DIGITS_SUPP);
    }
    public static CustList<CommentDelimiters> buildComments(StringMap<String> _messages,String _line) {
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        for (String c: StringUtil.splitChar(
                _line.trim(),
                ';')) {
            StringList parts_ = StringUtil.splitChar(
                    c.trim(),
                    ',');
            if (parts_.size() <= 1) {
                parts_.clear();
                parts_.add(" ");
                parts_.add(" ");
            }
            String begin_ = ParseLinesArgUtil.parseValue(_messages,parts_.first());
            String end_ = ParseLinesArgUtil.parseValue(_messages,parts_.last());
            comments_.add(new CommentDelimiters(begin_,new StringList(end_)));
        }
        return comments_;
    }
    public static String buildCommentsLine(CustList<CommentDelimiters> _line) {
        char[] escaped_ = CharList.wrapCharArray(';', ',', ' ', '\\', '\n', '\t','\r');
        StringBuilder e_ = new StringBuilder();
        int len_ = _line.size();
        for (int i = 0; i < len_; i++) {
            StringList ends_ = _line.get(i).getEnd();
            exportString(e_,_line.get(i).getBegin(),escaped_);
            e_.append(',');
            exportString(e_, ends_.get(0),escaped_);
            if (i + 1 < len_) {
                e_.append(';');
            }
        }
        return e_.toString();
    }

    public static void buildMap(StringMap<String> _messages,StringBuilder _parts, StringMap<String> _map) {
        if (_parts.length() > 0) {
            StringList infos_ = StringUtil.splitChars(_parts.toString(),',');
            for (String l: infos_) {
                int sep_ = l.indexOf('=');
                if (sep_ < 0) {
                    continue;
                }
                String key_ = l.substring(0, sep_).trim();
                String value_ = StringUtil.removeAllSpaces(l.substring(sep_ +1));
                value_ = ParseLinesArgUtil.parseValue(_messages,value_);
                _map.put(key_,value_);
            }
        }
    }

    public static String buildMapLine(StringMap<String> _map) {
        char[] escaped_ = CharList.wrapCharArray(',', ' ', '\\', '\n', '\t','\r');
        StringBuilder e_ = new StringBuilder();
        int len_ = _map.size();
        for (int i = 0; i < len_; i++) {
            e_.append(_map.getKey(i));
            e_.append('=');
            exportString(e_,_map.getValue(i),escaped_);
            if (i + 1 < len_) {
                e_.append(',');
            }
        }
        return e_.toString();
    }

    public static void buildList(StringBuilder _parts, StringList _list) {
        if (_parts.length() > 0) {
            StringList infos_ = StringUtil.splitChars(_parts.toString(),',');
            for (String l: infos_) {
                String key_ = l.trim();
                if (key_.isEmpty()) {
                    continue;
                }
                _list.add(key_);
            }
        }
    }
    public static StringList parseLineArg(StringMap<String> _messages,String _line) {
        StringList args_ = new StringList();
        StringBuilder arg_ = new StringBuilder();
        int len_ = _line.length();
        int i_ = 0;
        ParseLinesArgUtil state_ = new ParseLinesArgUtil(_messages);
        while (i_ < len_) {
            char cur_ = _line.charAt(i_);
            i_+=state_.incrParseLineArg(_line,cur_,i_,arg_,args_)+1;
        }
        args_.add(arg_.toString());
        return args_;
    }
    public static String exportLineArg(StringList _args) {
        char[] escaped_ = CharList.wrapCharArray(' ', '\\', '\n', '\t','\r');
        StringBuilder e_ = new StringBuilder();
        int len_ = _args.size();
        for (int i = 0; i < len_; i++) {
            exportString(e_,_args.get(i),escaped_);
            if (i + 1 < len_) {
                e_.append(' ');
            }
        }
        return e_.toString();
    }

    private int incrParseLineArg(String _line, char _ch, int _i, StringBuilder _arg, StringList _args) {
        if (!escaped) {
            if (_ch == '\\') {
                escaped = true;
                return 0;
            }
            if (_ch == ' ') {
                _args.add(_arg.toString());
                _arg.delete(0, _arg.length());
                return 0;
            }
            _arg.append(_ch);
            return 0;
        }
        return prEsc(_line, _ch, _i, _arg);
    }

    private static void exportString(StringBuilder _dest,String _current,char... _ch) {
        for (char c: _current.toCharArray()) {
            _dest.append(exportChar(c,_ch));
        }
    }
    private static String exportChar(char _current,char... _ch) {
        for (char c: _ch) {
            if (c == _current) {
                if (c < 32) {
                    return NumParsers.exportChar(c);
                }
                return "\\"+ElResolver.OCTAL_FIRST+"00"+Long.toString(c / 64)+Long.toString((c / 8)%8)+Long.toString(c % 8);
//                String ch_ = StringExpUtil.toGeneHex(c, digitsSupp);
//                if (ch_.length() == 1) {
//                    return "\\"+unicode+"000"+ ch_;
//                }
//                return "\\"+unicode+"00"+ ch_;
            }
        }
        return Character.toString(_current);
    }
//    private static String convert(String _str, String _digitsSupp) {
//        StringBuilder str_ = new StringBuilder();
//        for (char c: _str.toCharArray()) {
//            if (StringExpUtil.isDigit(c)) {
//                str_.append(c);
//            } else {
//                int d_ = c - NumberUtil.MIN_LOW;
//                str_.append(StringDataUtil.toLowerCase(_digitsSupp).charAt(d_));
//            }
//        }
//        return str_.toString();
//    }
    public static String parseValue(StringMap<String> _messages,String _line) {
        StringBuilder arg_ = new StringBuilder();
        int len_ = _line.length();
        int i_ = 0;
        ParseLinesArgUtil state_ = new ParseLinesArgUtil(_messages);
        while (i_ < len_) {
            char cur_ = _line.charAt(i_);
            i_ += state_.incrParseValue(_line,cur_,i_,arg_)+1;
        }
        return arg_.toString();
    }
    private int incrParseValue(String _line, char _ch, int _i, StringBuilder _arg) {
        if (!escaped) {
            if (_ch == '\\') {
                escaped = true;
                return 0;
            }
            _arg.append(_ch);
            return 0;
        }
        return prEsc(_line, _ch, _i, _arg);
    }

    private int prEsc(String _line, char _ch, int _i, StringBuilder _arg) {
        escaped = false;
        if (_line.startsWith(newLine,_i)) {
            _arg.append('\n');
            return newLine.length() - 1;
        }
        if (_line.startsWith(space,_i)) {
            _arg.append(' ');
            return space.length() - 1;
        }
        if (_line.startsWith(tab,_i)) {
            _arg.append('\t');
            return tab.length() - 1;
        }
        return specEsc(_line, _ch, _i, _arg);
    }

    private int specEsc(String _line, char _ch, int _i, StringBuilder _arg) {
        int len_ = _line.length();
        if (_line.startsWith(character,_i) && _i + character.length() + 2 - 1 < len_) {
            String sub_ = _line.substring(_i + character.length(), _i + character.length() + 2);
            LongInfo char_ = parseLong(sub_);
            if (char_.isValid()) {
                long value_ = char_.getValue();
                if (value_ < ' ') {
                    char ch_ = (char) value_;
                    _arg.append(ch_);
                    return 2 + character.length() - 1;
                }
            }
        }
        if (_line.startsWith(unicode,_i) && _i + unicode.length() + 4 - 1 < len_) {
            String sub_ = _line.substring(_i + unicode.length(), _i + unicode.length() + 4);
            LongInfo char_ = parseLong(sub_);
            if (char_.isValid()) {
                long value_ = char_.getValue();
                char ch_ = (char) value_;
                _arg.append(ch_);
                return 4 + unicode.length() - 1;
            }
        }
        if (isOctalPrefix(_ch) && _i + 5 < len_) {
            String sub_ = _line.substring(_i + 1, _i + 6);
            LongInfo char_ = NumParsers.parseLong(sub_,8);
            if (char_.isValid()) {
                long value_ = value(_ch,char_);
                char ch_ = (char) value_;
                _arg.append(ch_);
                return 5;
            }
        }
        _arg.append(_ch);
        return 0;
    }

    private static long value(char _ch, LongInfo _longInfo) {
        if (_ch == ElResolver.OCTAL_LAST) {
            return _longInfo.getValue()+128*256;
        }
        return _longInfo.getValue();
    }

    private static boolean isOctalPrefix(char _ch) {
        return _ch == ElResolver.OCTAL_FIRST || _ch == ElResolver.OCTAL_LAST;
    }

    private LongInfo parseLong(String _string) {
        DefCharacterCaseConverter def_ = new DefCharacterCaseConverter();
        return buildAccLg(_string, def_);
    }

    private LongInfo buildAccLg(String _string, DefCharacterCaseConverter _def) {
        int max_ = _string.length();
        long result_ = 0;
        int i_ = 0;
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            char ch_ = _string.charAt(i_);
            int dig_ = _def.index(digitsSupp, ch_);
            if (dig_ < 0) {
                return new LongInfo();
            }
            result_ *= 16;
            result_ -= dig_;
            i_++;
        }
        return buildLg(result_);
    }
    private static LongInfo buildLg(long _result) {
        return new LongInfo(-_result);
    }
}
