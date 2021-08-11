package code.bean.nat.analyze.instr;



public final class NatExpPartDelimiters {
    private final int lastPrintIndex;
    public NatExpPartDelimiters(String _string) {
        int len_ = _string.length();
        lastPrintIndex = len_ - 1;
    }

    public int getLastPrintIndex() {
        return lastPrintIndex;
    }
}
