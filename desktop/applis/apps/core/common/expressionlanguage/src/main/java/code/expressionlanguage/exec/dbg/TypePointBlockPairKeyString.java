package code.expressionlanguage.exec.dbg;

public final class TypePointBlockPairKeyString implements AbsKeyString<TypePointBlockPair> {
    @Override
    public String keyString(TypePointBlockPair _elt) {
        return _elt.keyStr();
    }
}
