package code.expressionlanguage.exec.dbg;

public final class BreakPointBlockPairKeyString implements AbsKeyString<BreakPointBlockPair> {
    @Override
    public String keyString(BreakPointBlockPair _elt) {
        return _elt.keyStr();
    }
}
