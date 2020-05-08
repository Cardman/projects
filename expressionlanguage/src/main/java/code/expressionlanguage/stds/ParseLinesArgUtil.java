package code.expressionlanguage.stds;

import code.util.StringList;

public final class ParseLinesArgUtil {
    private ParseLinesArgUtil() {
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
