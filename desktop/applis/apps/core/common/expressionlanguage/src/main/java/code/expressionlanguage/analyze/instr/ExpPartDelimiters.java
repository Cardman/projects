package code.expressionlanguage.analyze.instr;


public final class ExpPartDelimiters {
    private final int firstPrintIndex;
    private final int lastPrintIndex;
    public ExpPartDelimiters(String _string) {
        firstPrintIndex = ElResolver.firstPrint(_string);
        lastPrintIndex = ElResolver.lastPrintChar(_string);
    }

    public int getFirstPrintIndex() {
        return firstPrintIndex;
    }

    public int getLastPrintIndex() {
        return lastPrintIndex;
    }
}
