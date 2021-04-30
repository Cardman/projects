package code.expressionlanguage.common;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ParseLinesArgUtil {
    private boolean escaped;
    private ParseLinesArgUtil() {
    }
    public static CustList<CommentDelimiters> buildComments(String _line) {
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
            String begin_ = ParseLinesArgUtil.parseValue(parts_.first());
            String end_ = ParseLinesArgUtil.parseValue(parts_.last());
            comments_.add(new CommentDelimiters(begin_,new StringList(end_)));
        }
        return comments_;
    }
    public static void buildMap(StringBuilder _parts, StringMap<String> _map) {
        if (_parts.length() > 0) {
            StringList infos_ = StringUtil.splitChars(_parts.toString(),',');
            for (String l: infos_) {
                int sep_ = l.indexOf('=');
                if (sep_ < 0) {
                    continue;
                }
                String key_ = l.substring(0, sep_).trim();
                String value_ = StringUtil.removeAllSpaces(l.substring(sep_ +1));
                value_ = ParseLinesArgUtil.parseValue(value_);
                _map.put(key_,value_);
            }
        }
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
    public static StringList parseLineArg(String _line) {
        StringList args_ = new StringList();
        StringBuilder arg_ = new StringBuilder();
        int len_ = _line.length();
        int i_ = 0;
        ParseLinesArgUtil state_ = new ParseLinesArgUtil();
        while (i_ < len_) {
            char cur_ = _line.charAt(i_);
            i_+=state_.incrParseLineArg(_line,cur_,i_,arg_,args_)+1;
        }
        args_.add(arg_.toString());
        return args_;
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
    public static String parseValue(String _line) {
        StringBuilder arg_ = new StringBuilder();
        int len_ = _line.length();
        int i_ = 0;
        ParseLinesArgUtil state_ = new ParseLinesArgUtil();
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
        if (_ch == 'n') {
            _arg.append('\n');
            return 0;
        }
        if (_ch == 'e') {
            _arg.append(' ');
            return 0;
        }
        if (_ch == 't') {
            _arg.append('\t');
            return 0;
        }
        return specEsc(_line, _ch, _i, _arg);
    }

    private static int specEsc(String _line, char _ch, int _i, StringBuilder _arg) {
        int len_ = _line.length();
        if (_ch == 'c' && _i + 2 < len_) {
            String sub_ = _line.substring(_i + 1, _i + 3);
            LongInfo char_ = NumParsers.parseLong(sub_, 16);
            if (char_.isValid()) {
                long value_ = char_.getValue();
                if (value_ >= 0 && value_ < ' ') {
                    char ch_ = (char) value_;
                    _arg.append(ch_);
                    return 2;
                }
            }
        }
        if (_ch == 'u' && _i + 4 < len_) {
            String sub_ = _line.substring(_i + 1, _i + 5);
            LongInfo char_ = NumParsers.parseLong(sub_, 16);
            if (char_.isValid()) {
                long value_ = char_.getValue();
                if (value_ >= 0) {
                    char ch_ = (char) value_;
                    _arg.append(ch_);
                    return 4;
                }
            }
        }
        _arg.append(_ch);
        return 0;
    }
}
