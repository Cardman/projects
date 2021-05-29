package code.expressionlanguage.analyze.instr;


import code.util.core.StringUtil;

public final class ExpPartDelimiters {
    private int firstPrintIndex;
    private int lastPrintIndex;
    public ExpPartDelimiters(String _string) {
        int len_ = _string.length();
        while (StringUtil.isWhitespace(_string.charAt(firstPrintIndex))) {
            firstPrintIndex++;
        }
        lastPrintIndex = len_ - 1;
        while (StringUtil.isWhitespace(_string.charAt(lastPrintIndex))) {
            lastPrintIndex--;
        }
    }

    public int getFirstPrintIndex() {
        return firstPrintIndex;
    }

    public int getLastPrintIndex() {
        return lastPrintIndex;
    }
}
