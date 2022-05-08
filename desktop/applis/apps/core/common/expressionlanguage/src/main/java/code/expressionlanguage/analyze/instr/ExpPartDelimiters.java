package code.expressionlanguage.analyze.instr;


import code.expressionlanguage.common.StringExpUtil;
import code.util.core.StringUtil;

public final class ExpPartDelimiters {
    private final int firstPrintIndex;
    private final int lastPrintIndex;
    public ExpPartDelimiters(String _string) {
        firstPrintIndex = StringExpUtil.getOffset(_string);
        lastPrintIndex = StringUtil.getLastPrintableCharIndex(_string);
    }

    public int getFirstPrintIndex() {
        return firstPrintIndex;
    }

    public int getLastPrintIndex() {
        return lastPrintIndex;
    }
}
