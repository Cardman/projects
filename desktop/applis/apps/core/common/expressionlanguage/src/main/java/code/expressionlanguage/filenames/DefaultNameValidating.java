package code.expressionlanguage.filenames;

import code.expressionlanguage.common.StringDataUtil;
import code.util.StringList;
import code.util.core.StringUtil;

public final class DefaultNameValidating implements AbstractNameValidating {
    private final StringList forbidden;
    public DefaultNameValidating(StringList _forbidden) {
        forbidden = _forbidden;
    }

    @Override
    public boolean okPath(String _input, char... _seps) {
        for (String p: StringUtil.splitChars(_input, _seps)) {
            if (!ok(p)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean ok(String _input) {
        if (StringUtil.contains(forbidden,_input)) {
            return false;
        }
        boolean hasForbbidenChars_ = false;
        boolean containedPrint_ = false;
        boolean contained_ = false;
        boolean endedById_ = false;
        for (char c : _input.toCharArray()) {
            if (StringDataUtil.isLetterOrDigit(c) || c == '_' || c == '-') {
                contained_ = true;
                containedPrint_ = true;
                endedById_ = true;
            } else if (c == '.') {
                endedById_ = false;
                containedPrint_ = true;
            } else if (c == ' ' || c == 160) {
                if (!containedPrint_) {
                    return false;
                }
                endedById_ = false;
            } else {
                hasForbbidenChars_ = true;
                break;
            }
        }
        if (hasForbbidenChars_) {
            return false;
        }
        if (!contained_) {
            return false;
        }
        return endedById_;
    }
}