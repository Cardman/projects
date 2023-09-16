package code.expressionlanguage.exec.dbg;

public final class ParKeyString implements AbsKeyString<ParPointBlockPair> {
    @Override
    public String keyString(ParPointBlockPair _elt) {
        return _elt.keyStr();
    }
}
