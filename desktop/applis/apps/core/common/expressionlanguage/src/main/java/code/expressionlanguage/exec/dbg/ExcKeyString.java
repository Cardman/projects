package code.expressionlanguage.exec.dbg;

public final class ExcKeyString implements AbsKeyString<ExcPointBlockPair> {
    @Override
    public String keyString(ExcPointBlockPair _elt) {
        return _elt.getEp().keyStr();
    }
}
