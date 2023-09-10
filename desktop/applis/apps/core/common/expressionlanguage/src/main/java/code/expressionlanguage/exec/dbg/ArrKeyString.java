package code.expressionlanguage.exec.dbg;

public final class ArrKeyString implements AbsKeyString<ArrPointBlockPair> {
    @Override
    public String keyString(ArrPointBlockPair _elt) {
        return _elt.keyStr();
    }
}
