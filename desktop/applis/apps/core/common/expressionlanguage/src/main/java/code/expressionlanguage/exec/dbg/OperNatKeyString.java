package code.expressionlanguage.exec.dbg;

public final class OperNatKeyString implements AbsKeyString<OperNatPointBlockPair> {
    @Override
    public String keyString(OperNatPointBlockPair _elt) {
        return _elt.keyStr();
    }
}
