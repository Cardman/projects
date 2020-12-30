package code.expressionlanguage.common;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ParseLinesArgUtil {
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
    public static StringList parseLineArg(String _line) {
        StringList args_ = new StringList();
        StringBuilder arg_ = new StringBuilder();
        int len_ = _line.length();
        int i_ = 0;
        boolean escaped_ = false;
        while (i_ < len_) {
            char cur_ = _line.charAt(i_);
            if (escaped_) {
                escaped_ = false;
                if (cur_ == 'n') {
                    arg_.append('\n');
                    i_++;
                    continue;
                }
                if (cur_ == 'e') {
                    arg_.append(' ');
                    i_++;
                    continue;
                }
                if (cur_ == 't') {
                    arg_.append('\t');
                    i_++;
                    continue;
                }
                if (cur_ == 'c') {
                    if (i_ + 2 < len_) {
                        String sub_ = _line.substring(i_ + 1,i_ + 3);
                        LongInfo char_ = NumParsers.parseLong(sub_, 16);
                        if (char_.isValid()) {
                            long value_ = char_.getValue();
                            if (value_ >= 0 && value_ < ' ') {
                                char ch_ = (char) value_;
                                arg_.append(ch_);
                                i_ += 3;
                                continue;
                            }
                        }
                    }
                }
                if (cur_ == 'u') {
                    if (i_ + 4 < len_) {
                        String sub_ = _line.substring(i_ + 1,i_ + 5);
                        LongInfo char_ = NumParsers.parseLong(sub_, 16);
                        if (char_.isValid()) {
                            long value_ = char_.getValue();
                            if (value_ >= 0) {
                                char ch_ = (char) value_;
                                arg_.append(ch_);
                                i_ += 5;
                                continue;
                            }
                        }
                    }
                }
                arg_.append(cur_);
                i_++;
                continue;
            }
            if (cur_ == '\\') {
                escaped_ = true;
                i_++;
                continue;
            }
            if (cur_ == ' ') {
                args_.add(arg_.toString());
                arg_.delete(0,arg_.length());
                i_++;
                continue;
            }
            arg_.append(cur_);
            i_++;
        }
        args_.add(arg_.toString());
        return args_;
    }
    public static String parseValue(String _line) {
        StringBuilder arg_ = new StringBuilder();
        int len_ = _line.length();
        int i_ = 0;
        boolean escaped_ = false;
        while (i_ < len_) {
            char cur_ = _line.charAt(i_);
            if (escaped_) {
                escaped_ = false;
                if (cur_ == 'n') {
                    arg_.append('\n');
                    i_++;
                    continue;
                }
                if (cur_ == 'e') {
                    arg_.append(' ');
                    i_++;
                    continue;
                }
                if (cur_ == 't') {
                    arg_.append('\t');
                    i_++;
                    continue;
                }
                if (cur_ == 'c') {
                    if (i_ + 2 < len_) {
                        String sub_ = _line.substring(i_ + 1,i_ + 3);
                        LongInfo char_ = NumParsers.parseLong(sub_, 16);
                        if (char_.isValid()) {
                            long value_ = char_.getValue();
                            if (value_ >= 0 && value_ < ' ') {
                                char ch_ = (char) value_;
                                arg_.append(ch_);
                                i_ += 3;
                                continue;
                            }
                        }
                    }
                }
                if (cur_ == 'u') {
                    if (i_ + 4 < len_) {
                        String sub_ = _line.substring(i_ + 1,i_ + 5);
                        LongInfo char_ = NumParsers.parseLong(sub_, 16);
                        if (char_.isValid()) {
                            long value_ = char_.getValue();
                            if (value_ >= 0) {
                                char ch_ = (char) value_;
                                arg_.append(ch_);
                                i_ += 5;
                                continue;
                            }
                        }
                    }
                }
                arg_.append(cur_);
                i_++;
                continue;
            }
            if (cur_ == '\\') {
                escaped_ = true;
                i_++;
                continue;
            }
            arg_.append(cur_);
            i_++;
        }
        return arg_.toString();
    }
}
