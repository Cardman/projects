package code.expressionlanguage.analyze.instr;

import code.util.core.StringUtil;

public final class SkipArPart {
    private int index;
    private boolean skip;
    public void skip(int _i, String _string) {
        skip = false;
        index = _i;
        int len_ = _string.length();
        while (index < len_) {
            char nextChar_ = _string.charAt(index);
            if (!StringUtil.isWhitespace(nextChar_)) {
                if (nextChar_ == ']') {
                    skip = true;
                }
                break;
            }
            index++;
        }
    }

    public int getIndex() {
        return index;
    }

    public boolean isSkip() {
        return skip;
    }
}
