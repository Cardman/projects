package code.expressionlanguage.exec.dbg;

public final class MethodKeyString implements AbsKeyString<MethodPointBlockPair> {
    @Override
    public String keyString(MethodPointBlockPair _elt) {
        return _elt.keyStr();
    }
}
