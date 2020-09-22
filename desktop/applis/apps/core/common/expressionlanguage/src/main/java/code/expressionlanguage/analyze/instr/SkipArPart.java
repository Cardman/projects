package code.expressionlanguage.analyze.instr;

public final class SkipArPart {
    private int index;
    private boolean skip;
    public void skip(int _i, String _string) {
        skip = false;
        index = _i;
        int len_ = _string.length();
        while (index < len_) {
            char nextChar_ = _string.charAt(index);
            if (Character.isWhitespace(nextChar_)) {
                index++;
                continue;
            }
            if (nextChar_ == ']') {
                skip = true;
            }
            break;
        }
    }

    public int getIndex() {
        return index;
    }

    public boolean isSkip() {
        return skip;
    }
}
