package code.util.core;

import code.util.ints.UniformingString;

public final class DefaultUniformingString implements UniformingString {
    @Override
    public String apply(String _input) {
        int len_ = _input.length();
        int i_ = 0;
        StringBuilder str_ = new StringBuilder();
        while (i_ < len_) {
            char ch_ = _input.charAt(i_);
            if (ch_ == '\r') {
                if (i_ + 1 < len_) {
                    if (_input.charAt(i_ + 1) == '\n') {
                        str_.append('\n');
                        i_ += 2;
                        continue;
                    }
                }
                str_.append('\n');
                i_++;
                continue;
            }
            str_.append(ch_);
            i_++;
        }
        return str_.toString();
    }
}
