package code.expressionlanguage.exec.dbg;

public final class WatchPointBlockPairKeyString implements AbsKeyString<WatchPointBlockPair> {
    @Override
    public String keyString(WatchPointBlockPair _elt) {
        return _elt.getWp().keyStr();
    }
}
