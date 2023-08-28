package code.expressionlanguage.exec.dbg;

public final class BreakPointBlockPairKeyIdString implements AbsKeyString<BreakPointBlockKey> {
    @Override
    public String keyString(BreakPointBlockKey _elt) {
        return _elt.keyStr();
    }
}
