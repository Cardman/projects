package code.expressionlanguage.exec.dbg;

public final class OperNatKeyString implements AbsKeyString<AbsOperNatPointBlockPair> {
    @Override
    public String keyString(AbsOperNatPointBlockPair _elt) {
        return _elt.keyStr();
    }
}
