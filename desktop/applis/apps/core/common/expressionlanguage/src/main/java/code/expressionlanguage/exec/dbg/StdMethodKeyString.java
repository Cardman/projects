package code.expressionlanguage.exec.dbg;

public final class StdMethodKeyString implements AbsKeyString<StdMethodPointBlockPair> {
    @Override
    public String keyString(StdMethodPointBlockPair _elt) {
        return _elt.getSm().keyStr();
    }
}
